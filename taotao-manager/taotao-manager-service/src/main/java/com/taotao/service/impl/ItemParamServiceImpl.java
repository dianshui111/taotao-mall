package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	public TbItemParamMapper paramMapper;
	
	/**
	 * 根据类目查询模板库
	 */
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
	//根据类id判断该类模板是否已存在
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria=example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list=paramMapper.selectByExampleWithBLOBs(example);
		
		//模板已存在,返回模板
		if(list!=null&&list.size()>0) {
			return TaotaoResult.ok(list.get(0));
		}
		//模板不存在
		return TaotaoResult.ok();
	}
	
	
	/**
	 * 插入模板
	 */
	@Override
	public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
		//补全pojo
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		paramMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}

}
