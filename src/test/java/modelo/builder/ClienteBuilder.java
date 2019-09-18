package modelo.builder;

import modelo.Cliente;


public class ClienteBuilder {

	private Cliente cliente;
	
	private ClienteBuilder() {}
	
	public static ClienteBuilder umCliente() {
		
		ClienteBuilder builder = new ClienteBuilder();
		builder.cliente = new Cliente();
		builder.cliente.setNome("Wesley Eduardo");
		return builder;
	}
	
	public ClienteBuilder comNome(String nome) {
		cliente.setNome(nome);
		return this;
	}

	public Cliente constroi(){
		return this.cliente;
	}
}