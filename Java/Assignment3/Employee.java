/*PROBLEM STATEMENT - 
Build a data analysis tool for HR that processes a list of employees and provides insights using the Java Stream API.

REQUIREMENTS - 
Filter employees earning > ₹50K
Group employees by department
Get average salary per department
Sort employees by experience and salary
Create reusable stream-based utility methods
*/

import java.util.ArrayList;

class Employee {
    private int id;
    private String name;
    private String dept;
    private int salary;
    private int expYears;

    public Employee(int id, String name, String dept, int salary, int exp) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.expYears = exp;
    }

    public String getName() {
        return this.name;
    }

    public int getSalary() {
        return this.salary;
    }

    public String getDepartment() {
        return this.dept;
    }

    public int getExperience() {
        return this.expYears;
    }
}