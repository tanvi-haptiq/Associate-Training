/* PROBLEM STATEMENT - 
Design a command-line Java application that allows instructors to record and compute the academic 
performance of multiple students across various subjects. 
The app must allow entering marks, calculating averages, determining pass/fail status, 
and saving the report to a file*/

/* REQUIREMENTS - 
Use Scanner for input
Menu-driven system
Input validation
Calculate max/min/average grades
Save report to text or JSON file*/

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class StudentPerformance {
    static Scanner scanner = new Scanner(System.in);
    static String[] studentNames = new String[100];
    static String[][] subjectNames = new String[100][10];
    static int[][] marks = new int[100][10];
    static int[] subjectCounts = new int[100];

    static int studentCount = 0;

    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("\n** Student Performance **");
            System.out.println("1. Add Students");
            System.out.println("2. Enter Subject Name and Marks");
            System.out.println("3. Determine Pass or Fail");
            System.out.println("4. Save Report to File");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    enterSubjectsAndMarks();
                    break;
                case 3:
                    determineResults();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        studentNames[studentCount] = name;
        studentCount++;
        System.out.println("Student added.");
    }

    static void enterSubjectsAndMarks() {
        if (studentCount == 0) {
            System.out.println("Please add students first.");
            return;
        }
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nEntering subjects for " + studentNames[i]);
            int count;
            while (true) {
                System.out.print("How many subjects? ");
                String input = scanner.nextLine();
                try {
                    count = Integer.parseInt(input);
                    if (count > 0 && count <= 10) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            subjectCounts[i] = count;
            for (int j = 0; j < count; j++) {
                System.out.print("Enter subject name: ");
                subjectNames[i][j] = scanner.nextLine();
                int mark;
                do {
                    System.out.print("Enter marks for " + subjectNames[i][j] + " (0-100): ");
                    String markInput = scanner.nextLine();
                    try {
                        mark = Integer.parseInt(markInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number between 0 and 100.");
                        mark = -1;
                    }
                } while (mark < 0 || mark > 100);
                marks[i][j] = mark;
            }
        }
    }

    static void determineResults() {
        if (studentCount == 0) {
            System.out.println("No data to process.");
            return;
        }
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nResult for " + studentNames[i]);
            int total = 0;
            int max = -1;
            int min = 101;
            for (int j = 0; j < subjectCounts[i]; j++) {
                int mark = marks[i][j];
                total += mark;
                if (mark > max)
                    max = mark;
                if (mark < min)
                    min = mark;
                String result;
                if (mark < 35)
                    result = "Fail";
                else if (mark >= 55)
                    result = "Pass";
                else
                    result = "Borderline";
                System.out.println(subjectNames[i][j] + ": " + mark + " -> " + result);
            }
            double avg = (double) total / subjectCounts[i];
            System.out.println("Average Marks: " + avg);
            System.out.println("Minimum Marks: " + min);
            System.out.println("Maximum Marks: " + max);
        }
    }

    static void saveToFile() {
        try {
            FileWriter writer = new FileWriter("student_report.txt");
            for (int i = 0; i < studentCount; i++) {
                writer.write("Student: " + studentNames[i] + "\n");
                int total = 0;
                int max = -1;
                int min = 101;
                for (int j = 0; j < subjectCounts[i]; j++) {
                    int mark = marks[i][j];
                    total += mark;
                    if (mark > max)
                        max = mark;
                    if (mark < min)
                        min = mark;
                    String result;
                    if (mark < 35)
                        result = "Fail";
                    else if (mark >= 55)
                        result = "Pass";
                    else
                        result = "Borderline";
                    writer.write(subjectNames[i][j] + ": " + mark + " -> " + result + "\n");
                }
                double avg = (double) total / subjectCounts[i];
                writer.write("Average Marks: " + avg + "\n");
                writer.write("Minimum Marks: " + min + "\n");
                writer.write("Maximum Marks: " + max + "\n");
                writer.write("--------------------------\n");
            }
            writer.close();
            System.out.println("Report saved to student_report.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}