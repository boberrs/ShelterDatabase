package interfaces;

import database.*;
import dialogs.ClientEditor;
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
public class ClientsGUI
{
    private final double window_width = 1100;
    private final double window_height = 850;

    private AnimalsGUI gui;
    private DatabaseCommunicator database;
    private ClientEditor editor;

    GridPane selected;

    public ClientsGUI(DatabaseCommunicator communicator, AnimalsGUI gui)
    {
        database = communicator;
        this.gui = gui;
        editor = new ClientEditor(communicator);
    }

    public void start(Stage stage)
    {
        stage.setTitle("Shelter for Homeless Animals - Clients");
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
                clients_button.getStyleClass().add("active_tab");
            tabs.getChildren().add(clients_button);
            Button employees_button = new Button("Employees");
                employees_button.getStyleClass().add("unactive_tab");
                employees_button.setOnAction(event -> {
                    new EmployeesGUI(database, gui).start(stage);
                });
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

        GridPane headings = new GridPane();
            headings.getStyleClass().add("headings");
            headings.getColumnConstraints().addAll(new ColumnConstraints(90), new ColumnConstraints(200),
                    new ColumnConstraints(250), new ColumnConstraints(300), new ColumnConstraints(150));
            headings.add(new Label("PESEL"), 1, 0);
            headings.add(new Label("Name"), 2, 0);
            headings.add(new Label("Address"), 3, 0);
            headings.add(new Label("Phone number"), 4, 0);
            for (Node node : headings.getChildren())
                headings.setHalignment(node, HPos.CENTER);
        root.getChildren().add(headings);

        ScrollPane clients_pane = new ScrollPane();
            clients_pane.getStyleClass().add("body");
            clients_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            VBox clients_list = new VBox();
            clients_list.getStyleClass().add("clients_list");
            List<Client> clients = database.getClients();
            for (int i = 0; i < clients.size(); i++)
            {
                Client client_temp = clients.get(i);
                GridPane client_record = new GridPane();
                    client_record.getStyleClass().add(i%2 == 0 ? "even_record" : "odd_record");
                    client_record.getColumnConstraints().addAll(new ColumnConstraints(90),
                            new ColumnConstraints(200), new ColumnConstraints(250),
                            new ColumnConstraints(300), new ColumnConstraints(150));
                    Button change = new Button("Edit");
                        change.getStyleClass().add("edit_button");
                        change.setOnAction(event -> {
                            editor.editClient(client_temp);
                        });
                    client_record.add(change, 0, 0);
                    client_record.add(new Label(clients.get(i).PESEL), 1, 0);
                    client_record.add(new Label(clients.get(i).name + " " + clients.get(i).surname), 2, 0);
                    client_record.add(new Label(clients.get(i).address), 3, 0);
                    client_record.add(new Label(clients.get(i).phone_number), 4, 0);
                    for (Node node : client_record.getChildren())
                        headings.setHalignment(node, HPos.CENTER);

                    client_record.setOnMouseClicked(event->{
                        if (selected != null) selected.getStyleClass().remove("selected_record");
                        selected = (GridPane)event.getSource();
                        selected.getStyleClass().add("selected_record");
                    });
                clients_list.getChildren().add(client_record);
            }
            clients_pane.setContent(clients_list);
        root.getChildren().add(clients_pane);

        Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();
    }
}
