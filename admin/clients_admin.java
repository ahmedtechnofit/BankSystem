package admin;

import BaseController.basecontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class clients_admin  extends basecontroller {

    @FXML
    public void u_h(ActionEvent event) {
        navigateTo( "home_admin",  event);
    }
}
