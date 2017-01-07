package database;

import java.sql.Date;

/**
 * Created by Bober on 2016-12-31.
 */
public class Employee
{
    public Employee(String PESEL, String name, String surname, Date birth_date, String address,
                    Education education, Position position, int salary, Date employment_date, ContractType contract,
                    JobPosition job_position, Date last_medical_examination) {
        this.PESEL = PESEL;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.address = address;
        this.education = education;
        this.position = position;
        this.salary = salary;
        this.employment_date = employment_date;
        this.contract = contract;
        this.job_position = job_position;
        this.last_medical_examination = last_medical_examination;
    }

    public enum Education{ HIGHER, SECONDARY, LOWER }
    public enum Position { DIRECTOR, ACCOUNTANT, HUMAN_RESOURCE_MANAGER, DRIVER, DOCTOR, NURSE, CLERIC, COOK, CLEANER, CARETAKER, CONSERVATOR }
    public enum ContractType { CONTRACT_OF_MANDATE, TRIAL, CONTRACT_OF_COMMISSION, CONTRACT_OF_EMLOYMENT }
    public enum JobPosition { FULL_TIME, HALF, THREE_QUARTERS }
    public String PESEL;
    public String name;
    public String surname;
    public Date birth_date;
    public String address;
    public Education education;
    public Position position;
    public int salary;
    public Date employment_date;
    public ContractType contract;
    public JobPosition job_position;
    public Date last_medical_examination;
}
