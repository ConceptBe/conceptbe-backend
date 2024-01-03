package kr.co.conceptbe.skill.application;

import java.util.List;
import kr.co.conceptbe.skill.application.dto.FindMainSkillResponse;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillCategoryRepository skillCategoryRepository;

    public List<FindMainSkillResponse> getMainSkills() {
         return skillCategoryRepository.findMainSkills().stream()
             .map(skill -> new FindMainSkillResponse(skill.getId(), skill.getName()))
             .toList();
    }
}
