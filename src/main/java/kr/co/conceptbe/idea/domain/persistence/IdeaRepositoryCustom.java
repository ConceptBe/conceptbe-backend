package kr.co.conceptbe.idea.domain.persistence;

import java.util.List;
import kr.co.conceptbe.idea.application.request.FilteringRequest;
import kr.co.conceptbe.idea.domain.Idea;
import org.springframework.data.domain.Pageable;

public interface IdeaRepositoryCustom {

    List<Idea> findAllByOrderByCreatedAtDesc(FilteringRequest filteringRequest, Pageable pageable);

    List<Idea> findAllByOrderByLikesDesc(FilteringRequest filteringRequest,Pageable pageable);

}
