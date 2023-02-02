package br.org.fundatec.trabalhofinal.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TarifaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="idEstacionamento", updatable = false)
    @JsonIgnore
    private EstacionamentoEntity estacionamento;

    private LocalDateTime entrada;

    private LocalDateTime saida;

    private Double valorPago;

    @ManyToOne
    @JoinColumn(name = "idVeiculo", updatable = false)
    private VeiculoEntity veiculoEntity;
}
