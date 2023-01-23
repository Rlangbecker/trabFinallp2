package br.org.fundatec.trabalhofinal.demo.dto.plano;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class PlanoCreateDTO {

    @Min(1)
    private double valor;
}
