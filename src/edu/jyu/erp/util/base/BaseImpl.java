package edu.jyu.erp.util.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.jyu.erp.auth.dep.vo.DepModel;

public abstract class BaseImpl<T> extends HibernateDaoSupport {

	private Class<T> entityClass;

	// 将entityClass初始化
	public BaseImpl() {
		// getClass()获取到的是某一个BaseImpl的实现类，比如DepImpl
		// 所以下面是获取父类，也就是BaseImpl
		Type genType = getClass().getGenericSuperclass();
		// 下面代码就可以获取到传递进来的泛型参数类型了
		// 比如说DepImpl extends BaseImpl<DepModel>中的DepModel
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	public void save(T t) {
		// try {
		this.getHibernateTemplate().save(t);
		// } catch (Exception e) {
		// throw new AppException("ERR_EMP_DATABASE_IS_ERROR",e);
		// }
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public T get(Serializable uuid) {
		return this.getHibernateTemplate().get(entityClass, uuid);
	}

	/**
	 * 被getAll(BaseQueryModel, Integer, Integer)方法替代了
	 * 
	 * @return
	 */
	@Deprecated
	public List<T> getAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		return this.getHibernateTemplate().findByCriteria(dc);
	}


	public List<T> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		// 调用子类实现的自定义查询
		doQbc(dc, qm);

		return this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1) * pageCount, pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		// 查询记录数
		dc.setProjection(Projections.rowCount());
		// 调用子类实现的自定义查询
		doQbc(dc, qm);

		List<Long> count = this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}

	/**
	 * 钩子方法，留给各个实体的DAO类、同时也是该类的子类去实现，因为每个实体的查询条件都不同，然后再由 getAll方法调用
	 * 
	 * @param dc
	 * @param qm
	 */
	public abstract void doQbc(DetachedCriteria dc, BaseQueryModel qm);

}
