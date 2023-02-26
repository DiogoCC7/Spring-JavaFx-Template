package desktop.desktop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import desktop.desktop.helper.NavigationHelper;
import desktop.desktop.views.ViewConfiguration;
import javafx.fxml.FXML;

@Controller
public class WelcomeController {
    
    @Autowired
    private NavigationHelper navigationHelper;

    @FXML
    private void sayHello() {
        System.out.println("Hello World!");
        navigationHelper.navigateTo(ViewConfiguration.SECOND_PAGE);
    }

}
