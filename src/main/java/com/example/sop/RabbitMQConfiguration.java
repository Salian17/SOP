package com.example.sop;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    static final String queueName = "firstQueue";
    static final String exchangeName = "testExchange";

    @Bean
    public Queue myQueue(){return new Queue(queueName,false);}
    @Bean
    Exchange exchange(){return new TopicExchange(exchangeName, false,false);}
    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("my.key").noargs();
    }

}
