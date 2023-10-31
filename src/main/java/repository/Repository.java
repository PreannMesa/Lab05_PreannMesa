package repository;

import java.util.List;

public interface Repository<T> {
    public boolean insert(T o);
    public T selectUser(String username, String password);
    public T selectById(int id);
    public List<T> selectAll();
    public boolean delete(int id);
    public boolean delete(T o);
    public boolean update(T o);
}
