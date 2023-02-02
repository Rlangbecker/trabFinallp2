package br.org.fundatec.trabalhofinal.demo.dto.tarifa;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Data
public class TarifaEntradaCreateDTO {

    @PastOrPresent
    private LocalDateTime entrada;
    @NotNull
    private String placa;

    @Min(1)
    private Integer idEstacionamento;

}
