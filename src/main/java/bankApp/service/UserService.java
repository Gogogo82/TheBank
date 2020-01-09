package bankApp.service;

import bankApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {

    User findByUserName(String userName);

    void save(User user);

    UserDetails loadUserByUsername(String userName);
}
