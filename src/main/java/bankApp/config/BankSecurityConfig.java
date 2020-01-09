package bankApp.config;

import bankApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// enables spring web security, add authorization form
@Configuration
@EnableWebSecurity
public class BankSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public BankSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // restrict all HttpServletRequests
        http.authorizeRequests()
                // all pages available for all roles
                .anyRequest().authenticated()
                .and()
                // customize login form
                .formLogin()
                // form to show
                .loginPage("/showMyLoginPage")
                // add @PostMapping("/authenticateTheUser") controller automatically, in success return "/"
                .loginProcessingUrl("/authenticateTheUser")
                // not logged in users can see this page
                .permitAll()
                .and()
                // add @PostMapping("/logout") controller automatically, return "/"
                .logout()
                .permitAll()
                .and()
                // custom 403 error
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider result = new DaoAuthenticationProvider();
        result.setUserDetailsService(userService);
        result.setPasswordEncoder(passwordEncoder());
        return result;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
