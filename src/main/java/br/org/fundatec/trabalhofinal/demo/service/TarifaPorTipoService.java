package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo.TarifaPorTipoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo.TarifaPorTipoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.TarifaEntity;
import br.org.fundatec.trabalhofinal.demo.entity.TarifaPorTipoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.repository.TarifaPorTipoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public TarifaPorTipoEntity findTarifaByTipoVeiculo(TipoVeiculo tipoVeiculo) throws RegraDeNegocioException {

       Optional<TarifaPorTipoEntity> tarifaOpcional = tarifaPorTipoRepository.findTarifaPorTipoEntityByTipoVeiculo(tipoVeiculo);

        List<TarifaPorTipoEntity> lista = tarifaPorTipoRepository.findAll().stream()
                .collect(Collectors.toList());

//        Optional<TarifaPorTipoEntity> tarifaOpcional = lista.stream()
//                .filter(tarifaPorTipoEntity -> tarifaPorTipoEntity.getTipoVeiculo() == tipoVeiculo).findFirst();

        if (tarifaOpcional.isEmpty()) {
            throw new RegraDeNegocioException("Tarifa não encontrada");
        }

        TarifaPorTipoEntity tarifaPorTipoEntity = new TarifaPorTipoEntity();
        tarifaPorTipoEntity.setTipoVeiculo(tarifaOpcional.get().getTipoVeiculo());
        tarifaPorTipoEntity.setIdTarifa(tarifaOpcional.get().getIdTarifa());
        tarifaPorTipoEntity.setTaxaAdicional(tarifaOpcional.get().getTaxaAdicional());
        tarifaPorTipoEntity.setTaxaDiaria(tarifaOpcional.get().getTaxaDiaria());
        tarifaPorTipoEntity.setTaxaAteUmaHora(tarifaOpcional.get().getTaxaAteUmaHora());
        tarifaPorTipoEntity.setTaxaAteMeiaHora(tarifaOpcional.get().getTaxaAteMeiaHora());

        return tarifaPorTipoEntity;
    }

}
