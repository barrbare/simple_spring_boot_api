package ge.ibsu.demo.dto;

public class AddCustomer {

    private String firstName;
    private String lastName;
    private AddAddress address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddAddress getAddress() {
        return address;
    }

    public void setAddress(AddAddress address) {
        this.address = address;
    }
}
