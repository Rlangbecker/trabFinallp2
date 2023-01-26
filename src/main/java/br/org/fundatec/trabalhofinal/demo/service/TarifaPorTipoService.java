package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo.TarifaPorTipoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo.TarifaPorTipoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.TarifaPorTipoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import br.org.fundatec.trabalhofinal.demo.repository.TarifaPorTipoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TarifaPorTipoService {

    private final TarifaPorTipoRepository tarifaPorTipoRepository;

    private final ObjectMapper objectMapper;

    public TarifaPorTipoDTO create(TarifaPorTipoCreateDTO tarifaPorTipoCreateDTO, TipoVeiculo tipoVeiculo) {

        TarifaPorTipoEntity tarifaPorTipoEntity = objectMapper.convertValue(tarifaPorTipoCreateDTO, TarifaPorTipoEntity.class);
        tarifaPorTipoEntity.setTipoVeiculo(tipoVeiculo);

        TarifaPorTipoEntity tarifaRetornoEntity = tarifaPorTipoRepository.save(tarifaPorTipoEntity);

        TarifaPorTipoDTO tarifaPorTipoDTO = objectMapper.convertValue(tarifaRetornoEntity, TarifaPorTipoDTO.class);

        return tarifaPorTipoDTO;
    }

    public List<TarifaPorTipoDTO> listAll() {
        return tarifaPorTipoRepository.findAll().stream()
                .map(tarifaPorTipoEntity -> objectMapper.convertValue(tarifaPorTipoEntity, TarifaPorTipoDTO.class))
                .toList();
    }

}
