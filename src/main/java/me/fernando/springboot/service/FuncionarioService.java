package me.fernando.springboot.service;

import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.exception.BadRequestException;
import me.fernando.springboot.http.FuncionarioPostRequest;
import me.fernando.springboot.http.FuncionarioPutRequest;
import me.fernando.springboot.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listAll() {
        return this.funcionarioRepository.findAll();
    }

    public Page<Funcionario> listAll(Pageable pageable) {
        return this.funcionarioRepository.findAll(pageable);
    }

    public List<Funcionario> findAllByNome(String nome) {
        return this.funcionarioRepository.findAllByNome(nome);
    }

    public Funcionario findByIdOrThrowException(long id) {
        return this.funcionarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Funcionário não encontrado"));
    }

    @Transactional
    public Funcionario save(FuncionarioPostRequest funcionarioPostRequest) {
        return this.funcionarioRepository.save(funcionarioPostRequest.toEntity());
    }

    public void update(FuncionarioPutRequest funcionarioPutRequest) {
        throwBadRequestIfFuncionarioNotExists(funcionarioPutRequest.id());
        this.funcionarioRepository.updateFuncionario(funcionarioPutRequest.toEntity());
    }

    public void delete(Long id) {
        throwBadRequestIfFuncionarioNotExists(id);
        this.funcionarioRepository.deleteFuncionarioById(id);
    }

    public void throwBadRequestIfFuncionarioNotExists(Long id) {
        if (!existsById(id)) {
            throw new BadRequestException("Funcionário não encontrado");
        }
    }

    public boolean existsById(Long id) {
        if (id != null) {
            return this.funcionarioRepository.existsFuncionarioById(id);
        }
        return false;
    }

}
