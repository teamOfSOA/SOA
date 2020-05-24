import javax.xml.bind.JAXBException;
import java.io.IOException;

public class mainTrans {

    public static void main(String[] args) throws JAXBException, IOException {
        TransformUtil transformUtil = new TransformUtil();
        //transformUtil.trans();
        SortUtil sortUtil = new SortUtil();
        sortUtil.trans();
    }
}
