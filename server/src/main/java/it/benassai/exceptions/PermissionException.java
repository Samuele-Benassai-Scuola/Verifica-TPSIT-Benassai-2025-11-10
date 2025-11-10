package it.benassai.exceptions;

public class PermissionException extends CodableException {
    private static final String code = "PERMISSION";

    public String getCode() {
        return code;
    }
}
