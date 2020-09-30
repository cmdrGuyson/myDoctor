package Model;

public class User {

    private String email, password, firstName, lastName, contactNumber, NIC, type;

    //Used in registration
    public User(String email, String password, String firstName, String lastName, String contactNumber, String NIC) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.NIC = NIC;
        this.type = "user";
    }
    
    //Used in authentication
    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
    
    //Used in login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
 
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
