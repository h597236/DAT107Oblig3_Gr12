package no.hvl.dat107.oblig3.model;

import jakarta.persistence.*;

@Entity
@Table(schema = "Oblig3", name = "Prosjektdeltagelse")
public class Prosjektdeltagelse {
    @EmbeddedId
    private ProsjektdeltagelseId id;

    @ManyToOne
    @MapsId("ansattId")
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansatt;

    @ManyToOne
    @MapsId("prosjektId")
    @JoinColumn(name = "prosjekt_id")
    private Prosjekt prosjekt;

    @Column(nullable = false, length = 50)
    private String rolle;

    @Column(nullable = false)
    private int timer;

    // Gettere
    public ProsjektdeltagelseId getId() { return id; }
    public Ansatt getAnsatt() { return ansatt; }
    public Prosjekt getProsjekt() { return prosjekt; }
    public String getRolle() { return rolle; }
    public int getTimer() { return timer; }

    // Settere
    public void setId(ProsjektdeltagelseId id) { this.id = id; }
    public void setAnsatt(Ansatt ansatt) { this.ansatt = ansatt; }
    public void setProsjekt(Prosjekt prosjekt) { this.prosjekt = prosjekt; }
    public void setRolle(String rolle) { this.rolle = rolle; }
    public void setTimer(int timer) { this.timer = timer; }

    // toString-metode
    @Override
    public String toString() {
        return "Prosjektdeltagelse{" +
                "ansatt=" + ansatt.getFornavn() + " " + ansatt.getEtternavn() +
                ", prosjekt=" + prosjekt.getNavn() +
                ", rolle='" + rolle + '\'' +
                ", timer=" + timer +
                '}';
    }
}
