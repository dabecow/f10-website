package team.f10.exception;

import lombok.Getter;

public class NoSuchOccupationException extends RuntimeException {
    @Getter
    private final Long id;

    public NoSuchOccupationException(Long id) {
        super("No occupation with such id: " + id);
        this.id = id;
    }
}
