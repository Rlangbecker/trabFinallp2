package br.org.fundatec.trabalhofinal.demo.entity;

import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVeiculo;

    @Enumerated(EnumType.ORDINAL)
    private TipoVeiculo tipoVeiculo;

    private String placa;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="idCliente", referencedColumnName = "id_cliente")
    private ClienteEntity dono;

    @OneToMany(mappedBy = "veiculoEntity")
//    @JoinColumn(name = "idTarifa",updatable = false)
    private Set<TarifaEntity> tarifa;
}
