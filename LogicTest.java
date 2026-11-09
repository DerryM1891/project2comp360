public class LogicTest {
    public static void main(String[] args) {
        Course a = new Course("COMP 280", "Data Structures", 3);
        Course b = new Course("COMP 365", "AI/ML", 3);
        Student bs = new UndergraduateStudent("Moss", "Derry", "555", "1", "addr");
        MSStudent ms = new MSStudent("Lee", "Ann", "555", "2", "addr");
        PhDStudent phd = new PhDStudent("Kim", "Joe", "555", "3", "addr");

    // Logic checks to insure inputs are taken in correctly
        check(bs.registerCourse(a), "register");
        check(!bs.registerCourse(a), "duplicate rejected");
        bs.registerCourse(b);
        check(bs.setGrade("COMP 280", "a"), "grade A");
        check(bs.setGrade("COMP 167", "c+"), "grade C+");
        check(bs.setGrade("COMP 365", "B"), "grade B");
        check(Math.abs(bs.getGPA() - 3.5) < 1e-9, "gpa 3.5");
        check(!bs.setGrade("COMP 999", "A"), "unregistered grade rejected");
        check(!bs.setGrade("COMP 280", "Z"), "bad grade rejected");

        ms.registerProject("Intrusion detection");
        phd.registerProject("Prelim study");
        phd.registerDissertation("LLM SOC triage");
        check(ms.getDetails().contains("Intrusion detection"), "ms project shown");
        check(phd.getDetails().contains("LLM SOC triage"), "phd dissertation shown");
        check(phd instanceof MSStudent && phd instanceof Student, "inheritance chain");
        check(!(bs instanceof MSStudent), "bs has no project");
        check(bs.registrationSummary().contains("Moss") && bs.registrationSummary().contains("COMP 280"), "summary");
        System.out.println("ALL TESTS PASSED");
    }

    static void check(boolean ok, String name) {
        if (!ok) throw new AssertionError("FAILED: " + name);
    }
}
