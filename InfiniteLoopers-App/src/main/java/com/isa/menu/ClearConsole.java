package com.isa.menu;

public class ClearConsole {

    private ClearConsole() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
