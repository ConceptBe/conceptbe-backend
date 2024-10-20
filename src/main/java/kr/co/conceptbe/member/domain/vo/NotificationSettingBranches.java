package kr.co.conceptbe.member.domain.vo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class NotificationSettingBranches {

    private static final int NOTIFICATION_SETTING_PURPOSES_SIZE_LOWER_BOUND_INCLUSIVE = 1;

    @OneToMany(
            mappedBy = "notificationSetting",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<NotificationSettingBranch> notificationSettingBranches;

    private NotificationSettingBranches(List<NotificationSettingBranch> notificationSettingBranches) {
        this.notificationSettingBranches = notificationSettingBranches;
    }

}
