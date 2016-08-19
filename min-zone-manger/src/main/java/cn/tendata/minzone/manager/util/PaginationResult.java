package cn.tendata.minzone.manager.util;

import java.util.List;

import org.springframework.data.domain.Page;

public class PaginationResult<T> {
  
	
	private Integer sumPage;
	
	private List<T> lists;
	
	private long totalRecords;
	
	private long totalDisplayRecords;
	
	
	

	public PaginationResult() {

	}
	
	

	public PaginationResult(Integer sumPage, List<T> lists, long totalRecords, long totalDisplayRecords) {
		this.sumPage = sumPage;
		this.lists = lists;
		this.totalRecords = totalRecords;
		this.totalDisplayRecords = totalDisplayRecords;
	}



	public Integer getSumPage() {
		return sumPage;
	}

	public void setSumPage(Integer sumPage) {
		this.sumPage = sumPage;
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public long getTotalDisplayRecords() {
		return totalDisplayRecords;
	}

	public void setTotalDisplayRecords(long totalDisplayRecords) {
		this.totalDisplayRecords = totalDisplayRecords;
	}
	
	
	public void fillData(Page<T> page){
		this.setTotalRecords(page.getTotalElements());
		this.setLists(page.getContent());
//		this.setSumPage(sumPage);
	}
	
	public void fillData(List<T> list){
		this.setLists(list);
	}
}
