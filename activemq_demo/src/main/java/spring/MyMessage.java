package spring;


import java.io.Serializable;

public class MyMessage implements Serializable {
    private static final long serialVersionUID = -4360789240260037588L;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        return text;
    }
}