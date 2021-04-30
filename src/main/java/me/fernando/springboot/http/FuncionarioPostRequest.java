package me.fernando.springboot.http;

import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.mapper.FuncionarioMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record FuncionarioPostRequest(
        @NotNull @NotEmpty(message = "Informe o nome do funcionário")
        String nome) {

    public Funcionario toEntity() {
        return FuncionarioMapper.getInstance().toFuncionario(this);
    }

}
