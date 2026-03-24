package com.redactd.verdictms.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.redactd.verdictms.eventDTO.PlatformDeactivatedEvent;
import com.redactd.verdictms.service.VerdictService;

@Service
public class PlatformEventListener {

    private VerdictService VerdictService;

    public PlatformEventListener(VerdictService VerdictService) {
        this.VerdictService = VerdictService;
    }

    @RabbitListener(queues = RabbitMQConfiguration.PLATFORM_DEACTIVATED_QUEUE_VERDICTS)
    public void handlePlatformDeactivatedEvent(PlatformDeactivatedEvent event) {
        VerdictService.deleteByPlatformId(event.getPlatformId());
    }

}







