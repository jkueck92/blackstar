package de.jkueck.web.common;

import lombok.Getter;

public enum Pages {

    OPERATIONS("operations"),
    POSTS("posts");

    @Getter
    private String name;

    Pages(String name) {
        this.name = name;
    }

}
