import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class Component1 {
    /**
     * The file path for the student data file.
     */
    private static final String STUDENT_DATA_FILE = "student_data.csv";
    /**
     * The folder path for batch files.
     */
    private static final String BATCH_FOLDER_PATH =
    "E:\\college\\level_4\\last_semester\\cloud_computing"
           + "\\assignment2\\app\\data\\batch";

    private Component1() {

    }

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayMenu();

        while (true) {
            System.out.print("Enter your choice (1-2): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudentData(scanner);
                    break;
                case 2:
                    batchInsert(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            displayMenu();
        }
    }

    private static void displayMenu() {
        System.out.println("Main Menu");
        System.out.println("1- Add student data");
        System.out.println("2- Add batch students data");
    }

    private static void addStudentData(final Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter student courses (comma-separated list): ");
        String courses = scanner.nextLine();

        try (FileWriter writer = new FileWriter(STUDENT_DATA_FILE, true)) {
            writer.write(name + "," + id + ","
                   + courses.replaceAll(",", ",") + "\n");
            System.out.println("Student data added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void batchInsert(final Scanner scanner) {
        List<String> eligibleFiles = listEligibleFiles();
        if (eligibleFiles.isEmpty()) {
            System.out.println("No eligible batch files found.");
            return;
        }

        System.out.println("Eligible batch files:");
        for (int i = 0; i < eligibleFiles.size(); i++) {
            System.out.println((i + 1) + "- " + eligibleFiles.get(i));
        }

        System.out.print("Enter the file number to insert students from: ");
        int fileNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String selectedFile = eligibleFiles.get(fileNumber - 1);
        String filePath = BATCH_FOLDER_PATH + "/" + selectedFile;

        try (BufferedReader reader =
        new BufferedReader(new FileReader(filePath));
             FileWriter writer = new FileWriter(STUDENT_DATA_FILE, true)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                for (String element : data) {
                    writer.write(element + ",");
                }
                writer.write("\n");
            }

            System.out.println("Batch insert completed!");
        } catch (IOException e) {
            System.out.println("Error reading or writing file: "
            + e.getMessage());
        }
    }

    private static List<String> listEligibleFiles() {
        List<String> eligibleFiles = new ArrayList<>();

        File batchFolder = new File(BATCH_FOLDER_PATH);
        File[] files = batchFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains("verified")) {
                    eligibleFiles.add(file.getName());
                }
            }
        }

        return eligibleFiles;
    }
}
