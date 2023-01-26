package br.org.fundatec.trabalhofinal.demo.controller;

import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.estacionamento.EstacionamentoDTO;
import br.org.fundatec.trabalhofinal.demo.service.EstacionamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {
    private final EstacionamentoService estacionamentoService;

    @PostMapping
    public ResponseEntity<EstacionamentoDTO> create(@RequestBody EstacionamentoCreateDTO estacionamentoCreateDTO) {
        EstacionamentoDTO estacionamentoRetorno = estacionamentoService.create(estacionamentoCreateDTO);
        return new ResponseEntity<>(estacionamentoRetorno, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstacionamentoDTO>> listAll() {
        return new ResponseEntity<>(estacionamentoService.listAll(), HttpStatus.OK);
    }
}
