import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SelectUtil {
    public void select() throws JAXBException, IOException {
//        解析xml3，读取到s中，其实不该用SortScoreList这个类，而应该自己重写一个,借用了前面两个同学写的类
        JAXBContext context1 = JAXBContext.newInstance(SortScoreList.class);
        Unmarshaller unmarshaller = context1.createUnmarshaller();
        InputStream stream = SelectUtil.class.getClassLoader().getResourceAsStream("xml3.xml");
        SortScoreList s=(SortScoreList)unmarshaller.unmarshal(stream);
//        从l中筛选不及格的成绩到result中
        ArrayList<ClassScore> l=s.getClassScores();
        ArrayList<ClassScore> result=new ArrayList<>();
        for(int i=0;i<l.size();i++){
            if(l.get(i).getScore().getGrade()<60){
                switch(i%4){
                    case 0:
                        for(int j=0;j<=3;j++){
                            result.add(l.get(i+j));
                        }
                        i+=4;
                        break;
                    case 1:
                        for(int j=-1;j<=2;j++){
                            result.add(l.get(i+j));
                        }
                        i+=3;
                        break;
                    case 2:
                        for(int j=-2;j<=1;j++){
                            result.add(l.get(i+j));
                        }
                        i+=2;
                        break;
                    case 3:
                        for(int j=-3;j<0;j++){
                            result.add(l.get(i+j));
                        }
                        i+=1;
                        break;
                }
            }
        }
//        再根据bean对象生成xml4
        s.setClassScores(result);
        JAXBContext context2 = JAXBContext.newInstance(SortScoreList.class);
        Marshaller marshaller = context2.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("src\\main\\resources\\xml4.xml");
        if(!file.exists())
            file.createNewFile();
        marshaller.marshal(s, file);
    }
}
