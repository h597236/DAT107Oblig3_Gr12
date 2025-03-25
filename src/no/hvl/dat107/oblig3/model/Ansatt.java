package no.hvl.dat107.oblig3.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "Oblig3", name = "Ansatt")
public class Ansatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ansattId;

    @Column(nullable = false, unique = true, length = 4)
    private String brukernavn;

    @Column(nullable = false, length = 50)
    private String fornavn;

    @Column(nullable = false, length = 50)
    private String etternavn;

    @Column(nullable = false)
    private LocalDate datoAnsettelse;

    @Column(nullable = false, length = 50)
    private String stilling;

    @Column(nullable = false)
    private double månedslønn;

    @ManyToOne
    @JoinColumn(name = "avdeling_id")
    private Avdeling avdeling;

    @OneToMany(mappedBy = "ansatt", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prosjektdeltagelse> prosjektdeltagelser = new HashSet<>();

    // Gettere
    public int getAnsattId() { return ansattId; }
    public String getBrukernavn() { return brukernavn; }
    public String getFornavn() { return fornavn; }
    public String getEtternavn() { return etternavn; }
    public LocalDate getDatoAnsettelse() { return datoAnsettelse; }
    public String getStilling() { return stilling; }
    public double getMånedslønn() { return månedslønn; }
    public Avdeling getAvdeling() { return avdeling; }

    // Settere
    public void setAnsattId(int ansattId) { this.ansattId = ansattId; }
    public void setBrukernavn(String brukernavn) { this.brukernavn = brukernavn; }
    public void setFornavn(String fornavn) { this.fornavn = fornavn; }
    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }
    public void setDatoAnsettelse(LocalDate datoAnsettelse) { this.datoAnsettelse = datoAnsettelse; }
    public void setStilling(String stilling) { this.stilling = stilling; }
    public void setMånedslønn(double månedslønn) { this.månedslønn = månedslønn; }
    public void setAvdeling(Avdeling avdeling) { this.avdeling = avdeling; }

    // toString-metode
    @Override
    public String toString() {
        return "Ansatt{" +
                "ansattId=" + ansattId +
                ", brukernavn='" + brukernavn + '\'' +
                ", fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", datoAnsettelse=" + datoAnsettelse +
                ", stilling='" + stilling + '\'' +
                ", månedslønn=" + månedslønn +
                ", avdeling=" + avdeling.getNavn() +
                '}';
    }
}
