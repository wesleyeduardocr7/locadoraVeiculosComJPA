package modelo;

import enums.SituacaoReserva;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reserva implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idCarro", nullable = false)
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "idSedeLocacao", nullable = false)
    private Sede sedeLocacao;

    @ManyToOne
    @JoinColumn(name = "idSedeDevolucao", nullable = false)
    private Sede sedeDevolucao;


    @Column (nullable = false)
    private Short quantidadeDiarias;

    @Column (nullable = false)
    private LocalDate dataLocacao;

    @Column (nullable = false)
    private LocalDate dataDevolucao;

    @Column (nullable = false)
    private Integer quilometroRodados;

    @Column (nullable = true)
    private BigDecimal valorMulta;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private SituacaoReserva situacao = SituacaoReserva.ORCAMENTO;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Transient
    public BigDecimal getSubTotal() {
        return valorTotal.multiply(new BigDecimal(quantidadeDiarias));
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public Sede getSedeLocacao() {
        return sedeLocacao;
    }

    public Sede getSedeDevolucao() {
        return sedeDevolucao;
    }

    public Short getQuantidadeDiarias() {
        return quantidadeDiarias;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public Integer getQuilometroRodados() {
        return quilometroRodados;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public SituacaoReserva getSituacao() {
        return situacao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public void setSedeLocacao(Sede sedeLocacao) {
        this.sedeLocacao = sedeLocacao;
    }

    public void setSedeDevolucao(Sede sedeDevolucao) {
        this.sedeDevolucao = sedeDevolucao;
    }

    public void setQuantidadeDiarias(Short quantidadeDiarias) {
        this.quantidadeDiarias = quantidadeDiarias;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setQuilometroRodados(Integer quilometroRodados) {
        this.quilometroRodados = quilometroRodados;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public void setSituacao(SituacaoReserva situacao) {
        this.situacao = situacao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
