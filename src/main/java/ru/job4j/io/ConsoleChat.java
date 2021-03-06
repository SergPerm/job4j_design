package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answersBot;
    private int countOfBotAnswer;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private void readBotAnswer() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            answersBot = new ArrayList<>();
            while (br.ready()) {
                answersBot.add(br.readLine());
            }
            countOfBotAnswer = answersBot.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        List<String> log = new ArrayList<>();
        this.readBotAnswer();
        Scanner scanner = new Scanner(System.in);
        boolean answer = true;
        String message;
        String replay;
        do {
            message = scanner.nextLine();
            if (message.equals(STOP)) {
                answer = false;
            }
            if (message.equals(CONTINUE)) {
                answer = true;
            }
            log.add(message);
            if (answer) {
                replay = answersBot.get((int) (Math.random() * (countOfBotAnswer - 1)));
                System.out.println(replay);
                log.add(replay);
            }
        } while (!message.equals(OUT));
        scanner.close();
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\resultChat.txt",
                "C:\\projects\\job4j_design\\sourсeChat");
        cc.run();
    }
}
