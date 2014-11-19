package cn.edu.seu.personinfo;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IPersonInfoDAO;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonInfo entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cn.edu.seu.personinfo.PersonInfo
 * @author MyEclipse Persistence Tools
 */

public class PersonInfoDAO implements IPersonInfoDAO {
	// property constants
	public static final String CUSTOMERNAME = "customername";
	public static final String CARDNUM = "cardnum";
	public static final String BLUETOOTHMAC = "bluetoothmac";
	public static final String PRIVATEKEY = "privatekey";
	public static final String IDENTIFICATIONCARDNUM = "identificationcardnum";
	public static final String PHONENUM = "phonenum";
	public static final String BALANCE = "balance";
	public static final String PASSWORD = "password";
	public static final String PUBLICKEYN = "publickeyn";
	public static final String PUBLICKEYD = "publickeyd";
	public static final String IMEI = "imei";
	public static final String ACCOUNTBALANCE = "accountbalance";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PersonInfo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonInfo entity) {
		EntityManagerHelper.log("saving PersonInfo instance", Level.INFO, null);
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
	 * Delete a persistent PersonInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonInfo entity) {
		EntityManagerHelper.log("deleting PersonInfo instance", Level.INFO,
				null);
		try {
			getEntityManager().getTransaction().begin();
			entity = getEntityManager().getReference(PersonInfo.class,
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
	 * Persist a previously saved PersonInfo entity and return it or a copy of
	 * it to the sender. A copy of the PersonInfo entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PersonInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInfo entity to update
	 * @return PersonInfo the persisted PersonInfo entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PersonInfo update(PersonInfo entity) {
		EntityManagerHelper.log("updating PersonInfo instance", Level.INFO,
				null);
		try {
			getEntityManager().getTransaction().begin();
			PersonInfo result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PersonInfo findById(String id) {
		EntityManagerHelper.log("finding PersonInfo instance with id: " + id,
				Level.INFO, null);
		try {
			PersonInfo instance = getEntityManager().find(PersonInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PersonInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PersonInfo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding PersonInfo instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PersonInfo model where model."
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

	public List<PersonInfo> findByCustomername(Object customername) {
		return findByProperty(CUSTOMERNAME, customername);
	}

	public List<PersonInfo> findByCardnum(Object cardnum) {
		return findByProperty(CARDNUM, cardnum);
	}

	public List<PersonInfo> findByBluetoothmac(Object bluetoothmac) {
		return findByProperty(BLUETOOTHMAC, bluetoothmac);
	}

	public List<PersonInfo> findByPrivatekey(Object privatekey) {
		return findByProperty(PRIVATEKEY, privatekey);
	}

	public List<PersonInfo> findByIdentificationcardnum(
			Object identificationcardnum) {
		return findByProperty(IDENTIFICATIONCARDNUM, identificationcardnum);
	}

	public List<PersonInfo> findByPhonenum(Object phonenum) {
		return findByProperty(PHONENUM, phonenum);
	}

	public List<PersonInfo> findByBalance(Object balance) {
		return findByProperty(BALANCE, balance);
	}

	public List<PersonInfo> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<PersonInfo> findByPublickeyn(Object publickeyn) {
		return findByProperty(PUBLICKEYN, publickeyn);
	}

	public List<PersonInfo> findByPublickeyd(Object publickeyd) {
		return findByProperty(PUBLICKEYD, publickeyd);
	}

	public List<PersonInfo> findByImei(Object imei) {
		return findByProperty(IMEI, imei);
	}

	public List<PersonInfo> findByAccountbalance(Object accountbalance) {
		return findByProperty(ACCOUNTBALANCE, accountbalance);
	}

	/**
	 * Find all PersonInfo entities.
	 * 
	 * @return List<PersonInfo> all PersonInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<PersonInfo> findAll() {
		EntityManagerHelper.log("finding all PersonInfo instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from PersonInfo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}