package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.ClienteEntity;
import br.org.fundatec.trabalhofinal.demo.entity.EstacionamentoEntity;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.repository.EstacionamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public EstacionamentoDTO findById(Integer id) throws RegraDeNegocioException {
        Optional<EstacionamentoEntity> estacionamentoOptional = estacionamentoRepository.findById(id);
        if (estacionamentoOptional.isEmpty()) {
            throw new RegraDeNegocioException("Não existe estacionamento com este id.");
        }
        EstacionamentoDTO estacionamentoDTO = new EstacionamentoDTO();
        estacionamentoDTO.setId(estacionamentoOptional.get().getId());
        estacionamentoDTO.setNome(estacionamentoOptional.get().getNome());
        estacionamentoDTO.setResponsavel(estacionamentoOptional.get().getResponsavel());

        return estacionamentoDTO;
    }
}
