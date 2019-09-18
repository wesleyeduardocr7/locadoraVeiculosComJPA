package repositorio;

import modelo.Endereco;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoRepository {

    private final EntityManager manager;
    private DAOGenerico<Endereco> daoGenerico;

    public EnderecoRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<Endereco>(manager);
    }

    public Endereco buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Endereco.class, id);
    }

    public List<Endereco> buscaPor(String nome) {
        return this.manager.createQuery("from Endereco " +
                "where upper(nome) like :nome", Endereco.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    public Endereco salvaOuAtualiza(Endereco Endereco) {
        return daoGenerico.salvaOuAtualiza(Endereco);
    }

    public void remove(Endereco Endereco) {
        daoGenerico.remove(Endereco );
    }

}