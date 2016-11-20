/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Modelos.Empleado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelos.Puesto;
import Modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getUsuarioList() == null) {
            empleado.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puesto idpuesto = empleado.getIdpuesto();
            if (idpuesto != null) {
                idpuesto = em.getReference(idpuesto.getClass(), idpuesto.getIdpuesto());
                empleado.setIdpuesto(idpuesto);
            }
            Usuario idusuario = empleado.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                empleado.setIdusuario(idusuario);
            }
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : empleado.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdusuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            empleado.setUsuarioList(attachedUsuarioList);
            em.persist(empleado);
            if (idpuesto != null) {
                idpuesto.getEmpleadoList().add(empleado);
                idpuesto = em.merge(idpuesto);
            }
            if (idusuario != null) {
                idusuario.getEmpleadoList().add(empleado);
                idusuario = em.merge(idusuario);
            }
            for (Usuario usuarioListUsuario : empleado.getUsuarioList()) {
                Empleado oldCodempleadoOfUsuarioListUsuario = usuarioListUsuario.getCodempleado();
                usuarioListUsuario.setCodempleado(empleado);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldCodempleadoOfUsuarioListUsuario != null) {
                    oldCodempleadoOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldCodempleadoOfUsuarioListUsuario = em.merge(oldCodempleadoOfUsuarioListUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getCodempleado());
            Puesto idpuestoOld = persistentEmpleado.getIdpuesto();
            Puesto idpuestoNew = empleado.getIdpuesto();
            Usuario idusuarioOld = persistentEmpleado.getIdusuario();
            Usuario idusuarioNew = empleado.getIdusuario();
            List<Usuario> usuarioListOld = persistentEmpleado.getUsuarioList();
            List<Usuario> usuarioListNew = empleado.getUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioListOldUsuario + " since its codempleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idpuestoNew != null) {
                idpuestoNew = em.getReference(idpuestoNew.getClass(), idpuestoNew.getIdpuesto());
                empleado.setIdpuesto(idpuestoNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                empleado.setIdusuario(idusuarioNew);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdusuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            empleado.setUsuarioList(usuarioListNew);
            empleado = em.merge(empleado);
            if (idpuestoOld != null && !idpuestoOld.equals(idpuestoNew)) {
                idpuestoOld.getEmpleadoList().remove(empleado);
                idpuestoOld = em.merge(idpuestoOld);
            }
            if (idpuestoNew != null && !idpuestoNew.equals(idpuestoOld)) {
                idpuestoNew.getEmpleadoList().add(empleado);
                idpuestoNew = em.merge(idpuestoNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getEmpleadoList().remove(empleado);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getEmpleadoList().add(empleado);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Empleado oldCodempleadoOfUsuarioListNewUsuario = usuarioListNewUsuario.getCodempleado();
                    usuarioListNewUsuario.setCodempleado(empleado);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldCodempleadoOfUsuarioListNewUsuario != null && !oldCodempleadoOfUsuarioListNewUsuario.equals(empleado)) {
                        oldCodempleadoOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldCodempleadoOfUsuarioListNewUsuario = em.merge(oldCodempleadoOfUsuarioListNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getCodempleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getCodempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuario> usuarioListOrphanCheck = empleado.getUsuarioList();
            for (Usuario usuarioListOrphanCheckUsuario : usuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Usuario " + usuarioListOrphanCheckUsuario + " in its usuarioList field has a non-nullable codempleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Puesto idpuesto = empleado.getIdpuesto();
            if (idpuesto != null) {
                idpuesto.getEmpleadoList().remove(empleado);
                idpuesto = em.merge(idpuesto);
            }
            Usuario idusuario = empleado.getIdusuario();
            if (idusuario != null) {
                idusuario.getEmpleadoList().remove(empleado);
                idusuario = em.merge(idusuario);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
