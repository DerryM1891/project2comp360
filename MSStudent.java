/** MS student. Inherits course registration and adds an MS project. */
public class MSStudent extends UndergraduateStudent {
    private String projectTitle = "";

    public MSStudent(String lastName, String firstName, String phone, String id, String address) {
        super(lastName, firstName, phone, id, address);
    }

    public void registerProject(String title) { this.projectTitle = title; }

    public String getProjectTitle() { return projectTitle; }

    @Override
    public String getType() { return "Graduate (MS)"; }

    @Override
    public String getDetails() {
        return super.getDetails()
                + "MS project: " + (projectTitle.isEmpty() ? "(not registered)" : projectTitle) + "\n";
    }

    @Override
    public String registrationSummary() {
        return super.registrationSummary()
                + "MS project: " + (projectTitle.isEmpty() ? "(not registered)" : projectTitle) + "\n";
    }
}
