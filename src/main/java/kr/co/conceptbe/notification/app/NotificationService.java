package kr.co.conceptbe.notification.app;

import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.event.CreatedIdeaEvent;
import kr.co.conceptbe.member.domain.IdeaNotificationSetting;
import kr.co.conceptbe.member.domain.repository.NotificationSettingRepository;
import kr.co.conceptbe.member.exception.NotFoundAuthCredentialException;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.notification.app.dto.NotificationResponse;
import kr.co.conceptbe.notification.domain.IdeaNotification;
import kr.co.conceptbe.notification.domain.NotificationTrigger;
import kr.co.conceptbe.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MemberRepository memberRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationSettingRepository notificationSettingRepository;
    private final NotificationTrigger notificationTrigger;

    @Transactional(readOnly = true)
    public List<NotificationResponse> findAllNotifications(
            AuthCredentials authCredentials,
            Long cursorId,
            Long limit
    ) {
        if (!memberRepository.existsById(authCredentials.id())) {
            throw new NotFoundAuthCredentialException(authCredentials.id());
        }

        return notificationRepository.findAllNotifications(authCredentials.id(), cursorId, limit)
                .stream()
                .map(NotificationResponse::from)
                .toList();
    }

    @Transactional
    public void createNotificationByCreateIdeaEvent(CreatedIdeaEvent createdIdeaEvent) {
        Idea idea = createdIdeaEvent.idea();
        List<IdeaNotificationSetting> notificationSettings = notificationSettingRepository.findAll();

        List<IdeaNotification> ideaNotifications = notificationTrigger.getNotificationByCreatedIdeaEvent(idea, notificationSettings);
        notificationRepository.saveAll(ideaNotifications);
    }

}
