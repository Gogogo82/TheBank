package bankApp.dao;

import bankApp.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User user);
}
