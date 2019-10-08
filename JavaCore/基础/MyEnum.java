package 基础;

import java.util.Random;

public enum MyEnum {
    ONE(1,"one"),TWO(2,"two"),THREE(3,"three");
    private int code;
    private String message;

    MyEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public static void main(String[] args) {
        for(MyEnum it:MyEnum.values()) {
            System.out.println(it.message);
        }
        System.out.println(MyEnum.valueOf("ONE").message);
        System.out.println(MyEnum.ONE.ordinal());
        switch (THREE) {
            case ONE: System.out.println("one");break;
            case TWO: System.out.println("two");break;
            case THREE: System.out.println("three");break;
            default: System.out.println("unknow");
        }
    }


}
