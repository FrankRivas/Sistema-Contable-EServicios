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
import Modelos.Catalogocuenta;
import Modelos.Area;
import Modelos.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresa empresa) {
        if (empresa.getAreaList() == null) {
            empresa.setAreaList(new ArrayList<Area>());
        }
        if (empresa.getCatalogocuentaList() == null) {
            empresa.setCatalogocuentaList(new ArrayList<Catalogocuenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catalogocuenta idcatalogo = empresa.getIdcatalogo();
            if (idcatalogo != null) {
                idcatalogo = em.getReference(idcatalogo.getClass(), idcatalogo.getIdcatalogo());
                empresa.setIdcatalogo(idcatalogo);
            }
            List<Area> attachedAreaList = new ArrayList<Area>();
            for (Area areaListAreaToAttach : empresa.getAreaList()) {
                areaListAreaToAttach = em.getReference(areaListAreaToAttach.getClass(), areaListAreaToAttach.getIdarea());
                attachedAreaList.add(areaListAreaToAttach);
            }
            empresa.setAreaList(attachedAreaList);
            List<Catalogocuenta> attachedCatalogocuentaList = new ArrayList<Catalogocuenta>();
            for (Catalogocuenta catalogocuentaListCatalogocuentaToAttach : empresa.getCatalogocuentaList()) {
                catalogocuentaListCatalogocuentaToAttach = em.getReference(catalogocuentaListCatalogocuentaToAttach.getClass(), catalogocuentaListCatalogocuentaToAttach.getIdcatalogo());
                attachedCatalogocuentaList.add(catalogocuentaListCatalogocuentaToAttach);
            }
            empresa.setCatalogocuentaList(attachedCatalogocuentaList);
            em.persist(empresa);
            if (idcatalogo != null) {
                Empresa oldIdempresaOfIdcatalogo = idcatalogo.getIdempresa();
                if (oldIdempresaOfIdcatalogo != null) {
                    oldIdempresaOfIdcatalogo.setIdcatalogo(null);
                    oldIdempresaOfIdcatalogo = em.merge(oldIdempresaOfIdcatalogo);
                }
                idcatalogo.setIdempresa(empresa);
                idcatalogo = em.merge(idcatalogo);
            }
            for (Area areaListArea : empresa.getAreaList()) {
                Empresa oldIdempresaOfAreaListArea = areaListArea.getIdempresa();
                areaListArea.setIdempresa(empresa);
                areaListArea = em.merge(areaListArea);
                if (oldIdempresaOfAreaListArea != null) {
                    oldIdempresaOfAreaListArea.getAreaList().remove(areaListArea);
                    oldIdempresaOfAreaListArea = em.merge(oldIdempresaOfAreaListArea);
                }
            }
            for (Catalogocuenta catalogocuentaListCatalogocuenta : empresa.getCatalogocuentaList()) {
                Empresa oldIdempresaOfCatalogocuentaListCatalogocuenta = catalogocuentaListCatalogocuenta.getIdempresa();
                catalogocuentaListCatalogocuenta.setIdempresa(empresa);
                catalogocuentaListCatalogocuenta = em.merge(catalogocuentaListCatalogocuenta);
                if (oldIdempresaOfCatalogocuentaListCatalogocuenta != null) {
                    oldIdempresaOfCatalogocuentaListCatalogocuenta.getCatalogocuentaList().remove(catalogocuentaListCatalogocuenta);
                    oldIdempresaOfCatalogocuentaListCatalogocuenta = em.merge(oldIdempresaOfCatalogocuentaListCatalogocuenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresa empresa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getIdempresa());
            Catalogocuenta idcatalogoOld = persistentEmpresa.getIdcatalogo();
            Catalogocuenta idcatalogoNew = empresa.getIdcatalogo();
            List<Area> areaListOld = persistentEmpresa.getAreaList();
            List<Area> areaListNew = empresa.getAreaList();
            List<Catalogocuenta> catalogocuentaListOld = persistentEmpresa.getCatalogocuentaList();
            List<Catalogocuenta> catalogocuentaListNew = empresa.getCatalogocuentaList();
            List<String> illegalOrphanMessages = null;
            for (Area areaListOldArea : areaListOld) {
                if (!areaListNew.contains(areaListOldArea)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Area " + areaListOldArea + " since its idempresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcatalogoNew != null) {
                idcatalogoNew = em.getReference(idcatalogoNew.getClass(), idcatalogoNew.getIdcatalogo());
                empresa.setIdcatalogo(idcatalogoNew);
            }
            List<Area> attachedAreaListNew = new ArrayList<Area>();
            for (Area areaListNewAreaToAttach : areaListNew) {
                areaListNewAreaToAttach = em.getReference(areaListNewAreaToAttach.getClass(), areaListNewAreaToAttach.getIdarea());
                attachedAreaListNew.add(areaListNewAreaToAttach);
            }
            areaListNew = attachedAreaListNew;
            empresa.setAreaList(areaListNew);
            List<Catalogocuenta> attachedCatalogocuentaListNew = new ArrayList<Catalogocuenta>();
            for (Catalogocuenta catalogocuentaListNewCatalogocuentaToAttach : catalogocuentaListNew) {
                catalogocuentaListNewCatalogocuentaToAttach = em.getReference(catalogocuentaListNewCatalogocuentaToAttach.getClass(), catalogocuentaListNewCatalogocuentaToAttach.getIdcatalogo());
                attachedCatalogocuentaListNew.add(catalogocuentaListNewCatalogocuentaToAttach);
            }
            catalogocuentaListNew = attachedCatalogocuentaListNew;
            empresa.setCatalogocuentaList(catalogocuentaListNew);
            empresa = em.merge(empresa);
            if (idcatalogoOld != null && !idcatalogoOld.equals(idcatalogoNew)) {
                idcatalogoOld.setIdempresa(null);
                idcatalogoOld = em.merge(idcatalogoOld);
            }
            if (idcatalogoNew != null && !idcatalogoNew.equals(idcatalogoOld)) {
                Empresa oldIdempresaOfIdcatalogo = idcatalogoNew.getIdempresa();
                if (oldIdempresaOfIdcatalogo != null) {
                    oldIdempresaOfIdcatalogo.setIdcatalogo(null);
                    oldIdempresaOfIdcatalogo = em.merge(oldIdempresaOfIdcatalogo);
                }
                idcatalogoNew.setIdempresa(empresa);
                idcatalogoNew = em.merge(idcatalogoNew);
            }
            for (Area areaListNewArea : areaListNew) {
                if (!areaListOld.contains(areaListNewArea)) {
                    Empresa oldIdempresaOfAreaListNewArea = areaListNewArea.getIdempresa();
                    areaListNewArea.setIdempresa(empresa);
                    areaListNewArea = em.merge(areaListNewArea);
                    if (oldIdempresaOfAreaListNewArea != null && !oldIdempresaOfAreaListNewArea.equals(empresa)) {
                        oldIdempresaOfAreaListNewArea.getAreaList().remove(areaListNewArea);
                        oldIdempresaOfAreaListNewArea = em.merge(oldIdempresaOfAreaListNewArea);
                    }
                }
            }
            for (Catalogocuenta catalogocuentaListOldCatalogocuenta : catalogocuentaListOld) {
                if (!catalogocuentaListNew.contains(catalogocuentaListOldCatalogocuenta)) {
                    catalogocuentaListOldCatalogocuenta.setIdempresa(null);
                    catalogocuentaListOldCatalogocuenta = em.merge(catalogocuentaListOldCatalogocuenta);
                }
            }
            for (Catalogocuenta catalogocuentaListNewCatalogocuenta : catalogocuentaListNew) {
                if (!catalogocuentaListOld.contains(catalogocuentaListNewCatalogocuenta)) {
                    Empresa oldIdempresaOfCatalogocuentaListNewCatalogocuenta = catalogocuentaListNewCatalogocuenta.getIdempresa();
                    catalogocuentaListNewCatalogocuenta.setIdempresa(empresa);
                    catalogocuentaListNewCatalogocuenta = em.merge(catalogocuentaListNewCatalogocuenta);
                    if (oldIdempresaOfCatalogocuentaListNewCatalogocuenta != null && !oldIdempresaOfCatalogocuentaListNewCatalogocuenta.equals(empresa)) {
                        oldIdempresaOfCatalogocuentaListNewCatalogocuenta.getCatalogocuentaList().remove(catalogocuentaListNewCatalogocuenta);
                        oldIdempresaOfCatalogocuentaListNewCatalogocuenta = em.merge(oldIdempresaOfCatalogocuentaListNewCatalogocuenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresa.getIdempresa();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
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
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getIdempresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Area> areaListOrphanCheck = empresa.getAreaList();
            for (Area areaListOrphanCheckArea : areaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresa (" + empresa + ") cannot be destroyed since the Area " + areaListOrphanCheckArea + " in its areaList field has a non-nullable idempresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Catalogocuenta idcatalogo = empresa.getIdcatalogo();
            if (idcatalogo != null) {
                idcatalogo.setIdempresa(null);
                idcatalogo = em.merge(idcatalogo);
            }
            List<Catalogocuenta> catalogocuentaList = empresa.getCatalogocuentaList();
            for (Catalogocuenta catalogocuentaListCatalogocuenta : catalogocuentaList) {
                catalogocuentaListCatalogocuenta.setIdempresa(null);
                catalogocuentaListCatalogocuenta = em.merge(catalogocuentaListCatalogocuenta);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
