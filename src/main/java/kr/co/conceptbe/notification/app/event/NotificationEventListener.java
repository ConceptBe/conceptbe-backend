package kr.co.conceptbe.notification.app.event;

import kr.co.conceptbe.idea.domain.event.CreatedIdeaEvent;
import kr.co.conceptbe.notification.app.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationService notificationService;

    @EventListener
    private void createIdea(CreatedIdeaEvent createdIdeaEvent) {
        notificationService.createNotificationByCreateIdeaEvent(createdIdeaEvent);
    }

}
