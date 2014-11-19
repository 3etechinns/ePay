package cn.edu.seu.bankaccountinfo;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IBankAccountInfoDAO;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * BankAccountInfo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cn.edu.seu.bankaccountinfo.BankAccountInfo
 * @author MyEclipse Persistence Tools
 */

public class BankAccountInfoDAO implements IBankAccountInfoDAO {
	// property constants
	public static final String NAME = "name";
	public static final String IDENTIFICATIONCARDNUMBER = "identificationcardnumber";
	public static final String PASSWORD = "password";
	public static final String BALANCE = "balance";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved BankAccountInfo entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BankAccountInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankAccountInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(BankAccountInfo entity) {
		EntityManagerHelper.log("saving BankAccountInfo instance", Level.INFO,
				null);
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
	 * Delete a persistent BankAccountInfo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BankAccountInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            BankAccountInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BankAccountInfo entity) {
		EntityManagerHelper.log("deleting BankAccountInfo instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			entity = getEntityManager().getReference(BankAccountInfo.class,
					entity.getCardnumber());
			getEntityManager().remove(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved BankAccountInfo entity and return it or a copy
	 * of it to the sender. A copy of the BankAccountInfo entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = BankAccountInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankAccountInfo entity to update
	 * @return BankAccountInfo the persisted BankAccountInfo entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public BankAccountInfo update(BankAccountInfo entity) {
		EntityManagerHelper.log("updating BankAccountInfo instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			BankAccountInfo result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public BankAccountInfo findById(String id) {
		EntityManagerHelper.log("finding BankAccountInfo instance with id: "
				+ id, Level.INFO, null);
		try {
			BankAccountInfo instance = getEntityManager().find(
					BankAccountInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all BankAccountInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BankAccountInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<BankAccountInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<BankAccountInfo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding BankAccountInfo instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from BankAccountInfo model where model."
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

	public List<BankAccountInfo> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<BankAccountInfo> findByIdentificationcardnumber(
			Object identificationcardnumber) {
		return findByProperty(IDENTIFICATIONCARDNUMBER,
				identificationcardnumber);
	}

	public List<BankAccountInfo> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<BankAccountInfo> findByBalance(Object balance) {
		return findByProperty(BALANCE, balance);
	}

	/**
	 * Find all BankAccountInfo entities.
	 * 
	 * @return List<BankAccountInfo> all BankAccountInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<BankAccountInfo> findAll() {
		EntityManagerHelper.log("finding all BankAccountInfo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from BankAccountInfo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}