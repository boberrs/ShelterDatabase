package dialogs;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by Bober on 2016-12-31.
 */
public class Logger extends Dialog<String>
{
    public String logged_as;

    public Logger()
    {
        logged_as = "";

        this.setTitle("Log in");
        this.setHeaderText("Log in");
        ButtonType dialog_ok_button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(dialog_ok_button, ButtonType.CANCEL);
        GridPane login_grid = new GridPane();
            login_grid.setHgap(10);
            login_grid.setVgap(10);
            login_grid.setPadding(new Insets(20, 150, 10, 10));
            Text login_text = new Text("Login");
            login_grid.add(login_text, 0, 0);
            TextField login_input = new TextField();
                login_input.setText("");
            login_grid.add(login_input, 1, 0);
            Text password_text = new Text("Password");
            login_grid.add(password_text, 0, 1);
            PasswordField password_input = new PasswordField();
                password_input.setText("");
            login_grid.add(password_input, 1, 1);
        this.getDialogPane().setContent(login_grid);
        this.setResultConverter(event -> {
            if (event == dialog_ok_button) {
                logged_as = login_input.getText();
                return login_input.getText();
            }
            return null;
        });
    }
}
