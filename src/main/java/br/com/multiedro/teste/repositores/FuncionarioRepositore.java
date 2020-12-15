package br.com.multiedro.teste.repositores;

import br.com.multiedro.teste.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepositore extends JpaRepository<Funcionario, Integer> {
}