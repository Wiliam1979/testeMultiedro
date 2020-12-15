package br.com.multiedro.teste.repositores;

import br.com.multiedro.teste.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepositore extends JpaRepository<Departamento, Integer> {
}
