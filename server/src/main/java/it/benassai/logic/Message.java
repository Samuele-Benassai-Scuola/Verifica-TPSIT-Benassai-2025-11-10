package it.benassai.logic;

public class Message {
    private String owner;
    private String content;
    
    public Message(String owner, String content) {
        this.owner = owner;
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
