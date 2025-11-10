package it.benassai.exceptions;

public abstract class CodableException extends Exception implements Codable {
    public CodableException() {
    }

    public CodableException(String arg0) {
        super(arg0);
    }
}
