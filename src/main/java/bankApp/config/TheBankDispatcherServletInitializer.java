package bankApp.config;

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

    // ru encoding
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return new Filter[]{filter};
    }
}