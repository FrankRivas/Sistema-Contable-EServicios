/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Modelos.Area;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Empresa;
import Modelos.Puesto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */

public class AreaJpaController implements Serializable {

    public AreaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Area area) {
        if (area.getPuestoList() == null) {
            area.setPuestoList(new ArrayList<Puesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa idempresa = area.getIdempresa();
            if (idempresa != null) {
                idempresa = em.getReference(idempresa.getClass(), idempresa.getIdempresa());
                area.setIdempresa(idempresa);
            }
            List<Puesto> attachedPuestoList = new ArrayList<Puesto>();
            for (Puesto puestoListPuestoToAttach : area.getPuestoList()) {
                puestoListPuestoToAttach = em.getReference(puestoListPuestoToAttach.getClass(), puestoListPuestoToAttach.getIdpuesto());
                attachedPuestoList.add(puestoListPuestoToAttach);
            }
            area.setPuestoList(attachedPuestoList);
            em.persist(area);
            if (idempresa != null) {
                idempresa.getAreaList().add(area);
                idempresa = em.merge(idempresa);
            }
            for (Puesto puestoListPuesto : area.getPuestoList()) {
                Area oldIdareaOfPuestoListPuesto = puestoListPuesto.getIdarea();
                puestoListPuesto.setIdarea(area);
                puestoListPuesto = em.merge(puestoListPuesto);
                if (oldIdareaOfPuestoListPuesto != null) {
                    oldIdareaOfPuestoListPuesto.getPuestoList().remove(puestoListPuesto);
                    oldIdareaOfPuestoListPuesto = em.merge(oldIdareaOfPuestoListPuesto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Area area) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area persistentArea = em.find(Area.class, area.getIdarea());
            Empresa idempresaOld = persistentArea.getIdempresa();
            Empresa idempresaNew = area.getIdempresa();
            List<Puesto> puestoListOld = persistentArea.getPuestoList();
            List<Puesto> puestoListNew = area.getPuestoList();
            List<String> illegalOrphanMessages = null;
            for (Puesto puestoListOldPuesto : puestoListOld) {
                if (!puestoListNew.contains(puestoListOldPuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Puesto " + puestoListOldPuesto + " since its idarea field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idempresaNew != null) {
                idempresaNew = em.getReference(idempresaNew.getClass(), idempresaNew.getIdempresa());
                area.setIdempresa(idempresaNew);
            }
            List<Puesto> attachedPuestoListNew = new ArrayList<Puesto>();
            for (Puesto puestoListNewPuestoToAttach : puestoListNew) {
                puestoListNewPuestoToAttach = em.getReference(puestoListNewPuestoToAttach.getClass(), puestoListNewPuestoToAttach.getIdpuesto());
                attachedPuestoListNew.add(puestoListNewPuestoToAttach);
            }
            puestoListNew = attachedPuestoListNew;
            area.setPuestoList(puestoListNew);
            area = em.merge(area);
            if (idempresaOld != null && !idempresaOld.equals(idempresaNew)) {
                idempresaOld.getAreaList().remove(area);
                idempresaOld = em.merge(idempresaOld);
            }
            if (idempresaNew != null && !idempresaNew.equals(idempresaOld)) {
                idempresaNew.getAreaList().add(area);
                idempresaNew = em.merge(idempresaNew);
            }
            for (Puesto puestoListNewPuesto : puestoListNew) {
                if (!puestoListOld.contains(puestoListNewPuesto)) {
                    Area oldIdareaOfPuestoListNewPuesto = puestoListNewPuesto.getIdarea();
                    puestoListNewPuesto.setIdarea(area);
                    puestoListNewPuesto = em.merge(puestoListNewPuesto);
                    if (oldIdareaOfPuestoListNewPuesto != null && !oldIdareaOfPuestoListNewPuesto.equals(area)) {
                        oldIdareaOfPuestoListNewPuesto.getPuestoList().remove(puestoListNewPuesto);
                        oldIdareaOfPuestoListNewPuesto = em.merge(oldIdareaOfPuestoListNewPuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = area.getIdarea();
                if (findArea(id) == null) {
                    throw new NonexistentEntityException("The area with id " + id + " no longer exists.");
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
            Area area;
            try {
                area = em.getReference(Area.class, id);
                area.getIdarea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The area with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Puesto> puestoListOrphanCheck = area.getPuestoList();
            for (Puesto puestoListOrphanCheckPuesto : puestoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Area (" + area + ") cannot be destroyed since the Puesto " + puestoListOrphanCheckPuesto + " in its puestoList field has a non-nullable idarea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresa idempresa = area.getIdempresa();
            if (idempresa != null) {
                idempresa.getAreaList().remove(area);
                idempresa = em.merge(idempresa);
            }
            em.remove(area);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Area> findAreaEntities() {
        return findAreaEntities(true, -1, -1);
    }

    public List<Area> findAreaEntities(int maxResults, int firstResult) {
        return findAreaEntities(false, maxResults, firstResult);
    }

    private List<Area> findAreaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Area.class));
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

    public Area findArea(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Area.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Area> rt = cq.from(Area.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
