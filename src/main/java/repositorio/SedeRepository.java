package repositorio;



import modelo.Sede;

import javax.persistence.EntityManager;
import java.util.List;

public class SedeRepository {

	private final EntityManager manager;
	private DAOGenerico<Sede> daoGenerico;

	public SedeRepository(EntityManager manager) {
		this.manager = manager;
		daoGenerico = new DAOGenerico<Sede>(manager);
	}

	public Sede buscaPor(Integer id) {
		return daoGenerico.buscaPorId(Sede.class, id);
	}

	public List<Sede> buscaPor(String nome) {
		return this.manager.createQuery("from Sede " +
				"where upper(nome) like :nome", Sede.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}

	public Sede salvaOuAtualiza(Sede Sede) {
		return daoGenerico.salvaOuAtualiza(Sede);
	}

	public void remove(Sede Sede) {
		daoGenerico.remove(Sede );
	}
	
}