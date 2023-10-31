package dao;

import database.MySQLHibernateConnUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.User;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Repository {
    private User user;
    private List<User> userList = new ArrayList<>();
    private Session session;
    private boolean isAction = false;
    private volatile static UserDAO userDAO;
    private UserDAO() {}
    public static UserDAO getInstance() {
        if (userDAO == null){
            synchronized (UserDAO.class) {
                if (userDAO == null)
                    userDAO = new UserDAO();
            }
        }
        return userDAO;
    }

    public User selectUser(String username, String password) {
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        Transaction transaction = session.beginTransaction();
        String hql = "FROM User WHERE name LIKE :username AND password LIKE :password";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user1 = (User) query.uniqueResult();
        transaction.commit();

        session.close();
        return user1;
    }

    @Override
    public Object selectById(int id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        userList = query.getResultList();
        session.getTransaction().commit();

        session.close();
        return userList;
    }
    @Override
    public boolean insert(Object o) {
        if (o instanceof User)
            user = (User) o;
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        session.close();
        isAction = false;
        userList = selectAll();
        if (userList.size() != 0) {
            userList.forEach(user1 -> {
                if (user1.getId() == user1.getId()){
                    isAction = true;
                }
            });
        }
        return isAction;
    }
    @Override
    public boolean delete(int id) {
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM User WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int affectedRows = query.executeUpdate();
        transaction.commit();

        session.close();
        return affectedRows != 0;
    }

    @Override
    public boolean delete(Object o) {
        if (o instanceof User)
            user = (User) o;
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();

        session.close();
        isAction = true;
        selectAll().forEach(user1 -> {
            if (user1.getId() == user.getId()){
                isAction = false;
            }
        });
        return isAction;
    }

    @Override
    public boolean update(Object o) {
        if (o instanceof User)
            user = (User) o;
        if (session == null || !session.isOpen())
            session = MySQLHibernateConnUtils.getFactory().openSession();

        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

        session.close();
        isAction = false;
        selectAll().forEach(user1 -> {
            if (user1.getId() == user.getId()){
                if (user1.getName().equals(user.getName()) && user1.getEmail().equals(user.getEmail()) && user1.getPassword().equals(user.getPassword())) {
                    isAction = true;
                }
            }
        });
        return isAction;

//        ----- update with hql -----
//        String hql = "UPDATE Phone SET name = :name, price = :price, color = :color, country = :country, quantity = :quantity, manufacture = :manufacture WHERE ID LIKE :ID";
//        Query query = session.createQuery(hql);
//        query.setParameter("name", phone.getName());
//        query.setParameter("price", phone.getPrice());
//        query.setParameter("color", phone.getColor());
//        query.setParameter("country", phone.getCountry());
//        query.setParameter("quantity", phone.getQuantity());
//        query.setParameter("manufacture", phone.getManufacture());
//        query.setParameter("id", phone.getID());
//        int affectedRows = query.executeUpdate();
//        return affectedRows != 0;
    }
}
