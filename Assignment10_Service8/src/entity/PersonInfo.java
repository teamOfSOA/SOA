package entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"name", "gender", "age", "departmentList"})
public class PersonInfo {

    private String name;
    private String gender;
    private int age;
    private DepartmentList departmentList;

    public PersonInfo() {
    }

    public PersonInfo(String name, String gender, int age, DepartmentList departmentList) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.departmentList = departmentList;
    }

    @XmlElement(name = "姓名", namespace = "http://www.nju.edu.cn/schema")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "性别", namespace = "http://www.nju.edu.cn/schema")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlElement(name = "年龄", namespace = "http://www.nju.edu.cn/schema")
    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement(name = "所在部门列表", namespace = "http://www.nju.edu.cn/schema")
    public void setDepartmentList(DepartmentList departmentList) {
        this.departmentList = departmentList;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public DepartmentList getDepartmentList() {
        return departmentList;
    }

}
