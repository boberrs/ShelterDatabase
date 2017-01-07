package dialogs;

import database.Animal;
import database.Client;
import database.DatabaseCommunicator;
import database.Employee;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Bober on 2017-01-02.
 */
public class EmployeeEditor extends Dialog<Employee>
{
    private DatabaseCommunicator communicator;

    private TextField pesel_input;
    private TextField name_input;
    private TextField surname_input;
    private TextField birth_date_input;
    private TextField address_input;
    private ComboBox<String> education_input;
    private ComboBox<String> position_input;
    private TextField salary_input;
    private TextField employment_date_input;
    private ComboBox<String> contract_input;
    private ComboBox<String> job_position_input;
    private TextField last_medical_examination_input;

    private String edited_employee_pesel;

    public EmployeeEditor(DatabaseCommunicator communicator)
    {
        this.communicator = communicator;

        this.setTitle("Edit data");
        ButtonType ok = ButtonType.APPLY;

        this.getDialogPane().getButtonTypes().addAll(ok, ButtonType.CANCEL);
        GridPane edit_grid = new GridPane();
        edit_grid.setHgap(10);
        edit_grid.setVgap(10);
        edit_grid.setPadding(new Insets(20, 20, 20, 20));
        edit_grid.getColumnConstraints().addAll(new ColumnConstraints(150), new ColumnConstraints(250));

        Label pesel_text = new Label("PESEL");
        edit_grid.add(pesel_text, 0, 0);
        pesel_input = new TextField();
        edit_grid.add(pesel_input, 1, 0);

        Label name_text = new Label("Name");
        edit_grid.add(name_text, 0, 1);
        name_input = new TextField();
        edit_grid.add(name_input, 1, 1);

        Label surname_text = new Label("Surname");
        edit_grid.add(surname_text, 0, 2);
        surname_input = new TextField();
        edit_grid.add(surname_input, 1, 2);

        Label birth_date_text = new Label("Birth date");
        edit_grid.add(birth_date_text, 0, 3);
        birth_date_input = new TextField();
        edit_grid.add(birth_date_input, 1, 3);

        Label address_text = new Label("Address");
        edit_grid.add(address_text, 0, 4);
        address_input = new TextField();
        edit_grid.add(address_input, 1, 4);

        Label education_text = new Label("Education");
        edit_grid.add(education_text, 0, 5);
        education_input = new ComboBox<>();
            ArrayList<String> education_input_values = new ArrayList<>();
            for (int i = 0; i < Employee.Education.values().length; i++)
                education_input_values.add(Employee.Education.values()[i].toString());
            education_input.getItems().addAll(education_input_values);
        edit_grid.add(education_input, 1, 5);

        Label position_text = new Label("Position");
        edit_grid.add(position_text, 0, 6);
        position_input = new ComboBox<>();
            ArrayList<String> position_input_values = new ArrayList<>();
            for (int i = 0; i < Employee.Position.values().length; i++)
                position_input_values.add(Employee.Position.values()[i].toString());
            position_input.getItems().addAll(position_input_values);
        edit_grid.add(position_input, 1, 6);

        Label salary_text = new Label("Salary");
        edit_grid.add(salary_text, 0, 7);
        salary_input = new TextField();
        edit_grid.add(salary_input, 1, 7);

        Label employment_date_text = new Label("Employment date");
        edit_grid.add(employment_date_text, 0, 8);
        employment_date_input = new TextField();
        edit_grid.add(employment_date_input, 1, 8);

        Label contract_text = new Label("Contract");
        edit_grid.add(contract_text, 0, 9);
        contract_input = new ComboBox<>();
            ArrayList<String> contract_input_values = new ArrayList<>();
            for (int i = 0; i < Employee.ContractType.values().length; i++)
                contract_input_values.add(Employee.ContractType.values()[i].toString());
            contract_input.getItems().addAll(contract_input_values);
        edit_grid.add(contract_input, 1, 9);

        Label job_position_text = new Label("Job position");
        edit_grid.add(job_position_text, 0, 10);
        job_position_input = new ComboBox<>();
            ArrayList<String> job_input_values = new ArrayList<>();
            for (int i = 0; i < Employee.JobPosition.values().length; i++)
                job_input_values.add(Employee.JobPosition.values()[i].toString());
            job_position_input.getItems().addAll(job_input_values);
        edit_grid.add(job_position_input, 1, 10);

        Label last_medical_examination_text = new Label("Last medical examination");
        edit_grid.add(last_medical_examination_text, 0, 11);
        last_medical_examination_input = new TextField();
        edit_grid.add(last_medical_examination_input, 1, 11);

        for (int i = 0; i < edit_grid.getChildren().size(); i++)
            if (edit_grid.getChildren().get(i) instanceof ComboBox)
                ((ComboBox) edit_grid.getChildren().get(i)).setMinWidth(250);
            else if (edit_grid.getChildren().get(i) instanceof Label)
                GridPane.setHalignment(edit_grid.getChildren().get(i), HPos.RIGHT);



        this.getDialogPane().setContent(edit_grid);
        this.setResultConverter(e -> {
            if (e == ok)
            {
                return new Employee(pesel_input.getText(), name_input.getText(), surname_input.getText(),
                        Date.valueOf(birth_date_input.getText()), address_input.getText(),
                        Employee.Education.valueOf(education_input.getSelectionModel().getSelectedItem()),
                        Employee.Position.valueOf(position_input.getSelectionModel().getSelectedItem()),
                        Integer.valueOf(salary_input.getText()), Date.valueOf(employment_date_input.getText()),
                        Employee.ContractType.valueOf(contract_input.getSelectionModel().getSelectedItem()),
                        Employee.JobPosition.valueOf(job_position_input.getSelectionModel().getSelectedItem()),
                        Date.valueOf(last_medical_examination_text.getText()));
            }
            return null;
        });
    }

    public void editEmployee(Employee employee)
    {
        edited_employee_pesel = employee.PESEL;

        pesel_input.setText(employee.PESEL);
        name_input.setText(employee.name);
        surname_input.setText(employee.surname);
        address_input.setText(employee.address);
        birth_date_input.setText(employee.birth_date.toString());
        position_input.getSelectionModel().select(employee.position.toString());
        education_input.getSelectionModel().select(employee.education.toString());
        salary_input.setText(Integer.toString(employee.salary));
        job_position_input.getSelectionModel().select(employee.job_position.toString());
        last_medical_examination_input.setText(employee.last_medical_examination.toString());
        employment_date_input.setText(employee.employment_date.toString());
        contract_input.getSelectionModel().select(employee.contract.toString());
        Optional<Employee> result = showAndWait();
        if (result.isPresent())
            communicator.updateEmployee(edited_employee_pesel, result.get());
    }
}
