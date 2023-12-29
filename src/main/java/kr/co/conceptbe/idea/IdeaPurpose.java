package kr.co.conceptbe.idea;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.common.entity.Purpose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class IdeaPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Purpose purpose;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    private IdeaPurpose(Purpose purpose, Idea idea) {
        this.purpose = purpose;
        this.idea = idea;
    }

    public static IdeaPurpose of(String purpose, Idea idea) {
        return new IdeaPurpose(Purpose.from(purpose), idea);
    }

}
