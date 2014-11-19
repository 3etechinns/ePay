package cn.edu.seu.persondepositinfo;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IPersonDepositInfoDAO;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonDepositInfo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cn.edu.seu.persondepositinfo.PersonDepositInfo
 * @author MyEclipse Persistence Tools
 */

public class PersonDepositInfoDAO implements IPersonDepositInfoDAO {
	// property constants
	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String DEPOSITWAY = "depositway";
	public static final String INTERESTRATEWAY = "interestrateway";
	public static final String AMOUNT = "amount";
	public static final String TIME = "time";
	public static final String INTESERTRATE = "intesertrate";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PersonDepositInfo entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonDepositInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonDepositInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonDepositInfo entity) {
		EntityManagerHelper.log("saving PersonDepositInfo instance",
				Level.INFO, null);
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
	 * Delete a persistent PersonDepositInfo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonDepositInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonDepositInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonDepositInfo entity) {
		EntityManagerHelper.log("deleting PersonDepositInfo instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			entity = getEntityManager().getReference(PersonDepositInfo.class,
					entity.getNotes());
			getEntityManager().remove(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PersonDepositInfo entity and return it or a
	 * copy of it to the sender. A copy of the PersonDepositInfo entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PersonDepositInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonDepositInfo entity to update
	 * @return PersonDepositInfo the persisted PersonDepositInfo entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PersonDepositInfo update(PersonDepositInfo entity) {
		EntityManagerHelper.log("updating PersonDepositInfo instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			PersonDepositInfo result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PersonDepositInfo findById(String id) {
		EntityManagerHelper.log("finding PersonDepositInfo instance with id: "
				+ id, Level.INFO, null);
		try {
			PersonDepositInfo instance = getEntityManager().find(
					PersonDepositInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PersonDepositInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonDepositInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonDepositInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PersonDepositInfo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PersonDepositInfo instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PersonDepositInfo model where model."
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

	public List<PersonDepositInfo> findById(Object id) {
		return findByProperty(ID, id);
	}

	public List<PersonDepositInfo> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<PersonDepositInfo> findByDepositway(Object depositway) {
		return findByProperty(DEPOSITWAY, depositway);
	}

	public List<PersonDepositInfo> findByInterestrateway(Object interestrateway) {
		return findByProperty(INTERESTRATEWAY, interestrateway);
	}

	public List<PersonDepositInfo> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List<PersonDepositInfo> findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List<PersonDepositInfo> findByIntesertrate(Object intesertrate) {
		return findByProperty(INTESERTRATE, intesertrate);
	}

	/**
	 * Find all PersonDepositInfo entities.
	 * 
	 * @return List<PersonDepositInfo> all PersonDepositInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<PersonDepositInfo> findAll() {
		EntityManagerHelper.log("finding all PersonDepositInfo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from PersonDepositInfo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}