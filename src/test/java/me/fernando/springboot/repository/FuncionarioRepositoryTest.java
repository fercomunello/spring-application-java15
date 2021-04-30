package me.fernando.springboot.repository;

import me.fernando.springboot.domain.Funcionario;
import me.fernando.springboot.util.FuncionarioUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    @DisplayName("Salva o cadastro do funcionário")
    void deveSalvarCadastroFuncionario() {
        Funcionario funcionarioToSave = FuncionarioUtil.createFuncionarioToSave();
        Funcionario funcionarioSaved = this.funcionarioRepository.save(funcionarioToSave);

        assertThat(funcionarioSaved).isNotNull();
        assertThat(funcionarioSaved.getId()).isNotNull();
        assertThat(funcionarioSaved.getNome()).isEqualTo(funcionarioToSave.getNome());
    }

    @Test
    @DisplayName("Valida se o nome do funcionário à ser cadastrado é válido")
    void deveLancarExceptionQuandoFuncionarioVazio() {
        Funcionario funcionarioEmpty = new Funcionario();

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.funcionarioRepository.save(funcionarioEmpty));
    }

    @Test
    @DisplayName("Atualiza o nome do funcionário")
    void deveAtualizarNomeFuncionario() {
        Funcionario funcionarioToSave = FuncionarioUtil.createFuncionarioToSave();

        Funcionario funcionarioSaved = this.funcionarioRepository.save(funcionarioToSave);
        funcionarioSaved.setNome("Funcionario 1234");
        Funcionario funcionarioUpdated = this.funcionarioRepository.save(funcionarioSaved);

        assertThat(funcionarioUpdated).isNotNull();
        assertThat(funcionarioUpdated.getId()).isNotNull();
        assertThat(funcionarioUpdated.getNome()).isEqualTo(funcionarioSaved.getNome());
    }

    @Test
    @DisplayName("Remove o cadastro do funcionário")
    void deveRemoverFuncionario() {
        Funcionario funcionarioToSave = FuncionarioUtil.createFuncionarioToSave();
        Funcionario funcionarioSaved = this.funcionarioRepository.save(funcionarioToSave);

        this.funcionarioRepository.delete(funcionarioSaved);

        assertThat(this.funcionarioRepository.findById(funcionarioSaved.getId())).isEmpty();
    }

    @Test
    @DisplayName("Busca os funcionários de acordo com o nome")
    void deveBuscarFuncionarosByNome() {
        Funcionario funcionarioToSave = FuncionarioUtil.createFuncionarioToSave();
        Funcionario funcionarioSaved = this.funcionarioRepository.save(funcionarioToSave);

        List<Funcionario> funcionarioList = this.funcionarioRepository.findAllByNome(funcionarioSaved.getNome());

        assertThat(funcionarioList).isNotNull();
        assertThat(funcionarioList).isNotEmpty();
        assertThat(funcionarioList).contains(funcionarioSaved);
    }

    @Test
    @DisplayName("Retorna uma lista vazia quando o funcionário não for encontrado")
    void deveRetornarListaVaziaQuandoFuncionarioNaoEncontrado() {
        assertThat(this.funcionarioRepository.findAllByNome("   ")).isEmpty();
    }
}