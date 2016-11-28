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
import Modelos.Retencionimpuesto;
import Modelos.Techo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class TechoJpaController implements Serializable {

    public TechoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Techo techo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Retencionimpuesto idretimp = techo.getIdretimp();
            if (idretimp != null) {
                idretimp = em.getReference(idretimp.getClass(), idretimp.getIdretimp());
                techo.setIdretimp(idretimp);
            }
            em.persist(techo);
            if (idretimp != null) {
                idretimp.getTechoList().add(techo);
                idretimp = em.merge(idretimp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Techo techo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Techo persistentTecho = em.find(Techo.class, techo.getIdtecho());
            Retencionimpuesto idretimpOld = persistentTecho.getIdretimp();
            Retencionimpuesto idretimpNew = techo.getIdretimp();
            if (idretimpNew != null) {
                idretimpNew = em.getReference(idretimpNew.getClass(), idretimpNew.getIdretimp());
                techo.setIdretimp(idretimpNew);
            }
            techo = em.merge(techo);
            if (idretimpOld != null && !idretimpOld.equals(idretimpNew)) {
                idretimpOld.getTechoList().remove(techo);
                idretimpOld = em.merge(idretimpOld);
            }
            if (idretimpNew != null && !idretimpNew.equals(idretimpOld)) {
                idretimpNew.getTechoList().add(techo);
                idretimpNew = em.merge(idretimpNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = techo.getIdtecho();
                if (findTecho(id) == null) {
                    throw new NonexistentEntityException("The techo with id " + id + " no longer exists.");
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
            Techo techo;
            try {
                techo = em.getReference(Techo.class, id);
                techo.getIdtecho();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The techo with id " + id + " no longer exists.", enfe);
            }
            Retencionimpuesto idretimp = techo.getIdretimp();
            if (idretimp != null) {
                idretimp.getTechoList().remove(techo);
                idretimp = em.merge(idretimp);
            }
            em.remove(techo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Techo> findTechoEntities() {
        return findTechoEntities(true, -1, -1);
    }

    public List<Techo> findTechoEntities(int maxResults, int firstResult) {
        return findTechoEntities(false, maxResults, firstResult);
    }

    private List<Techo> findTechoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Techo.class));
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
    
    public List<Techo> findTechoEntitiesOrdered(){
        EntityManager em = getEntityManager();
        try {
            return (List<Techo>) em.createNamedQuery("Techo.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public Techo findTecho(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Techo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTechoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Techo> rt = cq.from(Techo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
