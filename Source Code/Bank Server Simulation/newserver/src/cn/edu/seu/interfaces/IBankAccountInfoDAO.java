package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.bankaccountinfo.BankAccountInfo;

/**
 * Interface for BankAccountInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IBankAccountInfoDAO {
	/**
	 * Perform an initial save of a previously unsaved BankAccountInfo entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBankAccountInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankAccountInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(BankAccountInfo entity);

	/**
	 * Delete a persistent BankAccountInfo entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBankAccountInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            BankAccountInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BankAccountInfo entity);

	/**
	 * Persist a previously saved BankAccountInfo entity and return it or a copy
	 * of it to the sender. A copy of the BankAccountInfo entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IBankAccountInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankAccountInfo entity to update
	 * @return BankAccountInfo the persisted BankAccountInfo entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public BankAccountInfo update(BankAccountInfo entity);

	public BankAccountInfo findById(String id);

	/**
	 * Find all BankAccountInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BankAccountInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<BankAccountInfo> found by query
	 */
	public List<BankAccountInfo> findByProperty(String propertyName,
			Object value);

	public List<BankAccountInfo> findByName(Object name);

	public List<BankAccountInfo> findByIdentificationcardnumber(
			Object identificationcardnumber);

	public List<BankAccountInfo> findByPassword(Object password);

	public List<BankAccountInfo> findByBalance(Object balance);

	/**
	 * Find all BankAccountInfo entities.
	 * 
	 * @return List<BankAccountInfo> all BankAccountInfo entities
	 */
	public List<BankAccountInfo> findAll();
}