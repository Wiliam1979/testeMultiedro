package br.com.multiedro.teste.resources;
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
import br.com.multiedro.teste.domain.Funcionario;
import br.com.multiedro.teste.dto.FuncionarioDTO;
import br.com.multiedro.teste.services.FuncionarioService;

@RestController
@RequestMapping(value ="/funcionario")

public class FuncionarioResource {
	@Autowired
    private FuncionarioService funcionarioService;

     @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Funcionario> find(@PathVariable Integer id){
        Funcionario obj = funcionarioService.find(id);
        return ResponseEntity.ok().body(obj);

    }
     @RequestMapping(value = "/{id}", method=RequestMethod.PUT)
     public ResponseEntity<Void>update(@Valid @RequestBody FuncionarioDTO objDTO, @PathVariable Integer id){
    	 Funcionario obj = funcionarioService.fromDTO(objDTO);
    	 obj.setId(id);
    	 obj = funcionarioService.update(obj);
    	 return ResponseEntity.noContent().build();
     }
     @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
     public ResponseEntity<Funcionario> delete(@PathVariable Integer id){
    	 
    	 funcionarioService.delete(id);
    	 return ResponseEntity.noContent().build();
    	 
     }
     
     @RequestMapping( method=RequestMethod.GET)
     public ResponseEntity<List<FuncionarioDTO>> findALL(){
        List<Funcionario> list = funcionarioService.findAll();
        List<FuncionarioDTO>listDTO = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());
         return ResponseEntity.ok().body(listDTO);

     }
     
     @RequestMapping(value="/page", method=RequestMethod.GET)
     public ResponseEntity<Page<FuncionarioDTO>> findPage(
    		 @RequestParam(value="page",defaultValue ="0")Integer page,
    		 @RequestParam(value="linesPerPage",defaultValue ="24")Integer linesPerPage, 
    		 @RequestParam(value="orderBy",defaultValue ="nome")String orderBy,
    		 @RequestParam(value="direction",defaultValue ="ASC")String direction){
        Page<Funcionario> list = funcionarioService.findPage(page, linesPerPage,orderBy,direction);
        Page<FuncionarioDTO>listDTO = list.map(obj -> new FuncionarioDTO(obj));
         return ResponseEntity.ok().body(listDTO);

     }
     


}