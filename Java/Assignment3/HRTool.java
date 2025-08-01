import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HRTool {
    static ArrayList<Employee> emp = new ArrayList<>();

    public static void generateData() {
        emp.add(new Employee(101, "Reva", "Software Engineer", 60000, 2));
        emp.add(new Employee(102, "Ashwini", "Devops Enginer", 70000, 3));
        emp.add(new Employee(103, "Shivansh", "Quality Assuarance", 40000, 4));
        emp.add(new Employee(104, "Riya", "Software Engineer", 620000, 3));
        emp.add(new Employee(105, "Shraddha", "Devops Enginer", 72000, 4));
        emp.add(new Employee(106, "Jayesh", "Quality Assuarance", 45000, 5));
    }

    // Filter employees earning > ₹50K
    public static List<Employee> getEmployeesBySalary(int higerSalary) {
        List<Employee> employees = emp.stream().filter(e -> e.getSalary() > higerSalary).collect(Collectors.toList());
        return employees;
    }

    // Group employees by department
    public static Map<String, List<Employee>> getEmployeesByGroupDept() {
        Map<String, List<Employee>> employees = emp.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        return employees;
    }

    // Get average salary per department
    public static Map<String, Double> getAverageSalaryByDepartment() {
        return emp.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
    }

    // Sort employees by experience and salary
    public static List<Employee> getEmployeeByExperienceAndSalary() {
        return emp.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .sorted(Comparator.comparingInt(Employee::getExperience).reversed())
                .collect(Collectors.toList());
    }
    // Create reusable stream-based utility methods

    public static void main(String[] args) {
        generateData();

        List<Employee> grtSalary = getEmployeesBySalary(50000);
        System.out.println(grtSalary);

        Map<String, List<Employee>> employees = getEmployeesByGroupDept();
        System.out.println(employees);

        Map<String, Double> avgSalary = getAverageSalaryByDepartment();
        System.out.println(avgSalary);

        List<Employee> expSal = getEmployeeByExperienceAndSalary();

        for (Employee e : expSal) {
            System.out.println(
                    e.getName() + " | " + e.getDepartment() + " | " + e.getSalary() + " | " + e.getExperience());
        }
    }
};