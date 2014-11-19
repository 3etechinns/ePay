package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.elenoteinfo.ElenoteInfo;


/**
 * Interface for ElenoteInfoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IElenoteInfoDAO {
	/**
	 * Perform an initial save of a previously unsaved ElenoteInfo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElenoteInfoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElenoteInfo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ElenoteInfo entity);

	/**
	 * Delete a persistent ElenoteInfo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IElenoteInfoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ElenoteInfo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ElenoteInfo entity);

	/**
	 * Persist a previously saved ElenoteInfo entity and return it or a copy of
	 * it to the sender. A copy of the ElenoteInfo entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IElenoteInfoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ElenoteInfo entity to update
	 * @return ElenoteInfo the persisted ElenoteInfo entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ElenoteInfo update(ElenoteInfo entity);

	public ElenoteInfo findById(String id);

	/**
	 * Find all ElenoteInfo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElenoteInfo property to query
	 * @param value
	 *            the property value to match
	 * @return List<ElenoteInfo> found by query
	 */
	public List<ElenoteInfo> findByProperty(String propertyName, Object value);

	public List<ElenoteInfo> findByPayernum(Object payernum);

	public List<ElenoteInfo> findByRecernum(Object recernum);

	public List<ElenoteInfo> findByAmount(Object amount);

	public List<ElenoteInfo> findByPayerdevice(Object payerdevice);

	public List<ElenoteInfo> findByRecerdevice(Object recerdevice);

	public List<ElenoteInfo> findByPayername(Object payername);

	public List<ElenoteInfo> findByRecername(Object recername);

	public List<ElenoteInfo> findByTransfertime(Object transfertime);

	/**
	 * Find all ElenoteInfo entities.
	 * 
	 * @return List<ElenoteInfo> all ElenoteInfo entities
	 */
	public List<ElenoteInfo> findAll();
}