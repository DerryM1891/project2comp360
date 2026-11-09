/** PhD student. Inherits courses and MS project, and adds a dissertation. */
public class PhDStudent extends MSStudent {
    private String dissertationTitle = "";

    public PhDStudent(String lastName, String firstName, String phone, String id, String address) {
        super(lastName, firstName, phone, id, address);
    }

    public void registerDissertation(String title) { this.dissertationTitle = title; }

    public String getDissertationTitle() { return dissertationTitle; }

    @Override
    public String getType() { return "Doctoral (PhD)"; }

    @Override
    public String getDetails() {
        return super.getDetails()
                + "PhD dissertation: " + (dissertationTitle.isEmpty() ? "(not registered)" : dissertationTitle) + "\n";
    }

    @Override
    public String registrationSummary() {
        return super.registrationSummary()
                + "PhD dissertation: " + (dissertationTitle.isEmpty() ? "(not registered)" : dissertationTitle) + "\n";
    }
}
