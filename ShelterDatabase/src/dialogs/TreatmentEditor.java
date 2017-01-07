package dialogs;

import database.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.sql.Date;
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

        this.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);
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

        this.getDialogPane().setContent(edit_grid);
        this.setResultConverter(e -> {
            if (e == ok)
            {
                return new Treatment(communicator.getEmployee(employee_pesel_input.getText()),
                        communicator.getAnimal(chip_input.getText()), Date.valueOf(date_input.getText()),
                        Treatment.TreatmentType.valueOf(type_input.getSelectionModel().getSelectedItem()),
                        details_input.getText(), comments_input.getText());
            }
            return null;
        });
    }

    public void addTreatment()
    {
        Optional<Treatment> result = showAndWait();
        if (result.isPresent())
            communicator.addTreatment(result.get());
    }
}
