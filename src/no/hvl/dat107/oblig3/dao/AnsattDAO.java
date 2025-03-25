package no.hvl.dat107.oblig3.dao;

import no.hvl.dat107.oblig3.model.Ansatt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AnsattDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Oblig3PU");

    public Ansatt finnAnsattMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
    }

    public void lagreAnsatt(Ansatt ansatt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ansatt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Ansatt> hentAlleAnsatte() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Ansatt a", Ansatt.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void oppdaterAnsatt(Ansatt ansatt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ansatt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
