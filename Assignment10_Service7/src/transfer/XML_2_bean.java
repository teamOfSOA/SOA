package transfer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.*;

public class XML_2_bean {
    public ArrayList read(String address) throws JAXBException, IOException {
        JAXBContext context1 = JAXBContext.newInstance(SortScoreList.class);
        Unmarshaller unmarshaller = context1.createUnmarshaller();
        InputStream stream=new FileInputStream(new File(address));
//        InputStream stream = XML_2_bean.class.getClassLoader().getResourceAsStream(address);
        SortScoreList s=(SortScoreList)unmarshaller.unmarshal(stream);
        ArrayList<ClassScore> l=s.getClassScores();
        return l;
    }
}
