package me.fernando.springboot.mapper;

import me.fernando.springboot.constants.Constants;
import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.http.FuncionarioPostRequest;
import me.fernando.springboot.http.FuncionarioPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = Constants.SPRING_COMPONENT_MODEL)
public abstract class FuncionarioMapper {

    private static final FuncionarioMapper instance;

    static {
        instance = Mappers.getMapper(FuncionarioMapper.class);
    }

    public abstract Funcionario toFuncionario(FuncionarioPostRequest funcionarioPostRequest);
    public abstract Funcionario toFuncionario(FuncionarioPutRequest funcionarioPutRequest);

    public static FuncionarioMapper getInstance() {
        return instance;
    }

}
