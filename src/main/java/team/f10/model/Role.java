package team.f10.model;

import lombok.Getter;

public enum Role {
    ROLE_USER ("User"),
    ROLE_ADMIN ("Admin"),
    ROLE_DIRECTOR ("Director");

    @Getter
    private final String name;

    Role(String s) {
        name = s;
    }
}
