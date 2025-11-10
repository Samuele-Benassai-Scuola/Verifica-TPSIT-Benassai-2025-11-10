package it.benassai.exceptions;

public class UnknownCommandException extends CodableException {
    private static final String code = "UNKNOWNCMD";

    public String getCode() {
        return code;
    }
}
