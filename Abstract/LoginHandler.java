package Abstract;

import javafx.event.ActionEvent;

public interface LoginHandler {

    boolean authenticate(String username, String password);

    void openHome(ActionEvent event);
}