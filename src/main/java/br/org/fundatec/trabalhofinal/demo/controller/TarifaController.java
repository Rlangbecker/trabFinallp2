package br.org.fundatec.trabalhofinal.demo.controller;

import br.org.fundatec.trabalhofinal.demo.dto.tarifa.*;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.service.TarifaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarifa")
public class TarifaController {


    private final TarifaService tarifaService;

    @PostMapping("/entrada")
    public ResponseEntity<TarifaEntradaDTO> entradaVeiculo(@RequestBody TarifaEntradaCreateDTO tarifaEntradaCreateDTO) throws RegraDeNegocioException {

        TarifaEntradaDTO tarifaDTO = tarifaService.entradaVeiculo(tarifaEntradaCreateDTO);

        return new ResponseEntity<>(tarifaDTO, HttpStatus.OK);
    }

    @PutMapping("/saida")
    public ResponseEntity<TarifaDTO> saidaVeiculo(@RequestBody TarifaSaidaCreateDTO tarifaSaidaCreateDTO) throws RegraDeNegocioException {

        TarifaDTO tarifaSaidaDTO = tarifaService.saidaVeiculo(tarifaSaidaCreateDTO);

        return new ResponseEntity<>(tarifaSaidaDTO, HttpStatus.OK);
    }
}
