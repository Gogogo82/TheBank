package bankApp.DAO;

import java.util.List;

public interface BankDao<T> {

    List<T> getAll();

    T getOne(int id);

    void saveOrUpdate(T entity);

    void delete(int id);
}
