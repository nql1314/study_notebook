package reflection.demo.field;

/**
 * from https://blog.csdn.net/sinat_38259539/article/details/71799078
 */
public class Student {
    public Student(){

    }
    //**********字段*************//
    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                + ", phoneNum=" + phoneNum + "]";
    }


}