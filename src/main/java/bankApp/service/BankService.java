package bankApp.service;

import java.util.List;

public interface BankService<T> {

    List<T> getAll();

    T getOne(int id);

    void saveOrUpdate(T t);

    void delete(int id);
}
