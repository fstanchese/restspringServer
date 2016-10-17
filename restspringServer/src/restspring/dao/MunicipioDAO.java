package restspring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import restspring.model.Municipio;

@Repository
public class MunicipioDAO {

	@PersistenceContext
	EntityManager manager;

	public Municipio salvar(Municipio municipio) {
		return manager.merge(municipio);
	}

	public void remover(Municipio municipio) {
		manager.remove(municipio);
	}
	
	public Municipio buscaPorId(Long id) {
		return manager.find(Municipio.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Municipio> listar() {
		return manager.createNamedQuery("Municipio.listar").getResultList();
	}
	
}
