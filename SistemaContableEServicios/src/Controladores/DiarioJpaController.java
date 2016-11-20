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
import Modelos.Periodocontable;
import Modelos.Usuario;
import Modelos.Detallediario;
import Modelos.Diario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Merii
 */
public class DiarioJpaController implements Serializable {

    public DiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diario diario) {
        if (diario.getDetallediarioList() == null) {
            diario.setDetallediarioList(new ArrayList<Detallediario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodocontable idperiodo = diario.getIdperiodo();
            if (idperiodo != null) {
                idperiodo = em.getReference(idperiodo.getClass(), idperiodo.getIdperiodo());
                diario.setIdperiodo(idperiodo);
            }
            Usuario idusuario = diario.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                diario.setIdusuario(idusuario);
            }
            List<Detallediario> attachedDetallediarioList = new ArrayList<Detallediario>();
            for (Detallediario detallediarioListDetallediarioToAttach : diario.getDetallediarioList()) {
                detallediarioListDetallediarioToAttach = em.getReference(detallediarioListDetallediarioToAttach.getClass(), detallediarioListDetallediarioToAttach.getIddetalle());
                attachedDetallediarioList.add(detallediarioListDetallediarioToAttach);
            }
            diario.setDetallediarioList(attachedDetallediarioList);
            em.persist(diario);
            if (idperiodo != null) {
                idperiodo.getDiarioList().add(diario);
                idperiodo = em.merge(idperiodo);
            }
            if (idusuario != null) {
                idusuario.getDiarioList().add(diario);
                idusuario = em.merge(idusuario);
            }
            for (Detallediario detallediarioListDetallediario : diario.getDetallediarioList()) {
                Diario oldIdregistroOfDetallediarioListDetallediario = detallediarioListDetallediario.getIdregistro();
                detallediarioListDetallediario.setIdregistro(diario);
                detallediarioListDetallediario = em.merge(detallediarioListDetallediario);
                if (oldIdregistroOfDetallediarioListDetallediario != null) {
                    oldIdregistroOfDetallediarioListDetallediario.getDetallediarioList().remove(detallediarioListDetallediario);
                    oldIdregistroOfDetallediarioListDetallediario = em.merge(oldIdregistroOfDetallediarioListDetallediario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diario diario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diario persistentDiario = em.find(Diario.class, diario.getIdregistro());
            Periodocontable idperiodoOld = persistentDiario.getIdperiodo();
            Periodocontable idperiodoNew = diario.getIdperiodo();
            Usuario idusuarioOld = persistentDiario.getIdusuario();
            Usuario idusuarioNew = diario.getIdusuario();
            List<Detallediario> detallediarioListOld = persistentDiario.getDetallediarioList();
            List<Detallediario> detallediarioListNew = diario.getDetallediarioList();
            List<String> illegalOrphanMessages = null;
            for (Detallediario detallediarioListOldDetallediario : detallediarioListOld) {
                if (!detallediarioListNew.contains(detallediarioListOldDetallediario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallediario " + detallediarioListOldDetallediario + " since its idregistro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idperiodoNew != null) {
                idperiodoNew = em.getReference(idperiodoNew.getClass(), idperiodoNew.getIdperiodo());
                diario.setIdperiodo(idperiodoNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                diario.setIdusuario(idusuarioNew);
            }
            List<Detallediario> attachedDetallediarioListNew = new ArrayList<Detallediario>();
            for (Detallediario detallediarioListNewDetallediarioToAttach : detallediarioListNew) {
                detallediarioListNewDetallediarioToAttach = em.getReference(detallediarioListNewDetallediarioToAttach.getClass(), detallediarioListNewDetallediarioToAttach.getIddetalle());
                attachedDetallediarioListNew.add(detallediarioListNewDetallediarioToAttach);
            }
            detallediarioListNew = attachedDetallediarioListNew;
            diario.setDetallediarioList(detallediarioListNew);
            diario = em.merge(diario);
            if (idperiodoOld != null && !idperiodoOld.equals(idperiodoNew)) {
                idperiodoOld.getDiarioList().remove(diario);
                idperiodoOld = em.merge(idperiodoOld);
            }
            if (idperiodoNew != null && !idperiodoNew.equals(idperiodoOld)) {
                idperiodoNew.getDiarioList().add(diario);
                idperiodoNew = em.merge(idperiodoNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getDiarioList().remove(diario);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getDiarioList().add(diario);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Detallediario detallediarioListNewDetallediario : detallediarioListNew) {
                if (!detallediarioListOld.contains(detallediarioListNewDetallediario)) {
                    Diario oldIdregistroOfDetallediarioListNewDetallediario = detallediarioListNewDetallediario.getIdregistro();
                    detallediarioListNewDetallediario.setIdregistro(diario);
                    detallediarioListNewDetallediario = em.merge(detallediarioListNewDetallediario);
                    if (oldIdregistroOfDetallediarioListNewDetallediario != null && !oldIdregistroOfDetallediarioListNewDetallediario.equals(diario)) {
                        oldIdregistroOfDetallediarioListNewDetallediario.getDetallediarioList().remove(detallediarioListNewDetallediario);
                        oldIdregistroOfDetallediarioListNewDetallediario = em.merge(oldIdregistroOfDetallediarioListNewDetallediario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diario.getIdregistro();
                if (findDiario(id) == null) {
                    throw new NonexistentEntityException("The diario with id " + id + " no longer exists.");
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
            Diario diario;
            try {
                diario = em.getReference(Diario.class, id);
                diario.getIdregistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallediario> detallediarioListOrphanCheck = diario.getDetallediarioList();
            for (Detallediario detallediarioListOrphanCheckDetallediario : detallediarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Diario (" + diario + ") cannot be destroyed since the Detallediario " + detallediarioListOrphanCheckDetallediario + " in its detallediarioList field has a non-nullable idregistro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Periodocontable idperiodo = diario.getIdperiodo();
            if (idperiodo != null) {
                idperiodo.getDiarioList().remove(diario);
                idperiodo = em.merge(idperiodo);
            }
            Usuario idusuario = diario.getIdusuario();
            if (idusuario != null) {
                idusuario.getDiarioList().remove(diario);
                idusuario = em.merge(idusuario);
            }
            em.remove(diario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diario> findDiarioEntities() {
        return findDiarioEntities(true, -1, -1);
    }

    public List<Diario> findDiarioEntities(int maxResults, int firstResult) {
        return findDiarioEntities(false, maxResults, firstResult);
    }

    private List<Diario> findDiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diario.class));
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

    public Diario findDiario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diario.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diario> rt = cq.from(Diario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
