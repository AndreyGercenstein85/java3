package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

class Main {
    private Vector<ClientHandler> clients;

    Main() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connection();
            server = new ServerSocket(8190);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(socket, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
                o.sendMsg(msg);
        }
    }

    void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
    }

    private void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }

        String out = sb.toString();

        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
    public void changeNikname(ClientHandler clientHandler, String oldNick, String newNick) throws SQLException {
        for (ClientHandler o : clients){
            if (AuthService.getIdByNick(newNick) == 0) {
                AuthService.changeNickname(oldNick,newNick);
            }
        }
    }
}