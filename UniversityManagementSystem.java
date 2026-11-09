import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Main GUI for the Project */
public class UniversityManagementSystem extends JFrame {

    private final Map<String, Student> students = new LinkedHashMap<>();
    private final List<Course> catalog = new ArrayList<>();

    // Add Student tab
    private final JComboBox<String> typeBox = new JComboBox<>(new String[]{"Undergraduate (BS)", "MS", "PhD"});
    private final JTextField lastNameField = new JTextField(18);
    private final JTextField firstNameField = new JTextField(18);
    private final JTextField phoneField = new JTextField(18);
    private final JTextField idField = new JTextField(18);
    private final JTextField addressField = new JTextField(18);
    private final JLabel addStatus = new JLabel(" ");

    // Register tab
    private final JTextField regIdField = new JTextField(12);
    private final JComboBox<Course> courseBox = new JComboBox<>();
    private final JTextField gradeField = new JTextField(4);
    private final JTextField projectField = new JTextField(22);
    private final JTextField dissertationField = new JTextField(22);
    private final JTextArea regOutput = new JTextArea(12, 40);

    // View tab
    private final JTextField viewIdField = new JTextField(12);
    private final JTextArea viewOutput = new JTextArea(14, 40);

    // Courses tab
    private final JTextArea courseOutput = new JTextArea(14, 40);

    public UniversityManagementSystem() {
        super("General University Management System");
        loadCatalog();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Add Student", buildAddPanel());
        tabs.addTab("Register", buildRegisterPanel());
        tabs.addTab("View Student", buildViewPanel());
        tabs.addTab("Courses", buildCoursesPanel());
        add(tabs);
        pack();
        setLocationRelativeTo(null);
    }

    private void loadCatalog() {
        catalog.add(new Course("COMP 121", "Freshman Colq.", 1));
        catalog.add(new Course("COMP 163", "Computer Science I", 3));
        catalog.add(new Course("COMP 167", "Computer Science I", 3));
        catalog.add(new Course("COMP 180", "Discrete Structures", 3));
         catalog.add(new Course("COMP 200", "Sophmore Colq.", 1));
        catalog.add(new Course("COMP 280", "Data Structures", 3));
        catalog.add(new Course("COMP 285", "Computer Science I", 3));
         catalog.add(new Course("COMP 300", "Junior Colq.", 1));
        catalog.add(new Course("COMP 320", "Cybersecurity Fundementals", 3));
        catalog.add(new Course("COMP 322", "Internet Systems", 3));
        catalog.add(new Course("COMP 167", "Operating Systems", 3));
        catalog.add(new Course("COMP 365", "AI/ML", 3));
        catalog.add(new Course("COMP 410", "Software Engineering", 3));
        catalog.add(new Course("COMP 610", "Advanced Algorithms", 3));
        catalog.add(new Course("COMP 720", "Network Security", 3));
        catalog.add(new Course("MATH 131", "Calculus I", 3));
        catalog.add(new Course("MATH 132", "Calculus II", 3));
        catalog.add(new Course("MATH 251", "Statistics & Probability", 3));
        catalog.add(new Course("MATH 351", "Linear Algebra", 3));

        for (Course c : catalog) courseBox.addItem(c);
    }

    // ---------- Panels ----------

