package br.org.fundatec.trabalhofinal.demo.dto.tarifa;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarifaSaidaCreateDTO {

    LocalDateTime saida;
    String placa;

}
