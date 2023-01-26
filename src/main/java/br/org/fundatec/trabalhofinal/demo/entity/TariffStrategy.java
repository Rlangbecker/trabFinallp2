package br.org.fundatec.trabalhofinal.demo.entity;

import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;

import java.time.Duration;
import java.time.LocalDateTime;

interface TariffStrategy {
    double calcular(long minutos, TipoVeiculo tipoVeiculo);
}

class TarifaMeiaHora implements TariffStrategy {
    public double calcular(long minutos, TipoVeiculo tipoVeiculo) {
        if (TipoVeiculo.CARRO == tipoVeiculo) {
            double meiaHora = 5.00;
            return (minutos > 60 && minutos <= 300) ? meiaHora : 0;
        } else {
            double meiaHora = 4.00;
            return (minutos > 60 && minutos <= 300) ? meiaHora : 0;

        }
    }
}

class TarifaUmaHora implements TariffStrategy {
    public double calcular(long minutos, TipoVeiculo tipoVeiculo) {
        if (TipoVeiculo.CARRO == tipoVeiculo) {
            double umaHora = 8.00;
            return (minutos <= 60) ? umaHora : 0;
        } else {
            double umaHora = 6.00;
            return (minutos <= 60) ? umaHora : 0;

        }
    }
}

class TarifaHoraAdicional implements TariffStrategy {
    public double calcular(long minutos, TipoVeiculo tipoVeiculo) {
        double umaHora = 1.0;
        double diaria = 1440;
        if (TipoVeiculo.CARRO == tipoVeiculo) {
            double horaAdicional = 15.00;
            return (minutos > umaHora && minutos <= diaria) ? horaAdicional : 0;
        } else {
            double horaAdicional = 12.00;
            return (minutos > umaHora && minutos <= diaria) ? horaAdicional : 0;
        }
    }
}

class TarifaDiaria implements TariffStrategy {
    public double calcular(long minutos, TipoVeiculo tipoVeiculo) {
        long dias = minutos / 1440;

        if (TipoVeiculo.CARRO == tipoVeiculo) {
            double diaria = 25.0;
            return dias * diaria;
        } else {
            double diaria = 18.0;
            return dias * diaria;
        }
    }
}


class TariffCalculator {
    private final TariffStrategy tarifaUmaHora = new TarifaUmaHora();
    private final TariffStrategy tarifaMeiaHora = new TarifaMeiaHora();
    private final TariffStrategy tarifaDiaria = new TarifaDiaria();

    private final TariffStrategy tarifaHoraAdicional = new TarifaHoraAdicional();

    public double calculate(LocalDateTime entryTime, LocalDateTime exitTime, TipoVeiculo tipoVeiculo) {
        Duration duration = Duration.between(entryTime, exitTime);
        long minutes = duration.toMinutes();
        double meiaHora = tarifaMeiaHora.calcular(minutes, tipoVeiculo);
        double umaHora = tarifaUmaHora.calcular(minutes, tipoVeiculo);
        double horaAdicional = tarifaHoraAdicional.calcular(minutes, tipoVeiculo);
        double diaria = tarifaDiaria.calcular(minutes, tipoVeiculo);
        return meiaHora + umaHora + horaAdicional + diaria;
    }
}
