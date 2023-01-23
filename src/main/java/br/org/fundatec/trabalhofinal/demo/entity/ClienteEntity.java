package br.org.fundatec.trabalhofinal.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    private String nome;

    private String cpf;

    @OneToOne(orphanRemoval = true)
    private EnderecoEntity endereco;

    @OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonIgnore
    private PlanoEntity planoEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VeiculoEntity> veiculos;

    private boolean assinante;
}
