package com.example.number.service;

import com.example.number.model.NumberTale;
import com.example.number.repository.NumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberService {

    private NumberRepository repository;

    public NumberService(NumberRepository repository) {
        this.repository = repository;
    }

    public NumberTale get(int number) {
        return repository.get(number);
    }

    public List<NumberTale> getTop10() {
        return repository.getTopNumbers();
    }

    public int getAverageLatency() {
        return repository.getAverageLatency();

    }
    public double getAverageSuccessRate() {
        return repository.getAverageSuccessRate();
    }
}
