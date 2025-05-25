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
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Bober on 2017-01-02.
 */
public class TreatmentEditor extends Dialog<Treatment>
{
    private DatabaseCommunicator communicator;

    private TextField chip_input;
    private TextField employee_pesel_input;
    private TextField date_input;
    private ComboBox<String> type_input;
    private TextField details_input;
    private TextField comments_input;

    public TreatmentEditor(DatabaseCommunicator communicator)
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

                Label chip_text = new Label("Animal chip");
                edit_grid.add(chip_text, 0, 0);
                chip_input = new TextField();
                edit_grid.add(chip_input, 1, 0);

                Label employee_pesel_text = new Label("Doctor");
                edit_grid.add(employee_pesel_text, 0, 1);
                employee_pesel_input = new TextField();
                edit_grid.add(employee_pesel_input, 1, 1);

                Label date_text = new Label("Date");
                edit_grid.add(date_text, 0, 2);
                date_input = new TextField();
                edit_grid.add(date_input, 1, 2);

                Label type_text = new Label("Type");
                edit_grid.add(type_text, 0, 3);
                type_input = new ComboBox<>();
                    ArrayList<String> type_input_values = new ArrayList<>();
                    for (int i = 0; i < Treatment.TreatmentType.values().length; i++)
                        type_input_values.add(Treatment.TreatmentType.values()[i].toString());
                    type_input.getItems().addAll(type_input_values);
                edit_grid.add(type_input, 1, 3);

                Label details_text = new Label("Detais");
                edit_grid.add(details_text, 0, 4);
                details_input = new TextField();
                edit_grid.add(details_input, 1, 4);

                Label comments_text = new Label("Comments");
                edit_grid.add(comments_text, 0, 5);
                comments_input = new TextField();
                edit_grid.add(comments_input, 1, 5);

                for (int i = 0; i < edit_grid.getChildren().size(); i++)
                    if (edit_grid.getChildren().get(i) instanceof ComboBox)
                        ((ComboBox) edit_grid.getChildren().get(i)).setMinWidth(250);
                    else if (edit_grid.getChildren().get(i) instanceof Label)
                        GridPane.setHalignment(edit_grid.getChildren().get(i), HPos.RIGHT);
            body.getChildren().add(edit_grid);

            TextArea errors = new TextArea();
                errors.setMaxWidth(500);
                errors.setMaxHeight(100);
                errors.setEditable(false);
            body.getChildren().add(errors);

            HBox buttons = new HBox();
                buttons.getStyleClass().add("dialog_buttons");
                Button my_ok = new Button("Apply");
                    my_ok.setOnAction(event->{
                        errors.setText("");

                        if (chip_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Chip cannot be empty\n"));
                        else if (chip_input.getText().length() != 15)
                            errors.setText(errors.getText().concat("Chip must have 15 characters\n"));

                        if (employee_pesel_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Employee PESEL cannot be empty\n"));
                        else if (employee_pesel_input.getText().length() != 11)
                            errors.setText(errors.getText().concat("PESEL must have 11 characters\n"));

                        if (date_input.getText().equals(""))
                            errors.setText(errors.getText().concat("Date cannot be empty\n"));

                        if (errors.getText().equals(""))
                        {
                            this.setResult(new Treatment(communicator.getEmployee(employee_pesel_input.getText()),
                                    communicator.getAnimal(chip_input.getText()), Date.valueOf(date_input.getText()),
                                    Treatment.TreatmentType.valueOf(type_input.getSelectionModel().getSelectedItem()),
                                    details_input.getText(), comments_input.getText()));
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

        /*this.setResultConverter(e -> {
            if (e == ok)
            {
                return new Treatment(communicator.getEmployee(employee_pesel_input.getText()),
                        communicator.getAnimal(chip_input.getText()), Date.valueOf(date_input.getText()),
                        Treatment.TreatmentType.valueOf(type_input.getSelectionModel().getSelectedItem()),
                        details_input.getText(), comments_input.getText());
            }
            return null;
        });*/

        this.getDialogPane().getStylesheets().add("style.css");
    }

    public void addTreatment()
    {
        date_input.setText(LocalDate.now().toString());
        type_input.getSelectionModel().select(0);
        Optional<Treatment> result = showAndWait();
        if (result.isPresent())
            communicator.addTreatment(result.get());
    }
}
