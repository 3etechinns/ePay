package cn.edu.seu.personinterestinfo;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IPersonInterestInfoDAO;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonInterestInfo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cn.edu.seu.personinterestinfo.PersonInterestInfo
 * @author MyEclipse Persistence Tools
 */

public class PersonInterestInfoDAO implements IPersonInterestInfoDAO {
	// property constants
	public static final String ID = "id";
	public static final String NOTES = "notes";
	public static final String BALANCE = "balance";
	public static final String TIME = "time";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PersonInterestInfo
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonInterestInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInterestInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonInterestInfo entity) {
		EntityManagerHelper.log("saving PersonInterestInfo instance",
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
	 * Delete a persistent PersonInterestInfo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonInterestInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInterestInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonInterestInfo entity) {
		EntityManagerHelper.log("deleting PersonInterestInfo instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			entity = getEntityManager().getReference(PersonInterestInfo.class,
					entity.getUsername());
			getEntityManager().remove(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PersonInterestInfo entity and return it or a
	 * copy of it to the sender. A copy of the PersonInterestInfo entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PersonInterestInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInterestInfo entity to update
	 * @return PersonInterestInfo the persisted PersonInterestInfo entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PersonInterestInfo update(PersonInterestInfo entity) {
		EntityManagerHelper.log("updating PersonInterestInfo instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			PersonInterestInfo result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PersonInterestInfo findById(String id) {
		EntityManagerHelper.log("finding PersonInterestInfo instance with id: "
				+ id, Level.INFO, null);
		try {
			PersonInterestInfo instance = getEntityManager().find(
					PersonInterestInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PersonInterestInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonInterestInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonInterestInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PersonInterestInfo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PersonInterestInfo instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PersonInterestInfo model where model."
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

	public List<PersonInterestInfo> findById(Object id) {
		return findByProperty(ID, id);
	}

	public List<PersonInterestInfo> findByNotes(Object notes) {
		return findByProperty(NOTES, notes);
	}

	public List<PersonInterestInfo> findByBalance(Object balance) {
		return findByProperty(BALANCE, balance);
	}

	public List<PersonInterestInfo> findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	/**
	 * Find all PersonInterestInfo entities.
	 * 
	 * @return List<PersonInterestInfo> all PersonInterestInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<PersonInterestInfo> findAll() {
		EntityManagerHelper.log("finding all PersonInterestInfo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from PersonInterestInfo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}