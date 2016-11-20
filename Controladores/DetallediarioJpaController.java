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
import Modelos.Cuenta;
import Modelos.Detallediario;
import Modelos.Diario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class DetallediarioJpaController implements Serializable {

    public DetallediarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallediario detallediario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta codcuenta = detallediario.getCodcuenta();
            if (codcuenta != null) {
                codcuenta = em.getReference(codcuenta.getClass(), codcuenta.getCodcuenta());
                detallediario.setCodcuenta(codcuenta);
            }
            Diario idregistro = detallediario.getIdregistro();
            if (idregistro != null) {
                idregistro = em.getReference(idregistro.getClass(), idregistro.getIdregistro());
                detallediario.setIdregistro(idregistro);
            }
            em.persist(detallediario);
            if (codcuenta != null) {
                codcuenta.getDetallediarioList().add(detallediario);
                codcuenta = em.merge(codcuenta);
            }
            if (idregistro != null) {
                idregistro.getDetallediarioList().add(detallediario);
                idregistro = em.merge(idregistro);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallediario detallediario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallediario persistentDetallediario = em.find(Detallediario.class, detallediario.getIddetalle());
            Cuenta codcuentaOld = persistentDetallediario.getCodcuenta();
            Cuenta codcuentaNew = detallediario.getCodcuenta();
            Diario idregistroOld = persistentDetallediario.getIdregistro();
            Diario idregistroNew = detallediario.getIdregistro();
            if (codcuentaNew != null) {
                codcuentaNew = em.getReference(codcuentaNew.getClass(), codcuentaNew.getCodcuenta());
                detallediario.setCodcuenta(codcuentaNew);
            }
            if (idregistroNew != null) {
                idregistroNew = em.getReference(idregistroNew.getClass(), idregistroNew.getIdregistro());
                detallediario.setIdregistro(idregistroNew);
            }
            detallediario = em.merge(detallediario);
            if (codcuentaOld != null && !codcuentaOld.equals(codcuentaNew)) {
                codcuentaOld.getDetallediarioList().remove(detallediario);
                codcuentaOld = em.merge(codcuentaOld);
            }
            if (codcuentaNew != null && !codcuentaNew.equals(codcuentaOld)) {
                codcuentaNew.getDetallediarioList().add(detallediario);
                codcuentaNew = em.merge(codcuentaNew);
            }
            if (idregistroOld != null && !idregistroOld.equals(idregistroNew)) {
                idregistroOld.getDetallediarioList().remove(detallediario);
                idregistroOld = em.merge(idregistroOld);
            }
            if (idregistroNew != null && !idregistroNew.equals(idregistroOld)) {
                idregistroNew.getDetallediarioList().add(detallediario);
                idregistroNew = em.merge(idregistroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallediario.getIddetalle();
                if (findDetallediario(id) == null) {
                    throw new NonexistentEntityException("The detallediario with id " + id + " no longer exists.");
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
            Detallediario detallediario;
            try {
                detallediario = em.getReference(Detallediario.class, id);
                detallediario.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallediario with id " + id + " no longer exists.", enfe);
            }
            Cuenta codcuenta = detallediario.getCodcuenta();
            if (codcuenta != null) {
                codcuenta.getDetallediarioList().remove(detallediario);
                codcuenta = em.merge(codcuenta);
            }
            Diario idregistro = detallediario.getIdregistro();
            if (idregistro != null) {
                idregistro.getDetallediarioList().remove(detallediario);
                idregistro = em.merge(idregistro);
            }
            em.remove(detallediario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallediario> findDetallediarioEntities() {
        return findDetallediarioEntities(true, -1, -1);
    }

    public List<Detallediario> findDetallediarioEntities(int maxResults, int firstResult) {
        return findDetallediarioEntities(false, maxResults, firstResult);
    }

    private List<Detallediario> findDetallediarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallediario.class));
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

    public Detallediario findDetallediario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallediario.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallediarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallediario> rt = cq.from(Detallediario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
