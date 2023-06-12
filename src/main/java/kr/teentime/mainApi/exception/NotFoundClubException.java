package kr.teentime.mainApi.exception;

public class NotFoundClubException extends Throwable {

    private final String message = "not found club";

    @Override
    public String getMessage() {
        return message;
    }
}
