package kr.co.conceptbe.idea;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.common.entity.Branch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class IdeaBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    private IdeaBranch(Branch branch, Idea idea) {
        this.branch = branch;
        this.idea = idea;
    }

    public static IdeaBranch of(String branch, Idea idea) {
        return new IdeaBranch(Branch.from(branch), idea);
    }

}
