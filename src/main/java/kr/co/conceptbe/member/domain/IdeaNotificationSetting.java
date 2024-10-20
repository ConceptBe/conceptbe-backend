package kr.co.conceptbe.member.domain;

import jakarta.persistence.*;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.domain.vo.CooperationWay;
import kr.co.conceptbe.member.domain.vo.NotificationSettingBranches;
import kr.co.conceptbe.member.domain.vo.NotificationSettingPurposes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_notification_setting_user_id",
                columnNames = {"user_id"}
        )
})
public class IdeaNotificationSetting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Embedded
    private NotificationSettingBranches branches;

    @Embedded
    private NotificationSettingPurposes purposes;

    @Enumerated(EnumType.STRING)
    private CooperationWay cooperationWay;

}
