package desktop.desktop;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import desktop.desktop.helper.ExceptionHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class EntryPoint extends Application {

    private static ConfigurableApplicationContext applicationContext;

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(DesktopApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler::handleException);
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
        Platform.exit();
    }

    public class StageReadyEvent extends ApplicationEvent {

        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
        
    }
    
}
