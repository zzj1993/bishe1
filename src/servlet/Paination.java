package servlet;

import itemCF.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paination {
	 private int currentPage;
	    private int totalPages;
	    private int pageRows=2;
	    private int totalRows;
	    private int pageStartRow;
	    private int pageEndRow;
	    private boolean hasPreviousPage;
	    private boolean hasNextPage;
	    private List<Object[]>totalList;

	public void selectBySQL() throws SQLException, ClassNotFoundException{//这个方法主要是输出数据库里信息，就是你在servlet里把数据库信息放到list里一个道理：
		List<Object[]> notes=new ArrayList<Object[]>();
	    DBUtil db = new DBUtil();
	    Connection conn = db.getConn();
	    try{	    	 
	         String sql="select * from result1 where userid=1";
	         PreparedStatement pst = conn.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery();
	         
	         while(rs.next()){
	         	Object[] note=new Object[5];
	         	note[0] = rs.getInt(1);
	         	note[1] = rs.getInt(2);
	         	note[2] = rs.getInt(3);
	         	note[3] = rs.getInt(4);
	         	notes.add(note);
	         }
	         while(rs!=null){
	        	 rs.close();
	         }
	     }catch (Exception e){
	          e.printStackTrace();
	     }
	     while(conn!=null){
	    	 conn.close();
	     }
	     totalList=notes;
	     initPageBean(totalList,pageRows);
	}
	       //分页的一个方法
	 public void initPageBean(List<Object[]>totalList,int pageRows){
	            this.totalList=totalList;
	            this.pageRows=pageRows;
	            this.totalRows=totalList.size();
	            this.currentPage=1;
	            if(totalRows%pageRows==0){
	             totalPages=totalRows/pageRows;
	             if(this.totalRows==0){
	              this.pageRows=0;
	             }
	            }else{
	             totalPages=totalRows/pageRows +1;
	            }
	            this.hasPreviousPage=false;
	            if(currentPage==totalPages){
	             hasNextPage=false;
	            }else{
	             hasNextPage=true;
	            }
	            this.pageStartRow=1;
	            if(totalRows<pageRows){
	             this.pageEndRow=totalRows;
	            }else{
	             this.pageEndRow=pageRows;
	            }
	            
	       }
	//当前页的方法
	       public List<Object[]> getCurrentPageList(){
	        if(currentPage*pageRows<totalRows){
	         pageEndRow=currentPage*pageRows;
	         pageStartRow=pageEndRow-pageRows;
	        }else{
	         pageEndRow=totalRows;
	         pageStartRow=pageRows*(totalPages-1);
	        }
	        List<Object[]>pageList=new ArrayList<Object[]>(pageEndRow-pageStartRow+1);
	        if(totalRows!=0){
	         for(int i=pageStartRow;i<pageEndRow;i++){
	          pageList.add(totalList.get(i));
	         }
	        }
	        return pageList;
	       }
	//上一页的一个方法
	       public List<Object[]> getPreviousPageList(){
	        currentPage=currentPage-1;
	        if(currentPage<1){
	         currentPage=1;
	        }
	        if(currentPage>=totalPages){
	         hasNextPage=false;
	        }else{
	         hasNextPage=true;
	        }
	        if((currentPage-1)>0){
	         hasPreviousPage=true;
	        }else{
	         hasPreviousPage=false;
	        }
	        List<Object[]> pageList=this.getCurrentPageList();
	        return pageList;
	       }
	//下一页的一个方法
	       public List<Object[]> getNextPageList(){
	        currentPage=currentPage+1;
	        if(currentPage>totalPages){
	         currentPage=totalPages;
	        }
	        if((currentPage-1)>0){
	         hasPreviousPage=true;
	        }else{
	         hasPreviousPage=false;
	        }
	        if(currentPage>=totalPages){
	         hasNextPage=false;
	        }else{
	         hasNextPage=true;
	        }
	       
	        List<Object[]> pageList=this.getCurrentPageList();
	        return pageList;
	       }
	       
	       public List<Object[]> AppointPageList(int currentPage){
	        this.currentPage=currentPage;
	        if(currentPage>this.totalPages){
	         this.currentPage=this.totalPages;
	        }
	        if(currentPage<1){
	        this.currentPage=1;
	        }else{
	         hasPreviousPage=false;
	        }
	        if(currentPage>1){
	       this.hasPreviousPage=true;
	        }else{
	         this.hasPreviousPage=false;
	        }
	        if(this.currentPage<this.totalPages){
	         this.hasNextPage=true;
	        }else{
	         this.hasNextPage=false;
	        }
	        List<Object[]> pageList=this.getCurrentPageList();
	        return pageList;
	       }
	       public int getCurrentPage() {
	     return currentPage;
	    }
	    public boolean isHasNextPage() {
	     return hasNextPage;
	    }
	    public boolean isHasPreviousPage() {
	     return hasPreviousPage;
	    }
	    public int getPageEndRow() {
	     return pageEndRow;
	    }
	    public int getPageRows() {
	     return pageRows=1;
	    }
	    public int getPageStartRow() {
	     return pageStartRow;
	    }
	    public List<Object[]> getTotalList() {
	     return totalList;
	    }
	    public int getTotalPages() {
	     return totalPages;
	    }
	    public int getTotalRows() {
	     return totalRows;
	    }
}
