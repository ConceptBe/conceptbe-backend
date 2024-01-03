package kr.co.conceptbe.comment.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import kr.co.conceptbe.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record CommentResponse (
	String nickname,
	List<String> memberSkills,
	String content,
	Integer likesCount
) {
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(
			comment.getCreator().getNickname(),
			comment.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			comment.getContent(),
			comment.getLikesCount()
		);
	}
}
