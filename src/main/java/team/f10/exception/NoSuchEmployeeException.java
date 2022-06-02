package team.f10.exception;

public class NoSuchEmployeeException extends RuntimeException {
    public NoSuchEmployeeException(Long employeeId) {
        super("No such employee: " + employeeId);
    }
}
