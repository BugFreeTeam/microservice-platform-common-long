package com.anjuxing.platform.common.crud;

import java.util.List;

import com.anjuxing.platform.common.base.BaseService;
import com.anjuxing.platform.common.exception.ServiceException;

public interface CrudService<T> extends BaseService<T> {
	
	/**
	 * 根据主键查询数据
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Long id) throws ServiceException;

	/**
	 * 根据主键查询数据列表
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> queryByIds(List<Long> ids) throws ServiceException;

	/**
	 * 根据条件查询数据列表
	 * 
	 * @param model
	 * @return
	 */
	public List<T> queryList(T model) throws ServiceException;

	/**
	 * 根据条件判断数据是否存在
	 * 
	 * @param model
	 * @return
	 */
	public List<T> exists(T model) throws ServiceException;

	/**
	 * 保存数据
	 * 
	 * @param model
	 * @return
	 */
	public boolean save(T model) throws ServiceException;

	/**
	 * 更新数据
	 * 
	 * @param model
	 * @return
	 */
	public boolean update(T model) throws ServiceException;

	/**
	 * 逻辑删除数据
	 * 
	 * @param model
	 * @return
	 */
	public boolean cancel(T model) throws ServiceException;

	/**
	 * 物理删除数据
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Long id) throws ServiceException;
	
	/**=======================================批量操作============================================*/

	/**
	 * 批量保存数据
	 * 
	 * @param list
	 * @return
	 */
	public boolean save(List<T> list) throws ServiceException;

	/**
	 * 批量更新数据
	 * 
	 * @param list
	 * @return
	 */
	public boolean update(List<T> list) throws ServiceException;

	/**
	 * 批量逻辑删除数据
	 * 
	 * @param list
	 * @return
	 */
	public boolean cancel(List<T> list) throws ServiceException;

	/**
	 * 批量物理删除数据
	 * 
	 * @param ids
	 * @return
	 */
	public boolean delete(List<Long> ids) throws ServiceException;

}
