package modelo.builder;

import modelo.Sede;

import java.math.BigDecimal;

public class SedeBuilder {


	private Sede sede;

	private SedeBuilder() {}

	public static SedeBuilder umaSede() {

		SedeBuilder builder = new SedeBuilder();
		builder.sede = new Sede();
		builder.sede.setMultaPorEntregaEmOutroPonto(new BigDecimal(100));
		builder.sede.setNome("Sao Luis");
		builder.sede.setNomeGerente("Wesley");

		return builder;
	}

	

	public SedeBuilder comNome(String nome) {
		sede.setNome(nome);
		return this;
	}


	public SedeBuilder deID(Integer id) {
		sede.setId(id);
		return this;
	}

	public Sede constroi(){
		return this.sede;
	}



}