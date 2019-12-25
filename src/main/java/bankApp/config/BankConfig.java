package bankApp.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("bankApp")
@PropertySource("classpath:database.properties")
// to autowire not only interfaces, but classes
//@EnableTransactionManagement(proxyTargetClass = true)
public class BankConfig {

    // data from @PropertySource
    private final Environment environment;

    @Autowired
    public BankConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "TheBankDataSource")
    public ComboPooledDataSource getDataSource() {
        ComboPooledDataSource result = new ComboPooledDataSource();
        try {
            result.setDriverClass(environment.getProperty("driverClass"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        result.setJdbcUrl(environment.getProperty("jdbcUrl"));
        result.setUser(environment.getProperty("user"));
        result.setPassword(environment.getProperty("password"));
        result.setInitialPoolSize(5);
        result.setMinPoolSize(5);
        result.setMaxPoolSize(20);
        result.setMaxIdleTime(300000);

        return result;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set hibernate properties
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(props);

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager getTransactionManager(SessionFactory getSessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory);

        return transactionManager;
    }

    @Bean
    public InternalResourceViewResolver getResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

//    @Bean
////    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public CharacterEncodingFilter getCharacterEncodingFilter() {
//
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//
//        return filter;
//    }
}