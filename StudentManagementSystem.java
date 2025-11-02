 import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return "Roll No: " + rollNumber + " | Name: " + name + " | Grade: " + grade;
    }

    public String toFileFormat() {
        return rollNumber + "," + name + "," + grade;
    }

    public static Student fromFileFormat(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) return null;
        return new Student(parts[1], Integer.parseInt(parts[0]), parts[2]);
    }
} 

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.txt";
    private static List<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();

        int choice;
        do {
            System.out.println("\n===== 🎓 STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> editStudent();
                case 5 -> deleteStudent();
                case 6 -> saveToFile();
                default -> System.out.println("⚠ Invalid choice. Try again!");
            }
        } while (choice != 6);

        System.out.println("✅ Exiting... Data saved successfully!");
    }

    // --- Utility methods ---
    private static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static void addStudent() {
        System.out.print("Enter Roll Number: ");
        int roll = getIntInput();
        sc.nextLine(); // clear buffer
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        students.add(new Student(name, roll, grade));
        saveToFile();
        System.out.println("✅ Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("📭 No students found!");
            return;
        }
        System.out.println("\n--- Student List ---");
        students.forEach(System.out::println);
    }

    private static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        int roll = getIntInput();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("🔍 Found: " + s);
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    private static void editStudent() {
        System.out.print("Enter roll number to edit: ");
        int roll = getIntInput();
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                sc.nextLine();
                System.out.print("Enter new name: ");
                s.setName(sc.nextLine());
                System.out.print("Enter new grade: ");
                s.setGrade(sc.nextLine());
                saveToFile();
                System.out.println("✅ Student updated successfully!");
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    private static void deleteStudent() {
        System.out.print("Enter roll number to delete: ");
        int roll = getIntInput();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getRollNumber() == roll) {
                it.remove();
                saveToFile();
                System.out.println("🗑️ Student deleted successfully!");
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    // --- File operations ---
    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromFileFormat(line);
                if (s != null) students.add(s);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading data: " + e.getMessage());
        }
    }

    private static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving data: " + e.getMessage());
        }
    }
}
