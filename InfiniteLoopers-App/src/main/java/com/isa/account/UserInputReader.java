package com.isa.account;

import java.util.Scanner;

public class UserInputReader {
    private Scanner scanner;

    public UserInputReader() {
        scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}