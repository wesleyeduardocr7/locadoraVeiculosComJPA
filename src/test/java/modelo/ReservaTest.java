package modelo;


import enums.SituacaoReserva;
import modelo.builder.CarroBuilder;
import modelo.builder.ClienteBuilder;
import modelo.builder.ReservaBuilder;
import modelo.builder.SedeBuilder;
import org.junit.Test;


public class ReservaTest {

    //TESTE 03
    //Deve realizar reserva para carro localizado em outra sede.
    @Test(expected = IllegalArgumentException.class)
    public void verificaSeReservaDeUmCarroPodeSerRealizadoEmOutraSede() {


       Sede sedeDeLocalizaoDoCliente = SedeBuilder.umaSede().constroi();
       Sede sedeDeLocalizacaoDoCarro = SedeBuilder.umaSede().constroi();

       Carro carro = CarroBuilder.umCarro().constroi();
       carro.setSedeLocacao(sedeDeLocalizaoDoCliente);
       carro.setSedeDevolucao(sedeDeLocalizacaoDoCarro);

       Reserva reserva = ReservaBuilder.umaReserva().constroi();
       Cliente cliente = ClienteBuilder.umCliente().constroi();

       reserva.setCliente(cliente);
       reserva.setCarro(carro);

    }










}