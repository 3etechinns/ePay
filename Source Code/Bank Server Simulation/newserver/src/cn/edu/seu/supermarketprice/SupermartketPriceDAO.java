package cn.edu.seu.supermarketprice;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.edu.seu.banktrade.EntityManagerHelper;
import cn.edu.seu.interfaces.ISupermartketPriceDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * SupermartketPrice entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cn.edu.seu.supermarketprice.SupermartketPrice
 * @author MyEclipse Persistence Tools
 */

public class SupermartketPriceDAO implements ISupermartketPriceDAO {
	// property constants
	public static final String GOODSNAME = "goodsname";
	public static final String GOODSPRICE = "goodsprice";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * SupermartketPriceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupermartketPrice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SupermartketPrice entity) {
		EntityManagerHelper.log("saving SupermartketPrice instance",
				Level.INFO, null);
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
	 * Delete a persistent SupermartketPrice entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SupermartketPriceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SupermartketPrice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SupermartketPrice entity) {
		EntityManagerHelper.log("deleting SupermartketPrice instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(SupermartketPrice.class,
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
	 * entity = SupermartketPriceDAO.update(entity);
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
	public SupermartketPrice update(SupermartketPrice entity) {
		EntityManagerHelper.log("updating SupermartketPrice instance",
				Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			SupermartketPrice result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SupermartketPrice findById(String id) {
		EntityManagerHelper.log("finding SupermartketPrice instance with id: "
				+ id, Level.INFO, null);
		try {
			SupermartketPrice instance = getEntityManager().find(
					SupermartketPrice.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all SupermartketPrice entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SupermartketPrice property to query
	 * @param value
	 *            the property value to match
	 * @return List<SupermartketPrice> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SupermartketPrice> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding SupermartketPrice instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from SupermartketPrice model where model."
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

	public List<SupermartketPrice> findByGoodsname(Object goodsname) {
		return findByProperty(GOODSNAME, goodsname);
	}

	public List<SupermartketPrice> findByGoodsprice(Object goodsprice) {
		return findByProperty(GOODSPRICE, goodsprice);
	}

	/**
	 * Find all SupermartketPrice entities.
	 * 
	 * @return List<SupermartketPrice> all SupermartketPrice entities
	 */
	@SuppressWarnings("unchecked")
	public List<SupermartketPrice> findAll() {
		EntityManagerHelper.log("finding all SupermartketPrice instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from SupermartketPrice model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}