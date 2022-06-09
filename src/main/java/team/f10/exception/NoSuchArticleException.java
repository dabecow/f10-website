package team.f10.exception;

public class NoSuchArticleException extends RuntimeException {
    public NoSuchArticleException(Long id) {
        super("No article with such id: " + id);
    }
}
