package restspring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restspring.dao.MunicipioDAO;
import restspring.model.Municipio;

@Service
public class MunicipioService {

	private MunicipioDAO daoMunicipio;

	@Autowired
	public MunicipioService(MunicipioDAO daoMunicipio) {
		this.daoMunicipio = daoMunicipio;
	}
	
	@Transactional
	public Municipio salvar(Municipio municipio) {
		return daoMunicipio.salvar(municipio);
	}
	
	@Transactional
	public void remover(Long id) {
		Municipio municipio = buscaPorId(id);
		daoMunicipio.remover(municipio);
	}
	
	public Municipio buscaPorId(Long id) {
		return daoMunicipio.buscaPorId(id);
	}
	
	public List<Municipio> listar() {
		return daoMunicipio.listar();
	}
}