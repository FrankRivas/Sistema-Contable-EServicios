/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Modelos.Baseprorrateo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Centrodecosto;
import java.util.ArrayList;
import java.util.List;
import Modelos.Cuenta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class BaseprorrateoJpaController implements Serializable {

    public BaseprorrateoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Baseprorrateo baseprorrateo) {
        if (baseprorrateo.getCentrodecostoList() == null) {
            baseprorrateo.setCentrodecostoList(new ArrayList<Centrodecosto>());
        }
        if (baseprorrateo.getCuentaList() == null) {
            baseprorrateo.setCuentaList(new ArrayList<Cuenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Centrodecosto> attachedCentrodecostoList = new ArrayList<Centrodecosto>();
            for (Centrodecosto centrodecostoListCentrodecostoToAttach : baseprorrateo.getCentrodecostoList()) {
                centrodecostoListCentrodecostoToAttach = em.getReference(centrodecostoListCentrodecostoToAttach.getClass(), centrodecostoListCentrodecostoToAttach.getIdcentro());
                attachedCentrodecostoList.add(centrodecostoListCentrodecostoToAttach);
            }
            baseprorrateo.setCentrodecostoList(attachedCentrodecostoList);
            List<Cuenta> attachedCuentaList = new ArrayList<Cuenta>();
            for (Cuenta cuentaListCuentaToAttach : baseprorrateo.getCuentaList()) {
                cuentaListCuentaToAttach = em.getReference(cuentaListCuentaToAttach.getClass(), cuentaListCuentaToAttach.getCodcuenta());
                attachedCuentaList.add(cuentaListCuentaToAttach);
            }
            baseprorrateo.setCuentaList(attachedCuentaList);
            em.persist(baseprorrateo);
            for (Centrodecosto centrodecostoListCentrodecosto : baseprorrateo.getCentrodecostoList()) {
                centrodecostoListCentrodecosto.getBaseprorrateoList().add(baseprorrateo);
                centrodecostoListCentrodecosto = em.merge(centrodecostoListCentrodecosto);
            }
            for (Cuenta cuentaListCuenta : baseprorrateo.getCuentaList()) {
                Baseprorrateo oldIdbaseOfCuentaListCuenta = cuentaListCuenta.getIdbase();
                cuentaListCuenta.setIdbase(baseprorrateo);
                cuentaListCuenta = em.merge(cuentaListCuenta);
                if (oldIdbaseOfCuentaListCuenta != null) {
                    oldIdbaseOfCuentaListCuenta.getCuentaList().remove(cuentaListCuenta);
                    oldIdbaseOfCuentaListCuenta = em.merge(oldIdbaseOfCuentaListCuenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Baseprorrateo baseprorrateo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baseprorrateo persistentBaseprorrateo = em.find(Baseprorrateo.class, baseprorrateo.getIdbase());
            List<Centrodecosto> centrodecostoListOld = persistentBaseprorrateo.getCentrodecostoList();
            List<Centrodecosto> centrodecostoListNew = baseprorrateo.getCentrodecostoList();
            List<Cuenta> cuentaListOld = persistentBaseprorrateo.getCuentaList();
            List<Cuenta> cuentaListNew = baseprorrateo.getCuentaList();
            List<Centrodecosto> attachedCentrodecostoListNew = new ArrayList<Centrodecosto>();
            for (Centrodecosto centrodecostoListNewCentrodecostoToAttach : centrodecostoListNew) {
                centrodecostoListNewCentrodecostoToAttach = em.getReference(centrodecostoListNewCentrodecostoToAttach.getClass(), centrodecostoListNewCentrodecostoToAttach.getIdcentro());
                attachedCentrodecostoListNew.add(centrodecostoListNewCentrodecostoToAttach);
            }
            centrodecostoListNew = attachedCentrodecostoListNew;
            baseprorrateo.setCentrodecostoList(centrodecostoListNew);
            List<Cuenta> attachedCuentaListNew = new ArrayList<Cuenta>();
            for (Cuenta cuentaListNewCuentaToAttach : cuentaListNew) {
                cuentaListNewCuentaToAttach = em.getReference(cuentaListNewCuentaToAttach.getClass(), cuentaListNewCuentaToAttach.getCodcuenta());
                attachedCuentaListNew.add(cuentaListNewCuentaToAttach);
            }
            cuentaListNew = attachedCuentaListNew;
            baseprorrateo.setCuentaList(cuentaListNew);
            baseprorrateo = em.merge(baseprorrateo);
            for (Centrodecosto centrodecostoListOldCentrodecosto : centrodecostoListOld) {
                if (!centrodecostoListNew.contains(centrodecostoListOldCentrodecosto)) {
                    centrodecostoListOldCentrodecosto.getBaseprorrateoList().remove(baseprorrateo);
                    centrodecostoListOldCentrodecosto = em.merge(centrodecostoListOldCentrodecosto);
                }
            }
            for (Centrodecosto centrodecostoListNewCentrodecosto : centrodecostoListNew) {
                if (!centrodecostoListOld.contains(centrodecostoListNewCentrodecosto)) {
                    centrodecostoListNewCentrodecosto.getBaseprorrateoList().add(baseprorrateo);
                    centrodecostoListNewCentrodecosto = em.merge(centrodecostoListNewCentrodecosto);
                }
            }
            for (Cuenta cuentaListOldCuenta : cuentaListOld) {
                if (!cuentaListNew.contains(cuentaListOldCuenta)) {
                    cuentaListOldCuenta.setIdbase(null);
                    cuentaListOldCuenta = em.merge(cuentaListOldCuenta);
                }
            }
            for (Cuenta cuentaListNewCuenta : cuentaListNew) {
                if (!cuentaListOld.contains(cuentaListNewCuenta)) {
                    Baseprorrateo oldIdbaseOfCuentaListNewCuenta = cuentaListNewCuenta.getIdbase();
                    cuentaListNewCuenta.setIdbase(baseprorrateo);
                    cuentaListNewCuenta = em.merge(cuentaListNewCuenta);
                    if (oldIdbaseOfCuentaListNewCuenta != null && !oldIdbaseOfCuentaListNewCuenta.equals(baseprorrateo)) {
                        oldIdbaseOfCuentaListNewCuenta.getCuentaList().remove(cuentaListNewCuenta);
                        oldIdbaseOfCuentaListNewCuenta = em.merge(oldIdbaseOfCuentaListNewCuenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baseprorrateo.getIdbase();
                if (findBaseprorrateo(id) == null) {
                    throw new NonexistentEntityException("The baseprorrateo with id " + id + " no longer exists.");
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
            Baseprorrateo baseprorrateo;
            try {
                baseprorrateo = em.getReference(Baseprorrateo.class, id);
                baseprorrateo.getIdbase();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baseprorrateo with id " + id + " no longer exists.", enfe);
            }
            List<Centrodecosto> centrodecostoList = baseprorrateo.getCentrodecostoList();
            for (Centrodecosto centrodecostoListCentrodecosto : centrodecostoList) {
                centrodecostoListCentrodecosto.getBaseprorrateoList().remove(baseprorrateo);
                centrodecostoListCentrodecosto = em.merge(centrodecostoListCentrodecosto);
            }
            List<Cuenta> cuentaList = baseprorrateo.getCuentaList();
            for (Cuenta cuentaListCuenta : cuentaList) {
                cuentaListCuenta.setIdbase(null);
                cuentaListCuenta = em.merge(cuentaListCuenta);
            }
            em.remove(baseprorrateo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Baseprorrateo> findBaseprorrateoEntities() {
        return findBaseprorrateoEntities(true, -1, -1);
    }

    public List<Baseprorrateo> findBaseprorrateoEntities(int maxResults, int firstResult) {
        return findBaseprorrateoEntities(false, maxResults, firstResult);
    }

    private List<Baseprorrateo> findBaseprorrateoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Baseprorrateo.class));
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

    public Baseprorrateo findBaseprorrateo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Baseprorrateo.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaseprorrateoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Baseprorrateo> rt = cq.from(Baseprorrateo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
