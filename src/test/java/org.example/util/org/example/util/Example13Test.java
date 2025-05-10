package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Example13Test {

    @Test
    void testEncode() {
        String string = "Success";
        String expected = ")())())";
        String encoded = Example13.encode(string);
        Assertions.assertEquals(expected, encoded);
    }
}