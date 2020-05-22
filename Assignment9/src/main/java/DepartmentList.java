import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class DepartmentList {
    ArrayList<String> departmentIds;

    public DepartmentList() {
    }

    public DepartmentList(ArrayList<String> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @XmlElement(name = "部门", namespace = "http://www.nju.edu.cn/schema")
    public void setDepartmentIds(ArrayList<String> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public ArrayList<String> getDepartmentIds() {
        return departmentIds;
    }
}
