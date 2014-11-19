package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.supermarketprice.SupermartketPrice;


/**
 * Interface for SupermartketPriceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISupermartketPriceDAO {
	/**
	 * Perform an initial save of a previously unsaved SupermartketPrice entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISupermartketPriceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupermartketPrice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SupermartketPrice entity);

	/**
	 * Delete a persistent SupermartketPrice entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISupermartketPriceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SupermartketPrice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SupermartketPrice entity);

	/**
	 * Persist a previously saved SupermartketPrice entity and return it or a
	 * copy of it to the sender. A copy of the SupermartketPrice entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISupermartketPriceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupermartketPrice entity to update
	 * @return SupermartketPrice the persisted SupermartketPrice entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SupermartketPrice update(SupermartketPrice entity);

	public SupermartketPrice findById(String id);

	/**
	 * Find all SupermartketPrice entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SupermartketPrice property to query
	 * @param value
	 *            the property value to match
	 * @return List<SupermartketPrice> found by query
	 */
	public List<SupermartketPrice> findByProperty(String propertyName,
			Object value);

	public List<SupermartketPrice> findByGoodsname(Object goodsname);

	public List<SupermartketPrice> findByGoodsprice(Object goodsprice);

	/**
	 * Find all SupermartketPrice entities.
	 * 
	 * @return List<SupermartketPrice> all SupermartketPrice entities
	 */
	public List<SupermartketPrice> findAll();
}