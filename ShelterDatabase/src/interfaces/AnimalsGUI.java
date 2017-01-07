package interfaces;

import database.*;
import dialogs.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

/**
 * Created by Bober on 2016-12-31.
 */
public class AnimalsGUI extends Application
{
    private final double window_width = 1100;
    private final double window_height = 850;

    private DatabaseCommunicator database;
    public Logger logger;

    private VBox details;
    private VBox animals_list;
    private GridPane selected;

    private AnimalEditor editor;
    private AdmissionEditor admission_editor;
    private TreatmentEditor treatment_editor;
    private AdoptionEditor adoption_editor;
    private Filter filter;

    public static void main(String[] args) {
        launch(args);
    }

    public AnimalsGUI()
    {
        logger = new Logger();
        database = new DatabaseCommunicator();
        editor = new AnimalEditor(database);
        admission_editor = new AdmissionEditor(database);
        treatment_editor = new TreatmentEditor(database);
        adoption_editor = new AdoptionEditor(database);
        filter = new Filter(database);
    }

    private void setRightPane(List<AnimalEvent> history, Animal animal)
    {
        details.getChildren().clear();

        VBox details_info = new VBox();
            details_info.getStyleClass().add("details");
            details_info.getChildren().addAll(
                    new HBox(new Label("Chip: "), new Label(animal.chip)),
                    new HBox(new Label("Type: "), new Label(animal.type)),
                    new HBox(new Label("Species: "), new Label(animal.race)),
                    new HBox(new Label("Coat: "), new Label(animal.coat)),
                    new HBox(new Label("Sex: "), new Label(animal.sex.toString())),
                    new HBox(new Label("Age: "), new Label(Integer.toString(animal.age))),
                    new HBox(new Label("Cage: "), new Label(Integer.toString(animal.cage))),
                    new HBox(new Label("Comments: "), new Label(animal.comments))
            );
            for (Node node : details_info.getChildren())
            {
                node.getStyleClass().add("animal_detail");
                ((HBox)node).getChildren().get(0).getStyleClass().add("animal_detail_title");
            }
        details.getChildren().add(details_info);

        for (AnimalEvent event : history)
        {
            VBox event_block = new VBox();
                GridPane event_row = new GridPane();
                    event_row.getStyleClass().add("event");
                    event_row.getColumnConstraints().addAll(new ColumnConstraints(80), new ColumnConstraints(90),
                            new ColumnConstraints(200));
                    if (event instanceof Admission)
                    {
                        event_row.add(new Label("Admission"), 0, 0);
                        event_row.add(new Label(event.date.toString()), 1, 0);
                        event_row.add(new Label(((Admission) event).admissioner.name + " " +
                                ((Admission) event).admissioner.surname), 2, 0);
                    }
                    else if (event instanceof Treatment)
                    {
                        event_row.add(new Label("Treatment"), 0, 0);
                        event_row.add(new Label(event.date.toString()), 1, 0);
                        event_row.add(new Label(((Treatment) event).type.toString() + " (" + ((Treatment) event).doctor.name +
                                " " + ((Treatment) event).doctor.surname + ")"), 2, 0);
                    }
                    else if (event instanceof Adoption)
                    {
                        event_row.add(new Label("Adoption"), 0, 0);
                        event_row.add(new Label(event.date.toString()), 1, 0);
                        event_row.add(new Label(((Adoption) event).adopting_person.name + " " +
                                ((Adoption) event).adopting_person.surname), 2, 0);
                    }
                    event_row.setOnMouseClicked(e -> {
                        //String selected_animal_chip = ((Label)(selected).getChildren().get(1)).getText();
                        //List<database.AnimalEvent> selected_animal_history = database.getAnimalHistory(selected_animal_chip);
                        VBox this_event_block = (VBox)((GridPane)e.getSource()).getParent();
                        if (this_event_block.getChildren().size() == 2)
                        {
                            this_event_block.getChildren().remove(1);
                        }
                        else {
                            AnimalEvent selected_event = history.get(details.getChildren().indexOf(this_event_block) - 1);
                            VBox event_description = new VBox();
                                event_description.getStyleClass().add("event_details");
                                if (selected_event instanceof Admission) {
                                    event_description.getChildren().addAll(
                                            new HBox(new Label("Admission date: "), new Label(selected_event.date.toString())),
                                            new HBox(new Label("Admitted by: "), new Label(((Admission) selected_event).admissioner.name +
                                                    " " + ((Admission) selected_event).admissioner.surname)),
                                            new HBox(new Label("Comes from: "), new Label(((Admission) selected_event).where_found)),
                                            new HBox(new Label("Comments: "), new Label(((Admission) selected_event).comments))
                                    );
                                } else if (selected_event instanceof Treatment) {
                                    event_description.getChildren().addAll(
                                            new HBox(new Label("Treatment date: "), new Label(selected_event.date.toString())),
                                            new HBox(new Label("Treated by: "), new Label(((Treatment) selected_event).doctor.name +
                                                    " " + ((Treatment) selected_event).doctor.surname)),
                                            new HBox(new Label("Type: "), new Label(((Treatment) selected_event).type.toString())),
                                            new HBox(new Label("Details: "), new Label(((Treatment) selected_event).details)),
                                            new HBox(new Label("Comments: "), new Label(((Treatment) selected_event).comments))
                                    );
                                } else if (selected_event instanceof Adoption) {
                                    event_description.getChildren().addAll(
                                            new HBox(new Label("Adoption date: "), new Label(selected_event.date.toString())),
                                            new HBox(new Label("Adopted by: "), new Label(((Adoption) selected_event).adopting_person.name +
                                                    " " + ((Adoption) selected_event).adopting_person.surname)),
                                            new HBox(new Label("Spended by: "), new Label(((Adoption) selected_event).spending_employee.name +
                                                    " " + ((Adoption) selected_event).adopting_person.surname))
                                    );
                                }
                                for (Node node : event_description.getChildren())
                                    ((HBox)node).getChildren().get(0).getStyleClass().add("event_info_title");
                            this_event_block.getChildren().add(event_description);
                        }
                    });
                event_block.getChildren().add(event_row);
            details.getChildren().add(event_block);
        }
    }

