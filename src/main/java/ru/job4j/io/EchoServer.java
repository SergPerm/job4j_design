package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                Boolean exit = false;
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine();
                         str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("msg=Bye")) {
                            exit = true;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    if (exit) {
                        server.close();
                    }
                }
            }
        }
    }
}
