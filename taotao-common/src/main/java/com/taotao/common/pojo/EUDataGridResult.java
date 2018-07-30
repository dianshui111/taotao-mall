package com.taotao.common.pojo;

import java.util.List;
/**
 * 按照EasyUI的griddata格式规定，创建的pojo
 * @author cbq
 *
 */
public class EUDataGridResult {
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long l) {
		this.total = l;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
