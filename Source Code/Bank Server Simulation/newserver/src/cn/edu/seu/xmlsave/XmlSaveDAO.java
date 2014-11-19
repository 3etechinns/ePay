package cn.edu.seu.xmlsave;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.IXmlSaveDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * XmlSave entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cn.edu.seu.xmlsave.XmlSave
 * @author MyEclipse Persistence Tools
 */

public class XmlSaveDAO implements IXmlSaveDAO {
	// property constants
	public static final String NUM = "num";
	public static final String XMLDOC = "xmldoc";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved XmlSave entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * XmlSaveDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            XmlSave entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(XmlSave entity) {
		EntityManagerHelper.log("saving XmlSave instance", Level.INFO, null);
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
	 * Delete a persistent XmlSave entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * XmlSaveDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            XmlSave entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(XmlSave entity) {
		EntityManagerHelper.log("deleting XmlSave instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(XmlSave.class,
					entity.getNotes());
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
	 * Persist a previously saved XmlSave entity and return it or a copy of it
	 * to the sender. A copy of the XmlSave entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = XmlSaveDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            XmlSave entity to update
	 * @return XmlSave the persisted XmlSave entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public XmlSave update(XmlSave entity) {
		EntityManagerHelper.log("updating XmlSave instance", Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			XmlSave result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public XmlSave findById(String id) {
		EntityManagerHelper.log("finding XmlSave instance with id: " + id,
				Level.INFO, null);
		try {
			XmlSave instance = getEntityManager().find(XmlSave.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all XmlSave entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the XmlSave property to query
	 * @param value
	 *            the property value to match
	 * @return List<XmlSave> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<XmlSave> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding XmlSave instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from XmlSave model where model."
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

	public List<XmlSave> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List<XmlSave> findByXmldoc(Object xmldoc) {
		return findByProperty(XMLDOC, xmldoc);
	}

	/**
	 * Find all XmlSave entities.
	 * 
	 * @return List<XmlSave> all XmlSave entities
	 */
	@SuppressWarnings("unchecked")
	public List<XmlSave> findAll() {
		EntityManagerHelper.log("finding all XmlSave instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from XmlSave model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}