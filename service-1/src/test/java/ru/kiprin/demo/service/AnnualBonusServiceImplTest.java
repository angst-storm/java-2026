package ru.kiprin.demo.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Year;

import org.junit.jupiter.api.Test;

import ru.kiprin.demo.model.Positions;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        // given
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);

        // then
        double expected = salary * bonus * Year.now().length() * position.getPositionCoefficient() / workDays;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusForManager() {
        // given
        Positions position = Positions.TL;
        double bonus = 2.0;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus);

        // then
        double expected = salary * bonus * position.getPositionCoefficient();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusForNonManager() {
        // given
        Positions position = Positions.DEV;
        double bonus = 2.0;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus);

        // then
        assertThat(result).isEqualTo(0);
    }
}
