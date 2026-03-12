package collections;

import java.util.HashMap;
import java.util.Map;

public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final Integer code;
    private final String message;

    private static final Map<Integer, HttpStatus> LOOKUP= new HashMap<>();

    static {
        for (HttpStatus status : values()) {
            LOOKUP.put(status.code, status);
        }
    }

    HttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static HttpStatus fromCode(Integer code) {
        HttpStatus status = LOOKUP.get(code);
        if (status == null) {
            throw new IllegalArgumentException("Unknown HTTP status code: " + code);
        }
        return status;
    }

}
