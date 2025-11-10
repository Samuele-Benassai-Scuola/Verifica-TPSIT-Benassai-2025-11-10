package it.benassai.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.benassai.exceptions.NotFoundException;
import it.benassai.exceptions.PermissionException;

import java.util.ArrayList;
import java.util.Collections;

public class MessageBoard {
    private int nextId = 1;

    private Map<Integer, Message> messages = Collections.synchronizedMap(new HashMap<>());

    public MessageBoard() {
    }

    public int addMessage(String user, String message) {
        messages.put(nextId, new Message(user, message));
        int id = nextId;

        nextId++;
        return id;
    }

    public void removeMessage(String user, int id) throws NotFoundException, PermissionException {
        if (!messages.containsKey(id))
            throw new NotFoundException();
        if (!messages.get(id).getOwner().equals(user))
            throw new PermissionException();
        
        messages.remove(id);
    }

    @Override
    public String toString() {
        List<String> messageList = new ArrayList<>();
        for (Entry<Integer, Message> pair : messages.entrySet()) {
            messageList.add("[" + pair.getKey() + "] " + pair.getValue().getOwner() + ": " + pair.getValue().getContent());
        }

        return String.join("\n", messageList);
    }
}
