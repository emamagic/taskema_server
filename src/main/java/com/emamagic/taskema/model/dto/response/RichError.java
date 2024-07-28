package com.emamagic.taskema.model.dto.response;

import com.emamagic.taskema.util.RichErrorSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Getter
@JsonSerialize(using = RichErrorSerializer.class)
public class RichError extends RuntimeException {
    private final String operation;
    private final String message;
    private final Exception nestedError;
    private final HttpStatus httpStatus;
    private final List<String> meta;
    private final Instant timestamp;

    private RichError(RichErrorBuilder builder) {
        super(builder.message, builder.nestedError);
        this.operation = builder.operation;
        this.message = builder.message;
        this.nestedError = builder.nestedError;
        this.meta = builder.meta;
        this.httpStatus = builder.httpStatus;
        this.timestamp = Instant.now();
    }

    public RichError getNestedError() {
        return (nestedError instanceof RichError) ? (RichError) nestedError : null;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static RichErrorBuilder builder() {
        return new RichErrorBuilder();
    }

    public static class RichErrorBuilder {
        private String operation;
        private String message;
        private Exception nestedError;
        private HttpStatus httpStatus;
        private List<String> meta;

        public RichErrorBuilder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public RichErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public RichErrorBuilder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public RichErrorBuilder nestedError(Exception nestedError) {
            if (nestedError instanceof RichError) {
                this.nestedError = nestedError;
            } else {
                this.nestedError = new RichError.RichErrorBuilder()
                        .operation("Nested operation")
                        .message(nestedError.getMessage())
                        .build();
            }
            return this;
        }

        public RichErrorBuilder meta(List<String> meta) {
            this.meta = meta;
            return this;
        }

        public RichError build() {
            return new RichError(this);
        }
    }

}
