package com.redactd.platformms.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String VERDICT_EXCHANGE = "verdict.exchange";
    public static final String PLATFORM_EXCHANGE = "platform.exchange";

    public static final String VERDICT_CREATED_KEY = "verdict.created";
    public static final String VERDICT_UPDATED_KEY = "verdict.updated";
    public static final String VERDICT_DELETED_KEY = "verdict.deleted";
    public static final String PLATFORM_DEACTIVATED_KEY = "platform.deactivated";

    public static final String VERDICT_CREATED_QUEUE = "verdict.created.queue.platforms";
    public static final String VERDICT_UPDATED_QUEUE = "verdict.updated.queue.platforms";
    public static final String VERDICT_DELETED_QUEUE = "verdict.deleted.queue.platforms";

    @Bean
    DirectExchange verdictExchange() {
        return new DirectExchange(VERDICT_EXCHANGE);
    }

    @Bean
    DirectExchange platformExchange() {
        return new DirectExchange(PLATFORM_EXCHANGE);
    }

    @Bean
    public Queue verdictCreatedQueue() {
        return new Queue(VERDICT_CREATED_QUEUE, true);
    }

    @Bean
    public Queue verdictUpdatedQueue() {
        return new Queue(VERDICT_UPDATED_QUEUE, true);
    }

    @Bean
    public Queue verdictDeletedQueue() {
        return new Queue(VERDICT_DELETED_QUEUE, true);
    }

    @Bean
    Binding verdictCreatedBinding(final Queue verdictCreatedQueue, final DirectExchange verdictExchange) {
        return BindingBuilder
                .bind(verdictCreatedQueue)
                .to(verdictExchange)
                .with(VERDICT_CREATED_KEY);
    }

    @Bean
    Binding verdictUpdatedBinding(final Queue verdictUpdatedQueue, final DirectExchange verdictExchange) {
        return BindingBuilder
                .bind(verdictUpdatedQueue)
                .to(verdictExchange)
                .with(VERDICT_UPDATED_KEY);
    }

    @Bean
    Binding verdictDeletedBinding(final Queue verdictDeletedQueue, final DirectExchange verdictExchange) {
        return BindingBuilder
                .bind(verdictDeletedQueue)
                .to(verdictExchange)
                .with(VERDICT_DELETED_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}







