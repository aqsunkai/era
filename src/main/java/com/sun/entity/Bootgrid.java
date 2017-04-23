package com.sun.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Bootgrid表格传值
 */
public class Bootgrid implements Serializable {

	private static final long serialVersionUID = -6410710264392294258L;
	private int current;
	private int rowCount;
	private int maxResults;
	private int firstResult;
	private long total;
	private Object rows;
	private String searchPhrase;
	private String sea1;
	private String sea2;
	private String sea3;
	private String sea4;
	private String sea5;
	private String sea6;
	private String sea7;
	private String sea8;
	private String sea9;
	private String sea10;
	private String sea11;
	private String sea12;
	private String sea13;
	private String sea14;
	private String sea15;
	private String sea16;
	private String sea17;
	private String sea18;
	private String sea19;
	private String sea20;
	
	
	
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	private String[] sea21;
	private String[] sea22;
	private String[] sea23;
	
	private String sea24;
	private String sea25;
	private String sea26;
	
	private String sea30;
	private String sea31;
	private String sea32;
	
	
	

	public String getSea30() {
		return sea30;
	}

	public void setSea30(String sea30) {
		this.sea30 = sea30;
	}

	public String getSea31() {
		return sea31;
	}

	public void setSea31(String sea31) {
		this.sea31 = sea31;
	}

	public String getSea32() {
		return sea32;
	}

	public void setSea32(String sea32) {
		this.sea32 = sea32;
	}

	public String getSea24() {
		return sea24;
	}

	public void setSea24(String sea24) {
		this.sea24 = sea24;
	}

	public String getSea25() {
		return sea25;
	}

	public void setSea25(String sea25) {
		this.sea25 = sea25;
	}

	public String getSea26() {
		return sea26;
	}

	public void setSea26(String sea26) {
		this.sea26 = sea26;
	}

	public String[] getSea21() {
		return sea21;
	}

	public void setSea21(String[] sea21) {
		this.sea21 = sea21;
	}

	public String[] getSea22() {
		return sea22;
	}

	public void setSea22(String[] sea22) {
		this.sea22 = sea22;
	}

	public String[] getSea23() {
		return sea23;
	}

	public void setSea23(String[] sea23) {
		this.sea23 = sea23;
	}

	public String getSea16() {
		return sea16;
	}

	public void setSea16(String sea16) {
		this.sea16 = sea16;
	}

	public String getSea17() {
		return sea17;
	}

	public void setSea17(String sea17) {
		this.sea17 = sea17;
	}

	public String getSea18() {
		return sea18;
	}

	public void setSea18(String sea18) {
		this.sea18 = sea18;
	}

	public String getSea19() {
		return sea19;
	}

	public void setSea19(String sea19) {
		this.sea19 = sea19;
	}

	public String getSea20() {
		return sea20;
	}

	public void setSea20(String sea20) {
		this.sea20 = sea20;
	}

	public String getSea1() {
		return sea1;
	}

	public void setSea1(String sea1) {
		this.sea1 = sea1;
	}

	public String getSea2() {
		return sea2;
	}

	public void setSea2(String sea2) {
		this.sea2 = sea2;
	}

	public String getSea3() {
		return sea3;
	}

	public void setSea3(String sea3) {
		this.sea3 = sea3;
	}

	public String getSea4() {
		return sea4;
	}

	public void setSea4(String sea4) {
		this.sea4 = sea4;
	}

	public String getSea5() {
		return sea5;
	}

	public void setSea5(String sea5) {
		this.sea5 = sea5;
	}

	public String getSea6() {
		return sea6;
	}

	public void setSea6(String sea6) {
		this.sea6 = sea6;
	}

	public String getSea7() {
		return sea7;
	}

	public void setSea7(String sea7) {
		this.sea7 = sea7;
	}

	public String getSea8() {
		return sea8;
	}

	public void setSea8(String sea8) {
		this.sea8 = sea8;
	}

	public String getSea9() {
		return sea9;
	}

	public void setSea9(String sea9) {
		this.sea9 = sea9;
	}

	public String getSea10() {
		return sea10;
	}

	public void setSea10(String sea10) {
		this.sea10 = sea10;
	}

	public String getSea11() {
		return sea11;
	}

	public void setSea11(String sea11) {
		this.sea11 = sea11;
	}

	public String getSea12() {
		return sea12;
	}

	public void setSea12(String sea12) {
		this.sea12 = sea12;
	}

	public String getSea13() {
		return sea13;
	}

	public void setSea13(String sea13) {
		this.sea13 = sea13;
	}

	public String getSea14() {
		return sea14;
	}

	public void setSea14(String sea14) {
		this.sea14 = sea14;
	}

	public String getSea15() {
		return sea15;
	}

	public void setSea15(String sea15) {
		this.sea15 = sea15;
	}

	private Integer param;
	private Map<String, Object> sort = new HashMap<>();
	
	private String sortType;
	private String sortBy;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = new StringBuffer("%")
				.append(searchPhrase == null ? "" : searchPhrase).append("%")
				.toString();
	}

	public int getFirstResult() {
		return (this.current - 1) * this.rowCount;
	}

	public int getMaxResults() {
		return this.rowCount == -1 ? Integer.MAX_VALUE : this.rowCount;
	}

	public Integer getParam() {
		return param;
	}

	public void setParam(Integer param) {
		this.param = param;
	}

	public Map<String, Object> getSort() {
		if(!sort.isEmpty()){
			Iterator<Entry<String, Object>> it =sort.entrySet().iterator();
			//如果排序字段有多个 则随机取一个进行排序
			Entry<String, Object> e = it.next();
			this.sortType=(String) e.getValue();
			this.sortBy=e.getKey();
		}
		return sort;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public void setSort(Map<String, Object> sort) {
		this.sort = sort;
	}

	public void fieldSort(){
		getSort();
	}
}
