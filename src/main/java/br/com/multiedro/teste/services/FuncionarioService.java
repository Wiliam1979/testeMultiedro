package br.com.multiedro.teste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.multiedro.teste.domain.Funcionario;
import br.com.multiedro.teste.dto.FuncionarioDTO;
import br.com.multiedro.teste.repositores.FuncionarioRepositore;
import br.com.multiedro.teste.services.exception.DataIntegrityException;
import br.com.multiedro.teste.services.exception.ObjectNotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepositore funcionarioRepositore;
	
	public Funcionario find(Integer id) {
		Optional<Funcionario>obj=funcionarioRepositore.findById(id);
	return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ ", Tipo:" +Funcionario.class.getName()));
			
	}
	
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);
		return funcionarioRepositore.save(obj);
		
	}
	public Funcionario update(Funcionario obj) {
		  find(obj.getId());
		  return  funcionarioRepositore.save(obj);
		  
	  }
	   
	   public void delete(Integer id) {
		   find(id);
		   try {
			   funcionarioRepositore.deleteById(id);
		   }
		   catch(DataIntegrityViolationException e) {
			   throw new DataIntegrityException("Não e possivel funcionarios");
			   
		   }
	   }
	   
	   public List<Funcionario>findAll(){
		   
		   return funcionarioRepositore.findAll();
	   }
	   
	   
	   public Page<Funcionario>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
			return  funcionarioRepositore.findAll(pageRequest);
			
		}
	   public Funcionario fromDTO(FuncionarioDTO objDTO) {
		   
		 return new Funcionario(objDTO.getId(),objDTO.getNome(), objDTO.getEmail(), objDTO.getSenha(), objDTO.getDepartamento(),objDTO.isAdmin());
		 
		
	   
	   }
	
	
       
    }


