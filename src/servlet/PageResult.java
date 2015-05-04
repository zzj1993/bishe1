package servlet;

import java.util.List;

public class PageResult {
	 private List list; //װ��ҳ�б��е�����

	 private Integer firstPage; //��һҳ

	 private Integer prePage;  //��һҳ

	 private Integer nextPage;//��һҳ

	 private Integer currentPage;//��ǰҳ��

	 private Integer totalPage;  //��ҳ��/βҳ

	 private Integer count;//������

	 private Integer size; //ÿҳ������

	 public List getList() {

	 return list;

	 }

	 public void setList(List list) {

	 this.list = list;

	 }

	 public Integer getFirstPage() {

	 return firstPage;

	 }

	 public void setFirstPage(Integer firstPage) {

	 this.firstPage = firstPage;

	 }

	 public Integer getPrePage() {

	 return (this.currentPage-1==0?1:this.currentPage-1);

	 }

	 public void setPrePage(Integer prePage) {

	 this.prePage = prePage;

	 }

	 public Integer getNextPage() {

	 return (this.currentPage==this.totalPage?this.totalPage:this.currentPage+1);

	 }

	 public void setNextPage(Integer nextPage) {

	 this.nextPage = nextPage;

	 }

	 public Integer getCurrentPage() {

	 return currentPage;

	 }

	 public void setCurrentPage(Integer currentPage) {

	 this.currentPage = currentPage;

	 }

	 public Integer getTotalPage() {

	 return (this.count%this.size==0?this.count/this.size:this.count/this.size+1);

	 }

	 public void setTotalPage(Integer totalPage) {

	 this.totalPage = totalPage;

	 }

	 public Integer getCount() {

	 return count;

	 }

	 public void setCount(Integer count) {

	 this.count = count;

	 }

	 public Integer getSize() {

	 return size;

	 }

	 public void setSize(Integer size) {

	 this.size = size;

	 }
}
