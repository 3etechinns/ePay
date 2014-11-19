package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.rsadb.RsaDb;

/**
 * Interface for RsaDbDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRsaDbDAO {
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
	 * IRsaDbDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RsaDb entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RsaDb entity);

	/**
	 * Delete a persistent RsaDb entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRsaDbDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RsaDb entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RsaDb entity);

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
	 * entity = IRsaDbDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RsaDb entity to update
	 * @return RsaDb the persisted RsaDb entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RsaDb update(RsaDb entity);

	public RsaDb findById(String id);

	/**
	 * Find all RsaDb entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RsaDb property to query
	 * @param value
	 *            the property value to match
	 * @return List<RsaDb> found by query
	 */
	public List<RsaDb> findByProperty(String propertyName, Object value);

	public List<RsaDb> findByKeye(Object keye);

	public List<RsaDb> findByKeyd(Object keyd);

	public List<RsaDb> findByTheidu(Object theidu);

	/**
	 * Find all RsaDb entities.
	 * 
	 * @return List<RsaDb> all RsaDb entities
	 */
	public List<RsaDb> findAll();
}