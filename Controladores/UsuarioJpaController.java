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
import Modelos.Empleado;
import Modelos.Rol;
import Modelos.Diario;
import Modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws IllegalOrphanException {
        if (usuario.getDiarioList() == null) {
            usuario.setDiarioList(new ArrayList<Diario>());
        }
        if (usuario.getEmpleadoList() == null) {
            usuario.setEmpleadoList(new ArrayList<Empleado>());
        }
        List<String> illegalOrphanMessages = null;
        Empleado codempleadoOrphanCheck = usuario.getCodempleado();
        if (codempleadoOrphanCheck != null) {
            Usuario oldIdusuarioOfCodempleado = codempleadoOrphanCheck.getIdusuario();
            if (oldIdusuarioOfCodempleado != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Empleado " + codempleadoOrphanCheck + " already has an item of type Usuario whose codempleado column cannot be null. Please make another selection for the codempleado field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado codempleado = usuario.getCodempleado();
            if (codempleado != null) {
                codempleado = em.getReference(codempleado.getClass(), codempleado.getCodempleado());
                usuario.setCodempleado(codempleado);
            }
            Rol idrol = usuario.getIdrol();
            if (idrol != null) {
                idrol = em.getReference(idrol.getClass(), idrol.getIdrol());
                usuario.setIdrol(idrol);
            }
            List<Diario> attachedDiarioList = new ArrayList<Diario>();
            for (Diario diarioListDiarioToAttach : usuario.getDiarioList()) {
                diarioListDiarioToAttach = em.getReference(diarioListDiarioToAttach.getClass(), diarioListDiarioToAttach.getIdregistro());
                attachedDiarioList.add(diarioListDiarioToAttach);
            }
            usuario.setDiarioList(attachedDiarioList);
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : usuario.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getCodempleado());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            usuario.setEmpleadoList(attachedEmpleadoList);
            em.persist(usuario);
            if (codempleado != null) {
                codempleado.setIdusuario(usuario);
                codempleado = em.merge(codempleado);
            }
            if (idrol != null) {
                idrol.getUsuarioList().add(usuario);
                idrol = em.merge(idrol);
            }
            for (Diario diarioListDiario : usuario.getDiarioList()) {
                Usuario oldIdusuarioOfDiarioListDiario = diarioListDiario.getIdusuario();
                diarioListDiario.setIdusuario(usuario);
                diarioListDiario = em.merge(diarioListDiario);
                if (oldIdusuarioOfDiarioListDiario != null) {
                    oldIdusuarioOfDiarioListDiario.getDiarioList().remove(diarioListDiario);
                    oldIdusuarioOfDiarioListDiario = em.merge(oldIdusuarioOfDiarioListDiario);
                }
            }
            for (Empleado empleadoListEmpleado : usuario.getEmpleadoList()) {
                Usuario oldIdusuarioOfEmpleadoListEmpleado = empleadoListEmpleado.getIdusuario();
                empleadoListEmpleado.setIdusuario(usuario);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldIdusuarioOfEmpleadoListEmpleado != null) {
                    oldIdusuarioOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldIdusuarioOfEmpleadoListEmpleado = em.merge(oldIdusuarioOfEmpleadoListEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdusuario());
            Empleado codempleadoOld = persistentUsuario.getCodempleado();
            Empleado codempleadoNew = usuario.getCodempleado();
            Rol idrolOld = persistentUsuario.getIdrol();
            Rol idrolNew = usuario.getIdrol();
            List<Diario> diarioListOld = persistentUsuario.getDiarioList();
            List<Diario> diarioListNew = usuario.getDiarioList();
            List<Empleado> empleadoListOld = persistentUsuario.getEmpleadoList();
            List<Empleado> empleadoListNew = usuario.getEmpleadoList();
            List<String> illegalOrphanMessages = null;
            if (codempleadoNew != null && !codempleadoNew.equals(codempleadoOld)) {
                Usuario oldIdusuarioOfCodempleado = codempleadoNew.getIdusuario();
                if (oldIdusuarioOfCodempleado != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Empleado " + codempleadoNew + " already has an item of type Usuario whose codempleado column cannot be null. Please make another selection for the codempleado field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codempleadoNew != null) {
                codempleadoNew = em.getReference(codempleadoNew.getClass(), codempleadoNew.getCodempleado());
                usuario.setCodempleado(codempleadoNew);
            }
            if (idrolNew != null) {
                idrolNew = em.getReference(idrolNew.getClass(), idrolNew.getIdrol());
                usuario.setIdrol(idrolNew);
            }
            List<Diario> attachedDiarioListNew = new ArrayList<Diario>();
            for (Diario diarioListNewDiarioToAttach : diarioListNew) {
                diarioListNewDiarioToAttach = em.getReference(diarioListNewDiarioToAttach.getClass(), diarioListNewDiarioToAttach.getIdregistro());
                attachedDiarioListNew.add(diarioListNewDiarioToAttach);
            }
            diarioListNew = attachedDiarioListNew;
            usuario.setDiarioList(diarioListNew);
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getCodempleado());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            usuario.setEmpleadoList(empleadoListNew);
            usuario = em.merge(usuario);
            if (codempleadoOld != null && !codempleadoOld.equals(codempleadoNew)) {
                codempleadoOld.setIdusuario(null);
                codempleadoOld = em.merge(codempleadoOld);
            }
            if (codempleadoNew != null && !codempleadoNew.equals(codempleadoOld)) {
                codempleadoNew.setIdusuario(usuario);
                codempleadoNew = em.merge(codempleadoNew);
            }
            if (idrolOld != null && !idrolOld.equals(idrolNew)) {
                idrolOld.getUsuarioList().remove(usuario);
                idrolOld = em.merge(idrolOld);
            }
            if (idrolNew != null && !idrolNew.equals(idrolOld)) {
                idrolNew.getUsuarioList().add(usuario);
                idrolNew = em.merge(idrolNew);
            }
            for (Diario diarioListOldDiario : diarioListOld) {
                if (!diarioListNew.contains(diarioListOldDiario)) {
                    diarioListOldDiario.setIdusuario(null);
                    diarioListOldDiario = em.merge(diarioListOldDiario);
                }
            }
            for (Diario diarioListNewDiario : diarioListNew) {
                if (!diarioListOld.contains(diarioListNewDiario)) {
                    Usuario oldIdusuarioOfDiarioListNewDiario = diarioListNewDiario.getIdusuario();
                    diarioListNewDiario.setIdusuario(usuario);
                    diarioListNewDiario = em.merge(diarioListNewDiario);
                    if (oldIdusuarioOfDiarioListNewDiario != null && !oldIdusuarioOfDiarioListNewDiario.equals(usuario)) {
                        oldIdusuarioOfDiarioListNewDiario.getDiarioList().remove(diarioListNewDiario);
                        oldIdusuarioOfDiarioListNewDiario = em.merge(oldIdusuarioOfDiarioListNewDiario);
                    }
                }
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    empleadoListOldEmpleado.setIdusuario(null);
                    empleadoListOldEmpleado = em.merge(empleadoListOldEmpleado);
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Usuario oldIdusuarioOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getIdusuario();
                    empleadoListNewEmpleado.setIdusuario(usuario);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldIdusuarioOfEmpleadoListNewEmpleado != null && !oldIdusuarioOfEmpleadoListNewEmpleado.equals(usuario)) {
                        oldIdusuarioOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldIdusuarioOfEmpleadoListNewEmpleado = em.merge(oldIdusuarioOfEmpleadoListNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdusuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Empleado codempleado = usuario.getCodempleado();
            if (codempleado != null) {
                codempleado.setIdusuario(null);
                codempleado = em.merge(codempleado);
            }
            Rol idrol = usuario.getIdrol();
            if (idrol != null) {
                idrol.getUsuarioList().remove(usuario);
                idrol = em.merge(idrol);
            }
            List<Diario> diarioList = usuario.getDiarioList();
            for (Diario diarioListDiario : diarioList) {
                diarioListDiario.setIdusuario(null);
                diarioListDiario = em.merge(diarioListDiario);
            }
            List<Empleado> empleadoList = usuario.getEmpleadoList();
            for (Empleado empleadoListEmpleado : empleadoList) {
                empleadoListEmpleado.setIdusuario(null);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
