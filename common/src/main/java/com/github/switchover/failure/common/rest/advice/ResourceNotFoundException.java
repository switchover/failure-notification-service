package com.github.switchover.failure.common.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private final Integer id;
    private final String name;

    public ResourceNotFoundException(String message, Integer id) {
        super(message);
        this.id = id;
        this.name = "";
    }

    public ResourceNotFoundException(String message, String name) {
        super(message);
        this.id = 0;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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
