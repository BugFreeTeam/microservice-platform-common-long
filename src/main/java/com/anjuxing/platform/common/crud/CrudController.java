package com.anjuxing.platform.common.crud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anjuxing.platform.common.base.BaseController;
import com.anjuxing.platform.common.base.JsonResult;
import com.anjuxing.platform.common.base.ValidateData;
import com.anjuxing.platform.common.exception.ControllerException;
import com.anjuxing.platform.common.util.ValidateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 基础Controller,封装公共方法
 * <br/> 1.根据主键查询数据  GET  /{id}
 * <br/> 2.根据主键查询数据列表  POST  /ids
 * <br/> 3.根据条件查询数据列表  POST  /list
 * <br/> 4.根据条件分页查询数据  POST  /page
 * <br/> 5.根据条件决断数据是否存在  POST  /exists
 * <br/> 6.新增数据  POST  /
 * <br/> 7.更新数据  PUT  /{id}
 * <br/> 8.逻辑删除数据  PUT  /cancel/{id}
 * <br/> 9.物理删除数据  DELETE  /{id}
 * <br/> 10.批量新增数据  POST  /batch/
 * <br/> 11.批量更新数据  PUT  /batch/
 * <br/> 12.批量逻辑删除数据  PUT  /batch/cancel
 * <br/> 13.批量物理删除数据  POST /batch/delete
 */
public class CrudController<S extends CrudService<T>, T extends CrudModel<T>> extends BaseController {
	
	@Autowired
	private S service;
	
	/**
	 * 根据主键查询数据
	 * 
	 * @param id
	 * @return
	 * @throws ControllerException
	 */
	@GetMapping(value = "/{id}")
	public JsonResult get(@PathVariable Long id) throws ControllerException {
		return new JsonResult(service.queryById(id));
	}
	
	/**
	 * 根据主键查询数据列表
	 * @param ids
	 * @return
	 * @throws ControllerException
	 */
	@PostMapping(value="/ids")
	public JsonResult getIds(@RequestBody List<Long> ids) throws ControllerException {
		return new JsonResult(service.queryByIds(ids));
	}
	
	/**
	 * 根据条件判断数据是否存在
	 * @param model
	 * @return
	 * @throws ControllerException
	 */
	@PostMapping(value="/exists")
	public JsonResult exists(@RequestBody T model) throws ControllerException {
		return new JsonResult(service.exists(model));
	}
	
	/**
	 * 根据条件查询数据列表
	 * @param model
	 * @return
	 * @throws ControllerException
	 */
	@PostMapping(value="/list")
	public JsonResult list(@RequestBody T model) throws ControllerException {
		return new JsonResult(service.queryList(model));
	}
	
	/**
	 * 根据条件分页查询数据
	 * @param model
	 * @return
	 * @throws ControllerException
	 */
	@PostMapping(value="/page")
	public JsonResult page(@RequestBody T model) throws ControllerException {
		int pageNum = model.getPageNum() == null ? 1 : model.getPageNum();
		int pageSize = model.getPageSize() == null ? 20 :model.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = service.queryList(model);
		return new JsonResult(new PageInfo<T>(list));
	}
	
