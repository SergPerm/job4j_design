package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                String command = "unknown command";
                String answer = null;
                boolean exit = false;
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine();
                         str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("msg=")) {
                            command = str.split("msg=")[1].split(" ")[0];
                        }
                    }
                    switch (command) {
                        case "Hello" -> {
                            answer = "Hello.";
                        }
                        case "Exit" -> {
                            exit = true;
                        }
                        case "Any" -> {
                            answer = "What?";
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + command);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (answer != null) {
                        out.write(answer.getBytes());
                    }
                    if (exit) {
                        server.close();
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            LOG.error("Exception IO", e);
        }
    }
}
