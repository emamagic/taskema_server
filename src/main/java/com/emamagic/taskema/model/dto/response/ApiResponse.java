package com.emamagic.taskema.model.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.List;

public class ApiResponse<T> extends ResponseEntity<ApiResponse.ApiResponseBody<T>> {

    private ApiResponse(ApiResponseBuilder<T> builder) {
        super(new ApiResponseBody<>(
                builder.message,
                builder.payload,
                builder.hasError,
                builder.errors,
                builder.httpStatus,
                Instant.now()), builder.httpStatus);
    }

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    public static class ApiResponseBuilder<T> {
        private String message;
        private T payload;
        private boolean hasError;
        private List<RichError> errors;
        private HttpStatus httpStatus;

        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

        public ApiResponseBuilder<T> hasError(boolean hasError) {
            this.hasError = hasError;
            return this;
        }

        public ApiResponseBuilder<T> errors(List<RichError> errors) {
            this.errors = errors;
            return this;
        }

        public ApiResponseBuilder<T> httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(this);
        }
    }

    @Data
    public static class ApiResponseBody<T> {
        private final String message;
        private final T payload;
        private final boolean hasError;
        private final List<RichError> errors;
        private final HttpStatus httpStatus;
        private final Instant timestamp;
    }
}

