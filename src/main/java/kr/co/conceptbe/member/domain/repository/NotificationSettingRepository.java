package kr.co.conceptbe.member.domain.repository;

import kr.co.conceptbe.member.domain.IdeaNotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationSettingRepository extends JpaRepository<IdeaNotificationSetting, Long> {
}
