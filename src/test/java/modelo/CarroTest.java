package modelo;

import enums.TipoCarro;
import modelo.builder.CarroBuilder;
import org.junit.*;

import java.math.BigDecimal;


public class CarroTest {

    @Test(expected = IllegalArgumentException.class)
    public void naodeveAdicionarValorNegativoNaDiaria() {
        Carro carro = CarroBuilder.umCarro().constroi();
        carro.setValorDiaria(new BigDecimal(-545));
    }

}