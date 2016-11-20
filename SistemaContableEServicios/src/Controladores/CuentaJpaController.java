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
import Modelos.Baseprorrateo;
import Modelos.Catalogocuenta;
import Modelos.Cuenta;
import Modelos.Tipocuenta;
import Modelos.Detallediario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class CuentaJpaController implements Serializable {

    public CuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuenta cuenta) throws PreexistingEntityException, Exception {
        if (cuenta.getDetallediarioList() == null) {
            cuenta.setDetallediarioList(new ArrayList<Detallediario>());
        }
        if (cuenta.getCuentaList() == null) {
            cuenta.setCuentaList(new ArrayList<Cuenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baseprorrateo idbase = cuenta.getIdbase();
            if (idbase != null) {
                idbase = em.getReference(idbase.getClass(), idbase.getIdbase());
                cuenta.setIdbase(idbase);
            }
            Catalogocuenta idcatalogo = cuenta.getIdcatalogo();
            if (idcatalogo != null) {
                idcatalogo = em.getReference(idcatalogo.getClass(), idcatalogo.getIdcatalogo());
                cuenta.setIdcatalogo(idcatalogo);
            }
            Cuenta cueCodcuenta = cuenta.getCueCodcuenta();
            if (cueCodcuenta != null) {
                cueCodcuenta = em.getReference(cueCodcuenta.getClass(), cueCodcuenta.getCodcuenta());
                cuenta.setCueCodcuenta(cueCodcuenta);
            }
            Tipocuenta idtipocuenta = cuenta.getIdtipocuenta();
            if (idtipocuenta != null) {
                idtipocuenta = em.getReference(idtipocuenta.getClass(), idtipocuenta.getIdtipocuenta());
                cuenta.setIdtipocuenta(idtipocuenta);
            }
            List<Detallediario> attachedDetallediarioList = new ArrayList<Detallediario>();
            for (Detallediario detallediarioListDetallediarioToAttach : cuenta.getDetallediarioList()) {
                detallediarioListDetallediarioToAttach = em.getReference(detallediarioListDetallediarioToAttach.getClass(), detallediarioListDetallediarioToAttach.getIddetalle());
                attachedDetallediarioList.add(detallediarioListDetallediarioToAttach);
            }
            cuenta.setDetallediarioList(attachedDetallediarioList);
            List<Cuenta> attachedCuentaList = new ArrayList<Cuenta>();
            for (Cuenta cuentaListCuentaToAttach : cuenta.getCuentaList()) {
                cuentaListCuentaToAttach = em.getReference(cuentaListCuentaToAttach.getClass(), cuentaListCuentaToAttach.getCodcuenta());
                attachedCuentaList.add(cuentaListCuentaToAttach);
            }
            cuenta.setCuentaList(attachedCuentaList);
            em.persist(cuenta);
            if (idbase != null) {
                idbase.getCuentaList().add(cuenta);
                idbase = em.merge(idbase);
            }
            if (idcatalogo != null) {
                idcatalogo.getCuentaList().add(cuenta);
                idcatalogo = em.merge(idcatalogo);
            }
            if (cueCodcuenta != null) {
                cueCodcuenta.getCuentaList().add(cuenta);
                cueCodcuenta = em.merge(cueCodcuenta);
            }
            if (idtipocuenta != null) {
                idtipocuenta.getCuentaList().add(cuenta);
                idtipocuenta = em.merge(idtipocuenta);
            }
            for (Detallediario detallediarioListDetallediario : cuenta.getDetallediarioList()) {
                Cuenta oldCodcuentaOfDetallediarioListDetallediario = detallediarioListDetallediario.getCodcuenta();
                detallediarioListDetallediario.setCodcuenta(cuenta);
                detallediarioListDetallediario = em.merge(detallediarioListDetallediario);
                if (oldCodcuentaOfDetallediarioListDetallediario != null) {
                    oldCodcuentaOfDetallediarioListDetallediario.getDetallediarioList().remove(detallediarioListDetallediario);
                    oldCodcuentaOfDetallediarioListDetallediario = em.merge(oldCodcuentaOfDetallediarioListDetallediario);
                }
            }
            for (Cuenta cuentaListCuenta : cuenta.getCuentaList()) {
                Cuenta oldCueCodcuentaOfCuentaListCuenta = cuentaListCuenta.getCueCodcuenta();
                cuentaListCuenta.setCueCodcuenta(cuenta);
                cuentaListCuenta = em.merge(cuentaListCuenta);
                if (oldCueCodcuentaOfCuentaListCuenta != null) {
                    oldCueCodcuentaOfCuentaListCuenta.getCuentaList().remove(cuentaListCuenta);
                    oldCueCodcuentaOfCuentaListCuenta = em.merge(oldCueCodcuentaOfCuentaListCuenta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCuenta(cuenta.getCodcuenta()) != null) {
                throw new PreexistingEntityException("Cuenta " + cuenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuenta cuenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta persistentCuenta = em.find(Cuenta.class, cuenta.getCodcuenta());
            Baseprorrateo idbaseOld = persistentCuenta.getIdbase();
            Baseprorrateo idbaseNew = cuenta.getIdbase();
            Catalogocuenta idcatalogoOld = persistentCuenta.getIdcatalogo();
            Catalogocuenta idcatalogoNew = cuenta.getIdcatalogo();
            Cuenta cueCodcuentaOld = persistentCuenta.getCueCodcuenta();
            Cuenta cueCodcuentaNew = cuenta.getCueCodcuenta();
            Tipocuenta idtipocuentaOld = persistentCuenta.getIdtipocuenta();
            Tipocuenta idtipocuentaNew = cuenta.getIdtipocuenta();
            List<Detallediario> detallediarioListOld = persistentCuenta.getDetallediarioList();
            List<Detallediario> detallediarioListNew = cuenta.getDetallediarioList();
            List<Cuenta> cuentaListOld = persistentCuenta.getCuentaList();
            List<Cuenta> cuentaListNew = cuenta.getCuentaList();
            List<String> illegalOrphanMessages = null;
            for (Detallediario detallediarioListOldDetallediario : detallediarioListOld) {
                if (!detallediarioListNew.contains(detallediarioListOldDetallediario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallediario " + detallediarioListOldDetallediario + " since its codcuenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idbaseNew != null) {
                idbaseNew = em.getReference(idbaseNew.getClass(), idbaseNew.getIdbase());
                cuenta.setIdbase(idbaseNew);
            }
            if (idcatalogoNew != null) {
                idcatalogoNew = em.getReference(idcatalogoNew.getClass(), idcatalogoNew.getIdcatalogo());
                cuenta.setIdcatalogo(idcatalogoNew);
            }
            if (cueCodcuentaNew != null) {
                cueCodcuentaNew = em.getReference(cueCodcuentaNew.getClass(), cueCodcuentaNew.getCodcuenta());
                cuenta.setCueCodcuenta(cueCodcuentaNew);
            }
            if (idtipocuentaNew != null) {
                idtipocuentaNew = em.getReference(idtipocuentaNew.getClass(), idtipocuentaNew.getIdtipocuenta());
                cuenta.setIdtipocuenta(idtipocuentaNew);
            }
            List<Detallediario> attachedDetallediarioListNew = new ArrayList<Detallediario>();
            for (Detallediario detallediarioListNewDetallediarioToAttach : detallediarioListNew) {
                detallediarioListNewDetallediarioToAttach = em.getReference(detallediarioListNewDetallediarioToAttach.getClass(), detallediarioListNewDetallediarioToAttach.getIddetalle());
                attachedDetallediarioListNew.add(detallediarioListNewDetallediarioToAttach);
            }
            detallediarioListNew = attachedDetallediarioListNew;
            cuenta.setDetallediarioList(detallediarioListNew);
            List<Cuenta> attachedCuentaListNew = new ArrayList<Cuenta>();
            for (Cuenta cuentaListNewCuentaToAttach : cuentaListNew) {
                cuentaListNewCuentaToAttach = em.getReference(cuentaListNewCuentaToAttach.getClass(), cuentaListNewCuentaToAttach.getCodcuenta());
                attachedCuentaListNew.add(cuentaListNewCuentaToAttach);
            }
            cuentaListNew = attachedCuentaListNew;
            cuenta.setCuentaList(cuentaListNew);
            cuenta = em.merge(cuenta);
            if (idbaseOld != null && !idbaseOld.equals(idbaseNew)) {
                idbaseOld.getCuentaList().remove(cuenta);
                idbaseOld = em.merge(idbaseOld);
            }
            if (idbaseNew != null && !idbaseNew.equals(idbaseOld)) {
                idbaseNew.getCuentaList().add(cuenta);
                idbaseNew = em.merge(idbaseNew);
            }
            if (idcatalogoOld != null && !idcatalogoOld.equals(idcatalogoNew)) {
                idcatalogoOld.getCuentaList().remove(cuenta);
                idcatalogoOld = em.merge(idcatalogoOld);
            }
            if (idcatalogoNew != null && !idcatalogoNew.equals(idcatalogoOld)) {
                idcatalogoNew.getCuentaList().add(cuenta);
                idcatalogoNew = em.merge(idcatalogoNew);
            }
            if (cueCodcuentaOld != null && !cueCodcuentaOld.equals(cueCodcuentaNew)) {
                cueCodcuentaOld.getCuentaList().remove(cuenta);
                cueCodcuentaOld = em.merge(cueCodcuentaOld);
            }
            if (cueCodcuentaNew != null && !cueCodcuentaNew.equals(cueCodcuentaOld)) {
                cueCodcuentaNew.getCuentaList().add(cuenta);
                cueCodcuentaNew = em.merge(cueCodcuentaNew);
            }
            if (idtipocuentaOld != null && !idtipocuentaOld.equals(idtipocuentaNew)) {
                idtipocuentaOld.getCuentaList().remove(cuenta);
                idtipocuentaOld = em.merge(idtipocuentaOld);
            }
            if (idtipocuentaNew != null && !idtipocuentaNew.equals(idtipocuentaOld)) {
                idtipocuentaNew.getCuentaList().add(cuenta);
                idtipocuentaNew = em.merge(idtipocuentaNew);
            }
            for (Detallediario detallediarioListNewDetallediario : detallediarioListNew) {
                if (!detallediarioListOld.contains(detallediarioListNewDetallediario)) {
                    Cuenta oldCodcuentaOfDetallediarioListNewDetallediario = detallediarioListNewDetallediario.getCodcuenta();
                    detallediarioListNewDetallediario.setCodcuenta(cuenta);
                    detallediarioListNewDetallediario = em.merge(detallediarioListNewDetallediario);
                    if (oldCodcuentaOfDetallediarioListNewDetallediario != null && !oldCodcuentaOfDetallediarioListNewDetallediario.equals(cuenta)) {
                        oldCodcuentaOfDetallediarioListNewDetallediario.getDetallediarioList().remove(detallediarioListNewDetallediario);
                        oldCodcuentaOfDetallediarioListNewDetallediario = em.merge(oldCodcuentaOfDetallediarioListNewDetallediario);
                    }
                }
            }
            for (Cuenta cuentaListOldCuenta : cuentaListOld) {
                if (!cuentaListNew.contains(cuentaListOldCuenta)) {
                    cuentaListOldCuenta.setCueCodcuenta(null);
                    cuentaListOldCuenta = em.merge(cuentaListOldCuenta);
                }
            }
            for (Cuenta cuentaListNewCuenta : cuentaListNew) {
                if (!cuentaListOld.contains(cuentaListNewCuenta)) {
                    Cuenta oldCueCodcuentaOfCuentaListNewCuenta = cuentaListNewCuenta.getCueCodcuenta();
                    cuentaListNewCuenta.setCueCodcuenta(cuenta);
                    cuentaListNewCuenta = em.merge(cuentaListNewCuenta);
                    if (oldCueCodcuentaOfCuentaListNewCuenta != null && !oldCueCodcuentaOfCuentaListNewCuenta.equals(cuenta)) {
                        oldCueCodcuentaOfCuentaListNewCuenta.getCuentaList().remove(cuentaListNewCuenta);
                        oldCueCodcuentaOfCuentaListNewCuenta = em.merge(oldCueCodcuentaOfCuentaListNewCuenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cuenta.getCodcuenta();
                if (findCuenta(id) == null) {
                    throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.");
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
            Cuenta cuenta;
            try {
                cuenta = em.getReference(Cuenta.class, id);
                cuenta.getCodcuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallediario> detallediarioListOrphanCheck = cuenta.getDetallediarioList();
            for (Detallediario detallediarioListOrphanCheckDetallediario : detallediarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuenta (" + cuenta + ") cannot be destroyed since the Detallediario " + detallediarioListOrphanCheckDetallediario + " in its detallediarioList field has a non-nullable codcuenta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Baseprorrateo idbase = cuenta.getIdbase();
            if (idbase != null) {
                idbase.getCuentaList().remove(cuenta);
                idbase = em.merge(idbase);
            }
            Catalogocuenta idcatalogo = cuenta.getIdcatalogo();
            if (idcatalogo != null) {
                idcatalogo.getCuentaList().remove(cuenta);
                idcatalogo = em.merge(idcatalogo);
            }
            Cuenta cueCodcuenta = cuenta.getCueCodcuenta();
            if (cueCodcuenta != null) {
                cueCodcuenta.getCuentaList().remove(cuenta);
                cueCodcuenta = em.merge(cueCodcuenta);
            }
            Tipocuenta idtipocuenta = cuenta.getIdtipocuenta();
            if (idtipocuenta != null) {
                idtipocuenta.getCuentaList().remove(cuenta);
                idtipocuenta = em.merge(idtipocuenta);
            }
            List<Cuenta> cuentaList = cuenta.getCuentaList();
            for (Cuenta cuentaListCuenta : cuentaList) {
                cuentaListCuenta.setCueCodcuenta(null);
                cuentaListCuenta = em.merge(cuentaListCuenta);
            }
            em.remove(cuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuenta> findCuentaEntities() {
        return findCuentaEntities(true, -1, -1);
    }

    public List<Cuenta> findCuentaEntities(int maxResults, int firstResult) {
        return findCuentaEntities(false, maxResults, firstResult);
    }

    private List<Cuenta> findCuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuenta.class));
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

    public Cuenta findCuenta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuenta> rt = cq.from(Cuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
