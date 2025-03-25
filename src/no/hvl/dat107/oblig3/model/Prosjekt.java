package no.hvl.dat107.oblig3.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "Oblig3", name = "Prosjekt")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjektId;

    @Column(nullable = false, length = 100)
    private String navn;

    @Column
    private String beskrivelse;

    @OneToMany(mappedBy = "prosjekt", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prosjektdeltagelse> deltagelser = new HashSet<>();

    // Gettere
    public int getProsjektId() { return prosjektId; }
    public String getNavn() { return navn; }
    public String getBeskrivelse() { return beskrivelse; }

    // Settere
    public void setProsjektId(int prosjektId) { this.prosjektId = prosjektId; }
    public void setNavn(String navn) { this.navn = navn; }
    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    // toString-metode
    @Override
    public String toString() {
        return "Prosjekt{" +
                "prosjektId=" + prosjektId +
                ", navn='" + navn + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                '}';
    }
}