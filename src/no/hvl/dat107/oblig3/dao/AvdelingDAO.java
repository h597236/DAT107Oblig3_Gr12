package no.hvl.dat107.oblig3.dao;

import no.hvl.dat107.oblig3.model.Avdeling;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AvdelingDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Oblig3PU");

    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
    }

    public void lagreAvdeling(Avdeling avdeling) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(avdeling);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Avdeling> hentAlleAvdelinger() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Avdeling a", Avdeling.class).getResultList();
        } finally {
            em.close();
        }
    }
}
