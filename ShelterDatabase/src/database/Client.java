package database;

/**
 * Created by Bober on 2016-12-31.
 */
public class Client
{
    public String PESEL;
    public String name;
    public String surname;
    public String address;
    public String phone_number;

    public Client(String PESEL, String name, String surname, String address, String phone_number) {
        this.PESEL = PESEL;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
    }
}
