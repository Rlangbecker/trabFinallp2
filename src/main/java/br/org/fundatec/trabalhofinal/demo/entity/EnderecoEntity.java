package br.org.fundatec.trabalhofinal.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;
    private String rua;

    private Integer numero;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco")
    private ClienteEntity clienteEntity;
}
