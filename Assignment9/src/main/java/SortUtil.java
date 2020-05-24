import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SortUtil {

    private ArrayList<Student> students = new ArrayList<>();

    private void xml2Java() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(StudentList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream stream = TransformUtil.class.getClassLoader().getResourceAsStream("StudentList.xml");
        StudentList studentList = (StudentList) unmarshaller.unmarshal(stream);
        students = studentList.getStudents();
    }

    public void trans() throws JAXBException, IOException {
        xml2Java();
        SortScoreList sortScoreList = new SortScoreList();
        ArrayList<ClassScore> classScoresToAdd = new ArrayList<>();
        for(Student student:students){
            ArrayList<ClassScore> scores = student.getPersonalScoreList().getScoreList().getClassScores();
            ArrayList<ClassScore> generalScore = new ArrayList<>();
            for(ClassScore classScore:scores){
                if(classScore.getScoreAttribute().equals("总评成绩")){
                    generalScore.add(classScore);
                }
            }
            generalScore.sort((o1, o2) -> o1.getScore().getGrade()-o2.getScore().getGrade());
            for(ClassScore c_score:generalScore){
                for(int i=3;i<scores.size();i+=4){
                    if(scores.get(i).getClassId().equals(c_score.getClassId()) && scores.get(i).getScore().getGrade()==c_score.getScore().getGrade()){
                        classScoresToAdd.add(scores.get(i-3));
                        classScoresToAdd.add(scores.get(i-2));
                        classScoresToAdd.add(scores.get(i-1));
                        classScoresToAdd.add(scores.get(i));
                    }
                }
            }
        }
        //打印结果
        /*for(ClassScore classScore:classScoresToAdd){
            if(classScore.getScoreAttribute().equals("总评成绩")) System.out.println(classScore.getScore().getStudentId()+":"+classScore.getScoreAttribute()+":"+classScore.getScore().getGrade());
        }*/
        sortScoreList.setClassScores(classScoresToAdd);
        JAXBContext context = JAXBContext.newInstance(SortScoreList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("src\\main\\resources\\xml3.xml");
        if(!file.exists())
            file.createNewFile();
        marshaller.marshal(sortScoreList, file);
//        PrintStream out = System.out;
//        marshaller.marshal(stu, out);
    }
}
