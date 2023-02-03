package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoDTO;
import br.org.fundatec.trabalhofinal.demo.dto.tarifa.*;
import br.org.fundatec.trabalhofinal.demo.dto.veiculo.VeiculoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.*;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.repository.TarifaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TarifaService {

    private final TarifaRepository tarifaRepository;

    private final EstacionamentoService estacionamentoService;

    private final TarifaPorTipoService tarifaPorTipoService;
    private final VeiculoService veiculoService;

    private final PlanoService planoService;
    private final ObjectMapper objectMapper;

    public TarifaDTO createTarifa(TarifaCreateDTO tarifaCreateDTO) throws RegraDeNegocioException {

        VeiculoDTO veiculoDTO = veiculoService.findByPlaca(tarifaCreateDTO.getPlacaVeiculo());

        EstacionamentoDTO estacionamentoDTO = estacionamentoService.findById(tarifaCreateDTO.getIdEstacionamento());
        EstacionamentoEntity estacionamento = objectMapper.convertValue(estacionamentoDTO, EstacionamentoEntity.class);

        TarifaEntity tarifa = objectMapper.convertValue(tarifaCreateDTO, TarifaEntity.class);
        tarifa.setEstacionamento(estacionamento);
        tarifa.setVeiculoEntity(objectMapper.convertValue(veiculoDTO, VeiculoEntity.class));


        TarifaEntity tarifaRetorno = tarifaRepository.save(tarifa);

        TarifaDTO tarifaDTO = objectMapper.convertValue(tarifaRetorno, TarifaDTO.class);
        tarifaDTO.setIdTarifa(tarifaRetorno.getIdTarifa());
        tarifaDTO.setEstacionamento(estacionamentoDTO);

        return tarifaDTO;
    }

    private Double calcularTarifa(TipoVeiculo tipoVeiculo, LocalDateTime entrada, LocalDateTime saida) throws RegraDeNegocioException {

        Long horas = ChronoUnit.HOURS.between(entrada, saida);

        TarifaPorTipoEntity tarifaPorTipoEntity = tarifaPorTipoService.findTarifaByTipoVeiculo(tipoVeiculo);

        switch (tipoVeiculo) {
            case CARRO -> {
                if (horas > 0 && horas <= 0.5) {
                    return tarifaPorTipoEntity.getTaxaAteMeiaHora();
                } else if (horas > 0.5 && horas <= 1) {
                    return tarifaPorTipoEntity.getTaxaAteUmaHora();
                } else if (horas > 1 && horas <= 12) {
                    return tarifaPorTipoEntity.getTaxaAdicional();
//                }else if (horas > 12 && horas <= 24) {
                } else {
                    return tarifaPorTipoEntity.getTaxaDiaria();
                }

            }
            case MOTO -> {
                if (horas > 0 && horas <= 0.5) {
                    return tarifaPorTipoEntity.getTaxaAteMeiaHora();
                } else if (horas > 0.5 && horas <= 1) {
                    return tarifaPorTipoEntity.getTaxaAteUmaHora();
                } else if (horas > 1 && horas <= 12) {
                    return tarifaPorTipoEntity.getTaxaAdicional();
//                } else if (horas > 12 && horas <= 24) {
                } else {
                    return tarifaPorTipoEntity.getTaxaDiaria();
                }
            }
            default -> {
                throw new RegraDeNegocioException("Erro inesperado");
            }
        }

    }

    public TarifaEntradaDTO entradaVeiculo(TarifaEntradaCreateDTO tarifaEntradaCreateDTO) throws RegraDeNegocioException {

        VeiculoEntity veiculoEntity = veiculoService.findVeiculoEntityByPlaca(tarifaEntradaCreateDTO.getPlaca());

        EstacionamentoDTO estacionamentoDTO = estacionamentoService.findById(tarifaEntradaCreateDTO.getIdEstacionamento());

        TarifaEntity tarifaEntity = new TarifaEntity();
        tarifaEntity.setVeiculoEntity(veiculoEntity);
        EstacionamentoEntity estacionamentoEntity = objectMapper.convertValue(estacionamentoDTO, EstacionamentoEntity.class);
        estacionamentoEntity.setId(tarifaEntradaCreateDTO.getIdEstacionamento());
        tarifaEntity.setEstacionamento(estacionamentoEntity);
        tarifaEntity.setEntrada(tarifaEntradaCreateDTO.getEntrada());

        TarifaEntity tarifaRetorno = tarifaRepository.save(tarifaEntity);

        TarifaEntradaDTO tarifaEntradaDTO = new TarifaEntradaDTO();
        tarifaEntradaDTO.setEntrada(tarifaRetorno.getEntrada());
        tarifaEntradaDTO.setEstacionamentoDTO(estacionamentoDTO);
        tarifaEntradaDTO.setVeiculoDTO(objectMapper.convertValue(veiculoEntity, br.org.fundatec.trabalhofinal.demo.dto.VeiculoDTO.class));

        return tarifaEntradaDTO;
    }

    public TarifaDTO saidaVeiculo(TarifaSaidaCreateDTO tarifaSaidaCreateDTO) throws RegraDeNegocioException {

        VeiculoEntity veiculoEntity = veiculoService.findVeiculoEntityByPlaca(tarifaSaidaCreateDTO.getPlaca());
        ClienteEntity cliente = veiculoEntity.getDono();

//        TarifaEntity tarifaRetorno = tarifaRepository.findTarifaEntityByVeiculoEntity_Placa(veiculoEntity.getPlaca());
        List<TarifaEntity> listaTarifas = tarifaRepository.findTarifaEntitiesByVeiculoEntity_Placa(tarifaSaidaCreateDTO.getPlaca());

       Optional<TarifaEntity> tarifaRetornoFindFirst = listaTarifas.stream()
                .filter(tarifaEntity -> tarifaEntity.getSaida() == null)
                .findFirst();

       TarifaEntity tarifaRetorno = tarifaRetornoFindFirst.get();

        if (tarifaRetorno == null) {
            throw new RegraDeNegocioException("Tarifa para este veículo não existe!");
        }

        tarifaRetorno.setSaida(tarifaSaidaCreateDTO.getSaida());

        double valor = calcularTarifa(veiculoEntity.getTipoVeiculo(), tarifaRetorno.getEntrada(), tarifaSaidaCreateDTO.getSaida());

        if (cliente.isAssinante()) {
            valor = valor -(valor*0.15);
        planoService.descontarTarifa(cliente.getIdCliente(),valor);
        }

        tarifaRetorno.setValorPago(valor);
        TarifaEntity tarifaRetornoFinal = tarifaRepository.save(tarifaRetorno);

        TarifaDTO tarifaDTO = new TarifaDTO();
        tarifaDTO.setSaida(tarifaRetornoFinal.getSaida());
        tarifaDTO.setEntrada(tarifaRetorno.getEntrada());
        tarifaDTO.setValorPago(tarifaRetornoFinal.getValorPago());
        tarifaDTO.setVeiculoEntity(veiculoEntity);
        tarifaDTO.setIdTarifa(tarifaRetornoFinal.getIdTarifa());
        EstacionamentoDTO estacionamentoDTO = new EstacionamentoDTO();
        estacionamentoDTO.setId(tarifaRetornoFinal.getEstacionamento().getId());
        estacionamentoDTO.setNome(tarifaRetornoFinal.getEstacionamento().getNome());
        estacionamentoDTO.setResponsavel(tarifaRetornoFinal.getEstacionamento().getResponsavel());
        tarifaDTO.setEstacionamento(estacionamentoDTO);

        return tarifaDTO;
    }
}
