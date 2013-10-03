/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.sugen.beans.entity;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import my.sugen.beans.entity.exceptions.NonexistentEntityException;
import my.sugen.beans.entity.exceptions.PreexistingEntityException;

/**
 *
 * @author cafestop
 */
public class CommodityJpaController implements Serializable {

    public CommodityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Commodity commodity) throws PreexistingEntityException, Exception {
        if (commodity.getRelated() == null) {
            commodity.setRelated(new ArrayList<Commodity>());
        }
        if (commodity.getRecommanded() == null) {
            commodity.setRecommanded(new ArrayList<Commodity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Commodity> attachedRelated = new ArrayList<Commodity>();
            for (Commodity relatedCommodityToAttach : commodity.getRelated()) {
                relatedCommodityToAttach = em.getReference(relatedCommodityToAttach.getClass(), relatedCommodityToAttach.getId());
                attachedRelated.add(relatedCommodityToAttach);
            }
            commodity.setRelated(attachedRelated);
            ArrayList<Commodity> attachedRecommanded = new ArrayList<Commodity>();
            for (Commodity recommandedCommodityToAttach : commodity.getRecommanded()) {
                recommandedCommodityToAttach = em.getReference(recommandedCommodityToAttach.getClass(), recommandedCommodityToAttach.getId());
                attachedRecommanded.add(recommandedCommodityToAttach);
            }
            commodity.setRecommanded(attachedRecommanded);
            em.persist(commodity);
            for (Commodity relatedCommodity : commodity.getRelated()) {
                relatedCommodity.getRelated().add(commodity);
                relatedCommodity = em.merge(relatedCommodity);
            }
            for (Commodity recommandedCommodity : commodity.getRecommanded()) {
                recommandedCommodity.getRelated().add(commodity);
                recommandedCommodity = em.merge(recommandedCommodity);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCommodity(commodity.getId()) != null) {
                throw new PreexistingEntityException("Commodity " + commodity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Commodity commodity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commodity persistentCommodity = em.find(Commodity.class, commodity.getId());
            Collection<Commodity> relatedOld = persistentCommodity.getRelated();
            Collection<Commodity> relatedNew = commodity.getRelated();
            Collection<Commodity> recommandedOld = persistentCommodity.getRecommanded();
            Collection<Commodity> recommandedNew = commodity.getRecommanded();
            Collection<Commodity> attachedRelatedNew = new ArrayList<Commodity>();
            for (Commodity relatedNewCommodityToAttach : relatedNew) {
                relatedNewCommodityToAttach = em.getReference(relatedNewCommodityToAttach.getClass(), relatedNewCommodityToAttach.getId());
                attachedRelatedNew.add(relatedNewCommodityToAttach);
            }
            relatedNew = attachedRelatedNew;
            commodity.setRelated(relatedNew);
            Collection<Commodity> attachedRecommandedNew = new ArrayList<Commodity>();
            for (Commodity recommandedNewCommodityToAttach : recommandedNew) {
                recommandedNewCommodityToAttach = em.getReference(recommandedNewCommodityToAttach.getClass(), recommandedNewCommodityToAttach.getId());
                attachedRecommandedNew.add(recommandedNewCommodityToAttach);
            }
            recommandedNew = attachedRecommandedNew;
            commodity.setRecommanded(recommandedNew);
            commodity = em.merge(commodity);
            for (Commodity relatedOldCommodity : relatedOld) {
                if (!relatedNew.contains(relatedOldCommodity)) {
                    relatedOldCommodity.getRelated().remove(commodity);
                    relatedOldCommodity = em.merge(relatedOldCommodity);
                }
            }
            for (Commodity relatedNewCommodity : relatedNew) {
                if (!relatedOld.contains(relatedNewCommodity)) {
                    relatedNewCommodity.getRelated().add(commodity);
                    relatedNewCommodity = em.merge(relatedNewCommodity);
                }
            }
            for (Commodity recommandedOldCommodity : recommandedOld) {
                if (!recommandedNew.contains(recommandedOldCommodity)) {
                    recommandedOldCommodity.getRelated().remove(commodity);
                    recommandedOldCommodity = em.merge(recommandedOldCommodity);
                }
            }
            for (Commodity recommandedNewCommodity : recommandedNew) {
                if (!recommandedOld.contains(recommandedNewCommodity)) {
                    recommandedNewCommodity.getRelated().add(commodity);
                    recommandedNewCommodity = em.merge(recommandedNewCommodity);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = commodity.getId();
                if (findCommodity(id) == null) {
                    throw new NonexistentEntityException("The commodity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commodity commodity;
            try {
                commodity = em.getReference(Commodity.class, id);
                commodity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commodity with id " + id + " no longer exists.", enfe);
            }
            Collection<Commodity> related = commodity.getRelated();
            for (Commodity relatedCommodity : related) {
                relatedCommodity.getRelated().remove(commodity);
                relatedCommodity = em.merge(relatedCommodity);
            }
            Collection<Commodity> recommanded = commodity.getRecommanded();
            for (Commodity recommandedCommodity : recommanded) {
                recommandedCommodity.getRelated().remove(commodity);
                recommandedCommodity = em.merge(recommandedCommodity);
            }
            em.remove(commodity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Commodity> findCommodityEntities() {
        return findCommodityEntities(true, -1, -1);
    }

    public List<Commodity> findCommodityEntities(int maxResults, int firstResult) {
        return findCommodityEntities(false, maxResults, firstResult);
    }

    private List<Commodity> findCommodityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Commodity.class));
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

    public Commodity findCommodity(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Commodity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommodityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Commodity> rt = cq.from(Commodity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
