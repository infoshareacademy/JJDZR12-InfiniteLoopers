package com.isa.account;

import java.util.Scanner;

public class UserInputReader {
    private static Scanner scanner;

    public UserInputReader() {
        scanner = new Scanner(System.in);
    }
/*    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }*/

    public String readNonEmptyString (String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Nic nie zostalo wprowadzone, sproboj jeszcze raz.");
            }
        } while (input.trim().isEmpty());
        return input;
    }

}