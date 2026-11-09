/** Undergraduate (BS) student. Can only register for courses. */
public class UndergraduateStudent extends Student {

    public UndergraduateStudent(String lastName, String firstName, String phone, String id, String address) {
        super(lastName, firstName, phone, id, address);
    }

    @Override
    public String getType() { return "Undergraduate (BS)"; }
}
