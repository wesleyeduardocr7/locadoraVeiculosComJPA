package repositorio;

import enums.TipoCarro;
import modelo.Carro;

import javax.persistence.EntityManager;
import java.util.List;

public class CarroRepository {

    private final EntityManager manager;
    private DAOGenerico<Carro> daoGenerico;

    public CarroRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<Carro>(manager);
    }

    public Carro buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Carro.class, id);
    }

    public List<Carro> buscaPor(String placa) {
        return this.manager.createQuery("from Carro " +
                "where upper(placa) like :placa", Carro.class)
                .setParameter("placa", placa.toUpperCase() + "%")
                .getResultList();
    }


    public List<Carro> buscaPorModelo(String modelo) {
        return this.manager.createQuery("from Carro " +
                "where upper(placa) like :modelo", Carro.class)
                .setParameter("modelo", modelo.toUpperCase() + "%")
                .getResultList();
    }


    public List<Carro> compacto() {
        return manager
                .createQuery("from Carro c where c.descricao = :tipo", Carro.class)
                .setParameter("tipo", TipoCarro.COMPACTO)
                .getResultList();
    }

    public List<Carro> luxo() {
        return manager
                .createQuery("from Carro c where c.descricao = :tipo", Carro.class)
                .setParameter("tipo", TipoCarro.LUXO)
                .getResultList();
    }


    public Carro salvaOuAtualiza(Carro carro) {
        return daoGenerico.salvaOuAtualiza(carro);
    }

    public void remove(Carro Carro) {
        daoGenerico.remove(Carro );
    }

}