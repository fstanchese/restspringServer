package restspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import restspring.model.Municipio;
import restspring.service.MunicipioService;

@RestController
public class MunicipioController {

	private MunicipioService serviceMunicipio;
	
	@Autowired
	public MunicipioController(MunicipioService serviceMunicipio) {
		this.serviceMunicipio = serviceMunicipio;
	}

	@RequestMapping(value="/municipio/",method = RequestMethod.GET)
	public ResponseEntity<List<Municipio>> listarTodos() {
		List<Municipio> municipios = serviceMunicipio.listar();
		if (municipios.isEmpty()) {
			return new ResponseEntity<List<Municipio>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Municipio>>(municipios,HttpStatus.OK);
	}
	
	@RequestMapping(value="municipio/{id}",method = RequestMethod.GET)
	public ResponseEntity<Municipio> getMunicipio(@PathVariable("id") Long id) {
		Municipio municipio = serviceMunicipio.buscaPorId(id);
		if (municipio == null) {
			return new ResponseEntity<Municipio>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Municipio>(municipio,HttpStatus.OK);
	}

	@RequestMapping(value="/municipio/",method = RequestMethod.POST)
	public ResponseEntity<Void> addMunicipio(@RequestBody Municipio municipio, UriComponentsBuilder ucBuilder) {
		if (municipio.getId() != null){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		Municipio municipioSalvo = serviceMunicipio.salvar(municipio);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/municipio/{id}").buildAndExpand(municipioSalvo.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}	

	@RequestMapping(value="/municipio/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Municipio> updateMunicipio(@PathVariable("id") Long id, @RequestBody Municipio municipio ) {
		Municipio municipioUpd = serviceMunicipio.buscaPorId(id);
		
		if (municipioUpd==null) {
			return new ResponseEntity<Municipio>(HttpStatus.NOT_FOUND);
		}
		
		municipioUpd.setNome(municipio.getNome());
		
		serviceMunicipio.salvar(municipioUpd);
		return new ResponseEntity<Municipio>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/municipio/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Municipio> deleteMunicipio(@PathVariable("id") Long id) {
		Municipio municipio = serviceMunicipio.buscaPorId(id);
		if (municipio==null) {
			return new ResponseEntity<Municipio>(HttpStatus.NOT_FOUND);
		}
		serviceMunicipio.remover(id);
		return new ResponseEntity<Municipio>(HttpStatus.NO_CONTENT);
	}

}
