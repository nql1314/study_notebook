package spring;

import org.apache.activemq.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;


public class Producer {
    private JmsTemplate template;
    private Destination destination;

    public void sendMessage(final MyMessage message){
        template.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return (Message) session.createTextMessage(message.getText());
            }
        });
    }

    public JmsTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}