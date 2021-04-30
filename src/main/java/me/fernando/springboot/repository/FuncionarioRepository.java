package me.fernando.springboot.repository;

import me.fernando.springboot.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findAllByNome(String name);

    @Query("select new java.lang.Boolean((count(id) > 0)) from Funcionario where id = ?1")
    boolean existsFuncionarioById(long id);

    @Modifying
    @Transactional
    @Query("""
            update Funcionario set nome = :#{#funcionario.nome}
            where id = :#{#funcionario.id}""")
    void updateFuncionario(@Param("funcionario") Funcionario funcionario);

    @Modifying
    @Transactional
    @Query("delete from Funcionario where id = ?1")
    void deleteFuncionarioById(long id);
}
