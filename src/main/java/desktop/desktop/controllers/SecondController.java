package desktop.desktop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import desktop.desktop.helper.NavigationHelper;
import javafx.fxml.FXML;

@Controller
public class SecondController {
    
    @Autowired
    private NavigationHelper navigationHelper;

    @FXML
    private void goBack() {
        System.out.println("Going back!");
        navigationHelper.navigateBack();
    }

}
