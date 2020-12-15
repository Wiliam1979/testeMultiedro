package br.com.multiedro.teste.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.multiedro.teste.domain.Departamento;
import br.com.multiedro.teste.domain.Funcionario;

public class FuncionarioDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	 @NotEmpty(message="Preenchimento obrigatorio")
	 @Length(min=5, max=120, message="o tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	 @NotEmpty(message="Preenchimento obrigatorio")
	 @Email(message="email inválido")
	private String email;
	private String senha;
	private boolean admin;
	private Departamento departamento;
	
	public FuncionarioDTO() {
		
	}
	
	 public FuncionarioDTO(Funcionario obj) {
		 id = obj.getId();
		 nome = obj.getNome();
		 setEmail(obj.getEmail());
		 senha = obj.getSenha();
		 admin = obj.isAdmin();
		 setDepartamento(obj.getDepartamento());
				 
		 
	 }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	
	
	
	

}