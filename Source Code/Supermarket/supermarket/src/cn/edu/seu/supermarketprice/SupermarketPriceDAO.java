package cn.edu.seu.supermarketprice;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * SupermarketPrice entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cn.edu.seu.supermarketprice.SupermarketPrice
 * @author MyEclipse Persistence Tools
 */

public class SupermarketPriceDAO implements ISupermarketPriceDAO {
	// property constants
	public static final String GOODSNAME = "goodsname";
	public static final String GOODSPRICE = "goodsprice";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * SupermarketPriceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupermarketPrice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SupermarketPrice entity) {
		EntityManagerHelper.log("saving SupermarketPrice instance", Level.INFO,
				null);
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
	 * Delete a persistent SupermarketPrice entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SupermarketPriceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SupermarketPrice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SupermarketPrice entity) {
		EntityManagerHelper.log("deleting SupermarketPrice instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(SupermarketPrice.class,
					entity.getGoodsbarcode());
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
	 * entity = SupermarketPriceDAO.update(entity);
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
	public SupermarketPrice update(SupermarketPrice entity) {
		EntityManagerHelper.log("updating SupermarketPrice instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			SupermarketPrice result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SupermarketPrice findById(String id) {
		EntityManagerHelper.log("finding SupermarketPrice instance with id: "
				+ id, Level.INFO, null);
		try {
			SupermarketPrice instance = getEntityManager().find(
					SupermarketPrice.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all SupermarketPrice entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SupermarketPrice property to query
	 * @param value
	 *            the property value to match
	 * @return List<SupermarketPrice> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SupermarketPrice> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding SupermarketPrice instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from SupermarketPrice model where model."
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

	public List<SupermarketPrice> findByGoodsname(Object goodsname) {
		return findByProperty(GOODSNAME, goodsname);
	}

	public List<SupermarketPrice> findByGoodsprice(Object goodsprice) {
		return findByProperty(GOODSPRICE, goodsprice);
	}

	/**
	 * Find all SupermarketPrice entities.
	 * 
	 * @return List<SupermarketPrice> all SupermarketPrice entities
	 */
	@SuppressWarnings("unchecked")
	public List<SupermarketPrice> findAll() {
		EntityManagerHelper.log("finding all SupermarketPrice instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from SupermarketPrice model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}