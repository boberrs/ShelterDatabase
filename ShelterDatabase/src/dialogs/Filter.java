package dialogs;

import database.Animal;
import database.DatabaseCommunicator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bober on 2017-01-02.
 */
public class Filter extends Dialog<List<Animal>>
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

    private String edited_animal_chip;

    public Filter(DatabaseCommunicator communicator)
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
        ArrayList<String>sex_input_values = new ArrayList<>();
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

        for (int i = 0; i < edit_grid.getChildren().size(); i++)
            if (edit_grid.getChildren().get(i) instanceof ComboBox)
                ((ComboBox) edit_grid.getChildren().get(i)).setMinWidth(250);
            else if (edit_grid.getChildren().get(i) instanceof Label)
                GridPane.setHalignment(edit_grid.getChildren().get(i), HPos.RIGHT);

        this.getDialogPane().setContent(edit_grid);
        this.setResultConverter(e -> {
            if (e == ok)
            {
                return communicator.filter(chip_input.getText().equals("") ? null : chip_input.getText(),
                        type_input.getText().equals("") ? null : type_input.getText(),
                        race_input.getText().equals("") ? null : race_input.getText(),
                        coat_input.getText().equals("") ? null : coat_text.getText(),
                        sex_input.getSelectionModel().getSelectedItem() == null ? null : Animal.Sex.valueOf(sex_input.getSelectionModel().getSelectedItem()),
                        age_input.getText().equals("") ? null : Integer.valueOf(age_input.getText()),
                        cage_input.getText().equals("") ? null : Integer.valueOf(cage_input.getText()));
            }
            return null;
        });
    }

    public Optional<List<Animal>> filter()
    {
        return showAndWait();
    }
}
