package me.fernando.springboot.http;

import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.mapper.FuncionarioMapper;

public record FuncionarioPutRequest(Long id, String nome) {

    public Funcionario toEntity() {
        return FuncionarioMapper.getInstance().toFuncionario(this);
    }
}
