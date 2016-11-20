/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Modelos.Retencionimpuesto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Techo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class RetencionimpuestoJpaController implements Serializable {

    public RetencionimpuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Retencionimpuesto retencionimpuesto) {
        if (retencionimpuesto.getTechoList() == null) {
            retencionimpuesto.setTechoList(new ArrayList<Techo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Techo> attachedTechoList = new ArrayList<Techo>();
            for (Techo techoListTechoToAttach : retencionimpuesto.getTechoList()) {
                techoListTechoToAttach = em.getReference(techoListTechoToAttach.getClass(), techoListTechoToAttach.getIdtecho());
                attachedTechoList.add(techoListTechoToAttach);
            }
            retencionimpuesto.setTechoList(attachedTechoList);
            em.persist(retencionimpuesto);
            for (Techo techoListTecho : retencionimpuesto.getTechoList()) {
                Retencionimpuesto oldIdretimpOfTechoListTecho = techoListTecho.getIdretimp();
                techoListTecho.setIdretimp(retencionimpuesto);
                techoListTecho = em.merge(techoListTecho);
                if (oldIdretimpOfTechoListTecho != null) {
                    oldIdretimpOfTechoListTecho.getTechoList().remove(techoListTecho);
                    oldIdretimpOfTechoListTecho = em.merge(oldIdretimpOfTechoListTecho);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Retencionimpuesto retencionimpuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Retencionimpuesto persistentRetencionimpuesto = em.find(Retencionimpuesto.class, retencionimpuesto.getIdretimp());
            List<Techo> techoListOld = persistentRetencionimpuesto.getTechoList();
            List<Techo> techoListNew = retencionimpuesto.getTechoList();
            List<Techo> attachedTechoListNew = new ArrayList<Techo>();
            for (Techo techoListNewTechoToAttach : techoListNew) {
                techoListNewTechoToAttach = em.getReference(techoListNewTechoToAttach.getClass(), techoListNewTechoToAttach.getIdtecho());
                attachedTechoListNew.add(techoListNewTechoToAttach);
            }
            techoListNew = attachedTechoListNew;
            retencionimpuesto.setTechoList(techoListNew);
            retencionimpuesto = em.merge(retencionimpuesto);
            for (Techo techoListOldTecho : techoListOld) {
                if (!techoListNew.contains(techoListOldTecho)) {
                    techoListOldTecho.setIdretimp(null);
                    techoListOldTecho = em.merge(techoListOldTecho);
                }
            }
            for (Techo techoListNewTecho : techoListNew) {
                if (!techoListOld.contains(techoListNewTecho)) {
                    Retencionimpuesto oldIdretimpOfTechoListNewTecho = techoListNewTecho.getIdretimp();
                    techoListNewTecho.setIdretimp(retencionimpuesto);
                    techoListNewTecho = em.merge(techoListNewTecho);
                    if (oldIdretimpOfTechoListNewTecho != null && !oldIdretimpOfTechoListNewTecho.equals(retencionimpuesto)) {
                        oldIdretimpOfTechoListNewTecho.getTechoList().remove(techoListNewTecho);
                        oldIdretimpOfTechoListNewTecho = em.merge(oldIdretimpOfTechoListNewTecho);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = retencionimpuesto.getIdretimp();
                if (findRetencionimpuesto(id) == null) {
                    throw new NonexistentEntityException("The retencionimpuesto with id " + id + " no longer exists.");
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
            Retencionimpuesto retencionimpuesto;
            try {
                retencionimpuesto = em.getReference(Retencionimpuesto.class, id);
                retencionimpuesto.getIdretimp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The retencionimpuesto with id " + id + " no longer exists.", enfe);
            }
            List<Techo> techoList = retencionimpuesto.getTechoList();
            for (Techo techoListTecho : techoList) {
                techoListTecho.setIdretimp(null);
                techoListTecho = em.merge(techoListTecho);
            }
            em.remove(retencionimpuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Retencionimpuesto> findRetencionimpuestoEntities() {
        return findRetencionimpuestoEntities(true, -1, -1);
    }

    public List<Retencionimpuesto> findRetencionimpuestoEntities(int maxResults, int firstResult) {
        return findRetencionimpuestoEntities(false, maxResults, firstResult);
    }

    private List<Retencionimpuesto> findRetencionimpuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Retencionimpuesto.class));
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

    public Retencionimpuesto findRetencionimpuesto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Retencionimpuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getRetencionimpuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Retencionimpuesto> rt = cq.from(Retencionimpuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