	/**
	 * 保存数据
	 * @param model
	 * @return
	 * @throws ControllerException
	 */
	@PostMapping
	public JsonResult add(@RequestBody T model) throws ControllerException {
		JsonResult jsonResult = getJsonResult();
		model.preInsert();
		ValidateData valid = model.validate();
		if (valid.isStatus()) {
			boolean result = service.save(model);
			jsonResult = new JsonResult(result, model);
		}else {
			jsonResult.setResult(FAILURE);
			jsonResult.setMessage(valid.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 更新数据
	 * @param id
	 * @param model
	 * @return
	 * @throws ControllerException
	 */
	@PutMapping(value="/{id}")
	public JsonResult update(@PathVariable Long id, @RequestBody T model) throws ControllerException {
		JsonResult jsonResult = getJsonResult();
		if (ValidateUtils.isNotEmpty(id) && ValidateUtils.isNotEmpty(model)) {
			model.setId(id);
			model.preUpdate();
			boolean result = service.update(model);
			jsonResult = new JsonResult(result, model);
		}else {
			jsonResult.setResult(FAILURE);
			jsonResult.setMessage(MSG_PARAMETERS_ERROR);
		}
		return jsonResult;
	}
	
	/**
	 * 逻辑删除数据
	 * @param id
	 * @return
	 * @throws ControllerException
	 */
	@PutMapping(value="/cancel/{id}")
	public JsonResult cancel(@PathVariable Long id) throws ControllerException {
		JsonResult jsonResult = getJsonResult();
		T model = service.queryById(id);
		if (model != null) {
			model.preCancel();
			boolean result = service.cancel(model);
			jsonResult = new JsonResult(result, model);
		} else {
			jsonResult.setResult(FAILURE);
			jsonResult.setMessage(MSG_NO_DATA_EXIST);
		}
		return jsonResult;
	}
	
	/**
	 * 物理删除数据
	 * @param id
	 * @return
	 * @throws ControllerException
	 */
	@DeleteMapping(value="/{id}")
	public JsonResult remove(@PathVariable Long id) throws ControllerException {
		return new JsonResult(service.delete(id));
	}
	
	/**=======================================批量操作============================================*/
	
	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 * @throws ControllerException
	 */
	@PostMapping(value="/batch")
	public JsonResult add(@RequestBody List<T> list) throws ControllerException {
		JsonResult jsonResult = getJsonResult();
		if (list != null && list.size() > 0) {
			boolean addStatus = false;
			List<T> saveList = new ArrayList<T>();
			for(T model : list) {
				model.preInsert();
				ValidateData valid = model.validate();
				if (valid.isStatus()){
					saveList.add(model);
					addStatus = service.save(model);
				}else {
					jsonResult.setResult(FAILURE);
					jsonResult.setMessage(valid.getMessage());
					addStatus = false;
					break;
				}
			}
			if (addStatus){
				jsonResult.setResult(SUCCESS);
				jsonResult.setData(saveList);
				jsonResult.setMessage(SUCCESS_MESSAGE);
			}
		}else {
			jsonResult.setResult(FAILURE);
			jsonResult.setMessage(MSG_PARAMETERS_EMPTY);
		}
		return jsonResult;
	}
	
	/**
	 * 批量更新数据
	 * @param list
	 * @return
	 * @throws ControllerException
	 */
	@PutMapping(value="/batch")
	public JsonResult update(@RequestBody List<T> list) throws ControllerException {
		JsonResult jsonResult = getJsonResult();
		if (list != null && list.size() > 0) {
			boolean updateStatus = false;
			List<T> updateList = new ArrayList<T>();
			for(T model : list) {
				if (model != null && ValidateUtils.isNotEmpty(model.getId())) {
					model.preUpdate();
					updateList.add(model);
					updateStatus = service.update(model);
					if (!updateStatus){
						break;
					}
				}
			}
			if (updateStatus){
				jsonResult.setResult(SUCCESS);
				jsonResult.setData(updateList);
				jsonResult.setMessage(SUCCESS_MESSAGE);
			}else {
				jsonResult.setResult(FAILURE);
				jsonResult.setMessage(MSG_PARAMETERS_ERROR);
			}
		}else {
			jsonResult.setResult(FAILURE);
			jsonResult.setMessage(MSG_PARAMETERS_EMPTY);
		}
		return jsonResult;
	}
	
	/**
	 * 批量逻辑删除数据
	 * @param ids
	 * @return
	 * @throws ControllerException
	 */
	@PutMapping(value = "/batch/cancel")
	public JsonResult cancel(@RequestBody List<Long> ids) throws ControllerException {
		JsonResult jsonResult = getJsonResult();
		if (ids != null && ids.size() > 0) {
			List<T> list = service.queryByIds(ids);
			if (list != null && list.size() > 0) {
				boolean result = false;
				for (T model : list) {
					model.preCancel();
					result = service.cancel(model);
				}
				if (result) {
					jsonResult.setResult(SUCCESS);
					jsonResult.setMessage(SUCCESS_MESSAGE);
				}else {
					jsonResult.setResult(FAILURE);
					jsonResult.setMessage(FAILURE_MESSAGE);
				}
			}else {
				jsonResult.setResult(FAILURE);
				jsonResult.setMessage(MSG_NO_DATA_EXIST);
			}
		}else {
			jsonResult.setResult(FAILURE);
			jsonResult.setMessage(MSG_PARAMETERS_EMPTY);
		}
		return jsonResult;
	}
	
	/**
	 * 批量物理删除数据
	 * @param ids
	 * @return
	 * @throws ControllerException
	 */
	@PostMapping(value = "/batch/delete")
	public JsonResult remove(@RequestBody List<Long> ids) throws ControllerException {
		JsonResult jsonResult = getJsonResult();
		if (ids != null && ids.size() > 0) {
			boolean result = service.delete(ids);
			if (result) {
				jsonResult.setResult(SUCCESS);
				jsonResult.setMessage(SUCCESS_MESSAGE);
			}else {
				jsonResult.setResult(FAILURE);
				jsonResult.setMessage(FAILURE_MESSAGE);
			}
		}else {
			jsonResult.setResult(FAILURE);
			jsonResult.setMessage(MSG_PARAMETERS_EMPTY);
		}
		return jsonResult;
	}

}
