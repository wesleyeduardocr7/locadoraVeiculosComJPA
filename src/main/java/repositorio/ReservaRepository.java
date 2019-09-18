package repositorio;

import enums.SituacaoReserva;
import modelo.Reserva;

import javax.persistence.EntityManager;
import java.util.List;

public class ReservaRepository {

    private final EntityManager manager;
    private final DAOGenerico<Reserva> daoGenerico;


    public ReservaRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<Reserva>(manager);

    }

    public Reserva buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Reserva.class, id);
    }


    public List<Reserva> porSituacao(String situacao) {
        return this.manager
                .createQuery("from Reserva where upper(situacao) like :situacao", Reserva.class)
                .setParameter("situacao", "%" + situacao.toUpperCase() + "%")
                .getResultList();
    }



    public List<Reserva> orcamento() {
        return manager
                .createQuery("from Reserva s where s.situacao = :situacao", Reserva.class)
                .setParameter("situacao", SituacaoReserva.ORCAMENTO)
                .getResultList();
    }

    public List<Reserva> alugado() {
        return manager
                .createQuery("from Reserva s where s.situacao = :situacao", Reserva.class)
                .setParameter("situacao", SituacaoReserva.ALUGADO)
                .getResultList();
    }


    public List<Reserva> devolvido() {
        return manager
                .createQuery("from Reserva s where s.situacao = :situacao", Reserva.class)
                .setParameter("situacao", SituacaoReserva.DEVOLVIDO)
                .getResultList();
    }

    public List<Reserva> cancelado() {
        return manager
                .createQuery("from Reserva s where s.situacao = :situacao", Reserva.class)
                .setParameter("situacao", SituacaoReserva.CANCELADO)
                .getResultList();
    }




    public Reserva salvaOuAtualiza(Reserva Reserva) {
        return daoGenerico.salvaOuAtualiza(Reserva );
    }


    public void remover(Reserva Reserva)  {
        daoGenerico.remove(Reserva );
    }

}