package enums;

public enum SituacaoReserva {


    ORCAMENTO {

        @Override
        public SituacaoReserva finaliza() {
            return SituacaoReserva.ALUGADO;

        }

        @Override
        public SituacaoReserva cancela() {
            return SituacaoReserva.CANCELADO;

        }
    },


    ALUGADO {
        @Override
        public SituacaoReserva finaliza() {
            return SituacaoReserva.DEVOLVIDO;

        }

        @Override
        public SituacaoReserva cancela() {
            throw new IllegalStateException("O carro já foi alugado nao pode ser cancelado");
        }
    },


    DEVOLVIDO {
        @Override
        public SituacaoReserva finaliza() {
            throw new IllegalStateException("O carro já foi devolvido nao pode ser finalizado");
        }

        @Override
        public SituacaoReserva cancela() {
            throw new IllegalStateException("O carro já foi devolvido nao pode ser cancelado");
        }
    },


    CANCELADO {
        @Override
        public SituacaoReserva finaliza() {
            throw new IllegalStateException("Pedido Cancelado não pode ser ALUGADO");

        }

        @Override
        public SituacaoReserva cancela() {
            throw new IllegalStateException("Pedido já foi cancelado");
        }
    };


    public abstract SituacaoReserva finaliza();
    public abstract SituacaoReserva cancela();
}
