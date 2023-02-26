package desktop.desktop.helper;

import org.springframework.context.ApplicationContext;

import desktop.desktop.EntryPoint;
import desktop.desktop.views.ViewConfiguration;

public class ExceptionHandler {
    
    private static final ApplicationContext APPLICATION_CONTEXT = EntryPoint.getApplicationContext();

    public static void handleException(Thread thread, Throwable throwable) {
        
        // Catch Exceptions
        NavigationHelper navigationHelper = APPLICATION_CONTEXT.getBean(NavigationHelper.class);
        navigationHelper.navigateTo(ViewConfiguration.WELCOME_PAGE);

        System.out.println("ExceptionCatcher -> " + throwable.getMessage());

    }

}
