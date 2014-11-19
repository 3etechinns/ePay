package cn.edu.seu.businessinfo;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IBusinessInfoDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * BusinessInfo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cn.edu.seu.businessinfo.BusinessInfo
 * @author MyEclipse Persistence Tools
 */

public class BusinessInfoDAO implements IBusinessInfoDAO {
	// property constants
	public static final String STORENAME = "storename";
	public static final String CARDNUM = "cardnum";
	public static final String BLUETOOTHPWD = "bluetoothpwd";
	public static final String BALANCE = "balance";
	public static final String RANDCODE = "randcode";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved BusinessInfo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BusinessInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BusinessInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(BusinessInfo entity) {
		EntityManagerHelper.log("saving BusinessInfo instance", Level.INFO,
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
	 * Delete a persistent BusinessInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BusinessInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            BusinessInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BusinessInfo entity) {
		EntityManagerHelper.log("deleting BusinessInfo instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(BusinessInfo.class,
					entity.getUsername());
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
	 * Persist a previously saved BusinessInfo entity and return it or a copy of
	 * it to the sender. A copy of the BusinessInfo entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = BusinessInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BusinessInfo entity to update
	 * @return BusinessInfo the persisted BusinessInfo entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public BusinessInfo update(BusinessInfo entity) {
		EntityManagerHelper.log("updating BusinessInfo instance", Level.INFO,
				null);
		try {
			getEntityManager().getTransaction().begin();
			BusinessInfo result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public BusinessInfo findById(String id) {
		EntityManagerHelper.log("finding BusinessInfo instance with id: " + id,
				Level.INFO, null);
		try {
			BusinessInfo instance = getEntityManager().find(BusinessInfo.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all BusinessInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BusinessInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<BusinessInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<BusinessInfo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding BusinessInfo instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from BusinessInfo model where model."
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

	public List<BusinessInfo> findByStorename(Object storename) {
		return findByProperty(STORENAME, storename);
	}

	public List<BusinessInfo> findByCardnum(Object cardnum) {
		return findByProperty(CARDNUM, cardnum);
	}

	public List<BusinessInfo> findByBluetoothpwd(Object bluetoothpwd) {
		return findByProperty(BLUETOOTHPWD, bluetoothpwd);
	}

	public List<BusinessInfo> findByBalance(Object balance) {
		return findByProperty(BALANCE, balance);
	}

	public List<BusinessInfo> findByRandcode(Object randcode) {
		return findByProperty(RANDCODE, randcode);
	}

	/**
	 * Find all BusinessInfo entities.
	 * 
	 * @return List<BusinessInfo> all BusinessInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<BusinessInfo> findAll() {
		EntityManagerHelper.log("finding all BusinessInfo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from BusinessInfo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}