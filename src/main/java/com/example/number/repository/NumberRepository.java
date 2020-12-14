package com.example.number.repository;

import com.example.number.model.NumberTale;

import java.util.List;

public interface NumberRepository {

    NumberTale get(int number);

    List<NumberTale> getTopNumbers();

    int getAverageLatency();

    double getAverageSuccessRate();
}
