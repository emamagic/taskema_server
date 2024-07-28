package com.emamagic.taskema.util;

import com.emamagic.taskema.model.dto.response.RichError;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RichErrorSerializer extends JsonSerializer<RichError> {

    @Override
    public void serialize(RichError richError, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("operation", richError.getOperation());
        gen.writeStringField("message", richError.getMessage());
        gen.writeObjectField("meta", richError.getMeta());
        gen.writeStringField("timestamp", richError.getTimestamp().toString());
        if (richError.getNestedError() != null) {
            gen.writeObjectField("nestedError", richError.getNestedError());
        }
        gen.writeEndObject();
    }
}
