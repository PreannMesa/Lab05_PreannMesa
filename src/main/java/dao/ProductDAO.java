package dao;

import database.MySQLHibernateConnUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Product;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository {
    private Product product;
    private List<Product> productList = new ArrayList<>();
    private Session session;
    private boolean isAction = false;
    private volatile static ProductDAO productDAO;
    private ProductDAO() {}
    public static ProductDAO getInstance() {
        if (productDAO == null){
            synchronized (ProductDAO.class) {
                if (productDAO == null)
                    productDAO = new ProductDAO();
            }
        }
        return productDAO;
    }

    @Override
    public Product selectById(int id) {
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        Transaction transaction = session.beginTransaction();
        String hql = "FROM Product WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        Product product1 = (Product) query.uniqueResult();
        transaction.commit();

        session.close();
        return product1;
    }
    @Override
    public List<Product> selectAll() {
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        String hql = "FROM Product ";
        Query query = session.createQuery(hql);
        productList = query.getResultList();
        session.getTransaction().commit();

        session.close();
        return productList;
    }
    @Override
    public boolean insert(Object o) {
        if (o instanceof Product)
            product = (Product) o;
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();

        session.close();
        isAction = false;
//        List<Product> studentList = selectAll();
        selectAll().forEach(student1 -> {
            if (student1.getId() == product.getId()){
                isAction = true;
            }
        });
        return isAction;
    }

    @Override
    public Object selectUser(String username, String password) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM Product WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int affectedRows = query.executeUpdate();
        transaction.commit();

        session.close();
        return affectedRows != 0;
    }

    @Override
    public boolean delete(Object o) {
        if (o instanceof Product)
            product = (Product) o;
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();

        session.close();
        isAction = true;
        selectAll().forEach(student1 -> {
            if (student1.getId() == product.getId()){
                isAction = false;
            }
        });
        return isAction;
    }

    @Override
    public boolean update(Object o) {
        if (o instanceof Product)
            product = (Product) o;
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();

        session.close();
        isAction = false;
        selectAll().forEach(product1 -> {
            if (product1.getId() == product.getId()){
                if (product1.getName().equals(product.getName()) && product1.getPrice().equals(product.getPrice())) {
                    isAction = true;
                }
            }
        });
        return isAction;
    }
}
