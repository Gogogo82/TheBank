package bankApp.config;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

public class TheBankDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // config classes for non-WEB
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // config classes for WEB
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {BankConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {

        // ru encoding
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{characterEncodingFilter};
    }
}