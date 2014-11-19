package cn.edu.seu.imageguide;

import java.util.List;

/**
 * Interface for ImageGuideDAO.
 * @author MyEclipse Persistence Tools
 */

public interface IImageGuideDAO {
		/**
	 Perform an initial save of a previously unsaved ImageGuide entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   IImageGuideDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity ImageGuide entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(ImageGuide entity);
    /**
	 Delete a persistent ImageGuide entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   IImageGuideDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity ImageGuide entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(ImageGuide entity);
   /**
	 Persist a previously saved ImageGuide entity and return it or a copy of it to the sender. 
	 A copy of the ImageGuide entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = IImageGuideDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity ImageGuide entity to update
	 @return ImageGuide the persisted ImageGuide entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
	public ImageGuide update(ImageGuide entity);
	public ImageGuide findById( String id);
	 /**
	 * Find all ImageGuide entities with a specific property value.  
	 
	  @param propertyName the name of the ImageGuide property to query
	  @param value the property value to match
	  	  @return List<ImageGuide> found by query
	 */
	public List<ImageGuide> findByProperty(String propertyName, Object value
		);
	public List<ImageGuide> findByImagename(Object imagename
		);
	public List<ImageGuide> findByNum(Object num
		);
	public List<ImageGuide> findByImagestream(Object imagestream
		);
	public List<ImageGuide> findByRemark(Object remark
		);
	/**
	 * Find all ImageGuide entities.
	  	  @return List<ImageGuide> all ImageGuide entities
	 */
	public List<ImageGuide> findAll(
		);	
}