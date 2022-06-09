package team.f10.exception;

public class TagAlreadyExistsException extends RuntimeException {
    public TagAlreadyExistsException(String tagName) {
        super("Tag " + tagName + " already exists.");
    }
}
