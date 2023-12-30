package kr.co.conceptbe.common.exception;

import org.springframework.http.HttpStatus;

public record ErrorCode(HttpStatus status, String message) {
}
