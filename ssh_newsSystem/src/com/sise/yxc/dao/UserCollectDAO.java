package com.sise.yxc.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;
import com.sise.yxc.model.UserCollect;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserCollect entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sise.yxc.dao.UserCollect
 * @author MyEclipse Persistence Tools
 */
//@Transactional
public class UserCollectDAO {
//	private static final Logger log = LoggerFactory
//			.getLogger(UserCollectDAO.class);

	// property constants
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
	public void save(UserCollect transientInstance) {
//		log.debug("saving UserCollect instance");
		try {
			getCurrentSession().save(transientInstance);
//			log.debug("save successful");
		} catch (RuntimeException re) {
//			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserCollect persistentInstance) {
//		log.debug("deleting UserCollect instance");
		try {
			getCurrentSession().delete(persistentInstance);
//			log.debug("delete successful");
		} catch (RuntimeException re) {
//			log.error("delete failed", re);
			throw re;
		}
	}

	public UserCollect findById(java.lang.Integer id) {
//		log.debug("getting UserCollect instance with id: " + id);
		try {
			UserCollect instance = (UserCollect) getCurrentSession().get(
					"com.sise.yxc.model.UserCollect", id);
			return instance;
		} catch (RuntimeException re) {
//			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserCollect instance) {
//		log.debug("finding UserCollect instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.sise.yxc.model.UserCollect")
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
//		log.debug("finding UserCollect instance with property: " + propertyName
//				+ ", value: " + value);
		try {
			String queryString = "from UserCollect as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
//		log.debug("finding all UserCollect instances");
		try {
			String queryString = "from UserCollect";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find all failed", re);
			throw re;
		}
	}

	public UserCollect merge(UserCollect detachedInstance) {
//		log.debug("merging UserCollect instance");
		try {
			UserCollect result = (UserCollect)getCurrentSession().merge(
					detachedInstance);
//			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
//			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserCollect instance) {
//		log.debug("attaching dirty UserCollect instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserCollect instance) {
//		log.debug("attaching clean UserCollect instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}
	public static UserCollectDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UserCollectDAO) ctx.getBean("UserCollectDAO");
	}
}