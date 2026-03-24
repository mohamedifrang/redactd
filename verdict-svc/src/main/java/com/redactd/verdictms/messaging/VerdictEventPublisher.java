package com.redactd.verdictms.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.redactd.verdictms.bean.Verdict;
import com.redactd.verdictms.eventDTO.VerdictCreatedEvent;
import com.redactd.verdictms.eventDTO.VerdictDeletedEvent;
import com.redactd.verdictms.eventDTO.VerdictUpdatedEvent;

@Service
public class VerdictEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(VerdictEventPublisher.class);

    public VerdictEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishVerdictCreatedEvent(Verdict Verdict) {
        VerdictCreatedEvent event = new VerdictCreatedEvent(
                Verdict.getId(),
                Verdict.getDecision(),
                Verdict.getPlatformId(),
                Verdict.getContentId());
        logger.info("Publishing Verdict created event: {}", event);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.VERDICT_EXCHANGE, RabbitMQConfiguration.VERDICT_CREATED_KEY, event, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
    }

    public void publishVerdictUpdatedEvent(Verdict Verdict, String oldDecision) {
        VerdictUpdatedEvent event = new VerdictUpdatedEvent(
                Verdict.getId(),
                oldDecision,
                Verdict.getDecision(),
                Verdict.getPlatformId());
        logger.info("Publishing Verdict updated event: {}", event);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.VERDICT_EXCHANGE, RabbitMQConfiguration.VERDICT_UPDATED_KEY, event, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
    }

    public void publishVerdictDeletedEvent(Verdict Verdict) {
        VerdictDeletedEvent event = new VerdictDeletedEvent(Verdict.getId(), Verdict.getPlatformId(), Verdict.getDecision());
        logger.info("Publishing Verdict deleted event: {}", event);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.VERDICT_EXCHANGE, RabbitMQConfiguration.VERDICT_DELETED_KEY, event, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
    }
}







