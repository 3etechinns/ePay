package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.personinfo.PersonInfo;

/**
 * Interface for PersonInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPersonInfoDAO {
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
	 * IPersonInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonInfo entity);

	/**
	 * Delete a persistent PersonInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPersonInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonInfo entity);

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
	 * entity = IPersonInfoDAO.update(entity);
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
	public PersonInfo update(PersonInfo entity);

	public PersonInfo findById(String id);

	/**
	 * Find all PersonInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonInfo> found by query
	 */
	public List<PersonInfo> findByProperty(String propertyName, Object value);

	public List<PersonInfo> findByCustomername(Object customername);

	public List<PersonInfo> findByCardnum(Object cardnum);

	public List<PersonInfo> findByBluetoothmac(Object bluetoothmac);

	public List<PersonInfo> findByPrivatekey(Object privatekey);

	public List<PersonInfo> findByIdentificationcardnum(
			Object identificationcardnum);

	public List<PersonInfo> findByPhonenum(Object phonenum);

	public List<PersonInfo> findByBalance(Object balance);

	public List<PersonInfo> findByPassword(Object password);

	public List<PersonInfo> findByPublickeyn(Object publickeyn);

	public List<PersonInfo> findByPublickeyd(Object publickeyd);

	public List<PersonInfo> findByImei(Object imei);

	public List<PersonInfo> findByAccountbalance(Object accountbalance);

	/**
	 * Find all PersonInfo entities.
	 * 
	 * @return List<PersonInfo> all PersonInfo entities
	 */
	public List<PersonInfo> findAll();
}