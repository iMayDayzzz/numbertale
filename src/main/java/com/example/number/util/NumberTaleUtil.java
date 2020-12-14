package com.example.number.util;

import com.example.number.model.NumberTale;

public class NumberTaleUtil {

    public static NumberTale createNew(int number, String description, int latency) {
        return new NumberTale(number, 1, description, latency);
    }
}
