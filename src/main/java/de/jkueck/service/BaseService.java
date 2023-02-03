package de.jkueck.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;

public abstract class BaseService implements Serializable {

    @Inject
    @Getter
    private HttpService httpService;

    @Getter
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

}
