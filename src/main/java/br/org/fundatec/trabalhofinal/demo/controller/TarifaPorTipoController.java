package br.org.fundatec.trabalhofinal.demo.controller;

import br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo.TarifaPorTipoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo.TarifaPorTipoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import br.org.fundatec.trabalhofinal.demo.service.TarifaPorTipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tarifa-por-tipo")
public class TarifaPorTipoController {

    private final TarifaPorTipoService tarifaPorTipoService;

    @PostMapping
    public ResponseEntity<TarifaPorTipoDTO> create(@RequestParam TipoVeiculo tipoVeiculo, @RequestBody TarifaPorTipoCreateDTO tarifaPorTipoCreateDTO){
        TarifaPorTipoDTO tarifaPorTipoDTO = tarifaPorTipoService.create(tarifaPorTipoCreateDTO,tipoVeiculo);

        return new ResponseEntity<>(tarifaPorTipoDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TarifaPorTipoDTO>> listAll(){
        return new ResponseEntity<>(tarifaPorTipoService.listAll(),HttpStatus.OK);
    }

}
