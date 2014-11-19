package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.persondepositinfo.PersonDepositInfo;

/**
 * Interface for PersonDepositInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPersonDepositInfoDAO {
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
	 * IPersonDepositInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonDepositInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonDepositInfo entity);

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
	 * IPersonDepositInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonDepositInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonDepositInfo entity);

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
	 * entity = IPersonDepositInfoDAO.update(entity);
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
	public PersonDepositInfo update(PersonDepositInfo entity);

	public PersonDepositInfo findById(String id);

	/**
	 * Find all PersonDepositInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonDepositInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonDepositInfo> found by query
	 */
	public List<PersonDepositInfo> findByProperty(String propertyName,
			Object value);

	public List<PersonDepositInfo> findById(Object id);

	public List<PersonDepositInfo> findByUsername(Object username);

	public List<PersonDepositInfo> findByDepositway(Object depositway);

	public List<PersonDepositInfo> findByInterestrateway(Object interestrateway);

	public List<PersonDepositInfo> findByAmount(Object amount);

	public List<PersonDepositInfo> findByTime(Object time);

	public List<PersonDepositInfo> findByIntesertrate(Object intesertrate);

	/**
	 * Find all PersonDepositInfo entities.
	 * 
	 * @return List<PersonDepositInfo> all PersonDepositInfo entities
	 */
	public List<PersonDepositInfo> findAll();
}