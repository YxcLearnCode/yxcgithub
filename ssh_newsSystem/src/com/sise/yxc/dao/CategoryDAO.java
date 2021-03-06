package com.sise.yxc.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.sise.yxc.model.Category;

/**
 * A data access object (DAO) providing persistence and search support for
 * Category entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.sise.yxc.model.Category
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CategoryDAO {
	/**private static final Logger log = LoggerFactory
			.getLogger(CategoryDAO.class);*/
	
	// property constants
	public static final String TYPE_NAME = "typeName";
	
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}
	public void save(Category transientInstance) {
//		log.debug("saving Category instance");
		try {
			getCurrentSession().save(transientInstance);
//			log.debug("save successful");
		} catch (RuntimeException re) {
//			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Category persistentInstance) {
//		log.debug("deleting Category instance");
		try {
			getCurrentSession().delete(persistentInstance);
//			log.debug("delete successful");
		} catch (RuntimeException re) {
//			log.error("delete failed", re);
			throw re;
		}
	}

	public Category findById(java.lang.Integer id) {
//		log.debug("getting Category instance with id: " + id);
		try {
			Category instance = (Category) getCurrentSession().get(
					"com.sise.yxc.model.Category", id);
			return instance;
		} catch (RuntimeException re) {
//			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Category instance) {
//		log.debug("finding Category instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.sise.yxc.model.Category")
					.add(Example.create(instance)).list();
//			log.debug("find by example successful, result size: "
//					+ results.size());
			return results;
		} catch (RuntimeException re) {
//			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
//		log.debug("finding Category instance with property: " + propertyName
//				+ ", value: " + value);
		try {
			String queryString = "from Category as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTypeName(Object typeName) {
		return findByProperty(TYPE_NAME, typeName);
	}

	public List findAll() {
//		log.debug("finding all Category instances");
		try {
			String queryString = "from Category";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find all failed", re);
			throw re;
		}
	}

	public Category merge(Category detachedInstance) {
//		log.debug("merging Category instance");
		try {
			Category result = (Category) getCurrentSession().merge(detachedInstance);
//			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
//			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Category instance) {
//		log.debug("attaching dirty Category instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Category instance) {
//		log.debug("attaching clean Category instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}
	public static CategoryDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CategoryDAO) ctx.getBean("CategoryDAO");
	}
}