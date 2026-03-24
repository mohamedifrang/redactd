package com.redactd.platformms.messaging;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.redactd.platformms.eventDTO.PlatformDeactivatedEvent;

@Service
public class PlatformEventPublisher {

    private RabbitTemplate rabbitTemplate;

    public PlatformEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishPlatformDeactivatedEvent(Long platformId) {
        PlatformDeactivatedEvent event = new PlatformDeactivatedEvent(platformId);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.PLATFORM_EXCHANGE,
                                      RabbitMQConfiguration.PLATFORM_DEACTIVATED_KEY,
                                      event,
                                      message -> {
                                          message.getMessageProperties()
                                                  .setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                                          return message;
                                      });
    }

}







