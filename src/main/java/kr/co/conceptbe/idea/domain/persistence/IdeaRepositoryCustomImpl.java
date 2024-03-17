package kr.co.conceptbe.idea.domain.persistence;

import static kr.co.conceptbe.idea.domain.QIdea.idea;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import kr.co.conceptbe.idea.application.request.FilteringRequest;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.vo.CooperationWay;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class IdeaRepositoryCustomImpl implements IdeaRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Idea> findAllByOrderByCreatedAtDesc(FilteringRequest filteringRequest,
        Pageable pageable) {
        return jpaQueryFactory.selectFrom(idea)
            .where(
                branchIdIn(filteringRequest.branchIds()),
                purposeIdIn(filteringRequest.purposeIds()),
                cooperationWayEqual(filteringRequest.cooperationWay()),
                recruitmentPlaceEqual(filteringRequest.recruitmentPlace()),
                skillCategoryIdIn(filteringRequest.skillCategoryIds())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(idea.createdAt.desc())
            .fetch();
    }

    public List<Idea> findAllByOrderByLikesDesc(FilteringRequest filteringRequest,
        Pageable pageable) {
        return jpaQueryFactory.selectFrom(idea)
            .where(
                branchIdIn(filteringRequest.branchIds()),
                purposeIdIn(filteringRequest.purposeIds()),
                cooperationWayEqual(filteringRequest.cooperationWay()),
                recruitmentPlaceEqual(filteringRequest.recruitmentPlace()),
                skillCategoryIdIn(filteringRequest.skillCategoryIds())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(idea.likes.size().desc())
            .fetch();
    }

    private BooleanExpression branchIdIn(List<Long> branchIds) {
        if (Objects.isNull(branchIds)) {
            return null;
        }

        return idea.branches
            .ideaBranches
            .any()
            .branch
            .id.in(branchIds);
    }

    private BooleanExpression purposeIdIn(List<Long> purposeIds) {
        if (Objects.isNull(purposeIds)) {
            return null;
        }

        return idea.purposes
            .ideaPurposes
            .any()
            .purpose
            .id.in(purposeIds);
    }

    private BooleanExpression cooperationWayEqual(CooperationWay cooperationWay) {
        if (Objects.isNull(cooperationWay) || cooperationWay == CooperationWay.NO_MATTER) {
            return null;
        }

        return idea.cooperationWay
            .eq(cooperationWay)
            .or(idea.cooperationWay.eq(CooperationWay.NO_MATTER));
    }

    private BooleanExpression recruitmentPlaceEqual(Long regionId) {
        if (Objects.isNull(regionId)) {
            return null;
        }

        return idea.recruitmentPlace
            .id.eq(regionId);
    }

    private BooleanExpression skillCategoryIdIn(List<Long> skillCategoryIdIn) {
        if (Objects.isNull(skillCategoryIdIn)) {
            return null;
        }

        return idea.skillCategories
            .ideaSkillCategories
            .any()
            .skillCategory
            .id.in(skillCategoryIdIn);
    }
}
