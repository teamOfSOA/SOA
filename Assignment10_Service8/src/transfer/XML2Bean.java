package transfer;

import entity.SortScoreList;
import entity.Student;
import entity.StudentList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XML2Bean {
    public ArrayList<Student> read(String address) throws JAXBException, IOException {
        JAXBContext context1 = JAXBContext.newInstance(StudentList.class);
        Unmarshaller unmarshaller = context1.createUnmarshaller();
        InputStream stream=new FileInputStream(new File(address));
//        InputStream stream = XML_2_bean.class.getClassLoader().getResourceAsStream(address);
        StudentList studentList=(StudentList)unmarshaller.unmarshal(stream);
        return studentList.getStudents();
    }
}
