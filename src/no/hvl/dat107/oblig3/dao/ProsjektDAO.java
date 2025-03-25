package no.hvl.dat107.oblig3.dao;

import no.hvl.dat107.oblig3.model.Prosjekt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProsjektDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Oblig3PU");

    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Prosjekt.class, id);
        } finally {
            em.close();
        }
    }

    public void lagreProsjekt(Prosjekt prosjekt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prosjekt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Prosjekt> hentAlleProsjekter() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Prosjekt p", Prosjekt.class).getResultList();
        } finally {
            em.close();
        }
    }
}
