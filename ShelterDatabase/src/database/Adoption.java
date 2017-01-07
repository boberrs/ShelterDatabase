package database;

import java.sql.Date;

/**
 * Created by Bober on 2016-12-31.
 */
public class Adoption extends AnimalEvent
{
    public Client adopting_person;
    public Employee spending_employee;

    public Adoption(Animal animal, Client adopting_person, Employee spending_employee, Date date) {
        this.animal = animal;
        this.adopting_person = adopting_person;
        this.spending_employee = spending_employee;
        this.date = date;
    }
}
