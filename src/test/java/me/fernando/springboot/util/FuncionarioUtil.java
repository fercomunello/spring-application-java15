package me.fernando.springboot.util;

import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.http.FuncionarioPostRequest;
import me.fernando.springboot.http.FuncionarioPutRequest;

public class FuncionarioUtil {

    public static Funcionario createFuncionarioToSave() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Hajime no Ippo");
        return funcionario;
    }

    public static Funcionario createValidFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Hajime no Ippo");
        return funcionario;
    }

    public static Funcionario createValidUpdatedFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Hajime no Ippo");
        return funcionario;
    }

    public static FuncionarioPostRequest createFuncionarioPostRequest() {
        Funcionario funcionario = FuncionarioUtil.createValidUpdatedFuncionario();

        return new FuncionarioPostRequest(
                funcionario.getNome()
        );
    }

    public static FuncionarioPutRequest createFuncionarioPutRequest() {
        Funcionario funcionario = FuncionarioUtil.createValidUpdatedFuncionario();

        return new FuncionarioPutRequest(
                funcionario.getId(),
                funcionario.getNome()
        );
    }
}
