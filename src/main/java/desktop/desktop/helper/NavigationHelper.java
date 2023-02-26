package desktop.desktop.helper;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

import org.springframework.context.ApplicationContext;

import desktop.desktop.EntryPoint;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class NavigationHelper {

    private final ApplicationContext applicationContext;
    private final double SCREEN_OFFSET = 150;
    private final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth() - (SCREEN_OFFSET * (double)0.5);
    private final double SCREEN_HIGHT = Screen.getPrimary().getBounds().getHeight() - SCREEN_OFFSET;

    private Deque<Parent> navigationStack;
    private Stage currentStage;

    public NavigationHelper(ApplicationContext applicationContext) {
        navigationStack = new LinkedList<>();
        this.applicationContext = applicationContext;
    }

    private Parent loadFXML(String fxml) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(EntryPoint.class.getResource("views/" + fxml + ".fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);

        return fxmlLoader.load();

    }

    public void setStage(Stage stage, String view) {
       
        Scene currentScene = null;

        try {
            currentScene = new Scene(loadFXML(view), SCREEN_WIDTH, SCREEN_HIGHT);
        } catch (Exception e) {
            throw new RuntimeException("Error trying to open the application");
        }

        stage.setScene(currentScene);
        stage.getScene().rootProperty().addListener(sceneListener);
        stage.show();

        this.currentStage = stage;
    }

    public Stage getCurrentStage() { return this.currentStage; }

    private ChangeListener<Parent> sceneListener = (ob, oldScene, newScene) -> {
        if (newScene != null)
            currentStage.getScene().setRoot(newScene);
    };

    public void navigateTo(String fxml) {

        navigationStack.add(currentStage.getScene().getRoot());

        try {
            sceneListener.changed(null, null, loadFXML(fxml));
        } catch (IOException e) {
           throw new RuntimeException("Error to change page");
        }
        
    }

    public void navigateBack() {

        if (navigationStack.isEmpty()) return;

        sceneListener.changed(null, null, navigationStack.pollLast());

    }

}