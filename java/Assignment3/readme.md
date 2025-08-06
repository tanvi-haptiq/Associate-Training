# HR Data Analysis Tool (Java Stream API Project)

## 📝 Problem Statement
Build a data analysis tool for HR that processes a list of employees and provides insights using the **Java Stream API**.

## 📋 Requirements
- ✅ Filter employees earning more than ₹50,000
- ✅ Group employees by department
- ✅ Calculate average salary per department
- ✅ Sort employees by experience and salary
- ✅ Reusable, stream-based utility methods

## 📁 Project Structure

```
HRTool.java
Employee.java
README.md
```

## 🚀 How to Run

1. Clone or download the project files.
2. Compile the Java files:
   ```bash
   javac Employee.java HRTool.java
   ```
3. Run the main program:
   ```bash
   java HRTool
   ```

## 💡 Features

### 1. Filter Employees by Salary (> ₹50,000)
```java
getEmployeesBySalary(int highSalary);
```

### 2. Group Employees by Department
```java
getEmployeesByGroupDept();
```

### 3. Average Salary Per Department
```java
getAverageSalaryByDepartment();
```

### 4. Sort by Experience & Salary
```java
getEmployeeByExperienceAndSalary();
```

## 📦 Sample Output
```
[Employee Objects with Salary > 50000]
{Department1=[Employee1, Employee2], Department2=[Employee3]}
{Department1=AvgSalary1, Department2=AvgSalary2}
EmployeeName | Department | Salary | Experience
...
```

## 👨‍💻 Technologies Used
- Java 8+
- Java Streams API

---

© 2025 HR Data Insights | Java Streams Mini Project