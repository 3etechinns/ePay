package cn.edu.seu.elenoteinfo;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IElenoteInfoDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElenoteInfo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cn.edu.seu.elenoteinfo.ElenoteInfo
 * @author MyEclipse Persistence Tools
 */

public class ElenoteInfoDAO implements IElenoteInfoDAO {
	// property constants
	public static final String PAYERNUM = "payernum";
	public static final String RECERNUM = "recernum";
	public static final String AMOUNT = "amount";
	public static final String PAYERDEVICE = "payerdevice";
	public static final String RECERDEVICE = "recerdevice";
	public static final String PAYERNAME = "payername";
	public static final String RECERNAME = "recername";
	public static final String TRANSFERTIME = "transfertime";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ElenoteInfo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ElenoteInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElenoteInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ElenoteInfo entity) {
		EntityManagerHelper
				.log("saving ElenoteInfo instance", Level.INFO, null);
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
	 * Delete a persistent ElenoteInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ElenoteInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ElenoteInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ElenoteInfo entity) {
		EntityManagerHelper.log("deleting ElenoteInfo instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(ElenoteInfo.class,
					entity.getPaynum());
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
	 * Persist a previously saved ElenoteInfo entity and return it or a copy of
	 * it to the sender. A copy of the ElenoteInfo entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ElenoteInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElenoteInfo entity to update
	 * @return ElenoteInfo the persisted ElenoteInfo entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ElenoteInfo update(ElenoteInfo entity) {
		EntityManagerHelper.log("updating ElenoteInfo instance", Level.INFO,
				null);
		try {
			getEntityManager().getTransaction().begin();
			ElenoteInfo result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ElenoteInfo findById(String id) {
		EntityManagerHelper.log("finding ElenoteInfo instance with id: " + id,
				Level.INFO, null);
		try {
			ElenoteInfo instance = getEntityManager().find(ElenoteInfo.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ElenoteInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElenoteInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<ElenoteInfo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ElenoteInfo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding ElenoteInfo instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ElenoteInfo model where model."
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

	public List<ElenoteInfo> findByPayernum(Object payernum) {
		return findByProperty(PAYERNUM, payernum);
	}

	public List<ElenoteInfo> findByRecernum(Object recernum) {
		return findByProperty(RECERNUM, recernum);
	}

	public List<ElenoteInfo> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List<ElenoteInfo> findByPayerdevice(Object payerdevice) {
		return findByProperty(PAYERDEVICE, payerdevice);
	}

	public List<ElenoteInfo> findByRecerdevice(Object recerdevice) {
		return findByProperty(RECERDEVICE, recerdevice);
	}

	public List<ElenoteInfo> findByPayername(Object payername) {
		return findByProperty(PAYERNAME, payername);
	}

	public List<ElenoteInfo> findByRecername(Object recername) {
		return findByProperty(RECERNAME, recername);
	}

	public List<ElenoteInfo> findByTransfertime(Object transfertime) {
		return findByProperty(TRANSFERTIME, transfertime);
	}

	/**
	 * Find all ElenoteInfo entities.
	 * 
	 * @return List<ElenoteInfo> all ElenoteInfo entities
	 */
	@SuppressWarnings("unchecked")
	public List<ElenoteInfo> findAll() {
		EntityManagerHelper.log("finding all ElenoteInfo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from ElenoteInfo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}