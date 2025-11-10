package it.benassai.exceptions;

public class LoginRequiredException extends CodableException {
    private static final String code = "LOGINREQUIRED";

    public String getCode() {
        return code;
    }
}
