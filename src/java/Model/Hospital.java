package Model;

public class Hospital {
    
    private int hospitalID;
    private String name, address;
    
    public Hospital(int hospitalID, String name, String address) {
        this.hospitalID = hospitalID;
        this.name = name;
        this.address = address;
    }

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