    private void setAnimalsList(List<Animal> animals)
    {
        animals_list.getChildren().clear();
        for (int i = 0; i < animals.size(); i++)
        {
            Animal animal_temp = animals.get(i);
            GridPane record = new GridPane();
            record.getStyleClass().add(i%2 == 0 ? "even_record" : "odd_record");
            record.getColumnConstraints().addAll(new ColumnConstraints(60),
                    new ColumnConstraints(150), new ColumnConstraints(75),
                    new ColumnConstraints(200), new ColumnConstraints(60),
                    new ColumnConstraints(50), new ColumnConstraints(50));
            Button change = new Button("Edit");
            change.getStyleClass().add("edit_button");
            change.setOnAction(event ->{
                editor.editAnimal(animal_temp);
            });
            record.add(change, 0, 0);
            record.add(new Label(animals.get(i).chip), 1, 0);
            record.add(new Label(animals.get(i).type), 2, 0);
            record.add(new Label(animals.get(i).race + " " + animals.get(i).coat), 3, 0);
            record.add(new Label(animals.get(i).sex.toString()), 4, 0);
            record.add(new Label(Integer.toString(animals.get(i).age)), 5, 0);
            record.add(new Label(Integer.toString(animals.get(i).cage)), 6,0);
            for (Node node : record.getChildren())
                record.setHalignment(node, HPos.CENTER);

            record.setOnMouseClicked(event->{
                String chip = ((Label)((GridPane)(event.getSource())).getChildren().get(1)).getText();
                List<AnimalEvent> history = database.getAnimalHistory(chip);
                Animal animal = database.getAnimal(chip);
                setRightPane(history, animal);

                if (selected != null) selected.getStyleClass().remove("selected_record");
                selected = (GridPane)event.getSource();
                selected.getStyleClass().add("selected_record");
            });
            animals_list.getChildren().add(record);
        }
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Shelter for Homeless Animals - Animals");
        stage.setWidth(window_width);
        stage.setHeight(window_height);
        stage.setResizable(false);
        VBox root = new VBox();

        HBox tabs = new HBox();
        tabs.getStyleClass().add("menu");
            Button animals_button = new Button("Animals");
                animals_button.getStyleClass().add("active_tab");
            tabs.getChildren().add(animals_button);
            Button clients_button = new Button("Clients");
                clients_button.getStyleClass().add("unactive_tab");
                clients_button.setOnAction(event -> {
                    new ClientsGUI(database, this).start(stage);
                });
            tabs.getChildren().add(clients_button);
            Button employees_button = new Button("Employees");
                employees_button.getStyleClass().add("unactive_tab");
                employees_button.setOnAction(event -> {
                    new EmployeesGUI(database, this).start(stage);
                });
            tabs.getChildren().add(employees_button);
            Button log_in_button = new Button(logger.logged_as.equals("") ? "Log in" : "Log out");
                log_in_button.getStyleClass().add("unactive_tab");
                log_in_button.setTranslateX(750);
                log_in_button.setOnAction(event -> {
                    if (logger.logged_as.equals(""))
                    {
                        logger.showAndWait();
                        if (!logger.logged_as.equals(""))
                            log_in_button.setText("Log out");
                    }
                    else
                    {
                        logger.logged_as = "";
                        log_in_button.setText("Log in");
                    }
                });
            tabs.getChildren().add(log_in_button);
        root.getChildren().add(tabs);

        HBox options = new HBox();
            options.getStyleClass().add("options");
            Button new_admission = new Button("Add admission");
                new_admission.setOnAction(event ->{
                    admission_editor.addAdmission();
                });
            options.getChildren().add(new_admission);
            Button new_treatment = new Button("Add treatment");
                new_treatment.setOnAction(event -> {
                    treatment_editor.addTreatment();
                });
            options.getChildren().add(new_treatment);
            Button new_adoption = new Button("Add adoption");
                new_adoption.setOnAction(event -> {
                    adoption_editor.addAdoption();
                });
            options.getChildren().add(new_adoption);
            Button filtering = new Button("Filter");
                filtering.setOnAction(event -> {
                    Optional<List<Animal>> result = filter.filter();
                    if (result.isPresent())
                    {
                        setAnimalsList(result.get());
                        Button clear_filter = new Button("Clear filter");
                            clear_filter.setOnAction(e -> {
                                setAnimalsList(database.getAnimals());
                                options.getChildren().remove(4);
                            });
                        options.getChildren().add(clear_filter);
                    }
                });
            options.getChildren().add(filtering);
        root.getChildren().add(options);

        HBox body = new HBox();
            VBox animals_table = new VBox();
                GridPane headings = new GridPane();
                    headings.getStyleClass().add("headings");
                    headings.getColumnConstraints().addAll(new ColumnConstraints(60), new ColumnConstraints(150),
                            new ColumnConstraints(75), new ColumnConstraints(200), new ColumnConstraints(60),
                            new ColumnConstraints(50), new ColumnConstraints(50));
                    headings.add(new Label("Chip"), 1, 0);
                    headings.add(new Label("Type"), 2, 0);
                    headings.add(new Label("Species and coat"), 3, 0);
                    headings.add(new Label("Sex"), 4, 0);
                    headings.add(new Label("Age"), 5, 0);
                    headings.add(new Label("Cage"), 6, 0);
                    for (Node node : headings.getChildren())
                        headings.setHalignment(node, HPos.CENTER);
                animals_table.getChildren().add(headings);

                ScrollPane animals_pane = new ScrollPane();
                    animals_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    animals_list = new VBox();
                        animals_list.getStyleClass().add("animals_list");
                        List<Animal> animals = database.getAnimals();
                        setAnimalsList(animals);
                    animals_pane.setContent(animals_list);
                animals_table.getChildren().add(animals_pane);
            body.getChildren().add(animals_table);

            details = new VBox();
                details.getStyleClass().add("details_pane");
            body.getChildren().add(details);
        root.getChildren().add(body);

        Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();
    }
}
