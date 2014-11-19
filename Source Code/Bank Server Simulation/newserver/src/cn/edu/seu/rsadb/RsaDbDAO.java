package cn.edu.seu.rsadb;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.edu.seu.banktrade.BankTrade;
import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IRsaDbDAO;

/**
 * A data access object (DAO) providing persistence and search support for RsaDb
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see cn.edu.seu.rsadb.RsaDb
 * @author MyEclipse Persistence Tools
 */

public class RsaDbDAO implements IRsaDbDAO {
	// property constants
	public static final String KEYE = "keye";
	public static final String KEYD = "keyd";
	public static final String THEIDU = "theidu";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved RsaDb entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RsaDbDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RsaDb entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RsaDb entity) {
		EntityManagerHelper.log("saving RsaDb instance", Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent RsaDb entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RsaDbDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RsaDb entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RsaDb entity) {
		EntityManagerHelper.log("deleting RsaDb instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(RsaDb.class,
					entity.getIdnum());
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved RsaDb entity and return it or a copy of it to
	 * the sender. A copy of the RsaDb entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RsaDbDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RsaDb entity to update
	 * @return RsaDb the persisted RsaDb entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RsaDb update(RsaDb entity) {
		EntityManagerHelper.log("updating RsaDb instance", Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			RsaDb result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public RsaDb findById(String id) {
		EntityManagerHelper.log("finding RsaDb instance with id: " + id,
				Level.INFO, null);
		try {
			RsaDb instance = getEntityManager().find(RsaDb.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all RsaDb entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RsaDb property to query
	 * @param value
	 *            the property value to match
	 * @return List<RsaDb> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<RsaDb> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding RsaDb instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from RsaDb model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<RsaDb> findByKeye(Object keye) {
		return findByProperty(KEYE, keye);
	}

	public List<RsaDb> findByKeyd(Object keyd) {
		return findByProperty(KEYD, keyd);
	}

	public List<RsaDb> findByTheidu(Object theidu) {
		return findByProperty(THEIDU, theidu);
	}

	/**
	 * Find all RsaDb entities.
	 * 
	 * @return List<RsaDb> all RsaDb entities
	 */
	@SuppressWarnings("unchecked")
	public List<RsaDb> findAll() {
		EntityManagerHelper
				.log("finding all RsaDb instances", Level.INFO, null);
		try {
			final String queryString = "select model from RsaDb model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}