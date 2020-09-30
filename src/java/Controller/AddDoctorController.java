package Controller;

import Model.Doctor;
import Model.DoctorFactory;
import Model.HandleDoctor;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddDoctorController extends HttpServlet {

    private String filePath;
    private final int maxFileSize = 50 * 1024 * 1024;
    private final int maxMemSize = 4 * 1024;
    private File file;

    @Override
    public void init() {
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload-doctor-image");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        createDoctor(request, response);

    }

    private void createDoctor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Doctor doctor = null;
        String type = "";

        // Check that we have a file upload request
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            System.out.println(fileItems.size());

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    
                    String fieldName = fi.getFieldName();
                    String fieldValue = fi.getString();
                    
                    // set post object's values according to user input
                    switch (fieldName) {
                        case "type":
                            type = fieldValue;
                            doctor = DoctorFactory.createDoctor(type);
                            break;
                        case "firstName":
                            doctor.setFirstName(fieldValue);
                            break;
                        case "lastName":
                            doctor.setLastName(fieldValue);
                            break;
                        case "contactNumber":
                            doctor.setContactNumber(fieldValue);
                            break;
                        case "specialization":
                            if(type.equals("special")){
                                doctor.setSpecialization(fieldValue);
                            }
                            break;
                        case "isChildDoctor":
                            if(type.equals("general")){
                                boolean isChildDoc = (fieldValue.equals("yes"));
                                doctor.setChildDoctor(isChildDoc);
                            }
                        default:
                            break;
                    }
                    

                } else {

                    //Create File Name
                    Random random = new Random();
                    String fileName = String.format("doc-%d-%d-%s", random.nextInt(1000000000), random.nextInt(1000000000), fi.getName());

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    
                    //Set post image URL as the newly upload image
                    doctor.setImageURL(String.format("http://localhost:8080/\\myDoc\\%s", fileName));

                }
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        
        HandleDoctor handleDoctor = new HandleDoctor();
        //Add doctor to the system by passing the created doctor object after calling addDoctor() method
        handleDoctor.addDoctor(doctor);
        
        //Direct user to hospitals page
        response.sendRedirect("ViewAllDoctorsController");
    }
}
