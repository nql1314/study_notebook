package spring;

import javax.jms.JMSException;

public class Consumer {
    private int num;

    public void consume(MyMessage message) throws JMSException {
        System.out.println(num+"号消费"+message);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}