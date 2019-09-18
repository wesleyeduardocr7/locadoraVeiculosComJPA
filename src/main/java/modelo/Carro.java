package modelo;

import enums.TipoCarro;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Carro implements EntidadeBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "carro")
    private Set<Reserva> reservas = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "idSedeLocalizacaoAtual", nullable = false)
    private Sede sedeLocacao;

    @ManyToOne
    @JoinColumn(name = "idSedePontoOrigem", nullable = false)
    private Sede sedeDevolucao;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    private int quilometragem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCarro descricao;


    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @Override
    public Integer getId() {
        return this.id;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public Sede getSedeLocacao() {
        return sedeLocacao;
    }

    public Sede getSedeDevolucao() {
        return sedeDevolucao;
    }

    public String getNome() {
        return nome;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public TipoCarro getDescricao() {

       return descricao;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }


    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setSedeLocacao(Sede sedeLocacao) {


        if(sedeLocacao.equals(sedeDevolucao)){

            this.sedeLocacao = sedeLocacao;

            return;


        }else{


            throw new IllegalArgumentException("O carro pode ser reservado em uma sede e ser retirado em outra");

        }
    }

    public void setSedeDevolucao(Sede sedeDevolucao) {

        if(sedeDevolucao.equals(sedeDevolucao)){

            this.sedeDevolucao = sedeDevolucao;

            return;

        }else{


            throw new IllegalArgumentException("O valor da diária dever ser maior que 0");


        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(int ano) {

        if(ano < 0){
            throw new IllegalArgumentException("O valor do ano dever ser maior que 0");

        }

        this.ano = ano;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public void setDescricao(TipoCarro descricao) {
        this.descricao = descricao;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {

        if(valorDiaria.doubleValue() <= 0){

            throw new IllegalArgumentException("O valor da diária dever ser maior que 0");

        }

        this.valorDiaria = valorDiaria;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
