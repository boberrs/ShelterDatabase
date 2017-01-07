package database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bober on 2016-12-31.
 */
public class DatabaseCommunicator
{
    public List<Animal> getAnimals()
    {
        List<Animal> result = new ArrayList<>();
        result.add(new Animal("742648294284281", "Cat", "European cat", "Ginger",
                Animal.Sex.MALE, 3, 134, "LOLOLOL"));
        result.add(new Animal("274646264626633", "Mouse", "Minimouse", "White-black",
                Animal.Sex.FEMALE, 2, 7, "A"));
        for (int i = 0; i < 100; i++)
        result.add(new Animal("264685902402456", "Dog", "Dalmatian", "White",
                Animal.Sex.FEMALE, 12, 37, ""));
        return result;
    }

    public List<Client> getClients()
    {
        List<Client> result = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            result.add(new Client("96121506879", "Lolesław", "Lolovitch",
                    "Lolity Lolskiej 53-311 Lolesławiec", "743882374"));
        return result;
    }

    public List<Employee> getEmployees()
    {
        List<Employee> result = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            result.add(new Employee("344453294573", "Kopiewicz", "Zaolski",
                    new Date(1996, 12, 15), "Woliwody 23-423 Poznań",
                    Employee.Education.HIGHER, Employee.Position.ACCOUNTANT, 3243,
                    new Date(2000, 10, 10), Employee.ContractType.CONTRACT_OF_COMMISSION,
                    Employee.JobPosition.FULL_TIME, new Date(2003, 1, 1)));
        return result;
    }

    public Animal getAnimal(String chip)
    {
        return new Animal(chip, "Mouse", "Minimouse", "White-black",
                Animal.Sex.FEMALE, 2, 7, "A");
    }

    public Employee getEmployee(String pesel)
    {
        return new Employee(pesel, "Kopiewicz", "Zaolski",
                new Date(1996, 12, 15), "Woliwody 23-423 Poznań",
                Employee.Education.HIGHER, Employee.Position.ACCOUNTANT, 3243,
                new Date(2000, 10, 10), Employee.ContractType.CONTRACT_OF_COMMISSION,
                Employee.JobPosition.FULL_TIME, new Date(2003, 1, 1));
    }

    public List<AnimalEvent> getAnimalHistory(String chip)
    {
        Animal animal = new Animal("274646264626633", "Mouse", "Minimouse", "White-black",
                Animal.Sex.FEMALE, 2, 7, "A");
        List<AnimalEvent> result = new ArrayList<>();
        result.add(new Admission(animal,
                new Client("28482974892", "LOL", "ZALEWSKI", "lolitow 13", "743749293"),
                new Date(10, 10, 10), "W koszu", "brak"));
        result.add(new Treatment(new Employee("344453294573", "Kopiewsdfsdfsfsicz", "Zaolsfsdfsfdfdski",
                new Date(1996, 12, 15), "Woliwody 23-423 Poznań",
                Employee.Education.HIGHER, Employee.Position.DOCTOR, 3243,
                new Date(2000, 10, 10), Employee.ContractType.CONTRACT_OF_COMMISSION,
                Employee.JobPosition.FULL_TIME, new Date(2003, 1, 1)), animal, new Date(834, 73, 38),
                Treatment.TreatmentType.CURING, "LOLNIĘTO", "w morde"));
        result.add(new Adoption(animal, new Client("96121506879", "Lolesław", "Lolovitch",
                "Lolity Lolskiej 53-311 Lolesławiec", "743882374"), new Employee("344453294573", "Kopiewicz", "Zaolski",
                new Date(1996, 12, 15), "Woliwody 23-423 Poznań",
                Employee.Education.HIGHER, Employee.Position.DOCTOR, 3243,
                new Date(2000, 10, 10), Employee.ContractType.CONTRACT_OF_COMMISSION,
                Employee.JobPosition.FULL_TIME, new Date(2003, 1, 1)), new Date(37, 48, 48)));
        return result;
    }

    public void updateAnimal(String old_chip, Animal new_data)
    {

    }

    public void updateClient(String old_client_pesel, Client new_data)
    {

    }

    public void updateEmployee(String old_employee_pesel, Employee new_data)
    {

    }

    public void addAdmission(Admission admission)
    {

    }

    public void addTreatment(Treatment treatment)
    {

    }

    public void addAdoption(Adoption adoption)
    {

    }

    //each parameter might be null
    public List<Animal> filter(String chip, String type, String race, String coat, Animal.Sex sex, Integer age, Integer cage)
    {
        List<Animal> result = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            result.add(new Animal("264685902402456", "Dog", "Dalmatian", "White",
                    Animal.Sex.FEMALE, 12, 37, ""));
        return result;
    }
}
