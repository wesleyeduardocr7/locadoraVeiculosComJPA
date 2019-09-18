package modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Cnh {

    @Column(name = "numeroCnh", nullable = false)
    private String numeroCnh;

    @Column(name = "validadeCnh", nullable = false)
    private LocalDate validadeCnh;

    @Column(name = "caregoriaChn", nullable = false)
    private String categoriaCnh;

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public LocalDate getValidadeCnh() {
        return validadeCnh;
    }

    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public void setValidadeCnh(LocalDate validadeCnh) {
        this.validadeCnh = validadeCnh;
    }

    public void setCategoriaCnh(String categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cnh cnh = (Cnh) o;
        return Objects.equals(numeroCnh, cnh.numeroCnh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCnh);
    }
}