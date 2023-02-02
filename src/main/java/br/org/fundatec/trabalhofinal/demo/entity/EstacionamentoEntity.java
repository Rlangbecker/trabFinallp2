package br.org.fundatec.trabalhofinal.demo.entity;

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
public class EstacionamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String responsavel;
    @OneToMany(mappedBy = "estacionamento")
//    @JoinColumn(name = "idTarifa", updatable = false)
    @JsonIgnore
    private Set<TarifaEntity> tarifas;
}
