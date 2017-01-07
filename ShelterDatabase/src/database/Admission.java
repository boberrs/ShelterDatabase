package database;

import java.sql.Date;

/**
 * Created by Bober on 2016-12-31.
 */
public class Admission extends AnimalEvent
{
    public Client admissioner;
    public String where_found;
    public String comments;

    public Admission(Animal animal, Client admissioner, Date date, String where_found, String comments) {
        this.animal = animal;
        this.admissioner = admissioner;
        this.date = date;
        this.where_found = where_found;
        this.comments = comments;
    }
}
