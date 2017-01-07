package database;

/**
 * Created by Bober on 2016-12-31.
 */
public class Animal
{
    public Animal(String chip, String type, String race, String coat, Sex sex, int age, int cage, String comments) {
        this.chip = chip;
        this.type = type;
        this.race = race;
        this.coat = coat;
        this.sex = sex;
        this.age = age;
        this.cage = cage;
        this.comments = comments;
    }

    public enum Sex { MALE, CASTRATED_MALE, FEMALE, STERILISED_FEMALE }
    public String chip;
    public String type;
    public String race;
    public  String coat;
    public Sex sex;
    public int age;
    public int cage;
    public String comments;
}
