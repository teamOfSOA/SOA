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
        
        for (int i = 0; i < l.size(); i += 4){
            boolean needRecord = false;
            for (int j = 0; j <= 3; j ++){
                if(l.get(i+j).getScore().getGrade()<60){ needRecord = true; break; }
            }
            if(needRecord){
                for(int j = 0; j <= 3; j ++) result.add(l.get(i+j));
            }
        }
        
//        再根据bean对象生成xml4
        SortUtil.sortScore(s, result, "src\\main\\resources\\xml4.xml");
    }
}
