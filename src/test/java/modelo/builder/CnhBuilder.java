package modelo.builder;

import com.mysql.cj.xdevapi.Client;
import modelo.Cliente;
import modelo.Cnh;

import java.time.LocalDate;


public class CnhBuilder {

	private Cnh cnh;

	private CnhBuilder() {}
	
	public static CnhBuilder umaCnh() {
		
		CnhBuilder builder = new CnhBuilder();
		builder.cnh = new Cnh();
		builder.cnh.setNumeroCnh("16464");
		builder.cnh.setCategoriaCnh("AB");
		return builder;
	}
	
	public CnhBuilder comNome(String categoria) {
		cnh.setCategoriaCnh("E");
		return this;
	}


	public CnhBuilder doCliente(Cliente cliente) {
		cliente.setCnh(cnh);
		return this;
	}

	public CnhBuilder comValidade(LocalDate validade) {
		cnh.setValidadeCnh(validade);
		return this;
	}


	public Cnh constroi(){

		return this.cnh;
	}
}