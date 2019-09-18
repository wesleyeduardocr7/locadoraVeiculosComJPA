package repositorio;


import enums.SituacaoReserva;
import modelo.*;
import modelo.builder.*;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class ReservaRepositorioTest {

    private EntityManager manager;
    private static EntityManagerFactory factory;
    private ReservaRepository reservaRepository;

    @BeforeClass
    public static void inicio() {
        factory = Persistence.createEntityManagerFactory("sistemalocacao-test");
    }

    @Before
    public void antes() {
        manager = factory.createEntityManager();
        reservaRepository = new ReservaRepository(manager);
        manager.getTransaction().begin();
    }

    @After
    public void depois() {
        manager.getTransaction().rollback();
    }

    @AfterClass
    public static void fim() {
        factory.close();
    }


    // TESTE 04
    //Deve realizar reserva para carro localizado em outra sede.

    @Test
    public void verificaSeClienteNaoPodeRealizarReservaComOutraEmAberto() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva01 = ReservaBuilder.umaReserva().constroi();
        reserva01.setCliente(cliente);
        reserva01.setSedeLocacao(sedeDeLocacao);
        reserva01.setCarro(carro);
        reserva01.setSituacao(SituacaoReserva.ALUGADO);

        Reserva reserva02 = ReservaBuilder.umaReserva().constroi();
        reserva02.setCliente(cliente);
        reserva02.setSedeLocacao(sedeDeLocacao);
        reserva02.setCarro(carro);
        reserva02.setSituacao(SituacaoReserva.DEVOLVIDO);

        Reserva reserva03 = ReservaBuilder.umaReserva().constroi();
        reserva03.setCliente(cliente);
        reserva03.setSedeLocacao(sedeDeLocacao);
        reserva03.setCarro(carro);
        reserva03.setSituacao(SituacaoReserva.CANCELADO);

        reservaRepository.salvaOuAtualiza(reserva01);
        reservaRepository.salvaOuAtualiza(reserva02);
        reservaRepository.salvaOuAtualiza(reserva03);

        List<Reserva> reservas = reservaRepository.alugado();
        for (Reserva reserva : reservas) {
            Assert.assertNotEquals(reserva.getSituacao(), SituacaoReserva.ALUGADO);
        }
    }


    // TESTE 05
    //Um cliente não deve realizar uma segunda reserva caso ainda possua locação
    //em aberto
    @Test
    public void verificaSeClienteSemPendenciaPodeRealizarReservas() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva01 = ReservaBuilder.umaReserva().constroi();
        reserva01.setCliente(cliente);
        reserva01.setSedeLocacao(sedeDeLocacao);
        reserva01.setCarro(carro);
        reserva01.setSituacao(SituacaoReserva.ALUGADO);

        Reserva reserva02 = ReservaBuilder.umaReserva().constroi();
        reserva02.setCliente(cliente);
        reserva02.setSedeLocacao(sedeDeLocacao);
        reserva02.setCarro(carro);
        reserva02.setSituacao(SituacaoReserva.DEVOLVIDO);

        Reserva reserva03 = ReservaBuilder.umaReserva().constroi();
        reserva03.setCliente(cliente);
        reserva03.setSedeLocacao(sedeDeLocacao);
        reserva03.setCarro(carro);
        reserva03.setSituacao(SituacaoReserva.CANCELADO);

        reservaRepository.salvaOuAtualiza(reserva01);
        reservaRepository.salvaOuAtualiza(reserva02);
        reservaRepository.salvaOuAtualiza(reserva03);


        List<Reserva> reservas = reservaRepository.devolvido();

        for (Reserva reserva : reservas) {
            Assert.assertEquals(reserva.getSituacao(), SituacaoReserva.DEVOLVIDO);
        }
    }

    // TESTE 07
    //Não deve alugar um carro (num mesmo intervalo de tempo).
        @Test
    public void verificaSeNaoAlugaCarroNoMesmoIntervalorDeTempo() {

            Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
            Cliente cliente = ClienteBuilder.umCliente().constroi();
            Carro carro = CarroBuilder.umCarro().constroi();

            Reserva reserva01 = ReservaBuilder.umaReserva().constroi();
            reserva01.setCliente(cliente);
            reserva01.setSedeLocacao(sedeDeLocacao);
            reserva01.setCarro(carro);
            reserva01.setSituacao(SituacaoReserva.ALUGADO);

            Reserva reserva02 = ReservaBuilder.umaReserva().constroi();
            reserva02.setCliente(cliente);
            reserva02.setSedeLocacao(sedeDeLocacao);
            reserva02.setCarro(carro);
            reserva02.setSituacao(SituacaoReserva.DEVOLVIDO);

            Reserva reserva03 = ReservaBuilder.umaReserva().constroi();
            reserva03.setCliente(cliente);
            reserva03.setSedeLocacao(sedeDeLocacao);
            reserva03.setCarro(carro);
            reserva03.setSituacao(SituacaoReserva.CANCELADO);

            reservaRepository.salvaOuAtualiza(reserva01);
            reservaRepository.salvaOuAtualiza(reserva02);
            reservaRepository.salvaOuAtualiza(reserva03);

        manager.flush();

        Assert.assertNotEquals(reserva01.getDataLocacao(), reserva02.getDataLocacao());
    }

    // TESTE 08
    //Deve efetuar reserva para clientes com habilitação dentro de prazo de validade.
    @Test
    public void deveEfetuarReservaDeClienteComHabilitacaoNoPrazoDeValidade() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva01 = ReservaBuilder.umaReserva().constroi();
        reserva01.setCliente(cliente);
        reserva01.setSedeLocacao(sedeDeLocacao);
        reserva01.setCarro(carro);
        reserva01.setSituacao(SituacaoReserva.ALUGADO);

        Reserva reserva02 = ReservaBuilder.umaReserva().constroi();
        reserva02.setCliente(cliente);
        reserva02.setSedeLocacao(sedeDeLocacao);
        reserva02.setCarro(carro);
        reserva02.setSituacao(SituacaoReserva.DEVOLVIDO);

        Reserva reserva03 = ReservaBuilder.umaReserva().constroi();
        reserva03.setCliente(cliente);
        reserva03.setSedeLocacao(sedeDeLocacao);
        reserva03.setCarro(carro);
        reserva03.setSituacao(SituacaoReserva.CANCELADO);

        reservaRepository.salvaOuAtualiza(reserva01);
        reservaRepository.salvaOuAtualiza(reserva02);
        reservaRepository.salvaOuAtualiza(reserva03);

        manager.flush();

        LocalDate currentDate = LocalDate.now();

        Assert.assertTrue(currentDate.isBefore(cliente.getCnh().getValidadeCnh()));

    }


    // TESTE 09
    //Não deve efetuar reserva para clientes com habilitação vencida.
    @Test
    public void naoDeveFazerReservaDeClienteComCnhVencida() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva = ReservaBuilder.umaReserva().constroi();
        reserva.setCliente(cliente);
        reserva.setSedeLocacao(sedeDeLocacao);
        reserva.setCarro(carro);
        reserva.setSituacao(SituacaoReserva.ALUGADO);

        reservaRepository.salvaOuAtualiza(reserva);

        LocalDate validadeCnh = LocalDate.of(1,1,2025);
        Cnh cnh = CnhBuilder.umaCnh().doCliente(cliente).comValidade(validadeCnh).constroi();

        cliente.setCnh(cnh);

        reserva.setCliente(cliente);
        reservaRepository.salvaOuAtualiza(reserva);

        manager.flush();

        LocalDate currentDate = LocalDate.now();

        Assert.assertFalse(currentDate.isAfter(validadeCnh));

    }

    // TESTE 10
    // Deve recuperar todas as reservas em aberto de uma determinada sede.
    @Test
    public void deveRecuperarReversasEmAbertoDeUmaSede() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva01 = ReservaBuilder.umaReserva().constroi();
        reserva01.setCliente(cliente);
        reserva01.setSedeLocacao(sedeDeLocacao);
        reserva01.setCarro(carro);
        reserva01.setSituacao(SituacaoReserva.ALUGADO);

        Reserva reserva02 = ReservaBuilder.umaReserva().constroi();
        reserva02.setCliente(cliente);
        reserva02.setSedeLocacao(sedeDeLocacao);
        reserva02.setCarro(carro);
        reserva02.setSituacao(SituacaoReserva.DEVOLVIDO);

        Reserva reserva03 = ReservaBuilder.umaReserva().constroi();
        reserva03.setCliente(cliente);
        reserva03.setSedeLocacao(sedeDeLocacao);
        reserva03.setCarro(carro);
        reserva03.setSituacao(SituacaoReserva.CANCELADO);

        reservaRepository.salvaOuAtualiza(reserva01);
        reservaRepository.salvaOuAtualiza(reserva02);
        reservaRepository.salvaOuAtualiza(reserva03);

        manager.flush();

        List<Reserva> reservas = reservaRepository.alugado();
        for (Reserva reserva : reservas) {
            Assert.assertEquals(reserva.getSituacao(), SituacaoReserva.ALUGADO);
        }

    }

    // TESTE 11
    //Deve cobrar uma taxa para carro devolvido em outra sede.
    @Test
    public void deveCobraTaxaParaCarroDevolvidoEmOutraSede() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva = ReservaBuilder.umaReserva().constroi();
        reserva.setCliente(cliente);
        reserva.setSedeLocacao(sedeDeLocacao);
        reserva.setCarro(carro);
        reserva.setSituacao(SituacaoReserva.ALUGADO);

        reservaRepository.salvaOuAtualiza(reserva);

        BigDecimal valorDiaria = carro.getValorDiaria();
        BigDecimal taxaMulta = new BigDecimal(25);

        reserva.setValorMulta(valorDiaria.add(taxaMulta));

        reservaRepository.salvaOuAtualiza(reserva);

        manager.flush();

        Assert.assertNotEquals(carro.getSedeLocacao(), carro.getSedeDevolucao());
    }


    // TESTE 12
    //Não deve cobrar taxa para carro devolvido na mesma sede.

    @Test
    public void naoCobraTaxaParaCarroDevolvidoNaMesmaSede() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva = ReservaBuilder.umaReserva().constroi();
        reserva.setCliente(cliente);
        reserva.setSedeLocacao(sedeDeLocacao);
        reserva.setCarro(carro);
        reserva.setSituacao(SituacaoReserva.ALUGADO);

        reservaRepository.salvaOuAtualiza(reserva);

        BigDecimal valorDiaria = carro.getValorDiaria();
        BigDecimal taxaMulta = new BigDecimal(25);

        reserva.setValorMulta(valorDiaria.add(taxaMulta));

        reservaRepository.salvaOuAtualiza(reserva);

        manager.flush();

        Assert.assertEquals(carro.getSedeLocacao(), carro.getSedeDevolucao());
    }


    // TESTE 13
    //Deve saber qual classe de carro teve menos reserva.
    @Test
    public void classeDeCarroComMenosReserva() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva = ReservaBuilder.umaReserva().constroi();
        reserva.setCliente(cliente);
        reserva.setSedeLocacao(sedeDeLocacao);
        reserva.setCarro(carro);
        reserva.setSituacao(SituacaoReserva.ALUGADO);

        reservaRepository.salvaOuAtualiza(reserva);

        manager.flush();

        //INCOMPLETO

    }


    // TESTE 14
    //Deve recuperar todas as reservas finalizadas numa sede (sede de devolução)
    //em um determinado período.
    @Test
    public void deveRecuperarReservasFinalizadasDeUmaSede() {

        Sede sedeDeDevolucao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva = ReservaBuilder.umaReserva().constroi();
        reserva.setCliente(cliente);
        reserva.setSedeLocacao(sedeDeDevolucao);
        reserva.setCarro(carro);
        reserva.setSituacao(SituacaoReserva.ALUGADO);

        LocalDate currentDate = LocalDate.now();

        reservaRepository.salvaOuAtualiza(reserva);

        manager.flush();

        List<Reserva> reservas = reservaRepository.devolvido();

        Assert.assertFalse(reservas.isEmpty());
    }




    // TESTE 15
    //Deve recuperar todas as reservas em aberto de uma determinada sede.
    @Test
    public void reservasEmAbertoDeUmaDeterminadaSede() {

        Sede sedeDeLocacao = SedeBuilder.umaSede().constroi();
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carro carro = CarroBuilder.umCarro().constroi();

        Reserva reserva = ReservaBuilder.umaReserva().constroi();
        reserva.setCliente(cliente);
        reserva.setSedeLocacao(sedeDeLocacao);
        reserva.setCarro(carro);
        reserva.setSituacao(SituacaoReserva.ALUGADO);

        reservaRepository.salvaOuAtualiza(reserva);

        manager.flush();

        List<Reserva> reservas = reservaRepository.alugado();
        for (Reserva reserva01 : reservas) {
            Assert.assertEquals(reserva01.getSituacao(),SituacaoReserva.ALUGADO);
        }


    }


}