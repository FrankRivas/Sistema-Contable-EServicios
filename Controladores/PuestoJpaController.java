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
import Modelos.Area;
import Modelos.Empleado;
import Modelos.Puesto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class PuestoJpaController implements Serializable {

    public PuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puesto puesto) {
        if (puesto.getEmpleadoList() == null) {
            puesto.setEmpleadoList(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area idarea = puesto.getIdarea();
            if (idarea != null) {
                idarea = em.getReference(idarea.getClass(), idarea.getIdarea());
                puesto.setIdarea(idarea);
            }
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : puesto.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getCodempleado());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            puesto.setEmpleadoList(attachedEmpleadoList);
            em.persist(puesto);
            if (idarea != null) {
                idarea.getPuestoList().add(puesto);
                idarea = em.merge(idarea);
            }
            for (Empleado empleadoListEmpleado : puesto.getEmpleadoList()) {
                Puesto oldIdpuestoOfEmpleadoListEmpleado = empleadoListEmpleado.getIdpuesto();
                empleadoListEmpleado.setIdpuesto(puesto);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldIdpuestoOfEmpleadoListEmpleado != null) {
                    oldIdpuestoOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldIdpuestoOfEmpleadoListEmpleado = em.merge(oldIdpuestoOfEmpleadoListEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puesto puesto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puesto persistentPuesto = em.find(Puesto.class, puesto.getIdpuesto());
            Area idareaOld = persistentPuesto.getIdarea();
            Area idareaNew = puesto.getIdarea();
            List<Empleado> empleadoListOld = persistentPuesto.getEmpleadoList();
            List<Empleado> empleadoListNew = puesto.getEmpleadoList();
            List<String> illegalOrphanMessages = null;
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleado " + empleadoListOldEmpleado + " since its idpuesto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idareaNew != null) {
                idareaNew = em.getReference(idareaNew.getClass(), idareaNew.getIdarea());
                puesto.setIdarea(idareaNew);
            }
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getCodempleado());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            puesto.setEmpleadoList(empleadoListNew);
            puesto = em.merge(puesto);
            if (idareaOld != null && !idareaOld.equals(idareaNew)) {
                idareaOld.getPuestoList().remove(puesto);
                idareaOld = em.merge(idareaOld);
            }
            if (idareaNew != null && !idareaNew.equals(idareaOld)) {
                idareaNew.getPuestoList().add(puesto);
                idareaNew = em.merge(idareaNew);
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Puesto oldIdpuestoOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getIdpuesto();
                    empleadoListNewEmpleado.setIdpuesto(puesto);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldIdpuestoOfEmpleadoListNewEmpleado != null && !oldIdpuestoOfEmpleadoListNewEmpleado.equals(puesto)) {
                        oldIdpuestoOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldIdpuestoOfEmpleadoListNewEmpleado = em.merge(oldIdpuestoOfEmpleadoListNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = puesto.getIdpuesto();
                if (findPuesto(id) == null) {
                    throw new NonexistentEntityException("The puesto with id " + id + " no longer exists.");
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
            Puesto puesto;
            try {
                puesto = em.getReference(Puesto.class, id);
                puesto.getIdpuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puesto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empleado> empleadoListOrphanCheck = puesto.getEmpleadoList();
            for (Empleado empleadoListOrphanCheckEmpleado : empleadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Puesto (" + puesto + ") cannot be destroyed since the Empleado " + empleadoListOrphanCheckEmpleado + " in its empleadoList field has a non-nullable idpuesto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Area idarea = puesto.getIdarea();
            if (idarea != null) {
                idarea.getPuestoList().remove(puesto);
                idarea = em.merge(idarea);
            }
            em.remove(puesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puesto> findPuestoEntities() {
        return findPuestoEntities(true, -1, -1);
    }

    public List<Puesto> findPuestoEntities(int maxResults, int firstResult) {
        return findPuestoEntities(false, maxResults, firstResult);
    }

    private List<Puesto> findPuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puesto.class));
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

    public Puesto findPuesto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puesto> rt = cq.from(Puesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
