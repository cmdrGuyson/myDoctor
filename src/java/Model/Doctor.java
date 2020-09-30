package Model;

public abstract class Doctor {

    private int doctorID;
    private String firstName, lastName, contactNumber, imageURL;
    
    public Doctor() {
        //NULL
    }
    
    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    //Abstract methods will be overriden by child classes
    public abstract boolean isChildDoctor();
    
    public abstract void setChildDoctor(boolean childDoctor);
    
    public abstract String getSpecialization();
    
    public abstract void setSpecialization(String specialization);
}
