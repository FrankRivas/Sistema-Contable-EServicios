/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Modelos.Catalogocuenta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Empresa;
import Modelos.Cuenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class CatalogocuentaJpaController implements Serializable {

    public CatalogocuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catalogocuenta catalogocuenta) {
        if (catalogocuenta.getCuentaList() == null) {
            catalogocuenta.setCuentaList(new ArrayList<Cuenta>());
        }
        if (catalogocuenta.getEmpresaList() == null) {
            catalogocuenta.setEmpresaList(new ArrayList<Empresa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa idempresa = catalogocuenta.getIdempresa();
            if (idempresa != null) {
                idempresa = em.getReference(idempresa.getClass(), idempresa.getIdempresa());
                catalogocuenta.setIdempresa(idempresa);
            }
            List<Cuenta> attachedCuentaList = new ArrayList<Cuenta>();
            for (Cuenta cuentaListCuentaToAttach : catalogocuenta.getCuentaList()) {
                cuentaListCuentaToAttach = em.getReference(cuentaListCuentaToAttach.getClass(), cuentaListCuentaToAttach.getCodcuenta());
                attachedCuentaList.add(cuentaListCuentaToAttach);
            }
            catalogocuenta.setCuentaList(attachedCuentaList);
            List<Empresa> attachedEmpresaList = new ArrayList<Empresa>();
            for (Empresa empresaListEmpresaToAttach : catalogocuenta.getEmpresaList()) {
                empresaListEmpresaToAttach = em.getReference(empresaListEmpresaToAttach.getClass(), empresaListEmpresaToAttach.getIdempresa());
                attachedEmpresaList.add(empresaListEmpresaToAttach);
            }
            catalogocuenta.setEmpresaList(attachedEmpresaList);
            em.persist(catalogocuenta);
            if (idempresa != null) {
                idempresa.getCatalogocuentaList().add(catalogocuenta);
                idempresa = em.merge(idempresa);
            }
            for (Cuenta cuentaListCuenta : catalogocuenta.getCuentaList()) {
                Catalogocuenta oldIdcatalogoOfCuentaListCuenta = cuentaListCuenta.getIdcatalogo();
                cuentaListCuenta.setIdcatalogo(catalogocuenta);
                cuentaListCuenta = em.merge(cuentaListCuenta);
                if (oldIdcatalogoOfCuentaListCuenta != null) {
                    oldIdcatalogoOfCuentaListCuenta.getCuentaList().remove(cuentaListCuenta);
                    oldIdcatalogoOfCuentaListCuenta = em.merge(oldIdcatalogoOfCuentaListCuenta);
                }
            }
            for (Empresa empresaListEmpresa : catalogocuenta.getEmpresaList()) {
                Catalogocuenta oldIdcatalogoOfEmpresaListEmpresa = empresaListEmpresa.getIdcatalogo();
                empresaListEmpresa.setIdcatalogo(catalogocuenta);
                empresaListEmpresa = em.merge(empresaListEmpresa);
                if (oldIdcatalogoOfEmpresaListEmpresa != null) {
                    oldIdcatalogoOfEmpresaListEmpresa.getEmpresaList().remove(empresaListEmpresa);
                    oldIdcatalogoOfEmpresaListEmpresa = em.merge(oldIdcatalogoOfEmpresaListEmpresa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catalogocuenta catalogocuenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catalogocuenta persistentCatalogocuenta = em.find(Catalogocuenta.class, catalogocuenta.getIdcatalogo());
            Empresa idempresaOld = persistentCatalogocuenta.getIdempresa();
            Empresa idempresaNew = catalogocuenta.getIdempresa();
            List<Cuenta> cuentaListOld = persistentCatalogocuenta.getCuentaList();
            List<Cuenta> cuentaListNew = catalogocuenta.getCuentaList();
            List<Empresa> empresaListOld = persistentCatalogocuenta.getEmpresaList();
            List<Empresa> empresaListNew = catalogocuenta.getEmpresaList();
            List<String> illegalOrphanMessages = null;
            for (Cuenta cuentaListOldCuenta : cuentaListOld) {
                if (!cuentaListNew.contains(cuentaListOldCuenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cuenta " + cuentaListOldCuenta + " since its idcatalogo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idempresaNew != null) {
                idempresaNew = em.getReference(idempresaNew.getClass(), idempresaNew.getIdempresa());
                catalogocuenta.setIdempresa(idempresaNew);
            }
            List<Cuenta> attachedCuentaListNew = new ArrayList<Cuenta>();
            for (Cuenta cuentaListNewCuentaToAttach : cuentaListNew) {
                cuentaListNewCuentaToAttach = em.getReference(cuentaListNewCuentaToAttach.getClass(), cuentaListNewCuentaToAttach.getCodcuenta());
                attachedCuentaListNew.add(cuentaListNewCuentaToAttach);
            }
            cuentaListNew = attachedCuentaListNew;
            catalogocuenta.setCuentaList(cuentaListNew);
            List<Empresa> attachedEmpresaListNew = new ArrayList<Empresa>();
            for (Empresa empresaListNewEmpresaToAttach : empresaListNew) {
                empresaListNewEmpresaToAttach = em.getReference(empresaListNewEmpresaToAttach.getClass(), empresaListNewEmpresaToAttach.getIdempresa());
                attachedEmpresaListNew.add(empresaListNewEmpresaToAttach);
            }
            empresaListNew = attachedEmpresaListNew;
            catalogocuenta.setEmpresaList(empresaListNew);
            catalogocuenta = em.merge(catalogocuenta);
            if (idempresaOld != null && !idempresaOld.equals(idempresaNew)) {
                idempresaOld.getCatalogocuentaList().remove(catalogocuenta);
                idempresaOld = em.merge(idempresaOld);
            }
            if (idempresaNew != null && !idempresaNew.equals(idempresaOld)) {
                idempresaNew.getCatalogocuentaList().add(catalogocuenta);
                idempresaNew = em.merge(idempresaNew);
            }
            for (Cuenta cuentaListNewCuenta : cuentaListNew) {
                if (!cuentaListOld.contains(cuentaListNewCuenta)) {
                    Catalogocuenta oldIdcatalogoOfCuentaListNewCuenta = cuentaListNewCuenta.getIdcatalogo();
                    cuentaListNewCuenta.setIdcatalogo(catalogocuenta);
                    cuentaListNewCuenta = em.merge(cuentaListNewCuenta);
                    if (oldIdcatalogoOfCuentaListNewCuenta != null && !oldIdcatalogoOfCuentaListNewCuenta.equals(catalogocuenta)) {
                        oldIdcatalogoOfCuentaListNewCuenta.getCuentaList().remove(cuentaListNewCuenta);
                        oldIdcatalogoOfCuentaListNewCuenta = em.merge(oldIdcatalogoOfCuentaListNewCuenta);
                    }
                }
            }
            for (Empresa empresaListOldEmpresa : empresaListOld) {
                if (!empresaListNew.contains(empresaListOldEmpresa)) {
                    empresaListOldEmpresa.setIdcatalogo(null);
                    empresaListOldEmpresa = em.merge(empresaListOldEmpresa);
                }
            }
            for (Empresa empresaListNewEmpresa : empresaListNew) {
                if (!empresaListOld.contains(empresaListNewEmpresa)) {
                    Catalogocuenta oldIdcatalogoOfEmpresaListNewEmpresa = empresaListNewEmpresa.getIdcatalogo();
                    empresaListNewEmpresa.setIdcatalogo(catalogocuenta);
                    empresaListNewEmpresa = em.merge(empresaListNewEmpresa);
                    if (oldIdcatalogoOfEmpresaListNewEmpresa != null && !oldIdcatalogoOfEmpresaListNewEmpresa.equals(catalogocuenta)) {
                        oldIdcatalogoOfEmpresaListNewEmpresa.getEmpresaList().remove(empresaListNewEmpresa);
                        oldIdcatalogoOfEmpresaListNewEmpresa = em.merge(oldIdcatalogoOfEmpresaListNewEmpresa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catalogocuenta.getIdcatalogo();
                if (findCatalogocuenta(id) == null) {
                    throw new NonexistentEntityException("The catalogocuenta with id " + id + " no longer exists.");
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
            Catalogocuenta catalogocuenta;
            try {
                catalogocuenta = em.getReference(Catalogocuenta.class, id);
                catalogocuenta.getIdcatalogo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catalogocuenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cuenta> cuentaListOrphanCheck = catalogocuenta.getCuentaList();
            for (Cuenta cuentaListOrphanCheckCuenta : cuentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catalogocuenta (" + catalogocuenta + ") cannot be destroyed since the Cuenta " + cuentaListOrphanCheckCuenta + " in its cuentaList field has a non-nullable idcatalogo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresa idempresa = catalogocuenta.getIdempresa();
            if (idempresa != null) {
                idempresa.getCatalogocuentaList().remove(catalogocuenta);
                idempresa = em.merge(idempresa);
            }
            List<Empresa> empresaList = catalogocuenta.getEmpresaList();
            for (Empresa empresaListEmpresa : empresaList) {
                empresaListEmpresa.setIdcatalogo(null);
                empresaListEmpresa = em.merge(empresaListEmpresa);
            }
            em.remove(catalogocuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catalogocuenta> findCatalogocuentaEntities() {
        return findCatalogocuentaEntities(true, -1, -1);
    }

    public List<Catalogocuenta> findCatalogocuentaEntities(int maxResults, int firstResult) {
        return findCatalogocuentaEntities(false, maxResults, firstResult);
    }

    private List<Catalogocuenta> findCatalogocuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catalogocuenta.class));
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

    public Catalogocuenta findCatalogocuenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catalogocuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatalogocuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catalogocuenta> rt = cq.from(Catalogocuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
