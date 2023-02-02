package br.org.fundatec.trabalhofinal.demo.dto.tarifa;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarifaCreateDTO {

    private LocalDateTime entrada;
    private Integer idEstacionamento;
    private LocalDateTime saida;

    private String placaVeiculo;
}
