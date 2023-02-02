package br.org.fundatec.trabalhofinal.demo.dto.tarifa;

import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.EstacionamentoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.VeiculoEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarifaDTO {

    private Integer idTarifa;
    private EstacionamentoDTO estacionamento;

    private LocalDateTime entrada;

    private LocalDateTime saida;

    private Double valorPago;

    private VeiculoEntity veiculoEntity;
}
