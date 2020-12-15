package br.com.multiedro.teste;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.multiedro.teste.domain.Departamento;
import br.com.multiedro.teste.domain.Funcionario;
import br.com.multiedro.teste.repositores.DepartamentoRepositore;
import br.com.multiedro.teste.repositores.FuncionarioRepositore;

@SpringBootApplication
public class TesteApplication implements CommandLineRunner {

	@Autowired
	DepartamentoRepositore departamentoRepositore;
	
	@Autowired
	FuncionarioRepositore funcionarioRepositore;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args);
	}
	 @Override
	 public void run(String...args)throws Exception{
		Departamento dep1 = new Departamento(null, 57, "Juridico");
		Departamento dep2 = new Departamento(null, 63, "Administrativo");
		Departamento dep3 = new Departamento(null, 20, "Recursos Humanos");
		Departamento dep4 = new Departamento(null, 30, "Manutenção");
		Departamento dep5 = new Departamento(null, 12, "TI");
		Departamento dep6 = new Departamento(null, 70, "Vendas");
		Departamento dep7 = new Departamento(null, 90, "Projetos");
		
		Funcionario func1 = new Funcionario(null,"William,","willifran@gmail.com","d12",dep1,true);
		Funcionario func2 = new Funcionario(null,"Ruan","ruan@gmail.com","d13",dep2,false);
		
		dep1.getFuncionarioList().addAll(Arrays.asList(func1));
		dep2.getFuncionarioList().addAll(Arrays.asList(func2));
		
		departamentoRepositore.saveAll(Arrays.asList(dep1, dep2, dep3, dep4, dep5, dep6, dep7));
		funcionarioRepositore.saveAll(Arrays.asList(func1, func2));		
		
	}
	
	
}
