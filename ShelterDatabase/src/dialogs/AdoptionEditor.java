package dialogs;

import database.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
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
        this.getDialogPane().getButtonTypes().addAll(ok);
        Node ok_button = this.getDialogPane().lookupButton(ok);
        ok_button.setVisible(false);

        VBox body = new VBox();
            body.setAlignment(Pos.TOP_CENTER);
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

            body.getChildren().add(edit_grid);

            TextArea errors = new TextArea();
                errors.setMaxWidth(700);
                errors.setMaxHeight(120);
                errors.setEditable(false);
            body.getChildren().add(errors);

            HBox buttons = new HBox();
                buttons.getStyleClass().add("dialog_buttons");
                Button my_ok = new Button("Apply");
                    my_ok.setOnAction(event->{
                        errors.setText("");

                        if (client_pesel_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Employee PESEL cannot be empty\n"));
                        else if (client_pesel_input.getText().length() != 11)
                            errors.setText(errors.getText().concat("PESEL must have 11 characters\n"));

                        if (client_name_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Client name cannot be empty\n"));

                        if (client_surname_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Client surname cannot be empty\n"));

                        if (client_address_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Client address cannot be empty\n"));

                        if (client_phone_number_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Client phone number cannot be empty\n"));

                        if (chip_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Chip cannot be empty\n"));
                        else if (chip_input.getText().length() != 15)
                            errors.setText(errors.getText().concat("Chip must have 15 characters\n"));

                        if (date_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Date cannot be empty\n"));

                        if (errors.getText().equals(""))
                        {
                            this.setResult(new Adoption(communicator.getAnimal(chip_input.getText()),
                                    new Client(client_pesel_input.getText(), client_name_input.getText(), client_surname_input.getText(),
                                            client_address_input.getText(), client_phone_number_input.getText()),
                                    communicator.getEmployee(employee_pesel_input.getText()), Date.valueOf(date_input.getText())));
                            this.close();
                        }
                    });
                buttons.getChildren().add(my_ok);

                Button cancel = new Button("Cancel");
                    cancel.setOnAction(event->{
                        this.close();
                    });
                buttons.getChildren().add(cancel);
            body.getChildren().add(buttons);
        this.getDialogPane().setContent(body);

        this.setResultConverter(e -> {
            /*if (e == ok)
            {
                return new Adoption(communicator.getAnimal(chip_input.getText()),
                        new Client(client_pesel_input.getText(), client_name_input.getText(), client_surname_input.getText(),
                                client_address_input.getText(), client_phone_number_input.getText()),
                        communicator.getEmployee(employee_pesel_input.getText()), Date.valueOf(date_input.getText()));

            }*/
            return null;
        });

        this.getDialogPane().getStylesheets().add("style.css");
    }

    public void addAdoption()
    {
        date_input.setText(LocalDate.now().toString());
        Optional<Adoption> result = showAndWait();
        if (result.isPresent())
            communicator.addAdoption(result.get());
    }
}
