package modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sede implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "sedeLocacao")
    private Set<Reserva> reservasLocacao = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sedeDevolucao")
    private Set<Reserva> reservasDevolucao = new LinkedHashSet<>();

    @OneToOne(mappedBy = "sede", cascade = CascadeType.ALL)
    private Endereco endereco;

    @Column(nullable = false)
    private String nome;

    @ElementCollection
    @Column(name = "telefone", nullable = false)
    @CollectionTable(name = "telefones", joinColumns = @JoinColumn(name = "idSede", nullable = false))
    private Set<String> telefones = new LinkedHashSet<>();

    @Column(nullable = false)
    private String nomeGerente;

    @Column(nullable = true)
    private BigDecimal multaPorEntregaEmOutroPonto;

    @Override
    public Integer getId() {
        return this.id;
    }

    public Set<Reserva> getReservasLocacao() {
        return reservasLocacao;
    }

    public Set<Reserva> getReservasDevolucao() {
        return reservasDevolucao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public String getNomeGerente() {
        return nomeGerente;
    }

    public BigDecimal getMultaPorEntregaEmOutroPonto() {
        return multaPorEntregaEmOutroPonto;
    }

    public void setReservasLocacao(Set<Reserva> reservasLocacao) {
        this.reservasLocacao = reservasLocacao;
    }

    public void setReservasDevolucao(Set<Reserva> reservasDevolucao) {
        this.reservasDevolucao = reservasDevolucao;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public void setNomeGerente(String nomeGerente) {
        this.nomeGerente = nomeGerente;
    }

    public void setMultaPorEntregaEmOutroPonto(BigDecimal multaPorEntregaEmOutroPonto) {
        this.multaPorEntregaEmOutroPonto = multaPorEntregaEmOutroPonto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sede sede = (Sede) o;
        return Objects.equals(id, sede.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
