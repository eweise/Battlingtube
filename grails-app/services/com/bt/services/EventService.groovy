package com.bt.services

import com.battlingtube.domain.BattlingTubeEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import com.battlingtube.domain.BattlingTubeEvent

class EventService implements ApplicationEventPublisherAware {

    ApplicationEventPublisher applicationEventPublisher


    public void sendEvent(BattlingTubeEvent event) {
        try {
            log.debug("publishing event ${event}")
            Thread.start {
              applicationEventPublisher.publishEvent event
            }
        } catch (Throwable t) {
            log.error(t)
        }
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher
    }

  
}
