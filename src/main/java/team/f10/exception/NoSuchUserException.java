package team.f10.exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(Long id) {
        super("No user with such id: " + id);
    }
}
