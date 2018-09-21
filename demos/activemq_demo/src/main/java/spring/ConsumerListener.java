package spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {
                System.out.println("QueueMessageListener监听到了文本消息：\t"
                        + tm.getText());
                message.acknowledge();
                //do something ...
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

}