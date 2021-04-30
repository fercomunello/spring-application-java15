package me.fernando.springboot.controller;

import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.http.FuncionarioPostRequest;
import me.fernando.springboot.http.FuncionarioPutRequest;
import me.fernando.springboot.service.FuncionarioService;
import me.fernando.springboot.util.FuncionarioUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
class FuncionarioControllerTest {

    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioServiceMock;

    @BeforeEach
    void setup() {
        Funcionario expectedFuncionario = FuncionarioUtil.createValidUpdatedFuncionario();
        PageImpl<Funcionario> funcionarioPage = new PageImpl<>(List.of(expectedFuncionario));

        BDDMockito.when(this.funcionarioServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(funcionarioPage);

        BDDMockito.when(this.funcionarioServiceMock.listAll())
                .thenReturn(List.of(expectedFuncionario));

        BDDMockito.when(this.funcionarioServiceMock.findByIdOrThrowException(ArgumentMatchers.anyLong()))
                .thenReturn(expectedFuncionario);

        BDDMockito.when(this.funcionarioServiceMock.findAllByNome(ArgumentMatchers.anyString()))
                .thenReturn(List.of(expectedFuncionario));

        BDDMockito.when(this.funcionarioServiceMock.save(ArgumentMatchers.any(FuncionarioPostRequest.class)))
                .thenReturn(expectedFuncionario);

        BDDMockito.doNothing().when(this.funcionarioServiceMock)
                .update(ArgumentMatchers.any(FuncionarioPutRequest.class));

        BDDMockito.doNothing().when(this.funcionarioServiceMock)
                .delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Retorna os funcionários paginados")
    void deveRetornarFuncionariosPaginados() {
        String expectedName = FuncionarioUtil.createValidFuncionario().getNome();
        Page<Funcionario> funcionarioPage = this.funcionarioController.listAll(null).getBody();

        assertThat(funcionarioPage).isNotNull();
        assertThat(funcionarioPage.toList()).isNotEmpty().hasSize(1);
        assertThat(funcionarioPage.getTotalElements()).isEqualTo(1L);
        assertThat(funcionarioPage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Retorna todos os funcionários cadastrados")
    void deveRetornarTodosFuncionarios() {
        String expectedName = FuncionarioUtil.createValidFuncionario().getNome();
        List<Funcionario> funcionarios = this.funcionarioController.listAll().getBody();

        assertThat(funcionarios).isNotNull().isNotEmpty();
        assertThat(funcionarios.get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Retorna o funcionário de acordo com ID")
    void deveRetornarFuncionarioById() {
        Long id = FuncionarioUtil.createValidFuncionario().getId();
        Funcionario funcionario = this.funcionarioController.findById(id).getBody();

        assertThat(funcionario).isNotNull();
        assertThat(funcionario.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    @DisplayName("Retorna os funcionários de acordo com o nome")
    void deveRetornarFuncionariosByNome() {
        String expectedName = FuncionarioUtil.createValidFuncionario().getNome();
        List<Funcionario> funcionarios = this.funcionarioController.findAllByNome(expectedName).getBody();

        assertThat(funcionarios).isNotNull().isNotEmpty();
        assertThat(funcionarios.get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Retorna uma lista vazia quando não encontrar funcionários")
    void deveRetornarListaVaziaQuandoFuncionarioNaoEncontrado() {
        BDDMockito.when(this.funcionarioServiceMock.findAllByNome(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Funcionario> funcionarios = this.funcionarioController.findAllByNome("   ").getBody();

        assertThat(funcionarios).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Salva o cadastro do funcionário")
    void deveSalvarCadastroFuncionario() {
        Funcionario expectedFuncionario = FuncionarioUtil.createValidFuncionario();
        Funcionario funcionario = this.funcionarioController.save(FuncionarioUtil.createFuncionarioPostRequest()).getBody();

        assertThat(funcionario).isNotNull().isEqualTo(expectedFuncionario);
    }

    @Test
    @DisplayName("Atualiza o cadastro do funcionário")
    void deveAtualizarCadastroFuncionario() {
        assertThatCode(() -> this.funcionarioController.update(FuncionarioUtil.createFuncionarioPutRequest()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> response = this.funcionarioController.update(FuncionarioUtil.createFuncionarioPutRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Remove o cadastro do funcionário")
    void deveRemoverCadastroFuncionario() {
        long id = 1L;

        assertThatCode(() -> this.funcionarioController.delete(id))
                .doesNotThrowAnyException();

        ResponseEntity<Void> response = this.funcionarioController.delete(id);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}