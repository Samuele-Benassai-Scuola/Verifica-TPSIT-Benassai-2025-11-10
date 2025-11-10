package it.benassai;

import it.benassai.socket.SocketListener;

public class Main {
    public static void main(String[] args) {
        SocketListener socketListener = new SocketListener(3000);

        socketListener.run();
    }
}