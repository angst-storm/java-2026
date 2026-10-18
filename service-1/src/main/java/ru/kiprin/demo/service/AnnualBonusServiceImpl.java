package ru.kiprin.demo.service;

import java.time.Year;

import org.springframework.stereotype.Service;

import ru.kiprin.demo.model.Positions;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().length();
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterlyBonus(Positions positions, double salary, double bonus) {
        if (!positions.isManager()) {
            return 0;
        }
        return salary * bonus * positions.getPositionCoefficient();
    }
}
