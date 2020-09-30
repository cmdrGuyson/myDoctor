package Model;

//Implements "Factory" design pattern and helps create Doctor objects
public class DoctorFactory {
    
    public static Doctor createDoctor(String type){
        
        //If user wants to create a General Doctor instantiate GeneralDoctor class object
        if(type.equals("general")){
            return new GeneralDoctor();
        }
        //If not instantiate the SpecializedDoctor class object
        else{
            return new SpecializedDoctor();
        }
    }
}
