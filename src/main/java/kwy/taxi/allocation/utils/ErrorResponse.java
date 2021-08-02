package kwy.taxi.allocation.utils;

import kwy.taxi.allocation.utils.exception.ErrorCode;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(
                        new ErrorResponse.Builder()
                                        .message(errorCode.getDetail())
                                        .build()
                );
    }

    public String getMessage() {
        return message;
    }

    public static final class Builder {
        private String message;

        private Builder() {
        }

        public static Builder anErrorResponse() {
            return new Builder();
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(message);
        }
    }
}
