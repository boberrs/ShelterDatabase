package dialogs;

import database.Admission;
import database.Animal;
import database.Client;
import database.DatabaseCommunicator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Created by Bober on 2017-01-02.
 */
public class AdmissionEditor extends Dialog<Admission>
{
    private DatabaseCommunicator communicator;

    private TextField chip_input;
    private TextField type_input;
    private TextField race_input;
    private TextField coat_input;
    private ComboBox<String> sex_input;
    private TextField age_input;
    private TextField cage_input;
    private TextField comments_input;

    private TextField client_pesel_input;
    private TextField client_name_input;
    private TextField client_surname_input;
    private TextField client_address_input;
    private TextField client_phone_number_input;

    private TextField date_input;
    private TextField where_found_input;
    private TextField admission_comments_input;

    public AdmissionEditor(DatabaseCommunicator communicator)
    {
        this.communicator = communicator;

        this.setTitle("Edit data");
        ButtonType ok = ButtonType.APPLY;

        this.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);

        VBox body = new VBox();
            body.setAlignment(Pos.TOP_CENTER);
            GridPane edit_grid = new GridPane();
                edit_grid.setHgap(10);
                edit_grid.setVgap(10);
                edit_grid.setPadding(new Insets(20, 20, 20, 20));
                edit_grid.getColumnConstraints().addAll(new ColumnConstraints(100), new ColumnConstraints(250),
                        new ColumnConstraints(100), new ColumnConstraints(250), new ColumnConstraints(100),
                        new ColumnConstraints(250));

                Label chip_text = new Label("Chip");
                edit_grid.add(chip_text, 0, 0);
                chip_input = new TextField();
                edit_grid.add(chip_input, 1, 0);

                Label type_text = new Label("Type");
                edit_grid.add(type_text, 0, 1);
                type_input = new TextField();
                edit_grid.add(type_input, 1, 1);

                Label race_text = new Label("Race");
                edit_grid.add(race_text, 0, 2);
                race_input = new TextField();
                edit_grid.add(race_input, 1, 2);

                Label coat_text = new Label("Coat");
                edit_grid.add(coat_text, 0, 3);
                coat_input = new TextField();
                edit_grid.add(coat_input, 1, 3);

                Label sex_text = new Label("Sex");
                edit_grid.add(sex_text, 0, 4);
                sex_input = new ComboBox<>();
                ArrayList<String> sex_input_values = new ArrayList<>();
                for (int i = 0; i < Animal.Sex.values().length; i++)
                    sex_input_values.add(Animal.Sex.values()[i].toString());
                sex_input.getItems().addAll(sex_input_values);
                edit_grid.add(sex_input, 1, 4);

                Label age_text = new Label("Age");
                edit_grid.add(age_text, 0, 5);
                age_input = new TextField();
                edit_grid.add(age_input, 1, 5);

                Label cage_text = new Label("Cage");
                edit_grid.add(cage_text, 0, 6);
                cage_input = new TextField();
                edit_grid.add(cage_input, 1, 6);

                Label comments_text = new Label("Comments");
                edit_grid.add(comments_text, 0, 7);
                comments_input = new TextField();
                edit_grid.add(comments_input, 1, 7);


                Label pesel_text = new Label("PESEL");
                edit_grid.add(pesel_text, 2, 0);
                client_pesel_input = new TextField();
                edit_grid.add(client_pesel_input, 3, 0);

                Label name_text = new Label("Name");
                edit_grid.add(name_text, 2, 1);
                client_name_input = new TextField();
                edit_grid.add(client_name_input, 3, 1);

                Label surname_text = new Label("Surname");
                edit_grid.add(surname_text, 2, 2);
                client_surname_input = new TextField();
                edit_grid.add(client_surname_input, 3, 2);

                Label address_text = new Label("Address");
                edit_grid.add(address_text, 2, 3);
                client_address_input = new TextField();
                edit_grid.add(client_address_input, 3, 3);