    private JPanel buildAddPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 4, 4, 4);
        g.anchor = GridBagConstraints.WEST;

        String[] labels = {"Student type", "Last name", "First name", "Phone", "ID", "Address"};
        JComponent[] fields = {typeBox, lastNameField, firstNameField, phoneField, idField, addressField};
        for (int i = 0; i < labels.length; i++) {
            g.gridx = 0; g.gridy = i;
            p.add(new JLabel(labels[i]), g);
            g.gridx = 1;
            p.add(fields[i], g);
        }
        JButton add = new JButton("Add Student");
        add.addActionListener(e -> addStudent());
        g.gridx = 1; g.gridy = labels.length;
        p.add(add, g);
        g.gridy = labels.length + 1;
        p.add(addStatus, g);
        return p;
    }

    private JPanel buildRegisterPanel() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(3, 3, 3, 3);
        g.anchor = GridBagConstraints.WEST;

        g.gridx = 0; g.gridy = 0; top.add(new JLabel("Student ID"), g);
        g.gridx = 1; top.add(regIdField, g);

        g.gridx = 0; g.gridy = 1; top.add(new JLabel("Course"), g);
        g.gridx = 1; top.add(courseBox, g);
        JButton reg = new JButton("Register Course");
        reg.addActionListener(e -> registerCourse());
        g.gridx = 2; top.add(reg, g);

        g.gridx = 0; g.gridy = 2; top.add(new JLabel("Grade (A-F)"), g);
        g.gridx = 1; top.add(gradeField, g);
        JButton grade = new JButton("Set Grade");
        grade.addActionListener(e -> setGrade());
        g.gridx = 2; top.add(grade, g);

        g.gridx = 0; g.gridy = 3; top.add(new JLabel("MS project title"), g);
        g.gridx = 1; top.add(projectField, g);
        JButton proj = new JButton("Register Project");
        proj.addActionListener(e -> registerProject());
        g.gridx = 2; top.add(proj, g);

        g.gridx = 0; g.gridy = 4; top.add(new JLabel("PhD dissertation"), g);
        g.gridx = 1; top.add(dissertationField, g);
        JButton diss = new JButton("Register Dissertation");
        diss.addActionListener(e -> registerDissertation());
        g.gridx = 2; top.add(diss, g);

        regOutput.setEditable(false);
        regOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        p.add(top, BorderLayout.NORTH);
        p.add(new JScrollPane(regOutput), BorderLayout.CENTER);
        return p;
    }

    private JPanel buildViewPanel() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Student ID"));
        top.add(viewIdField);
        JButton view = new JButton("View Information");
        view.addActionListener(e -> viewStudent());
        top.add(view);
        JButton all = new JButton("List All Students");
        all.addActionListener(e -> listStudents());
        top.add(all);
        viewOutput.setEditable(false);
        viewOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        p.add(top, BorderLayout.NORTH);
        p.add(new JScrollPane(viewOutput), BorderLayout.CENTER);
        return p;
    }

    private JPanel buildCoursesPanel() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton avail = new JButton("Show Available Courses");
        avail.addActionListener(e -> showAvailableCourses());
        top.add(avail);
        JButton cur = new JButton("Show Registered Courses (ID from Register tab)");
        cur.addActionListener(e -> showRegisteredCourses());
        top.add(cur);
        courseOutput.setEditable(false);
        courseOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        p.add(top, BorderLayout.NORTH);
        p.add(new JScrollPane(courseOutput), BorderLayout.CENTER);
        return p;
    }

    // ---------- Actions ----------

    private void addStudent() {
        String last = lastNameField.getText().trim();
        String first = firstNameField.getText().trim();
        String phone = phoneField.getText().trim();
        String id = idField.getText().trim();
        String addr = addressField.getText().trim();

        if (last.isEmpty() || first.isEmpty() || id.isEmpty()) {
            addStatus.setText("Last name, first name and ID are required.");
            return;
        }
        if (students.containsKey(id)) {
            addStatus.setText("A student with ID " + id + " already exists.");
            return;
        }
        Student s;
        switch (typeBox.getSelectedIndex()) {
            case 1:  s = new MSStudent(last, first, phone, id, addr); break;
            case 2:  s = new PhDStudent(last, first, phone, id, addr); break;
            default: s = new UndergraduateStudent(last, first, phone, id, addr);
        }
        students.put(id, s);
        addStatus.setText("Added " + s.getType() + " student " + first + " " + last + " (ID " + id + ").");
        lastNameField.setText("");
        firstNameField.setText("");
        phoneField.setText("");
        idField.setText("");
        addressField.setText("");
    }

    private Student lookup(String id, JTextArea out) {
        Student s = students.get(id.trim());
        if (s == null) out.setText("No student found with ID '" + id.trim() + "'.");
        return s;
    }

    private void registerCourse() {
        Student s = lookup(regIdField.getText(), regOutput);
        if (s == null) return;
        Course c = (Course) courseBox.getSelectedItem();
        if (c == null) return;
        String note = s.registerCourse(c)
                ? "Registered for " + c.getCode() + ".\n\n"
                : "Already registered for " + c.getCode() + ".\n\n";
        regOutput.setText(note + s.registrationSummary());
    }

    private void setGrade() {
        Student s = lookup(regIdField.getText(), regOutput);
        if (s == null) return;
        Course c = (Course) courseBox.getSelectedItem();
        if (c == null) return;
        String note = s.setGrade(c.getCode(), gradeField.getText())
                ? "Grade recorded for " + c.getCode() + ".\n\n"
                : "Could not set grade. Student must be registered for the course and grade must be A/A-, B+/B/B-, C+/C/C-, D+/D/D- or F.\n\n";
        regOutput.setText(note + s.registrationSummary());
    }

    private void registerProject() {
        Student s = lookup(regIdField.getText(), regOutput);
        if (s == null) return;
        String title = projectField.getText().trim();
        if (!(s instanceof MSStudent)) {
            regOutput.setText("Undergraduate students can only register for courses.");
        } else if (title.isEmpty()) {
            regOutput.setText("Enter a project title.");
        } else {
            ((MSStudent) s).registerProject(title);
            regOutput.setText("MS project registered.\n\n" + s.registrationSummary());
        }
    }

    private void registerDissertation() {
        Student s = lookup(regIdField.getText(), regOutput);
        if (s == null) return;
        String title = dissertationField.getText().trim();
        if (!(s instanceof PhDStudent)) {
            regOutput.setText("Only PhD students can register for a dissertation.");
        } else if (title.isEmpty()) {
            regOutput.setText("Enter a dissertation title.");
        } else {
            ((PhDStudent) s).registerDissertation(title);
            regOutput.setText("Dissertation registered.\n\n" + s.registrationSummary());
        }
    }

    private void viewStudent() {
        Student s = lookup(viewIdField.getText(), viewOutput);
        if (s != null) viewOutput.setText(s.getDetails());
    }

    private void listStudents() {
        if (students.isEmpty()) {
            viewOutput.setText("No students have been added yet.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Student s : students.values()) {
            sb.append(s.getId()).append("  ").append(s.getLastName()).append(", ")
              .append(s.getFirstName()).append("  [").append(s.getType()).append("]\n");
        }
        viewOutput.setText(sb.toString());
    }

    private void showAvailableCourses() {
        StringBuilder sb = new StringBuilder("Available courses:\n");
        for (Course c : catalog) sb.append("  ").append(c).append("\n");
        courseOutput.setText(sb.toString());
    }

    private void showRegisteredCourses() {
        Student s = lookup(regIdField.getText(), courseOutput);
        if (s == null) {
            courseOutput.append("\nEnter the student ID on the Register tab first.");
            return;
        }
        courseOutput.setText(s.registrationSummary());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UniversityManagementSystem().setVisible(true));
    }
}
