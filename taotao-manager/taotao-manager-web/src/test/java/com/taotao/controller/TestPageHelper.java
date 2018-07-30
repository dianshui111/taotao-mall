package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {
	@Test
	public void testPageHelper(){
		//1.创建spring容器
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//2.引入mapper代理
		TbItemMapper mapper=context.getBean(TbItemMapper.class);

		//3.pageHelper 分页
		PageHelper.startPage(1, 10);
		//4.mapper查询 example 不添加查询条件，则表示查询所有
		TbItemExample example=new TbItemExample();
		List<TbItem>list=mapper.selectByExample(example);
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品："+ total);

	}
}
