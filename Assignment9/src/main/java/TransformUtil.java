import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class TransformUtil {

    String[] ids = {"161250188", "171250631", "171250565", "171250517", "171250696", "171250654", "171250661", "171250670", "171250573", "171250558", "171250025", "171250682", "171250001"};
    String[] names = {"余含章", "勇中坚", "夏汉仲", "张建榕", "陈凌晨", "徐志威", "殷承鉴", "罗民胜", "苑宇航", "杨日东", "濮宗悦", "章诚", "雷媛"};
    String[] genders = {"male", "male", "male", "male", "male", "male", "male", "male", "male", "male", "female", "male", "female"};
    int[] ages = {22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21};

    private void java2Xml(StudentList stu) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(StudentList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("src\\main\\resources\\xml2.xml");
        if(!file.exists())
            file.createNewFile();
        marshaller.marshal(stu, file);
//        PrintStream out = System.out;
//        marshaller.marshal(stu, out);
    }


    public void trans(){
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 0; i < 13; i++) {
            ArrayList<String> departments = new ArrayList<String>();
            departments.add("000000");
            departments.add("000001");
            DepartmentList departmentList = new DepartmentList(departments);
            PersonInfo personInfo = new PersonInfo(names[i], genders[i], ages[i], departmentList);
            StudentInfo studentInfo = new StudentInfo(personInfo);
            ArrayList<String> classes = new ArrayList<String>();
            classes.add("000000");
            classes.add("000001");
            classes.add("000002");
            classes.add("000003");
            classes.add("000004");
            ClassList classList = new ClassList(classes);
            String[] classids = {"000000", "000001", "000002", "000003", "000004"};
            String[] scoreAttributes = {"平时成绩", "作业成绩", "期末成绩", "总评成绩"};
            ArrayList<ClassScore> scores = new ArrayList<ClassScore>();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 4; k++) {
                    Random random = new Random();
                    int grade = random.nextInt(100);
                    scores.add(new ClassScore(new Score(ids[i], grade), classids[j], scoreAttributes[k]));
                }
            }
            ScoreList scoreList = new ScoreList(scores);
            PersonalScoreList personalScoreList = new PersonalScoreList(scoreList);
            Student student = new Student(ids[i], studentInfo, classList, personalScoreList);
            students.add(student);
        }
        StudentList studentList = new StudentList(students);
        try {
            try {
                java2Xml(studentList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
