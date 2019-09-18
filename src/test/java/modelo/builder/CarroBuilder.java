package modelo.builder;
import enums.TipoCarro;
import modelo.Carro;

import java.math.BigDecimal;

public class CarroBuilder {


	private Carro carro;

	private CarroBuilder() {}

	public static CarroBuilder umCarro() {

		CarroBuilder builder = new CarroBuilder();
		builder.carro = new Carro();
		builder.carro.setAno(2019);
		builder.carro.setDescricao(TipoCarro.LUXO);
		builder.carro.setModelo("SUV");
		builder.carro.setNome("BMWX6M");
		builder.carro.setPlaca("NHN4697");
		builder.carro.setQuilometragem(145);
		builder.carro.setValorDiaria(new BigDecimal(1000));

		return builder;
	}

	

	public CarroBuilder comNome(String nome) {
		carro.setNome(nome);
		return this;
	}

	public CarroBuilder porTipo(TipoCarro tipoCarro) {
		carro.setDescricao(tipoCarro);
		return this;
	}

	public CarroBuilder deID(Integer id) {
		carro.setId(id );
		return this;
	}

	public Carro constroi(){
		return this.carro;
	}



}