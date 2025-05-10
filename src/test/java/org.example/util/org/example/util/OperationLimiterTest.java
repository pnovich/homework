package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperationLimiterTest {

    @Test
    void testIsOperationAllowed() {
        List<User> users = Arrays.asList(
                new User("vania","vania"),
                new User("tania","tania")
        );
        int readLimit = 3;
        int writeLimit = 3;
        OperationLimiter limiter = new OperationLimiter(users, readLimit, writeLimit);
        Operation operation1 = new Operation("vania",OperatioType.WRITE);
        Operation operation2 = new Operation("tania",OperatioType.READ);
        for (int i = 0; i <2 ; i++) {
            limiter.doOperation(operation1);
        }
        limiter.printMaps();
        boolean expected = true;
        boolean actual = limiter.isOperationAllowed(operation1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDoOperation() {
    }
}