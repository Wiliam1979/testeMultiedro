package br.com.multiedro.teste.services;

import br.com.multiedro.teste.domain.Departamento;
import br.com.multiedro.teste.dto.DepartamentoDTO;
import br.com.multiedro.teste.repositores.DepartamentoRepositore;
import br.com.multiedro.teste.services.exception.DataIntegrityException;
import br.com.multiedro.teste.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepositore repo;
	
	public Departamento find(Integer id) {
		Optional<Departamento>obj=repo.findById(id);
	return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ ", Tipo:" +Departamento.class.getName()));
			
	}
	
	public Departamento insert(Departamento obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	  public Departamento update(Departamento obj) {
		  find(obj.getId());
		  return repo.save(obj);
		  
	  }
	   
	   public void delete(Integer id) {
		   find(id);
		   try {
		   repo.deleteById(id);
		   }
		   catch(DataIntegrityViolationException e) {
			   throw new DataIntegrityException("Não e possivel excluir um departamento que possui funcionarios");
			   
		   }
	   }
	   
	   public List<Departamento>findAll(){
		   
		   return repo.findAll();
	   }
	   
	   
	   public Page<Departamento>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
			
		}
	   public Departamento fromDTO(DepartamentoDTO objDTO) {
		   return new Departamento(objDTO.getId(),objDTO.getCodigo(),objDTO.getNome());
		   
	   }

	
       
    }
