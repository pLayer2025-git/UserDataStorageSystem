import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserDataCSVSystem {

    private static final String FILE_NAME = "users.csv";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=== User Data Storage System (CSV) ===");

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

    // Add user with validation
    private static void addUser(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine().trim();
            if (isValidEmail(email)) break;
            System.out.println("Invalid email format. Try again.");
        }

        String phone;
        while (true) {
            System.out.print("Enter phone number: ");
            phone = scanner.nextLine().trim();
            if (isValidPhone(phone)) break;
            System.out.println("Invalid phone number. Must be 10 digits.");
        }

        // Format as CSV
        String record = String.format("%s,%s,%s%n", name, email, phone);

        // Write to file with append mode
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // If file is new, add header
            File file = new File(FILE_NAME);
            if (file.length() == 0) {
                bw.write("Name,Email,Phone\n");
            }

            bw.write(record);
            System.out.println("User data saved successfully in CSV format!");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // View users from CSV
    private static void viewUsers() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No user records found.");
            return;
        }

        System.out.println("\n--- User Records ---");

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    System.out.printf("Name: %-15s Email: %-25s Phone: %s%n",
                            fields[0], fields[1], fields[2]);
                } else {
                    // Print header or malformed lines
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Validate email using regex
    private static boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(regex, email);
    }

    // Validate phone: 10 digits
    private static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}
