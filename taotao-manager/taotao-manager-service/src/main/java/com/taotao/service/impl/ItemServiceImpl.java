package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
/**
 * 商品类的service
 * @author cbq
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper paramItemMapper;
	
	
	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		//直接用主键查询
		//itemMapper.selectByPrimaryKey(itemId);
		//采用条件查询，添加查询条件
		TbItemExample example=new TbItemExample();
		Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list=itemMapper.selectByExample(example);
		if(list!=null&&list.size()!=0) {
			TbItem item=list.get(0);
			return item;
		}
		return null;
		
	}
	
	
	
	@Override
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		//查询商品列表
		TbItemExample example=new TbItemExample();
		List<TbItem>list=itemMapper.selectByExample(example);
		
		//创建一个返回值对象
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}



	@Override
	public TaotaoResult createItem(TbItem item,String desc, String itemParam )throws Exception {
		//item补全
		//生成商品ID
		Long itemId=IDUtils.genItemId();
		item.setId(itemId);
		//生成商品状态 1-正常 2-下架 3-删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		//插入商品描述信息
		TaotaoResult result=insertItemDesc(itemId, desc);
		if(result.getStatus()!=200) {
			throw new Exception();
		}
		//插入商品规格
		insertItemParamItem(itemId, itemParam);
		if(result.getStatus()!=200) {
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	
	private TaotaoResult insertItemDesc(Long itemId,String desc) {
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);		
		return TaotaoResult.ok();
	}
	
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		paramItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}

}
