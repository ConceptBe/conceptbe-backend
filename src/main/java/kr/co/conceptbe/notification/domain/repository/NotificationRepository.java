package kr.co.conceptbe.notification.domain.repository;

import kr.co.conceptbe.notification.domain.IdeaNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<IdeaNotification, Long> {

    @Query(value = """
            SELECT n
            FROM notification n
            WHERE n.userId = :memberId and n.id < :cursorId
            LIMIT :limit
            """)
    List<IdeaNotification> findAllNotifications(Long memberId, Long cursorId, Long limit);

}
