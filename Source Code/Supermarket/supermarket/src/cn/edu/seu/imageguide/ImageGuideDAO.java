package cn.edu.seu.imageguide;

import cn.edu.seu.supermarketprice.EntityManagerHelper;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 	* A data access object (DAO) providing persistence and search support for ImageGuide entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see imageguide.ImageGuide
  * @author MyEclipse Persistence Tools 
 */

public class ImageGuideDAO  implements IImageGuideDAO{
	//property constants
	public static final String IMAGENAME = "imagename";
	public static final String NUM = "num";
	public static final String IMAGESTREAM = "imagestream";
	public static final String REMARK = "remark";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved ImageGuide entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   ImageGuideDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity ImageGuide entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(ImageGuide entity) {
    				EntityManagerHelper.log("saving ImageGuide instance", Level.INFO, null);
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
	 Delete a persistent ImageGuide entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   ImageGuideDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity ImageGuide entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(ImageGuide entity) {
    				EntityManagerHelper.log("deleting ImageGuide instance", Level.INFO, null);
	        try {
	        	getEntityManager().getTransaction().begin();
        	entity = getEntityManager().getReference(ImageGuide.class, entity.getNotes());
            getEntityManager().remove(entity);
            getEntityManager().getTransaction().commit();
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved ImageGuide entity and return it or a copy of it to the sender. 
	 A copy of the ImageGuide entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = ImageGuideDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity ImageGuide entity to update
	 @return ImageGuide the persisted ImageGuide entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public ImageGuide update(ImageGuide entity) {
    				EntityManagerHelper.log("updating ImageGuide instance", Level.INFO, null);
	        try {
	        	getEntityManager().getTransaction().begin();
            ImageGuide result = getEntityManager().merge(entity);
            getEntityManager().getTransaction().commit();
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public ImageGuide findById( String id) {
    				EntityManagerHelper.log("finding ImageGuide instance with id: " + id, Level.INFO, null);
	        try {
            ImageGuide instance = getEntityManager().find(ImageGuide.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all ImageGuide entities with a specific property value.  
	 
	  @param propertyName the name of the ImageGuide property to query
	  @param value the property value to match
	  	  @return List<ImageGuide> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<ImageGuide> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding ImageGuide instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from ImageGuide model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		}
	}			
	public List<ImageGuide> findByImagename(Object imagename
	) {
		return findByProperty(IMAGENAME, imagename
		);
	}
	
	public List<ImageGuide> findByNum(Object num
	) {
		return findByProperty(NUM, num
		);
	}
	
	public List<ImageGuide> findByImagestream(Object imagestream
	) {
		return findByProperty(IMAGESTREAM, imagestream
		);
	}
	
	public List<ImageGuide> findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	
	
	/**
	 * Find all ImageGuide entities.
	  	  @return List<ImageGuide> all ImageGuide entities
	 */
	@SuppressWarnings("unchecked")
	public List<ImageGuide> findAll(
		) {
					EntityManagerHelper.log("finding all ImageGuide instances", Level.INFO, null);
			try {
			final String queryString = "select model from ImageGuide model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
}