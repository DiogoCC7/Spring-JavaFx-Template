package desktop.desktop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import desktop.desktop.EntryPoint.StageReadyEvent;
import desktop.desktop.helper.NavigationHelper;
import desktop.desktop.views.ViewConfiguration;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Autowired
    private NavigationHelper navigationHelper;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = ((Stage)event.getSource());
        navigationHelper.setStage(stage, ViewConfiguration.WELCOME_PAGE);
    }
    
}