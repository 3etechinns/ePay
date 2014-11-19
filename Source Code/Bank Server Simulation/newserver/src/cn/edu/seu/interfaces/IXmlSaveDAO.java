package cn.edu.seu.interfaces;

import java.util.List;

import cn.edu.seu.xmlsave.XmlSave;


/**
 * Interface for XmlSaveDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IXmlSaveDAO {
	/**
	 * Perform an initial save of a previously unsaved XmlSave entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IXmlSaveDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            XmlSave entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(XmlSave entity);

	/**
	 * Delete a persistent XmlSave entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IXmlSaveDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            XmlSave entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(XmlSave entity);

	/**
	 * Persist a previously saved XmlSave entity and return it or a copy of it
	 * to the sender. A copy of the XmlSave entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IXmlSaveDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            XmlSave entity to update
	 * @return XmlSave the persisted XmlSave entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public XmlSave update(XmlSave entity);

	public XmlSave findById(String id);

	/**
	 * Find all XmlSave entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the XmlSave property to query
	 * @param value
	 *            the property value to match
	 * @return List<XmlSave> found by query
	 */
	public List<XmlSave> findByProperty(String propertyName, Object value);

	public List<XmlSave> findByNum(Object num);

	public List<XmlSave> findByXmldoc(Object xmldoc);

	/**
	 * Find all XmlSave entities.
	 * 
	 * @return List<XmlSave> all XmlSave entities
	 */
	public List<XmlSave> findAll();
}