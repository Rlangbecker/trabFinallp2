package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.tarifa.TarifaCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.tarifa.TarifaDTO;
import br.org.fundatec.trabalhofinal.demo.entity.TarifaEntity;
import br.org.fundatec.trabalhofinal.demo.repository.TarifaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TarifaService {

    private final TarifaRepository tarifaRepository;

    private final ObjectMapper objectMapper;

    public TarifaDTO calcularTarifa(TarifaCreateDTO tarifaCreateDTO) {

        TarifaEntity tarifa = objectMapper.convertValue(tarifaCreateDTO, TarifaEntity.class);

        TarifaEntity tarifaRetorno = tarifaRepository.save(tarifa);

        TarifaDTO tarifaDTO = objectMapper.convertValue(tarifaRetorno, TarifaDTO.class);
        tarifaDTO.setIdTarifa(tarifaRetorno.getIdTarifa());

        return tarifaDTO;

    }
}
