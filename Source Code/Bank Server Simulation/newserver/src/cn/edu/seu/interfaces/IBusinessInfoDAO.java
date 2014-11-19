package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.businessinfo.BusinessInfo;


/**
 * Interface for BusinessInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IBusinessInfoDAO {
	/**
	 * Perform an initial save of a previously unsaved BusinessInfo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBusinessInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BusinessInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(BusinessInfo entity);

	/**
	 * Delete a persistent BusinessInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBusinessInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            BusinessInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BusinessInfo entity);

	/**
	 * Persist a previously saved BusinessInfo entity and return it or a copy of
	 * it to the sender. A copy of the BusinessInfo entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IBusinessInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BusinessInfo entity to update
	 * @return BusinessInfo the persisted BusinessInfo entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public BusinessInfo update(BusinessInfo entity);

	public BusinessInfo findById(String id);

	/**
	 * Find all BusinessInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BusinessInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<BusinessInfo> found by query
	 */
	public List<BusinessInfo> findByProperty(String propertyName, Object value);

	public List<BusinessInfo> findByStorename(Object storename);

	public List<BusinessInfo> findByCardnum(Object cardnum);

	public List<BusinessInfo> findByBluetoothpwd(Object bluetoothpwd);

	public List<BusinessInfo> findByBalance(Object balance);

	public List<BusinessInfo> findByRandcode(Object randcode);

	/**
	 * Find all BusinessInfo entities.
	 * 
	 * @return List<BusinessInfo> all BusinessInfo entities
	 */
	public List<BusinessInfo> findAll();
}