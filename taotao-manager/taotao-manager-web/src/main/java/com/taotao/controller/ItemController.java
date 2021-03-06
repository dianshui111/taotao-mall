package com.taotao.controller;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 商品类的处理器
 * @author cbq
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem tbItem=itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public  EUDataGridResult getItemList(Integer page,Integer rows) {
		EUDataGridResult result= itemService.getItemList(page,rows);
		return result;
	}
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	//用Pojo接受post的内容，属性值必须一致,String变量与form表单的name一致
	public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
		TaotaoResult result=itemService.createItem(item,desc,itemParams);
		return result;
		
	}
}
