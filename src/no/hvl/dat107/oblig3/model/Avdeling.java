package no.hvl.dat107.oblig3.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "Oblig3", name = "Avdeling")
public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelingId;

    @Column(nullable = false, length = 100)
    private String navn;

    @OneToOne
    @JoinColumn(name = "sjef_id", unique = true)
    private Ansatt sjef;

    @OneToMany(mappedBy = "avdeling", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ansatt> ansatte = new HashSet<>();

    // Gettere
    public int getAvdelingId() { return avdelingId; }
    public String getNavn() { return navn; }
    public Ansatt getSjef() { return sjef; }

    // Settere
    public void setAvdelingId(int avdelingId) { this.avdelingId = avdelingId; }
    public void setNavn(String navn) { this.navn = navn; }
    public void setSjef(Ansatt sjef) { this.sjef = sjef; }

    // toString-metode
    @Override
    public String toString() {
        return "Avdeling{" +
                "avdelingId=" + avdelingId +
                ", navn='" + navn + '\'' +
                ", sjef=" + sjef.getFornavn() + " " + sjef.getEtternavn() +
                '}';
    }
}
