package dialogs;

import database.Animal;
import database.DatabaseCommunicator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bober on 2017-01-02.
 */
public class AnimalEditor extends Dialog<Animal>
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

    public AnimalEditor(DatabaseCommunicator communicator)
    {
        this.communicator = communicator;

        this.setTitle("Edit data");
        ButtonType ok = ButtonType.APPLY;

        this.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);
        VBox body = new VBox();
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

            body.getChildren().add(edit_grid);

            Button my_ok = new Button("Apply");
                my_ok.setOnAction(event->{

                });
            body.getChildren().add(my_ok);
        this.getDialogPane().setContent(body);

        this.setResultConverter(e -> {
            if (e == ok)
            {
                return new Animal(chip_input.getText(), type_input.getText(), race_input.getText(), coat_input.getText(),
                        Animal.Sex.valueOf(sex_input.getSelectionModel().getSelectedItem()), Integer.valueOf(age_input.getText()),
                        Integer.valueOf(cage_input.getText()), comments_input.getText());
            }
            return null;
        });
    }

    public void editAnimal(Animal animal)
    {
        edited_animal_chip = animal.chip;
        chip_input.setText(animal.chip);
        type_input.setText(animal.type);
        race_input.setText(animal.race);
        coat_input.setText(animal.coat);
        sex_input.getSelectionModel().select(animal.sex.toString());
        age_input.setText(Integer.toString(animal.age));
        cage_input.setText(Integer.toString(animal.cage));
        comments_input.setText(animal.comments);
        Optional<Animal> result = showAndWait();
        if (result.isPresent())
            communicator.updateAnimal(edited_animal_chip, result.get());
    }
}
