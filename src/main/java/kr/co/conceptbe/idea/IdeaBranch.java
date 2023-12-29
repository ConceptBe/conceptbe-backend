package kr.co.conceptbe.idea;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.common.entity.Branch;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class IdeaBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    private IdeaBranch(Idea idea, Branch branch) {
        this.idea = idea;
        this.branch = branch;
    }

    public static IdeaBranch of(Idea idea, Branch branch) {
        return new IdeaBranch(idea, branch);
    }

}
