import java.io.*;
import java.util.Scanner;

public class UserDataStorageSystem {

    private static final String FILE_NAME = "users.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=== User Data Storage System ===");

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add new user");
            System.out.println("2. View all users");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addUser(scanner);
                    break;
                case "2":
                    viewUsers();
                    break;
                case "3":
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        scanner.close();
    }

    // Method to add new user
    private static void addUser(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        // Format record
        String record = String.format("Name: %s | Email: %s | Phone: %s%n", name, email, phone);

        // Write to file with append mode
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(record);
            System.out.println("User data saved successfully!");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to view all users
    private static void viewUsers() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No user records found. File does not exist.");
            return;
        }

        System.out.println("\n--- User Records ---");

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
