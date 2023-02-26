package desktop.desktop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import desktop.desktop.helper.NavigationHelper;

@Configuration
public class DependencyInjection {
    
    @Autowired
    private ApplicationContext applicationContext;

    @Bean()
    public NavigationHelper navigationHelper() {
        return new NavigationHelper(applicationContext);
    }

}
