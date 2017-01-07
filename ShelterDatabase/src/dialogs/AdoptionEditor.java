package dialogs;

import database.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.sql.Date;
import java.util.Optional;

/**
 * Created by Bober on 2017-01-02.
 */
public class AdoptionEditor extends Dialog<Adoption>
{
    private DatabaseCommunicator communicator;

    private TextField client_pesel_input;
    private TextField client_name_input;
    private TextField client_surname_input;
    private TextField client_address_input;
    private TextField client_phone_number_input;

    private TextField chip_input;
    private TextField employee_pesel_input;
    private TextField date_input;

    public AdoptionEditor(DatabaseCommunicator communicator)
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
        client_pesel_input = new TextField();
        edit_grid.add(client_pesel_input, 1, 0);

        Label name_text = new Label("Name");
        edit_grid.add(name_text, 0, 1);
        client_name_input = new TextField();
        edit_grid.add(client_name_input, 1, 1);

        Label surname_text = new Label("Surname");
        edit_grid.add(surname_text, 0, 2);
        client_surname_input = new TextField();
        edit_grid.add(client_surname_input, 1, 2);

        Label address_text = new Label("Address");
        edit_grid.add(address_text, 0, 3);
        client_address_input = new TextField();
        edit_grid.add(client_address_input, 1, 3);

        Label phone_number_text = new Label("Phone number");
        edit_grid.add(phone_number_text, 0, 4);
        client_phone_number_input = new TextField();
        edit_grid.add(client_phone_number_input, 1, 4);


        Label chip_text = new Label("Animal chip");
        edit_grid.add(chip_text, 2, 0);
        chip_input = new TextField();
        edit_grid.add(chip_input, 3, 0);

        Label employee_pesel_text = new Label("Spending employee pesel");
        edit_grid.add(employee_pesel_text, 2, 1);
        employee_pesel_input = new TextField();
        edit_grid.add(employee_pesel_input, 3, 1);

        Label date_text = new Label("Date");
        edit_grid.add(date_text, 2, 2);
        date_input = new TextField();
        edit_grid.add(date_input, 3, 2);

        for (int i = 0; i < edit_grid.getChildren().size(); i++)
            if (edit_grid.getChildren().get(i) instanceof ComboBox)
                ((ComboBox) edit_grid.getChildren().get(i)).setMinWidth(250);
            else if (edit_grid.getChildren().get(i) instanceof Label)
                GridPane.setHalignment(edit_grid.getChildren().get(i), HPos.RIGHT);

        this.getDialogPane().setContent(edit_grid);
        this.setResultConverter(e -> {
            if (e == ok)
            {
                return new Adoption(communicator.getAnimal(chip_input.getText()),
                        new Client(client_pesel_input.getText(), client_name_input.getText(), client_surname_input.getText(),
                                client_address_input.getText(), client_phone_number_input.getText()),
                        communicator.getEmployee(employee_pesel_input.getText()), Date.valueOf(date_input.getText()));

            }
            return null;
        });
    }

    public void addAdoption()
    {
        Optional<Adoption> result = showAndWait();
        if (result.isPresent())
            communicator.addAdoption(result.get());
    }
}
