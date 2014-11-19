package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.personinterestinfo.PersonInterestInfo;

/**
 * Interface for PersonInterestInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPersonInterestInfoDAO {
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
	 * IPersonInterestInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInterestInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonInterestInfo entity);

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
	 * IPersonInterestInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInterestInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonInterestInfo entity);

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
	 * entity = IPersonInterestInfoDAO.update(entity);
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
	public PersonInterestInfo update(PersonInterestInfo entity);

	public PersonInterestInfo findById(String id);

	/**
	 * Find all PersonInterestInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonInterestInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonInterestInfo> found by query
	 */
	public List<PersonInterestInfo> findByProperty(String propertyName,
			Object value);

	public List<PersonInterestInfo> findById(Object id);

	public List<PersonInterestInfo> findByNotes(Object notes);

	public List<PersonInterestInfo> findByBalance(Object balance);

	public List<PersonInterestInfo> findByTime(Object time);

	/**
	 * Find all PersonInterestInfo entities.
	 * 
	 * @return List<PersonInterestInfo> all PersonInterestInfo entities
	 */
	public List<PersonInterestInfo> findAll();
}