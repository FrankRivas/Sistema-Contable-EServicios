/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Diario;
import Modelos.Periodocontable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class PeriodocontableJpaController implements Serializable {

    public PeriodocontableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodocontable periodocontable) {
        if (periodocontable.getDiarioList() == null) {
            periodocontable.setDiarioList(new ArrayList<Diario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Diario> attachedDiarioList = new ArrayList<Diario>();
            for (Diario diarioListDiarioToAttach : periodocontable.getDiarioList()) {
                diarioListDiarioToAttach = em.getReference(diarioListDiarioToAttach.getClass(), diarioListDiarioToAttach.getIdregistro());
                attachedDiarioList.add(diarioListDiarioToAttach);
            }
            periodocontable.setDiarioList(attachedDiarioList);
            em.persist(periodocontable);
            for (Diario diarioListDiario : periodocontable.getDiarioList()) {
                Periodocontable oldIdperiodoOfDiarioListDiario = diarioListDiario.getIdperiodo();
                diarioListDiario.setIdperiodo(periodocontable);
                diarioListDiario = em.merge(diarioListDiario);
                if (oldIdperiodoOfDiarioListDiario != null) {
                    oldIdperiodoOfDiarioListDiario.getDiarioList().remove(diarioListDiario);
                    oldIdperiodoOfDiarioListDiario = em.merge(oldIdperiodoOfDiarioListDiario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodocontable periodocontable) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodocontable persistentPeriodocontable = em.find(Periodocontable.class, periodocontable.getIdperiodo());
            List<Diario> diarioListOld = persistentPeriodocontable.getDiarioList();
            List<Diario> diarioListNew = periodocontable.getDiarioList();
            List<String> illegalOrphanMessages = null;
            for (Diario diarioListOldDiario : diarioListOld) {
                if (!diarioListNew.contains(diarioListOldDiario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Diario " + diarioListOldDiario + " since its idperiodo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Diario> attachedDiarioListNew = new ArrayList<Diario>();
            for (Diario diarioListNewDiarioToAttach : diarioListNew) {
                diarioListNewDiarioToAttach = em.getReference(diarioListNewDiarioToAttach.getClass(), diarioListNewDiarioToAttach.getIdregistro());
                attachedDiarioListNew.add(diarioListNewDiarioToAttach);
            }
            diarioListNew = attachedDiarioListNew;
            periodocontable.setDiarioList(diarioListNew);
            periodocontable = em.merge(periodocontable);
            for (Diario diarioListNewDiario : diarioListNew) {
                if (!diarioListOld.contains(diarioListNewDiario)) {
                    Periodocontable oldIdperiodoOfDiarioListNewDiario = diarioListNewDiario.getIdperiodo();
                    diarioListNewDiario.setIdperiodo(periodocontable);
                    diarioListNewDiario = em.merge(diarioListNewDiario);
                    if (oldIdperiodoOfDiarioListNewDiario != null && !oldIdperiodoOfDiarioListNewDiario.equals(periodocontable)) {
                        oldIdperiodoOfDiarioListNewDiario.getDiarioList().remove(diarioListNewDiario);
                        oldIdperiodoOfDiarioListNewDiario = em.merge(oldIdperiodoOfDiarioListNewDiario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = periodocontable.getIdperiodo();
                if (findPeriodocontable(id) == null) {
                    throw new NonexistentEntityException("The periodocontable with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodocontable periodocontable;
            try {
                periodocontable = em.getReference(Periodocontable.class, id);
                periodocontable.getIdperiodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodocontable with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Diario> diarioListOrphanCheck = periodocontable.getDiarioList();
            for (Diario diarioListOrphanCheckDiario : diarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodocontable (" + periodocontable + ") cannot be destroyed since the Diario " + diarioListOrphanCheckDiario + " in its diarioList field has a non-nullable idperiodo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(periodocontable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodocontable> findPeriodocontableEntities() {
        return findPeriodocontableEntities(true, -1, -1);
    }

    public List<Periodocontable> findPeriodocontableEntities(int maxResults, int firstResult) {
        return findPeriodocontableEntities(false, maxResults, firstResult);
    }

    private List<Periodocontable> findPeriodocontableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodocontable.class));
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

    public Periodocontable findPeriodocontable(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodocontable.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodocontableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periodocontable> rt = cq.from(Periodocontable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
