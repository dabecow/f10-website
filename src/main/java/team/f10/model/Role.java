package team.f10.model;

import lombok.Getter;

public enum Role {
    LEADER("Leader"),
    ADMIN("Admin"),
    USER("User");

    @Getter
    private final String name;

    Role(String s) {
        name = s;
    }
}
