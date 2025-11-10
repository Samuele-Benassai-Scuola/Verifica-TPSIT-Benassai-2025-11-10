package it.benassai.exceptions;

public class SyntaxException extends CodableException {
    private static final String code = "SYNTAX";

    public String getCode() {
        return code;
    }
}
