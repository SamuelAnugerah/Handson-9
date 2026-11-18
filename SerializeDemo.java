import java.io.*;
import java.util.ArrayList;

class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class SerializeDemo {

    public static void serialize(ArrayList<Employee> employees) {

        try (ObjectOutputStream oos =
                new ObjectOutputStream(
                        new FileOutputStream("emp.ser"))) {

            oos.writeObject(employees);

            System.out.println("Data berhasil disimpan.");

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ArrayList<Employee> deserialize() {

        try (ObjectInputStream ois =
                new ObjectInputStream(
                        new FileInputStream("emp.ser"))) {

            return (ArrayList<Employee>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee("John", 30));
        employees.add(new Employee("Samuel", 19));
        employees.add(new Employee("Andi", 25));

        serialize(employees);

        System.out.println("\nData Employee Setelah Deserialization:");

        ArrayList<Employee> restoredEmployees = deserialize();

        for (Employee employee : restoredEmployees) {

            System.out.println("Nama: " + employee.name);
            System.out.println("Umur: " + employee.age);
            System.out.println();
        }
    }
}