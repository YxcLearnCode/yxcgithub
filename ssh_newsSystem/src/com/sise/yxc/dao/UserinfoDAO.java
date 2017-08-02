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

import com.sise.yxc.model.Userinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Userinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.sise.yxc.dao.Userinfo
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UserinfoDAO {
//	private static final Logger log = LoggerFactory
//			.getLogger(UserinfoDAO.class);
//	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String NICKNAME = "nickname";
	public static final String SEX = "sex";
	public static final String INFO = "info";
	public static final String ISADMIN = "isadmin";
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
	public void save(Userinfo transientInstance) {
//		log.debug("saving Userinfo instance");
		try {
			getCurrentSession().save(transientInstance);
//			log.debug("save successful");
		} catch (RuntimeException re) {
//			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Userinfo persistentInstance) {
//		log.debug("deleting Userinfo instance");
		try {
			getCurrentSession().delete(persistentInstance);
//			log.debug("delete successful");
		} catch (RuntimeException re) {
//			log.error("delete failed", re);
			throw re;
		}
	}

	public Userinfo findById(java.lang.Integer id) {
//		log.debug("getting Userinfo instance with id: " + id);
		try {
			Userinfo instance = (Userinfo) getCurrentSession().get(
					"com.sise.yxc.model.Userinfo", id);
			return instance;
		} catch (RuntimeException re) {
//			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Userinfo instance) {
//		log.debug("finding Userinfo instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.sise.yxc.model.Userinfo")
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
//		log.debug("finding Userinfo instance with property: " + propertyName
//				+ ", value: " + value);
		try {
			String queryString = "from Userinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByNickname(Object nickname) {
		return findByProperty(NICKNAME, nickname);
	}

	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findByInfo(Object info) {
		return findByProperty(INFO, info);
	}

	public List findByIsadmin(Object isadmin) {
		return findByProperty(ISADMIN, isadmin);
	}

	public List findAll() {
//		log.debug("finding all Userinfo instances");
		try {
			String queryString = "from Userinfo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find all failed", re);
			throw re;
		}
	}

	public Userinfo merge(Userinfo detachedInstance) {
//		log.debug("merging Userinfo instance");
		try {
			Userinfo result = (Userinfo) getCurrentSession().merge(detachedInstance);
//			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
//			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Userinfo instance) {
//		log.debug("attaching dirty Userinfo instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userinfo instance) {
//		log.debug("attaching clean Userinfo instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
//			log.debug("attach successful");
		} catch (RuntimeException re) {
//			log.error("attach failed", re);
			throw re;
		}
	}
	public static UserinfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserinfoDAO) ctx.getBean("UserinfoDAO");
	}
}