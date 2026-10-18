package ru.kiprin.demo.service;

import org.springframework.stereotype.Service;

import ru.kiprin.demo.model.Positions;

@Service
public interface AnnualBonusService {

    double calculate(Positions positions, double salary, double bonus, int workDays);
}
