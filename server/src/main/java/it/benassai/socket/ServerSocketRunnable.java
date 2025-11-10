package it.benassai.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import it.benassai.exceptions.CodableException;
import it.benassai.exceptions.LoginRequiredException;
import it.benassai.exceptions.SyntaxException;
import it.benassai.logic.MessageBoard;

public class ServerSocketRunnable implements Runnable {
    private BufferedReader socketIn;
    private PrintWriter socketOut;

    private MessageBoard messageBoard;

    private String user;

    public ServerSocketRunnable(Socket socket, MessageBoard messageBoard) throws IOException {
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOut = new PrintWriter(socket.getOutputStream(), true);

        this.messageBoard = messageBoard;
    }

    @Override
    public void run() {
        socketOut.println("WELCOME");
        try {
            while (true) {
                try {
                    String command = socketIn.readLine();
                    if (command == null)
                        return;
                    
                    command = command.strip();
                    String[] args = command.split(" ", 2);
                    for (int i = 0; i < args.length; i++) {
                        args[i] = args[i].strip();
                    }
        
                    String resultMessage;
                    switch (args[0]) {
                        case "QUIT":
                            socketOut.println("BYE");
                            return;
                        case "LOGIN":
                            resultMessage = handleLogin(args);
                            break;
                        case "ADD":
                            resultMessage = handleAdd(args);
                            break;
                        case "LIST":
                            resultMessage = handleList(args);
                            break;
                        case "DEL":
                            resultMessage = handleDel(args);
                            break;
                        default:
                            throw new SyntaxException();
                    }

                    String[] lines = resultMessage.split("\n");
                    for (String line : lines) {
                        socketOut.println(line);
                    }    
                }
                catch (CodableException e) {
                    socketOut.println("ERR " + e.getCode());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String handleDel(String[] args) throws CodableException {
        if (user == null)
            throw new LoginRequiredException();

        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new SyntaxException();
        }

        messageBoard.removeMessage(user, id);
        return "OK REMOVED";
    }

    private String handleAdd(String[] args) throws CodableException {
        if (user == null)
            throw new LoginRequiredException();

        int id = messageBoard.addMessage(user, args[1]);
        return "OK ADDED " + id;
    }

    private String handleList(String[] args) throws CodableException {
        if (user == null)
            throw new LoginRequiredException();
        
        if (messageBoard.getMessages().isEmpty())
            return "BOARD:\nEND";
        
        return "BOARD:\n" + messageBoard.toString() + "\nEND";
    }

    private String handleLogin(String[] args) throws CodableException {
        if (user != null)
            throw new LoginRequiredException();
        if (args.length == 1)
            throw new SyntaxException();
        
        user = args[1];
        return "OK";
    }
}
