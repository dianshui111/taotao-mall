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
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
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
	public TaotaoResult createItem(TbItem item) {
		//item补全
		//生成商品ID
		Long itemId=IDUtils.genItemId();
		//生成商品状态 1-正常 2-下架 3-删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		
		return TaotaoResult.ok();
	}

}
