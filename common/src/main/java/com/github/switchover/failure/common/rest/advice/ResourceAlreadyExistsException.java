package com.github.switchover.failure.common.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException {
    private final String name;
    private final int id;

    public ResourceAlreadyExistsException(String message, String name) {
        super(message);
        this.id = 0;
        this.name = name;
    }

    public ResourceAlreadyExistsException(String message, int id) {
        super(message);
        this.id = id;
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        if (name.equals("")) {
            return super.getMessage() + " (id = " + id + ")";
        } else {
            return super.getMessage() + " (name = " + name + ")";
        }
    }
}
