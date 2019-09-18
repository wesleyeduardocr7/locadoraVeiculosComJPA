package modelo.builder;
import enums.SituacaoReserva;
import modelo.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaBuilder {


	private Reserva reserva;

	private ReservaBuilder() {}

	public static ReservaBuilder umaReserva() {

		ReservaBuilder builder = new ReservaBuilder();
		builder.reserva = new Reserva();
		builder.reserva.setDataDevolucao(LocalDate.now());
		builder.reserva.setDataLocacao(LocalDate.now());
		builder.reserva.setQuantidadeDiarias((short) 15);
		builder.reserva.setQuilometroRodados(500);
		builder.reserva.setSituacao(SituacaoReserva.ALUGADO);
		builder.reserva.setValorMulta(new BigDecimal(100));


		return builder;
	}


	public ReservaBuilder buscaPorID(Integer id) {
		reserva.setId(id );
		return this;
	}

	public ReservaBuilder buscaPorSituacao(SituacaoReserva situacaoReserva) {
		reserva.setSituacao(situacaoReserva);
		return this;
	}

	public Reserva constroi(){
		return this.reserva;
	}

}