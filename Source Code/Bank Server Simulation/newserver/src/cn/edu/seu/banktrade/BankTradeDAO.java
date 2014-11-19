package cn.edu.seu.banktrade;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IBankTradeDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * BankTrade entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see temp.BankTrade
 * @author MyEclipse Persistence Tools
 */

public class BankTradeDAO implements IBankTradeDAO {
	// property constants
	public static final String SALERNAME = "salername";
	public static final String SALERMAC = "salermac";
	public static final String BUYERNAME = "buyername";
	public static final String BUYERMAC = "buyermac";
	public static final String BUYERIMEI = "buyerimei";
	public static final String AMOUNT = "amount";
	public static final String TRADETIME = "tradetime";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved BankTrade entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BankTradeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankTrade entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(BankTrade entity) {
		EntityManagerHelper.log("saving BankTrade instance", Level.INFO, null);
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
	 * Delete a persistent BankTrade entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BankTradeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            BankTrade entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BankTrade entity) {
		EntityManagerHelper
				.log("deleting BankTrade instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(BankTrade.class,
					entity.getTradenum());
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
	 * Persist a previously saved BankTrade entity and return it or a copy of it
	 * to the sender. A copy of the BankTrade entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = BankTradeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankTrade entity to update
	 * @return BankTrade the persisted BankTrade entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public BankTrade update(BankTrade entity) {
		EntityManagerHelper
				.log("updating BankTrade instance", Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			BankTrade result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public BankTrade findById(String id) {
		EntityManagerHelper.log("finding BankTrade instance with id: " + id,
				Level.INFO, null);
		try {
			BankTrade instance = getEntityManager().find(BankTrade.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all BankTrade entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BankTrade property to query
	 * @param value
	 *            the property value to match
	 * @return List<BankTrade> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<BankTrade> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding BankTrade instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from BankTrade model where model."
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

	public List<BankTrade> findBySalername(Object salername) {
		return findByProperty(SALERNAME, salername);
	}

	public List<BankTrade> findBySalermac(Object salermac) {
		return findByProperty(SALERMAC, salermac);
	}

	public List<BankTrade> findByBuyername(Object buyername) {
		return findByProperty(BUYERNAME, buyername);
	}

	public List<BankTrade> findByBuyermac(Object buyermac) {
		return findByProperty(BUYERMAC, buyermac);
	}

	public List<BankTrade> findByBuyerimei(Object buyerimei) {
		return findByProperty(BUYERIMEI, buyerimei);
	}

	public List<BankTrade> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List<BankTrade> findByTradetime(Object tradetime) {
		return findByProperty(TRADETIME, tradetime);
	}

	/**
	 * Find all BankTrade entities.
	 * 
	 * @return List<BankTrade> all BankTrade entities
	 */
	@SuppressWarnings("unchecked")
	public List<BankTrade> findAll() {
		EntityManagerHelper.log("finding all BankTrade instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from BankTrade model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}