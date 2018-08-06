package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.service.ItemCatService;

import pojo.CatNode;
import pojo.CatResult;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	TbItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		CatResult result=new CatResult();
		result.setData(getCatList(0));
		return result;
	}
	//使用递归查询树状结构
	private List<?> getCatList(long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list=itemCatMapper.selectByExample(example);
		List resultList=new ArrayList<>();
		int count=0;
		for (TbItemCat tbItemCat:list) {
			//判断是否是叶子节点
			if(tbItemCat.getIsParent()) {
				//根节点
				CatNode catNode = new CatNode();
				if (parentId == 0) {
						catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
					} else {
						catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				//递归
				catNode.setItem(getCatList(tbItemCat.getId()));	
				count++;
				resultList.add(catNode);
				if(parentId==0&&count>=14) {
					break;
				}
				
			}else {
				//叶子节点
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
			
		}
		return resultList;
	}

}
