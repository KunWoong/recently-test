package kwy.taxi.allocation.utils.exception;

import org.springframework.http.HttpStatus;


public enum ErrorCode {

    INVALID_EMAIL_PASSWORD(HttpStatus.BAD_REQUEST, "아이디와 비밀번호를 확인해주세요"),
    INVALID_EMAIL_FORM(HttpStatus.BAD_REQUEST, "올바른 이메일을 입력해주세요"),
    INVALID_USER_TYPE(HttpStatus.BAD_REQUEST, "유저타입을 입력해주세요"),
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
    INVALID_ADDRESS_LENGTH(HttpStatus.BAD_REQUEST, "주소는 100자 이하로 입력해주세요"),

    PASSENGER_EXCLUSIVE(HttpStatus.FORBIDDEN, "승객만 배차 요청할 수 있습니다"),
    DRIVER_EXCLUSIVE(HttpStatus.FORBIDDEN, "기사만 배차 요청을 수락할 수 있습니다"),
    DUPLICATE_TAXI_REQUEST(HttpStatus.CONFLICT, "아직 대기중인 배차 요청이 있습니다"),
    OCCUPIED_TAXI_ALLOCATION(HttpStatus.CONFLICT, "수락할 수 없는 배차 요청입니다. 다른 배차 요청을 선택하세요"),
    TAXI_ALLOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 배차 요청입니다"),


    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다")
    ;

    private final HttpStatus httpStatus;
    private final String detail;

    ErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDetail() {
        return detail;
    }
}
