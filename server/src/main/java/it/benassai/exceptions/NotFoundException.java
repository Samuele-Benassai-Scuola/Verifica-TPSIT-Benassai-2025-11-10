package it.benassai.exceptions;

public class NotFoundException extends CodableException {
    private static final String code = "NOTFOUND";

    public String getCode() {
        return code;
    }
}
