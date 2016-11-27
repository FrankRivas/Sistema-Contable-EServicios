/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Baseprorrateo;
import Modelos.Centrodecosto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class CentrodecostoJpaController implements Serializable {

    public CentrodecostoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Centrodecosto centrodecosto) {
        if (centrodecosto.getBaseprorrateoList() == null) {
            centrodecosto.setBaseprorrateoList(new ArrayList<Baseprorrateo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Baseprorrateo> attachedBaseprorrateoList = new ArrayList<Baseprorrateo>();
            for (Baseprorrateo baseprorrateoListBaseprorrateoToAttach : centrodecosto.getBaseprorrateoList()) {
                baseprorrateoListBaseprorrateoToAttach = em.getReference(baseprorrateoListBaseprorrateoToAttach.getClass(), baseprorrateoListBaseprorrateoToAttach.getIdbase());
                attachedBaseprorrateoList.add(baseprorrateoListBaseprorrateoToAttach);
            }
            centrodecosto.setBaseprorrateoList(attachedBaseprorrateoList);
            em.persist(centrodecosto);
            for (Baseprorrateo baseprorrateoListBaseprorrateo : centrodecosto.getBaseprorrateoList()) {
                baseprorrateoListBaseprorrateo.getCentrodecostoList().add(centrodecosto);
                baseprorrateoListBaseprorrateo = em.merge(baseprorrateoListBaseprorrateo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Centrodecosto centrodecosto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centrodecosto persistentCentrodecosto = em.find(Centrodecosto.class, centrodecosto.getIdcentro());
            List<Baseprorrateo> baseprorrateoListOld = persistentCentrodecosto.getBaseprorrateoList();
            List<Baseprorrateo> baseprorrateoListNew = centrodecosto.getBaseprorrateoList();
            List<Baseprorrateo> attachedBaseprorrateoListNew = new ArrayList<Baseprorrateo>();
            for (Baseprorrateo baseprorrateoListNewBaseprorrateoToAttach : baseprorrateoListNew) {
                baseprorrateoListNewBaseprorrateoToAttach = em.getReference(baseprorrateoListNewBaseprorrateoToAttach.getClass(), baseprorrateoListNewBaseprorrateoToAttach.getIdbase());
                attachedBaseprorrateoListNew.add(baseprorrateoListNewBaseprorrateoToAttach);
            }
            baseprorrateoListNew = attachedBaseprorrateoListNew;
            centrodecosto.setBaseprorrateoList(baseprorrateoListNew);
            centrodecosto = em.merge(centrodecosto);
            for (Baseprorrateo baseprorrateoListOldBaseprorrateo : baseprorrateoListOld) {
                if (!baseprorrateoListNew.contains(baseprorrateoListOldBaseprorrateo)) {
                    baseprorrateoListOldBaseprorrateo.getCentrodecostoList().remove(centrodecosto);
                    baseprorrateoListOldBaseprorrateo = em.merge(baseprorrateoListOldBaseprorrateo);
                }
            }
            for (Baseprorrateo baseprorrateoListNewBaseprorrateo : baseprorrateoListNew) {
                if (!baseprorrateoListOld.contains(baseprorrateoListNewBaseprorrateo)) {
                    baseprorrateoListNewBaseprorrateo.getCentrodecostoList().add(centrodecosto);
                    baseprorrateoListNewBaseprorrateo = em.merge(baseprorrateoListNewBaseprorrateo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = centrodecosto.getIdcentro();
                if (findCentrodecosto(id) == null) {
                    throw new NonexistentEntityException("The centrodecosto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centrodecosto centrodecosto;
            try {
                centrodecosto = em.getReference(Centrodecosto.class, id);
                centrodecosto.getIdcentro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The centrodecosto with id " + id + " no longer exists.", enfe);
            }
            List<Baseprorrateo> baseprorrateoList = centrodecosto.getBaseprorrateoList();
            for (Baseprorrateo baseprorrateoListBaseprorrateo : baseprorrateoList) {
                baseprorrateoListBaseprorrateo.getCentrodecostoList().remove(centrodecosto);
                baseprorrateoListBaseprorrateo = em.merge(baseprorrateoListBaseprorrateo);
            }
            em.remove(centrodecosto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void destroyByName(String name) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centrodecosto centrodecosto;
            try {
                centrodecosto = (Centrodecosto)em.createNamedQuery("Centrodecosto.findByNomcentro").setParameter("nomcentro", name).getResultList().get(0);
                centrodecosto.getIdcentro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The centrodecosto with nombre " + name + " no longer exists.", enfe);
            }
            List<Baseprorrateo> baseprorrateoList = centrodecosto.getBaseprorrateoList();
            for (Baseprorrateo baseprorrateoListBaseprorrateo : baseprorrateoList) {
                baseprorrateoListBaseprorrateo.getCentrodecostoList().remove(centrodecosto);
                baseprorrateoListBaseprorrateo = em.merge(baseprorrateoListBaseprorrateo);
            }
            em.remove(centrodecosto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Centrodecosto> findCentrodecostoEntities() {
        return findCentrodecostoEntities(true, -1, -1);
    }

    public List<Centrodecosto> findCentrodecostoEntities(int maxResults, int firstResult) {
        return findCentrodecostoEntities(false, maxResults, firstResult);
    }

    private List<Centrodecosto> findCentrodecostoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Centrodecosto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Centrodecosto findCentrodecosto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Centrodecosto.class, id);
        } finally {
            em.close();
        }
    }
    
    public Centrodecosto findCentrodecostoByName(String name) {
        EntityManager em = getEntityManager();
        try {
            return (Centrodecosto)em.createNamedQuery("Centrodecosto.findByNomcentro").setParameter("nomcentro", name).getResultList().get(0);
        } finally {
            em.close();
        }
    }

    public int getCentrodecostoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Centrodecosto> rt = cq.from(Centrodecosto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
