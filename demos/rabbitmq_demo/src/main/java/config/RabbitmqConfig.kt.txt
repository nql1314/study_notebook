package config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn


@EnableRabbit
@Configuration
@DependsOn("disconfMgrBean2")
class RabbitmqConfig{

    @Autowired
    private lateinit var disConfRabbitmq: DisconfRabbitmq

    @Bean("rabbitConnectionFactory")
    @Throws(Exception::class)
    fun connectionFactor(): ConnectionFactory {

        val factory = CachingConnectionFactory()
        factory.setHost(disConfRabbitmq.host)
        factory.setPort(disConfRabbitmq.port!!)
        factory.setUsername(disConfRabbitmq.username)
        factory.setPassword(disConfRabbitmq.password)
        factory.setVirtualHost(disConfRabbitmq.virtualHost)
        return factory
    }

    @Bean("disableUserQueue")
    fun disableUserQueue(): Queue {
        return Queue(disConfRabbitmq.exchange + Constants.DISABLEUSER_QUEUE, false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(disConfRabbitmq.exchange)
    }

    @Bean
    fun binding3(@Qualifier("disableUserQueue") disableUserQueue: Queue,
                 exchange: TopicExchange): Binding {
        return BindingBuilder.bind(disableUserQueue).to(exchange).with(Constants.DISABLEUSER_QUEUE)
    }

    @Bean("disableUserListenerAdapter")
    fun disableUserListenerAdapter(receiver: UserDisableReceiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }

    @Bean
    fun disableUserContainer(@Qualifier("rabbitConnectionFactory") connectionFactory: ConnectionFactory,
                            @Qualifier("disableUserListenerAdapter") listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(disConfRabbitmq.exchange + Constants.DISABLEUSER_QUEUE)
        container.messageListener = listenerAdapter
        return container
    }
    @Bean
    internal fun rabbitTemplate(@Qualifier("rabbitConnectionFactory") connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }
}