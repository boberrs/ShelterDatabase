package interfaces;

import database.*;
import dialogs.EmployeeEditor;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Bober on 2016-12-31.
 */
public class EmployeesGUI
{
    private final double window_width = 1100;
    private final double window_height = 850;

    private AnimalsGUI gui;
    private DatabaseCommunicator database;
    private EmployeeEditor editor;

    GridPane selected;

    public EmployeesGUI(DatabaseCommunicator communicator, AnimalsGUI gui)
    {
        database = communicator;
        this.gui = gui;
        editor = new EmployeeEditor(communicator);
    }

    public void start(Stage stage)
    {
        stage.setTitle("Shelter for Homeless Animals - Employees");
        stage.setWidth(window_width);
        stage.setHeight(window_height);
        stage.setResizable(false);
        VBox root = new VBox();

        HBox tabs = new HBox();
            tabs.getStyleClass().add("menu");
            Button animals_button = new Button("Animals");
                animals_button.getStyleClass().add("unactive_tab");
                animals_button.setOnAction(event -> {
                    gui.start(stage);
                });
            tabs.getChildren().add(animals_button);
            Button clients_button = new Button("Clients");
                clients_button.getStyleClass().add("unactive_tab");
                clients_button.setOnAction(event -> {
                    new ClientsGUI(database, gui).start(stage);
                });
            tabs.getChildren().add(clients_button);
            Button employees_button = new Button("Employees");
                employees_button.getStyleClass().add("active_tab");
            tabs.getChildren().add(employees_button);
        Button log_in_button = new Button(gui.logger.logged_as.equals("") ? "Log in" : "Log out");
                log_in_button.getStyleClass().add("unactive_tab");
                log_in_button.setTranslateX(750);
                log_in_button.setOnAction(event -> {
                    if (gui.logger.logged_as.equals(""))
                    {
                        gui.logger.showAndWait();
                        if (!gui.logger.logged_as.equals(""))
                            log_in_button.setText("Log out");
                    }
                    else
                    {
                        gui.logger.logged_as = "";
                        log_in_button.setText("Log in");
                    }
                });
            tabs.getChildren().add(log_in_button);
        root.getChildren().add(tabs);

        ScrollPane employees_table_pane = new ScrollPane();
            //employees_table_pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            VBox employees_table = new VBox();
                GridPane headings = new GridPane();
                    headings.getStyleClass().add("headings");
                    headings.getColumnConstraints().addAll(new ColumnConstraints(60), new ColumnConstraints(125),
                            new ColumnConstraints(150), new ColumnConstraints(100), new ColumnConstraints(200),
                            new ColumnConstraints(75), new ColumnConstraints(100), new ColumnConstraints(75),
                            new ColumnConstraints(150), new ColumnConstraints(200), new ColumnConstraints(100),
                            new ColumnConstraints(200));
                    headings.add(new Label("PESEL"), 1, 0);
                    headings.add(new Label("Name"), 2, 0);
                    headings.add(new Label("Birth date"), 3, 0);
                    headings.add(new Label("Address"), 4, 0);
                    headings.add(new Label("Education"), 5, 0);
                    headings.add(new Label("Position"), 6, 0);
                    headings.add(new Label("Salary"), 7, 0);
                    headings.add(new Label("Employment date"), 8, 0);
                    headings.add(new Label("Contract"), 9, 0);
                    headings.add(new Label("Job position"), 10, 0);
                    headings.add(new Label("Last medical examination"), 11, 0);
                    for (Node node : headings.getChildren())
                        headings.setHalignment(node, HPos.CENTER);
                employees_table.getChildren().add(headings);

                ScrollPane employees_pane = new ScrollPane();
                    //employees_pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                    employees_pane.getStyleClass().add("body");
                    VBox employees_list = new VBox();
                        employees_list.getStyleClass().add("employees_list");
                        List<Employee> employees = database.getEmployees();
                        for (int i = 0; i < employees.size(); i++)
                        {
                            Employee employee_temp = employees.get(i);
                            GridPane employee_record = new GridPane();
                                employee_record.getStyleClass().add(i%2 == 0 ? "even_record" : "odd_record");
                                employee_record.getColumnConstraints().addAll(new ColumnConstraints(60),
                                        new ColumnConstraints(125), new ColumnConstraints(150),
                                        new ColumnConstraints(100), new ColumnConstraints(200),
                                        new ColumnConstraints(75), new ColumnConstraints(100),
                                        new ColumnConstraints(75), new ColumnConstraints(150),
                                        new ColumnConstraints(200), new ColumnConstraints(100),
                                        new ColumnConstraints(200));
                                Button change = new Button("Edit");
                                    change.getStyleClass().add("edit_button");
                                    change.setOnAction(event->{
                                        editor.editEmployee(employee_temp);
                                    });
                                employee_record.add(change, 0, 0);
                                employee_record.add(new Label(employees.get(i).PESEL), 1, 0);
                                employee_record.add(new Label(employees.get(i).name + " " + employees.get(i).surname), 2, 0);
                                employee_record.add(new Label(employees.get(i).birth_date.toString()), 3, 0);
                                employee_record.add(new Label(employees.get(i).address), 4, 0);
                                employee_record.add(new Label(employees.get(i).education.toString()), 5, 0);
                                employee_record.add(new Label(employees.get(i).position.toString()), 6, 0);
                                employee_record.add(new Label(Integer.toString(employees.get(i).salary)), 7, 0);
                                employee_record.add(new Label(employees.get(i).employment_date.toString()), 8, 0);
                                employee_record.add(new Label(employees.get(i).contract.toString()), 9, 0);
                                employee_record.add(new Label(employees.get(i).job_position.toString()), 10, 0);
                                employee_record.add(new Label(employees.get(i).last_medical_examination.toString()), 11, 0);
                                for (Node node : employee_record.getChildren())
                                    headings.setHalignment(node, HPos.CENTER);

                                employee_record.setOnMouseClicked(event->{
                                    if (selected != null) selected.getStyleClass().remove("selected_record");
                                    selected = (GridPane)event.getSource();
                                    selected.getStyleClass().add("selected_record");
                                });

                            employees_list.getChildren().add(employee_record);
                        }
                    employees_pane.setContent(employees_list);
                employees_table.getChildren().add(employees_pane);
            employees_table_pane.setContent(employees_table);
        root.getChildren().add(employees_table_pane);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();
    }
}
