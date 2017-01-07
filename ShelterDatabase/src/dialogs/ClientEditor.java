package dialogs;

import database.Animal;
import database.Client;
import database.DatabaseCommunicator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * Created by Bober on 2017-01-02.
 */
public class ClientEditor extends Dialog<Client>
{
    private DatabaseCommunicator communicator;

    private TextField pesel_input;
    private TextField name_input;
    private TextField surname_input;
    private TextField address_input;
    private TextField phone_number_input;

    private String edited_client_pesel;

    public ClientEditor(DatabaseCommunicator communicator)
    {
        this.communicator = communicator;

        this.setTitle("Edit data");
        ButtonType ok = ButtonType.APPLY;

        this.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);
        GridPane edit_grid = new GridPane();
        edit_grid.setHgap(10);
        edit_grid.setVgap(10);
        edit_grid.setPadding(new Insets(20, 20, 20, 20));
        edit_grid.getColumnConstraints().addAll(new ColumnConstraints(80), new ColumnConstraints(250));

        Label pesel_text = new Label("PESEL");
        edit_grid.add(pesel_text, 0, 0);
        pesel_input = new TextField();
        edit_grid.add(pesel_input, 1, 0);

        Label name_text = new Label("Name");
        edit_grid.add(name_text, 0, 1);
        name_input = new TextField();
        edit_grid.add(name_input, 1, 1);

        Label surname_text = new Label("Surname");
        edit_grid.add(surname_text, 0, 2);
        surname_input = new TextField();
        edit_grid.add(surname_input, 1, 2);

        Label address_text = new Label("Address");
        edit_grid.add(address_text, 0, 3);
        address_input = new TextField();
        edit_grid.add(address_input, 1, 3);

        Label phone_number_text = new Label("Phone number");
        edit_grid.add(phone_number_text, 0, 4);
        phone_number_input = new TextField();
        edit_grid.add(phone_number_input, 1, 4);

        for (int i = 0; i < edit_grid.getChildren().size(); i++)
            if (edit_grid.getChildren().get(i) instanceof ComboBox)
                ((ComboBox) edit_grid.getChildren().get(i)).setMinWidth(250);
            else if (edit_grid.getChildren().get(i) instanceof Label)
                GridPane.setHalignment(edit_grid.getChildren().get(i), HPos.RIGHT);

        this.getDialogPane().setContent(edit_grid);
        this.setResultConverter(e -> {
            if (e == ok)
            {
                return new Client(pesel_input.getText(), name_input.getText(), surname_input.getText(), address_input.getText(),
                        phone_number_input.getText());
            }
            return null;
        });
    }

    public void editClient(Client client)
    {

        pesel_input.setText(client.PESEL);
        name_input.setText(client.name);
        surname_input.setText(client.surname);
        address_input.setText(client.address);
        phone_number_input.setText(client.phone_number);
        Optional<Client> result = showAndWait();
        if (result.isPresent())
            communicator.updateClient(edited_client_pesel, result.get());
    }
}
