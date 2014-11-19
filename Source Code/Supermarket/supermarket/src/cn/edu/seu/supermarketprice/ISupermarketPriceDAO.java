package cn.edu.seu.supermarketprice;

import java.util.List;

/**
 * Interface for SupermarketPriceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISupermarketPriceDAO {
	/**
	 * Perform an initial save of a previously unsaved SupermarketPrice entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISupermarketPriceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupermarketPrice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SupermarketPrice entity);

	/**
	 * Delete a persistent SupermarketPrice entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISupermarketPriceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SupermarketPrice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SupermarketPrice entity);

	/**
	 * Persist a previously saved SupermarketPrice entity and return it or a
	 * copy of it to the sender. A copy of the SupermarketPrice entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISupermarketPriceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupermarketPrice entity to update
	 * @return SupermarketPrice the persisted SupermarketPrice entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SupermarketPrice update(SupermarketPrice entity);

	public SupermarketPrice findById(String id);

	/**
	 * Find all SupermarketPrice entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SupermarketPrice property to query
	 * @param value
	 *            the property value to match
	 * @return List<SupermarketPrice> found by query
	 */
	public List<SupermarketPrice> findByProperty(String propertyName,
			Object value);

	public List<SupermarketPrice> findByGoodsname(Object goodsname);

	public List<SupermarketPrice> findByGoodsprice(Object goodsprice);

	/**
	 * Find all SupermarketPrice entities.
	 * 
	 * @return List<SupermarketPrice> all SupermarketPrice entities
	 */
	public List<SupermarketPrice> findAll();
}