import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class. Holds common student data and course registration.
 * Undergraduate students use this behavior directly through UndergraduateStudent.
 */
public class Student {
    private final String lastName;
    private final String firstName;
    private final String phone;
    private final String id;
    private final String address;

    // course code -> Course, and course code -> letter grade ("" if none yet)
    private final Map<String, Course> courses = new LinkedHashMap<>();
    private final Map<String, String> grades = new LinkedHashMap<>();

    public Student(String lastName, String firstName, String phone, String id, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.id = id;
        this.address = address;
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getPhone() { return phone; }
    public String getId() { return id; }
    public String getAddress() { return address; }

    /** Overridden by subclasses to report the student level. */
    public String getType() { return "Student"; }

    /** Returns false if the course is already registered. */
    public boolean registerCourse(Course c) {
        if (courses.containsKey(c.getCode())) return false;
        courses.put(c.getCode(), c);
        grades.put(c.getCode(), "");
        return true;
    }

    public List<Course> getRegisteredCourses() {
        return new ArrayList<>(courses.values());
    }

    /** Returns false if the student is not registered for the course or the grade is invalid. */
    public boolean setGrade(String courseCode, String grade) {
        String g = grade.trim().toUpperCase();
        if (!courses.containsKey(courseCode) || gradePoints(g) < 0) return false;
        grades.put(courseCode, g);
        return true;
    }

    public String getGrade(String courseCode) {
        String g = grades.get(courseCode);
        return (g == null || g.isEmpty()) ? "N/A" : g;
    }

    private static double gradePoints(String g) {
        switch (g) {
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return -1;
        }
    }

    /** Credit-weighted GPA over graded courses. Returns 0.0 if nothing is graded. */
    public double getGPA() {
        double points = 0;
        int credits = 0;
        for (Course c : courses.values()) {
            double p = gradePoints(grades.get(c.getCode()));
            if (p >= 0) {
                points += p * c.getCredits();
                credits += c.getCredits();
            }
        }
        return credits == 0 ? 0.0 : points / credits;
    }

    /** Header with name and ID, followed by registered courses. */
    public String registrationSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Last name: ").append(lastName).append("\n");
        sb.append("First name: ").append(firstName).append("\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Registered courses:\n");
        if (courses.isEmpty()) {
            sb.append("  (none)\n");
        } else {
            for (Course c : courses.values()) sb.append("  ").append(c).append("\n");
        }
        return sb.toString();
    }

    /** Full record. Subclasses extend this with their extra items. */
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(getType()).append("\n");
        sb.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Phone: ").append(phone).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append(String.format("GPA: %.2f%n", getGPA()));
        sb.append("Enrolled courses:\n");
        if (courses.isEmpty()) {
            sb.append("  (none)\n");
        } else {
            for (Course c : courses.values()) {
                sb.append("  ").append(c).append("  Grade: ").append(getGrade(c.getCode())).append("\n");
            }
        }
        return sb.toString();
    }
}
