package br.org.fundatec.trabalhofinal.demo.dto.tarifa;

import br.org.fundatec.trabalhofinal.demo.dto.VeiculoDTO;
import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarifaEntradaDTO {

    private LocalDateTime entrada;

    private VeiculoDTO veiculoDTO;

    private EstacionamentoDTO estacionamentoDTO;
}
