package com.sise.yxc.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.sise.yxc.model.News;

/**
 * A data access object (DAO) providing persistence and search support for News
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.sise.yxc.dao.News
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class NewsDAO {
//	private static final Logger log = LoggerFactory.getLogger(NewsDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String TITLE = "title";
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
	public void save(News transientInstance) {
//		log.debug("saving News instance");
		try {
			getCurrentSession().save(transientInstance);
//			log.debug("save successful");
		} catch (RuntimeException re) {
//			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(News persistentInstance) {
//		log.debug("deleting News instance");
		try {
			getCurrentSession().delete(persistentInstance);
//			log.debug("delete successful");
		} catch (RuntimeException re) {
//			log.error("delete failed", re);
			throw re;
		}
	}

	public News findById(java.lang.Integer id) {
//		log.debug("getting News instance with id: " + id);
		try {
			News instance = (News) getCurrentSession()
					.get("com.sise.yxc.model.News", id);
			return instance;
		} catch (RuntimeException re) {
//			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(News instance) {
//		log.debug("finding News instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.sise.yxc.model.News")
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
//		log.debug("finding News instance with property: " + propertyName
//				+ ", value: " + value);
		try {
			String queryString = "from News as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findAll() {
//		log.debug("finding all News instances");
		try {
			String queryString = "from News";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find all failed", re);
			throw re;
		}
	}

	public News merge(News detachedInstance) {
//		log.debug("merging News instance");
		try {
			News result = (News) getCurrentSession().merge(detachedInstance);
//			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
//			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(News instance) {
//		log.debug("attaching dirty News instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(News instance) {
//		log.debug("attaching clean News instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}
	public static NewsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (NewsDAO) ctx.getBean("NewsDAO");
	}
}