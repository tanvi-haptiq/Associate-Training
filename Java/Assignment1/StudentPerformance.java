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
        int choice;
        do {
            System.out.println("\n** Student Performance **");
            System.out.println("1. Add Students");
            System.out.println("2. Enter Subject Name and Marks");
            System.out.println("3. Determine Pass or Fail");
            System.out.println("4. Save Report to File");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addStudent();
                case 2:
                    enterSubjectsAndMarks();
                case 3:
                    determineResults();
                case 4:
                    saveToFile();
                case 5:
                    System.out.println("Exiting program.");
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
            System.out.print("How many subjects? ");
            int count = Integer.parseInt(scanner.nextLine());
            subjectCounts[i] = count;
            for (int j = 0; j < count; j++) {
                System.out.print("Enter subject name: ");
                subjectNames[i][j] = scanner.nextLine();
                int mark;
                do {
                    System.out.print("Enter marks for " + subjectNames[i][j] + " (0-100): ");
                    mark = Integer.parseInt(scanner.nextLine());
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