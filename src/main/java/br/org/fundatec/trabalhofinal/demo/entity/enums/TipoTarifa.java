package br.org.fundatec.trabalhofinal.demo.entity.enums;

public enum TipoTarifa {
    MEIA_HORA_CARRO(5.00),
    UMA_HORA_CARRO(8.00),
    HORA_ADICIONAL_CARRO(15.00),
    DIARIA_CARRO(25.00),
    MEIA_HORA_MOTO(4.00),
    UMA_HORA_MOTO(6.00),
    HORA_ADICIONAL_MOTO(12.00),
    DIARIA_MOTO(18.00);

    private double tarifa;

    TipoTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public double getTarifa() {
        return tarifa;
    }
}

//    public double calcularTarifa(TipoVeiculo tipoVeiculo, Long horas) {
//
//        switch (tipoVeiculo) {
//            double tarifa = 0.0;
//
//            case CARRO -> {
//                if (horas <= 30) {
//                    tarifa = horas * MEIA_HORA_CARRO.getTarifa();
//                    return tarifa;
//
//                } else if (horas > 30 || horas <= 60) {
//
//                    tarifa = horas * UMA_HORA_CARRO.getTarifa();
//                    return tarifa;
//
//                } else if (horas > 60 || horas <= 360) {
//
//                    tarifa = horas * HORA_ADICIONAL_CARRO.getTarifa();
//                    return tarifa;
//
//                } else if (horas > 360 || horas <= 1440) {
//
//                    tarifa = horas * DIARIA_CARRO.getTarifa();
//                    return tarifa;
//
//                } else {
//                    while(true){
//                        Long dias = horas / 1440;
//                        System.out.println(); dias.toString().formatted(",");
//
//                    }
//
//
//                    tarifa =
//
//                }
//
//            }
//            case MOTO -> {
//
//                if (horas <= 30) {
//
//                } else if (horas > 30 || horas <= 60) {
//
//                    tarifa = horas * MEIA_HORA_MOTO.getTarifa();
//                    return tarifa;
//
//                } else if (horas > 60 || horas <= 360) {
//
//                    tarifa = horas * UMA_HORA_MOTO.getTarifa();
//                    return tarifa;
//
//                } else if (horas > 360 || horas <= 1440) {
//
//                    tarifa = horas * HORA_ADICIONAL_MOTO.getTarifa();
//                    return tarifa;
//
//                } else {
//
//                    tarifa = horas * DIARIA_MOTO.getTarifa();
//                    return tarifa;
//
//                }
//
//
//            }
//        }
//        return tarifa;
//    }
//}
