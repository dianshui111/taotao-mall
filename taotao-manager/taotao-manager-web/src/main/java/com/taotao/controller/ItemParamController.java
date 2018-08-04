package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 根据类目查询参数模板
 * @author cbq
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	/**
	 * 根据类目查询模板库
	 * @param cid
	 * @return
	 */
	//url="/item/param/query/itemcatid/" 
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	//@PathVariable 注解的参数名一定要与RequestMapping路径中用的参数名一致！
	public TaotaoResult getItemParamByCid(@PathVariable long itemCatId) {
		TaotaoResult result=itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	/**
	 * 根据参数创造一个模板pojo
	 * url="/item/param/save/"+$("#itemParamAddTable [name=cid]")
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid,String paramData) {
		TbItemParam tbItemParam=new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		TaotaoResult result=itemParamService.insertItemParam(tbItemParam);
		return result;
	}
}
