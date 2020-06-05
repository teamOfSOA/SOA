package transfer;

import entity.Student;
import entity.StudentList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Bean2XML {
    public void write(StudentList studentList, ArrayList<Student> studentsToAdd, String address) throws JAXBException, IOException {
        studentList.setStudents(studentsToAdd);
        JAXBContext context = JAXBContext.newInstance(StudentList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File(address);
        if(!file.exists()){
            file.createNewFile();
        }
        marshaller.marshal(studentList, file);
    }
}
