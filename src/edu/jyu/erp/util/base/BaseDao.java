package edu.jyu.erp.util.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	public void save(T t);

	public void update(T t);

	public void delete(T t);

	public List<T> getAll();

	public T get(Serializable uuid);

	/**
	 * 根据实体的查询条件和分页条件查询记录
	 * 
	 * @param qm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<T> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount);

	/**
	 * 获取总记录数
	 * 
	 * @param qm
	 * @return
	 */
	public Integer getCount(BaseQueryModel qm);
}
