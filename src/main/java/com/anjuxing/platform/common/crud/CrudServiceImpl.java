package com.anjuxing.platform.common.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.anjuxing.platform.common.base.BaseServiceImpl;
import com.anjuxing.platform.common.exception.ServiceException;

@Transactional(readOnly = true, rollbackFor = ServiceException.class)
public class CrudServiceImpl<M extends CrudMapper<T>, T extends CrudModel<T>> extends BaseServiceImpl<T> implements CrudService<T> {

	@Autowired
	private M mapper; 

	/**
	 * 根据主键查询数据 
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Long id) throws ServiceException {
		try {
			return mapper.selectById(id);
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
	}

	/**
	 * 根据主键查询数据列表
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> queryByIds(List<Long> ids) throws ServiceException {
		try {
			return mapper.selectByIds(ids);
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
	}
	
	/**
	 * 根据条件查询数据列表
	 * @param model
	 * @return
	 */
	public List<T> queryList(T model) throws ServiceException {
		try {
			return mapper.selectList(model);
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
	}

	/**
	 * 根据条件判断数据是否存在
	 * 
	 * @param model
	 * @return
	 */
	public List<T> exists(T model) throws ServiceException {
		try {
			return mapper.exists(model);
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
	}

	/**
	 * 保存数据
	 * 
	 * @param model
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean save(T model) throws ServiceException {
		boolean result = false;
		try {
			int num = mapper.save(model);
			result = num == 1 ? true : false;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}

	/**
	 * 更新数据
	 * 
	 * @param model
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean update(T model) throws ServiceException {
		boolean result = false;
		try {
			int num = mapper.update(model);
			result = num == 1 ? true : false;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 逻辑删除数据
	 * 
	 * @param model
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean cancel(T model) throws ServiceException {
		boolean result = false;
		try {
			int num = mapper.cancel(model);
			result = num == 1 ? true : false;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}

	/**
	 * 物理删除数据
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean delete(Long id) throws ServiceException {
		boolean result = false;
		try {
			mapper.delete(id);
			result = true;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}
	
	/**=======================================批量操作============================================*/

	/**
	 * 批量保存数据
	 * 
	 * @param list
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean save(List<T> list) throws ServiceException {
		boolean result = false;
		try {
			mapper.saveBatch(list);
			result = true;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}

	/**
	 * 批量更新数据
	 * 
	 * @param list
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean update(List<T> list) throws ServiceException {
		boolean result = false;
		try {
			mapper.updateBatch(list);
			result = true;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}

	/**
	 * 批量逻辑删除数据
	 * 
	 * @param list
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean cancel(List<T> list) throws ServiceException {
		boolean result = false;
		try {
			mapper.cancelBatch(list);
			result = true;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}

	/**
	 * 批量物理删除数据
	 * 
	 * @param ids
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean delete(List<Long> ids) throws ServiceException {
		boolean result = false;
		try {
			mapper.deleteBatch(ids);
			result = true;
		} catch (Exception e) {
			throw new ServiceException("", e.getMessage());
		}
		return result;
	}
	
}
