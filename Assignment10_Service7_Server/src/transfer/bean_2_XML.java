package transfer;
import entity.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class bean_2_XML {
    public void write(SortScoreList sortScoreList, ArrayList<ClassScore> classScoresToAdd, String address) throws JAXBException, IOException {
        sortScoreList.setClassScores(classScoresToAdd);
        JAXBContext context = JAXBContext.newInstance(SortScoreList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File(address);
        if(!file.exists()){
            file.createNewFile();
        }
        marshaller.marshal(sortScoreList, file);
    }
}
