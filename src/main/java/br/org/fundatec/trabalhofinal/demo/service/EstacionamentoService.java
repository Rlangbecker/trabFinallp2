package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.ClienteEntity;
import br.org.fundatec.trabalhofinal.demo.entity.EstacionamentoEntity;
import br.org.fundatec.trabalhofinal.demo.repository.EstacionamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EstacionamentoService {

    private final EstacionamentoRepository estacionamentoRepository;

    private final ObjectMapper objectMapper;

    public EstacionamentoDTO create(EstacionamentoCreateDTO estacionamentoCreateDTO) {

        EstacionamentoEntity estacionamentoEntity = objectMapper.convertValue(estacionamentoCreateDTO, EstacionamentoEntity.class);

        EstacionamentoEntity estacionamentoRetorno = estacionamentoRepository.save(estacionamentoEntity);

        EstacionamentoDTO estacionamentoDTO = objectMapper.convertValue(estacionamentoRetorno, EstacionamentoDTO.class);
        estacionamentoDTO.setId(estacionamentoRetorno.getId());

        return estacionamentoDTO;
    }

    public List<EstacionamentoDTO> listAll() {
        return estacionamentoRepository.findAll().stream()
                .map(estacionamentoEntity -> objectMapper.convertValue(estacionamentoEntity, EstacionamentoDTO.class))
                .toList();
    }

}
