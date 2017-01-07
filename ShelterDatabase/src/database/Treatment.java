package database;

import java.sql.Date;

/**
 * Created by Bober on 2016-12-31.
 */
public class Treatment extends AnimalEvent
{
    public enum TreatmentType { GRAFTING, STERILIZATION, OPERATION, PREVENTION, CURING, INSPECTION, OTHER }
    public Employee doctor;
    public TreatmentType type;
    public String details;
    public String comments;

    public Treatment(Employee doctor, Animal animal, Date date, TreatmentType type, String details, String comments) {
        this.doctor = doctor;
        this.animal = animal;
        this.date = date;
        this.type = type;
        this.details = details;
        this.comments = comments;
    }
}
