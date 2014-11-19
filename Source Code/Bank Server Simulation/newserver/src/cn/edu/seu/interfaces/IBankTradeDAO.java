package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.banktrade.BankTrade;



/**
 * Interface for BankTradeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IBankTradeDAO {
	/**
	 * Perform an initial save of a previously unsaved BankTrade entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBankTradeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankTrade entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(BankTrade entity);

	/**
	 * Delete a persistent BankTrade entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBankTradeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            BankTrade entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BankTrade entity);

	/**
	 * Persist a previously saved BankTrade entity and return it or a copy of it
	 * to the sender. A copy of the BankTrade entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IBankTradeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            BankTrade entity to update
	 * @return BankTrade the persisted BankTrade entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public BankTrade update(BankTrade entity);

	public BankTrade findById(String id);

	/**
	 * Find all BankTrade entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BankTrade property to query
	 * @param value
	 *            the property value to match
	 * @return List<BankTrade> found by query
	 */
	public List<BankTrade> findByProperty(String propertyName, Object value);

	public List<BankTrade> findBySalername(Object salername);

	public List<BankTrade> findBySalermac(Object salermac);

	public List<BankTrade> findByBuyername(Object buyername);

	public List<BankTrade> findByBuyermac(Object buyermac);

	public List<BankTrade> findByBuyerimei(Object buyerimei);

	public List<BankTrade> findByAmount(Object amount);

	public List<BankTrade> findByTradetime(Object tradetime);

	/**
	 * Find all BankTrade entities.
	 * 
	 * @return List<BankTrade> all BankTrade entities
	 */
	public List<BankTrade> findAll();
}