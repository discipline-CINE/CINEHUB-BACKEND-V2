package Discipline.CineHub.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    ACTOR("ROLE_ACTOR"),
    EXPERT("ROLE_EXPERT");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
