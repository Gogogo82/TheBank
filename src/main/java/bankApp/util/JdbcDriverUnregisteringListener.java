package bankApp.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

@WebListener
public class JdbcDriverUnregisteringListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Logger logger = LogManager.getLogger(JdbcDriverUnregisteringListener.class);
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();

            try {
                DriverManager.deregisterDriver(driver);
                logger.info("Deregistering JDBC driver: {}", driver);
            } catch (Exception e) {
                logger.warn("Error deregistering JDBC driver: {}. Root cause: ", driver, e);
            }
        }
    }
}

