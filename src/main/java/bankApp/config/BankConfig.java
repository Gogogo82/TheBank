package bankApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// to autowire not only interfaces, but classes
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class BankConfig {

}