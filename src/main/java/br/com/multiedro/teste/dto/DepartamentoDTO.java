package br.com.multiedro.teste.dto;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.multiedro.teste.domain.Departamento;


public class DepartamentoDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int codigo;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=2, max=80, message="O tamanho deve ser entre 2 e 80 caracteres" )
	private String nome;
	
   public DepartamentoDTO() {
	   
	   
   }
   
    public DepartamentoDTO(Departamento obj) {
    	
    	id =obj.getId();
    	codigo = obj.getCodigo();
    	nome =obj.getNome();
    	
    }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}
     
   
   
   
	

}
