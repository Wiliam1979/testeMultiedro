package br.com.multiedro.teste.resources;
import br.com.multiedro.teste.domain.Departamento;
import br.com.multiedro.teste.domain.Funcionario;
import br.com.multiedro.teste.dto.DepartamentoDTO;
import br.com.multiedro.teste.services.DepartamentoService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping(value ="/departamento")
public class DepartamentoResources {

     @Autowired
    private DepartamentoService departamentoService;

     @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Departamento> find(@PathVariable Integer id){
        Departamento obj = departamentoService.find(id);
        return ResponseEntity.ok().body(obj);

    }
     @RequestMapping(method=RequestMethod.POST)
     public ResponseEntity<Void>insert(@Valid @RequestBody DepartamentoDTO objDTO){
    	 Departamento obj = departamentoService.fromDTO(objDTO);
    	 obj=departamentoService.insert(obj);
    	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    			 .path("/{id}").buildAndExpand(obj.getId()).toUri();
    	 return ResponseEntity.created(uri).build();
    	 
     }
    
     @RequestMapping(value = "/{id}", method=RequestMethod.PUT)
     public ResponseEntity<Void>update(@Valid @RequestBody DepartamentoDTO objDTO, @PathVariable Integer id){
    	 Departamento obj = departamentoService.fromDTO(objDTO);
    	 obj.setId(id);
    	 obj = departamentoService.update(obj);
    	 return ResponseEntity.noContent().build();
     }
     @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
     public ResponseEntity<Funcionario> delete(@PathVariable Integer id){
    	 
    	 departamentoService.delete(id);
    	 return ResponseEntity.noContent().build();
    	 
     }
     
     @RequestMapping( method=RequestMethod.GET)
     public ResponseEntity<List<DepartamentoDTO>> findALL(){
        List<Departamento> list = departamentoService.findAll();
        List<DepartamentoDTO>listDTO = list.stream().map(obj -> new DepartamentoDTO(obj)).collect(Collectors.toList());
         return ResponseEntity.ok().body(listDTO);

     }
     
     @RequestMapping(value="/page", method=RequestMethod.GET)
     public ResponseEntity<Page<DepartamentoDTO>> findPage(
    		 @RequestParam(value="page",defaultValue ="0")Integer page,
    		 @RequestParam(value="linesPerPage",defaultValue ="24")Integer linesPerPage, 
    		 @RequestParam(value="orderBy",defaultValue ="nome")String orderBy,
    		 @RequestParam(value="direction",defaultValue ="ASC")String direction){
        Page<Departamento> list = departamentoService.findPage(page, linesPerPage,orderBy,direction);
        Page<DepartamentoDTO>listDTO = list.map(obj -> new DepartamentoDTO(obj));
         return ResponseEntity.ok().body(listDTO);

     }
     



}
