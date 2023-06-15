package kr.teentime.mainApi.exception;

public class ClubNotFoundException extends Throwable {
    private final String message = "club not found";

    @Override
    public String getMessage() {
        return message;
    }
}
