package com.hegazy.ssecuritypart26.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationEvents {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationEvents.class);
    
    @EventListener
    public void onFailure(AuthorizationDeniedEvent event) {
        logger.error("Authorization failed for user: {}, due to: {}", 
            event.getAuthentication().get().getName(),
            event.getAuthorizationDecision().toString());
    }


}