                Label phone_number_text = new Label("Phone number");
                edit_grid.add(phone_number_text, 2, 4);
                client_phone_number_input = new TextField();
                edit_grid.add(client_phone_number_input, 3, 4);


                Label date_text = new Label("Date");
                edit_grid.add(date_text, 4, 0);
                date_input = new TextField();
                edit_grid.add(date_input, 5, 0);

                Label where_found_text = new Label("Where found");
                edit_grid.add(where_found_text, 4, 1);
                where_found_input = new TextField();
                edit_grid.add(where_found_input, 5, 1);

                Label admission_comments_text = new Label("Comments");
                edit_grid.add(admission_comments_text, 4, 2);
                admission_comments_input = new TextField();
                edit_grid.add(admission_comments_input, 5, 2);

                for (int i = 0; i < edit_grid.getChildren().size(); i++)
                    if (edit_grid.getChildren().get(i) instanceof ComboBox)
                        ((ComboBox) edit_grid.getChildren().get(i)).setMinWidth(250);
                    else if (edit_grid.getChildren().get(i) instanceof Label)
                        GridPane.setHalignment(edit_grid.getChildren().get(i), HPos.RIGHT);

            body.getChildren().add(edit_grid);

            TextArea errors = new TextArea();
                errors.setMaxWidth(800);
                errors.setMaxHeight(100);
                errors.setEditable(false);
            body.getChildren().add(errors);

            Button my_ok = new Button("Apply");
                my_ok.setOnAction(event->{
                    errors.setText("");

                    if (chip_input.getText().equals(""))
                        errors.setText(errors.getText().concat("Chip cannot be empty\n"));
                    else if (chip_input.getText().length() != 15)
                        errors.setText(errors.getText().concat("Chip must have 15 characters\n"));

                    try {
                        if (!age_input.getText().equals(""))
                            Integer.valueOf(age_input.getText());
                    } catch (NumberFormatException e){
                        errors.setText(errors.getText().concat("Age must have numeric value\n"));
                    }

                    try {
                        if (!cage_input.getText().equals(""))
                            Integer.valueOf(cage_input.getText());
                    } catch (NumberFormatException e){
                        errors.setText(errors.getText().concat("Cage must have numeric value\n"));
                    }

                    if (client_name_input.getText().equals(""))
                        errors.setText(errors.getText().concat("Client name cannot be empty\n"));

                    if (!client_pesel_input.getText().equals("") && client_pesel_input.getText().length() != 11)
                        errors.setText(errors.getText().concat("PESEL must have 11 characters\n"));

                    if (date_input.getText().equals(""))
                        errors.setText(errors.getText().concat("Date cannot be empty\n"));
                });
            buttons.getChildren().add(my_ok);

            Button cancel = new Button("Cancel");
                cancel.setOnAction(event->{
                    this.close();
                });
            buttons.getChildren().add(cancel);

        this.getDialogPane().setContent(body);

        this.setResultConverter(e -> {
            if (e == ok)
            {
                return new Admission(
                        new Animal(chip_input.getText(), type_input.getText(), race_input.getText(), coat_input.getText(),
                                Animal.Sex.valueOf(sex_input.getSelectionModel().getSelectedItem()),
                                Integer.valueOf(age_input.getText()), Integer.valueOf(cage_input.getText()),
                                comments_input.getText()),
                        new Client(client_pesel_input.getText(), client_name_input.getText(), client_surname_input.getText(),
                                client_address_input.getText(), client_phone_number_input.getText()),
                        Date.valueOf(date_input.getText()), where_found_input.getText(), admission_comments_input.getText());
            }
            return null;
        });
    }

    public void addAdmission()
    {
        date_input.setText(LocalDate.now().toString());
        Optional<Admission> result = showAndWait();
        if (result.isPresent())
            communicator.addAdmission(result.get());
    }
}
