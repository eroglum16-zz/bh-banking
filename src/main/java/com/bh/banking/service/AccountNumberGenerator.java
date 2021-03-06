package com.bh.banking.service;

import java.util.Random;

public interface AccountNumberGenerator {
    static final int ACCOUNT_NUMBER_LENGTH = 8;
    final Random rand = new Random();

    String generateUniqueAccountNumber();
}
