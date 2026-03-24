package com.redactd.verdictms.messaging;

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

    public static final String PLATFORM_DEACTIVATED_QUEUE_VERDICTS = "platform.deactivated.queue.verdicts";

    @Bean
    DirectExchange verdictExchange() {
        return new DirectExchange(VERDICT_EXCHANGE);
    }

    @Bean
    DirectExchange platformExchange() {
        return new DirectExchange(PLATFORM_EXCHANGE);
    }

    @Bean
    public Queue platformDeactivatedQueueVerdicts() {
        return new Queue(PLATFORM_DEACTIVATED_QUEUE_VERDICTS, true);
    }

    @Bean
    Binding platformDeactivatedBindingVerdicts(final Queue platformDeactivatedQueueVerdicts, final DirectExchange platformExchange) {
        return BindingBuilder
                .bind(platformDeactivatedQueueVerdicts)
                .to(platformExchange)
                .with(PLATFORM_DEACTIVATED_KEY);
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







