package spring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActiveMqDemo {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-amq.xml");
        Producer producer = (Producer) context.getBean("producer");
        for(int i=0;i<10;i++){
            MyMessage myMessage = new MyMessage();
            System.out.println("生产第"+i+"条消息！");
            myMessage.setText("第"+i+"条消息！");
            producer.sendMessage(myMessage);
        }
    }
}