/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Cuenta;
import Modelos.Tipocuenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class TipocuentaJpaController implements Serializable {

    public TipocuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipocuenta tipocuenta) throws PreexistingEntityException, Exception {
        if (tipocuenta.getCuentaList() == null) {
            tipocuenta.setCuentaList(new ArrayList<Cuenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cuenta> attachedCuentaList = new ArrayList<Cuenta>();
            for (Cuenta cuentaListCuentaToAttach : tipocuenta.getCuentaList()) {
                cuentaListCuentaToAttach = em.getReference(cuentaListCuentaToAttach.getClass(), cuentaListCuentaToAttach.getCodcuenta());
                attachedCuentaList.add(cuentaListCuentaToAttach);
            }
            tipocuenta.setCuentaList(attachedCuentaList);
            em.persist(tipocuenta);
            for (Cuenta cuentaListCuenta : tipocuenta.getCuentaList()) {
                Tipocuenta oldIdtipocuentaOfCuentaListCuenta = cuentaListCuenta.getIdtipocuenta();
                cuentaListCuenta.setIdtipocuenta(tipocuenta);
                cuentaListCuenta = em.merge(cuentaListCuenta);
                if (oldIdtipocuentaOfCuentaListCuenta != null) {
                    oldIdtipocuentaOfCuentaListCuenta.getCuentaList().remove(cuentaListCuenta);
                    oldIdtipocuentaOfCuentaListCuenta = em.merge(oldIdtipocuentaOfCuentaListCuenta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipocuenta(tipocuenta.getIdtipocuenta()) != null) {
                throw new PreexistingEntityException("Tipocuenta " + tipocuenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipocuenta tipocuenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocuenta persistentTipocuenta = em.find(Tipocuenta.class, tipocuenta.getIdtipocuenta());
            List<Cuenta> cuentaListOld = persistentTipocuenta.getCuentaList();
            List<Cuenta> cuentaListNew = tipocuenta.getCuentaList();
            List<String> illegalOrphanMessages = null;
            for (Cuenta cuentaListOldCuenta : cuentaListOld) {
                if (!cuentaListNew.contains(cuentaListOldCuenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cuenta " + cuentaListOldCuenta + " since its idtipocuenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cuenta> attachedCuentaListNew = new ArrayList<Cuenta>();
            for (Cuenta cuentaListNewCuentaToAttach : cuentaListNew) {
                cuentaListNewCuentaToAttach = em.getReference(cuentaListNewCuentaToAttach.getClass(), cuentaListNewCuentaToAttach.getCodcuenta());
                attachedCuentaListNew.add(cuentaListNewCuentaToAttach);
            }
            cuentaListNew = attachedCuentaListNew;
            tipocuenta.setCuentaList(cuentaListNew);
            tipocuenta = em.merge(tipocuenta);
            for (Cuenta cuentaListNewCuenta : cuentaListNew) {
                if (!cuentaListOld.contains(cuentaListNewCuenta)) {
                    Tipocuenta oldIdtipocuentaOfCuentaListNewCuenta = cuentaListNewCuenta.getIdtipocuenta();
                    cuentaListNewCuenta.setIdtipocuenta(tipocuenta);
                    cuentaListNewCuenta = em.merge(cuentaListNewCuenta);
                    if (oldIdtipocuentaOfCuentaListNewCuenta != null && !oldIdtipocuentaOfCuentaListNewCuenta.equals(tipocuenta)) {
                        oldIdtipocuentaOfCuentaListNewCuenta.getCuentaList().remove(cuentaListNewCuenta);
                        oldIdtipocuentaOfCuentaListNewCuenta = em.merge(oldIdtipocuentaOfCuentaListNewCuenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipocuenta.getIdtipocuenta();
                if (findTipocuenta(id) == null) {
                    throw new NonexistentEntityException("The tipocuenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocuenta tipocuenta;
            try {
                tipocuenta = em.getReference(Tipocuenta.class, id);
                tipocuenta.getIdtipocuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipocuenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cuenta> cuentaListOrphanCheck = tipocuenta.getCuentaList();
            for (Cuenta cuentaListOrphanCheckCuenta : cuentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipocuenta (" + tipocuenta + ") cannot be destroyed since the Cuenta " + cuentaListOrphanCheckCuenta + " in its cuentaList field has a non-nullable idtipocuenta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipocuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipocuenta> findTipocuentaEntities() {
        return findTipocuentaEntities(true, -1, -1);
    }

    public List<Tipocuenta> findTipocuentaEntities(int maxResults, int firstResult) {
        return findTipocuentaEntities(false, maxResults, firstResult);
    }

    private List<Tipocuenta> findTipocuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipocuenta.class));
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

    public Tipocuenta findTipocuenta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipocuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipocuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipocuenta> rt = cq.from(Tipocuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
