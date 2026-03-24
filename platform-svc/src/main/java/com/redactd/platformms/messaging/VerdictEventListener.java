package com.redactd.platformms.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.redactd.platformms.eventDTO.VerdictCreatedEvent;
import com.redactd.platformms.eventDTO.VerdictDeletedEvent;
import com.redactd.platformms.eventDTO.VerdictUpdatedEvent;
import com.redactd.platformms.service.PlatformService;

@Service
public class VerdictEventListener {

    private PlatformService PlatformService;

    public VerdictEventListener(PlatformService PlatformService) {
        this.PlatformService = PlatformService;
    }

    @RabbitListener(queues = RabbitMQConfiguration.VERDICT_CREATED_QUEUE)
    public void handleVerdictCreatedEvent(VerdictCreatedEvent event) {
        PlatformService.updatePlatformRatingOnCreate(event);
    }

    @RabbitListener(queues = RabbitMQConfiguration.VERDICT_UPDATED_QUEUE)
    public void handleVerdictUpdatedEvent(VerdictUpdatedEvent event) {
        PlatformService.updatePlatformRatingOnUpdate(event);
    }

    @RabbitListener(queues = RabbitMQConfiguration.VERDICT_DELETED_QUEUE)
    public void handleVerdictDeletedEvent(VerdictDeletedEvent event) {
        PlatformService.updatePlatformRatingOnDelete(event);
    }

}







