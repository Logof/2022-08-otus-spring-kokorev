package ru.otus.collectorio.payload.response;

import java.util.HashMap;
import java.util.Objects;

public class EntityResponse<T> extends HashMap<String, Object> {

    private static final String STATUS = "status";
    private static final String CODE = "code";
    private static final String DATA = "data";
    private static final String MESSAGE = "message";

    private static final String SUCCESS_MESSAGE = "success!";

    private static final String ERROR_MESSAGE = "unknown exception!";
    

    private EntityResponse(AnswerStatus answerStatus, AnswerCode AnswerCode, T objectData, String message) {
        put(STATUS, answerStatus.getValue());
        put(CODE, AnswerCode.getValue());
        if (!Objects.isNull(objectData)) {
            put(DATA, objectData);
        }
        put(MESSAGE, message);
    }

    public static EntityResponse success() {
        return new EntityResponse(AnswerStatus.SUCCESS_STATUS,
                AnswerCode.SUCCESS_CODE,
                null,
                SUCCESS_MESSAGE);
    }
    public static EntityResponse success(Object data) {
        return new EntityResponse(AnswerStatus.SUCCESS_STATUS,
                AnswerCode.SUCCESS_CODE,
                data,
                SUCCESS_MESSAGE);
    }
    public static EntityResponse success(HashMap<String, Object> data) {
        return new EntityResponse(AnswerStatus.SUCCESS_STATUS,
                AnswerCode.SUCCESS_CODE,
                data,
                SUCCESS_MESSAGE);
    }

    public static EntityResponse error() {
        return new EntityResponse(AnswerStatus.ERROR_STATUS,
                AnswerCode.ERROR_CODE,
                null,
                ERROR_MESSAGE);
    }

    public static EntityResponse error(AnswerCode code) {
        return new EntityResponse(AnswerStatus.ERROR_STATUS,
                code,
                null,
                ERROR_MESSAGE);
    }

    public static EntityResponse error(String message) {
        return new EntityResponse(AnswerStatus.ERROR_STATUS,
                AnswerCode.ERROR_CODE,
                null,
                message);
    }

    public static EntityResponse error(AnswerCode code, String message) {
        return new EntityResponse(AnswerStatus.ERROR_STATUS,
                code,
                null,
                message);
    }

}
