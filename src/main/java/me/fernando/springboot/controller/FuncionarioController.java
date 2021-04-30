package me.fernando.springboot.controller;

import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.http.FuncionarioPostRequest;
import me.fernando.springboot.http.FuncionarioPutRequest;
import me.fernando.springboot.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Page<Funcionario>> listAll(Pageable pageable) {
        return new ResponseEntity<>(this.funcionarioService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/listAll")
    public ResponseEntity<List<Funcionario>> listAll() {
        return new ResponseEntity<>(this.funcionarioService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.funcionarioService.findByIdOrThrowException(id), HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Funcionario>> findAllByNome(@RequestParam String nome) {
        return new ResponseEntity<>(this.funcionarioService.findAllByNome(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody @Valid FuncionarioPostRequest funcionarioPostRequest) {
        return new ResponseEntity<>(this.funcionarioService.save(funcionarioPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.funcionarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody FuncionarioPutRequest funcionarioPutRequest) {
        this.funcionarioService.update(funcionarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
