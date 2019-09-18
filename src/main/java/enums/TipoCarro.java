package enums;


public enum TipoCarro {

    SUBCOMPACTO ("Carro Subbompacto"),
    COMPACTO ("Carro Compacto"),
    MEDIO ("Carro de Tamho Médio"),
    GRANDE ("Carro de Tamho Grande"),
    LUXO ("Carro Luxuoso");

    private String tipoCarro;


    private TipoCarro(String tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    public String getSituacao() {
        return tipoCarro;
    }


}
