import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Employee {
    int empId;
    String name;
    double basicPay;
    double hra;
    double da;
    double tax;
    double grossSalary;
    double netSalary;

    Employee(int empId, String name, double basicPay) {
        this.empId = empId;
        this.name = name;
        this.basicPay = basicPay;
        calculateSalary();
    }

    void calculateSalary() {
        hra = 0.20 * basicPay;
        da = 0.10 * basicPay;
        grossSalary = basicPay + hra + da;
        tax = 0.10 * grossSalary;
        netSalary = grossSalary - tax;
    }

    String getDetails() {
        return "-----------------------------\n"
                + "Employee ID: " + empId + "\n"
                + "Employee Name: " + name + "\n"
                + String.format("Basic Pay: %.2f\n", basicPay)
                + String.format("HRA: %.2f\n", hra)
                + String.format("DA: %.2f\n", da)
                + String.format("Gross Salary: %.2f\n", grossSalary)
                + String.format("Tax Deducted: %.2f\n", tax)
                + String.format("Net Salary: %.2f\n", netSalary)
                + "\n";
    }
}

public class EmployPayrollSystem extends JFrame implements ActionListener {
    JTextField idField, nameField, basicField;
    JTextArea outputArea;
    JButton addButton, clearButton;
    java.util.List<Employee> employeeList = new java.util.ArrayList<>();

    public EmployPayrollSystem() {
        setTitle("Employ Payroll System");
        setSize(600, 600); // Increased window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Employee Details"));

        inputPanel.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Basic Pay:"));
        basicField = new JTextField();
        inputPanel.add(basicField);

        addButton = new JButton("Add Employee");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        clearButton = new JButton("Clear All");
        clearButton.addActionListener(e -> {
            employeeList.clear();
            outputArea.setText("");
        });
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        // Output Area - enlarged
        outputArea = new JTextArea(20, 50); // Increased size
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Bigger font
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(550, 350)); // Bigger scroll area
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double basic = Double.parseDouble(basicField.getText());

            Employee emp = new Employee(id, name, basic);
            employeeList.add(emp);

            outputArea.append(emp.getDetails());

            idField.setText("");
            nameField.setText("");
            basicField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric ID and basic pay.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new EmployPayrollSystem();
    }
}
