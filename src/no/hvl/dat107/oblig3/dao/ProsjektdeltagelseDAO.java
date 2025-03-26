package no.hvl.dat107.oblig3.dao;

import no.hvl.dat107.oblig3.model.Prosjektdeltagelse;
import no.hvl.dat107.oblig3.model.ProsjektdeltagelseId;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProsjektdeltagelseDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Oblig3PU");

    // 🔹 Lagre en ny prosjektdeltagelse
    public void lagreProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prosjektdeltagelse);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // 🔹 Hente en prosjektdeltagelse basert på ansatt-ID og prosjekt-ID
    public Prosjektdeltagelse finnProsjektdeltagelse(int ansattId, int prosjektId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Prosjektdeltagelse.class, new ProsjektdeltagelseId(ansattId, prosjektId));
        } finally {
            em.close();
        }
    }

    // 🔹 Oppdatere en eksisterende prosjektdeltagelse (f.eks. føre timer)
    public void oppdaterProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(prosjektdeltagelse);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // 🔹 Hente alle prosjektdeltagelser for et gitt prosjekt
    public List<Prosjektdeltagelse> hentDeltagereForProsjekt(int prosjektId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Prosjektdeltagelse p WHERE p.prosjekt.prosjektId = :prosjektId",
                            Prosjektdeltagelse.class
                    ).setParameter("prosjektId", prosjektId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 🔹 Hente alle prosjektdeltagelser for en gitt ansatt
    public List<Prosjektdeltagelse> hentProsjekterForAnsatt(int ansattId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Prosjektdeltagelse p WHERE p.ansatt.ansattId = :ansattId",
                            Prosjektdeltagelse.class
                    ).setParameter("ansattId", ansattId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
