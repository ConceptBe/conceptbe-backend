package kr.co.conceptbe.idea.service;

import static kr.co.conceptbe.common.entity.utils.CommonResponse.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.common.entity.utils.CommonResponse;
import kr.co.conceptbe.comment.dto.CommentResponse;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.repository.IdeaRepository;
import kr.co.conceptbe.idea.Idea;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdeaService {

	private final IdeaRepository ideaRepository;

	public ResponseEntity<CommonResponse.SingleResponse<IdeaDetailResponse>> getDetailIdeaResponse(Long ideaId) {
		Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> new IllegalArgumentException("Not Found ID : " + ideaId));
		IdeaDetailResponse ideaDetailResponse = IdeaDetailResponse.builder()
			.imageUrl(idea.getCreator().getProfileImageUrl())
			.nickname(idea.getCreator().getNickname())
			.skillList(idea.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList())
			.title(idea.getTitle())
			.date(timeTranslate(idea.getCreatedAt()))
			.introduce(idea.getIntroduce())
			.branchList(idea.getBranches().stream().map(e -> e.getBranch().getName()).toList())
			.purposeList(idea.getPurposes().stream().map(e -> e.getPurpose().getName()).toList())
			.cooperationWay(idea.getCooperationWay())
			.recruitmentPlace(idea.getRecruitmentPlace())
			.teamRecruitmentsList(idea.getTeamRecruitments().stream().map(e -> e.getTeamRecruitment().getName()).toList())
			.build();

		List<CommentResponse.CommentParentResponse> list = new ArrayList<>();
		for (Comment comment : idea.getComments()) {
			int likesCnt = comment.getCommentLikes().size();
			int commentCnt = comment.getComments().size();

			CommentResponse.CommentParentResponse commentResponse = CommentResponse.CommentParentResponse.builder()
				.nickname(comment.getCreator().getNickname())
				.memberSkillList(comment.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList())
				.content(comment.getContent())
				.build();

			commentResponse.setLikesCount(likesCnt > 999 ? "999+" : String.valueOf(likesCnt));
			if(commentCnt > 999) {
				commentResponse.setCommentCount("999+");
			} else if(commentCnt == 0) {
				commentResponse.setCommentCount("댓글 작성");
			} else {
				commentResponse.setCommentCount(String.valueOf(commentCnt));
			}

			list.add(commentResponse);
		}

		return toSingleResponse("게시글 상세 조회", HttpStatus.OK.value(), ideaDetailResponse);
	}

	public static String timeTranslate(LocalDateTime targetTime) {
		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedNow = now.format(formatter);
		String formattedTarget = targetTime.format(formatter);

		// 오늘인 경우
		if(formattedNow.equals(formattedTarget)) {
			long gap = ChronoUnit.MINUTES.between(targetTime, LocalDateTime.now());
			String word;
			if (gap == 0){
				word = "방금 전";
			}else if (gap < 60) {
				word = gap + "분 전";
			}else {
				word = (gap/60) + "시간 전";
			}
			return word;
		} else {
			return formattedTarget;
		}
	}

}
