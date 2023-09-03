package com.isa.account;

import java.util.UUID;

class IdGenerator {

    private IdGenerator() {
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String getUniqueId() {
        return generateUniqueId();
    }
}
