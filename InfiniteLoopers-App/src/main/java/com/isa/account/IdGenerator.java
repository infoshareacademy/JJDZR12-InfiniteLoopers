package com.isa.account;

import java.util.Random;

public class IdGenerator {
    private static final String INTEGER = "0123456789";
    private static final int ID_LENGTH = 16;
    private static Random random = new Random();

    public  static String generateUniqueId() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(INTEGER.length());
            idBuilder.append(randomIndex);
        }
        return idBuilder.toString();
    }
}
