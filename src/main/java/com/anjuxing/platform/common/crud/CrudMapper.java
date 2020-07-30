package com.anjuxing.platform.common.crud;

import java.util.List;

import com.anjuxing.platform.common.base.BaseMapper;

public interface CrudMapper<T> extends BaseMapper<T> {
	
	/**
	 * 根据主键查询数据
	 * @param id
	 * @return
	 */
	public T selectById(Long id);
	/**
	 * 根据主键查询数据列表
	 * @param ids
	 * @return
	 */
	public List<T> selectByIds(List<Long> ids);
	/**
	 * 根据条件查询数据列表
	 * @param model
	 * @return
	 */
	public List<T> selectList(T model);
	/**
	 * 根据条件判断数据是否存在
	 * @param model
	 * @return
	 */
	public List<T> exists(T model);
	/**
	 * 保存数据
	 * @param model
	 * @return
	 */
	public int save(T model);
	/**
	 * 更新数据
	 * @param model
	 * @return
	 */
	public int update(T model);
	/**
	 * 逻辑删除数据
	 * @param model
	 * @return
	 */
	public int cancel(T model);
	/**
	 * 物理删除数据
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**=======================================批量操作============================================*/
	
	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public int saveBatch(List<T> list);
	/**
	 * 批量更新数据
	 * @param list
	 * @return
	 */
	public int updateBatch(List<T> list);
	/**
	 * 批量逻辑删除数据 
	 * @param list
	 * @return
	 */
	public int cancelBatch(List<T> list);
	/**
	 * 批量物理删除数据 
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Long> ids);

}
