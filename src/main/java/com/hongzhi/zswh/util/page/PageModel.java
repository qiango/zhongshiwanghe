package com.hongzhi.zswh.util.page;

import java.util.ArrayList;
import java.util.List;

import com.hongzhi.zswh.util.basic.ObjectUtil;

/**
 * @author Saxon create date: 2016年3月2日 下午8:07:20
 * @param <T>
 */
public class PageModel {
	//query  input params
	private int page ; // 当前页
	public int totalPages ; // 总页数
	private int pageRecorders;// 每页10条数据
	private int totalRows ; // 总数据数
	private int pageStartRow ;// 每页的起始数
	private int pageEndRow ; // 每页显示数据的终止数
	private Object model_object; // query condition 
	private String language;
	private String other ;  //  search condition
	
	//output 
	private Object result;
	private String pageString;
	
	/// generate pageString 
	private List<String>  pageParam;
	private List<String>  pageParamVal;
	
	////////////////////////////////////////////////////////////// below , test for delete 
	private boolean hasNextPage = false; // 是否有下一页
	private boolean hasPreviousPage = false; // 是否有前一页
	private String url = "";
	
	

	public PageModel() {
		this.page=1;
		this.pageRecorders=10;
	}
	
	
	/**
	 * @param page    page_number 
	 * @param pageRecorders  page_size
	 */
	public PageModel(String page, String pageRecorders) {
		if (ObjectUtil.isEmpty(page)){
			this.page = 1;
		} else {
			int temp_page = Integer.parseInt(page);
			if(temp_page>0){
				this.page=temp_page;
			}else{
				this.page=1;
			}
		}
		if (ObjectUtil.isEmpty(pageRecorders)){
			this.pageRecorders = 10;
		} else {
			this.pageRecorders = Integer.parseInt(pageRecorders);
			this.pageStartRow=(this.page - 1) * this.pageRecorders;
			this.pageEndRow = this.page * this.pageRecorders;
		}
	}
	
	/**
	 * @param page
	 * @param pageRecorders
	 * @param language
	 */
	public PageModel(String page, String pageRecorders,String language ,String url) {
		if (ObjectUtil.isEmpty(page)){
			this.page = 1;
		} else {
			int temp_page = Integer.parseInt(page);
			if(temp_page>0){
				this.page=temp_page;
			}else{
				this.page=1;
			}
		}
		
		if (ObjectUtil.isEmpty(pageRecorders)){
			this.pageRecorders = 10;
		} else {
			this.pageRecorders = Integer.parseInt(pageRecorders);
			this.pageStartRow=(this.page - 1) * this.pageRecorders;
			this.pageEndRow = this.page * this.pageRecorders;
		}
		
		if(ObjectUtil.isEmpty(language)){
			this.language = "zh";
		}else{
			this.language = language;
		}
		this.url = url;
	}
	
	
	public PageModel(String page, String pageRecorders,String language) {
		if (ObjectUtil.isEmpty(page)){
			this.page = 1;
		} else {
			int temp_page = Integer.parseInt(page);
			if(temp_page>0){
				this.page=temp_page;
			}else{
				this.page=1;
			}
		}
		
		if (ObjectUtil.isEmpty(pageRecorders)){
			this.pageRecorders = 10;
		} else {
			this.pageRecorders = Integer.parseInt(pageRecorders);
			this.pageStartRow=(this.page - 1) * this.pageRecorders;
			this.pageEndRow = this.page * this.pageRecorders;
		}
		
		if(ObjectUtil.isEmpty(language)){
			this.language = "zh";
		}else{
			this.language = language;
		}
	}
	
	

	public List<String> getPageParam() {
		return pageParam;
	}


	public void setPageParam(List<String> pageParam) {
		this.pageParam = pageParam;
	}


	public List<String> getPageParamVal() {
		return pageParamVal;
	}


	public void setPageParamVal(List<String> pageParamList) {
		this.pageParamVal = pageParamList;
		this.pageString=generatePageStr();
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 18, 2016    5:14:48 PM
	 * @param TotalDataCount
	 */
	public void  setTotalDataCount(int totalDataCount ) {
			this.totalRows = totalDataCount;
			if ((totalRows % pageRecorders) == 0) {
				totalPages = totalRows / pageRecorders;
			} else {
				totalPages = totalRows / pageRecorders + 1;
			}
			
			if(page>totalPages){
				page=totalPages;
			}
			
			if (page < totalPages) {
				hasNextPage = true;
			}
			
		this.pageString=displayForPage(); 
	}
	
	
	
	
	public Object getResult() {
		return result;
	}


	public void setResult(Object result) {
		this.result = result;
	}


	public String getPageString() {
		return pageString;
	}


	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	
	

public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}

//	private String listToStr(List<String> list){
//		String s = "";
//		for(int i=0;i<list.size();i++){
//			s +=","+ list.get(i);
//		}
//		return s;
//	}
	
	private String catParam(List<String> key , List<String> val){
		String s = "";
		for(int i=0;i<key.size();i++){
		    if(!ObjectUtil.isEmpty(val.get(i)) && !val.get(i).equals("null"))
			s += "&"+key.get(i)+"="+val.get(i);
		}
		return s;
	}

	
	public String  generatePageStr() {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"<script type=\"text/javascript\">function saxonPage(number"
//				+ listToStr(pageParam)
				+ ") { " 
//			    +"var url = window.location.href; url = url.substring(0, url.lastIndexOf('/'));"
			    + " var jumpUrl ='"
				+ url
				+ "?page_number='+number+'"
				+ "&page_size=" + pageRecorders
				+ catParam(pageParam, pageParamVal)
				+ "'; cadleo_data(jumpUrl); }</script>");

		sb.append("<ul class='pagination pull-left'>");

		// 判断是否有上一页
		if (this.page != 1) {
			sb.append("<li><a href=\"javascript:void(0)\" onclick=\""
					+ "saxonPage('" + String.valueOf(this.getPage() - 1)
					+ "'"
//					+ listToStr(pageParam)
					+ ");return false;\">上一页</a></li>");
		} else {
			sb.append("<li><span class=\"disabled\">上一页</span></li>");
		}

		// 中间显示
		for (int i = 1; i <= this.getTotalPages(); i++) {
			
			String spanClzz = "<li><a href=\"javascript:void(0)\" onclick=\""
					+ "saxonPage('" + String.valueOf(i) + "');return false;\">"
					+ i + "</a></li>";
			
			if (i == 1 && this.page > 5 ) {
				//当前页大于5时需要...	
				sb.append(spanClzz);
				sb.append("<li><a href='javascript:void(0);' class='page'>...</a></li>");

			} else if (i == this.page-1 || i == this.page-2 || i == this.page+1 || i == this.page+2 && this.page > 5){
				//当前页大于5时同时显示当前页的前后各两页
				sb.append(spanClzz);
			} else if (this.page == i) {
				//显示当前页
				spanClzz = "<li><span class='bg-theme border-theme color-white'>" + i + "</span></li>";
				sb.append(spanClzz);
			} else if (i <= 5 && this.page < 6) {
				//当前页在前5页
				sb.append(spanClzz);				
			} else if(i >= 6 && i <= 9){
				sb.append(spanClzz);				
			}

			// 当大于9页数的时候才进行分页显示
			if (this.getTotalPages() - 2 > 7 && this.getTotalPages() - this.page > 2 && i == this.getTotalPages()) {
//				if (i == 5) {
//					i = this.getTotalPages() - 2;
				if(this.getTotalPages() - this.page > 3){
					sb.append("<li><a href='javascript:void(0);' class='page'>...</a></li>");
				}
					sb.append(spanClzz);
//				}
			}
		}
		// 判断是否有下一页
		if (this.isHasNextPage()) {
			sb.append("<li><a href=\"javascript:void(0)\" onclick=\""
					+ "saxonPage('" + String.valueOf(this.getPage() + 1)
					+ "');return false;\">下一页</a></li>");

		} else {
			sb.append("<li><span class=\"disabled\">下一页</span></li>");
		}
		
		sb.append("</ul>");
		sb.append("<div class='pull-left pagination indent'>共 <span class='totalPages'> "+ totalPages + " </span> 页,到第 <input type='text' class='input-page indent-collapse' onkeyup=\"if(!(($.event.fix(event).which>=48 && $.event.fix(event).which<= 57) || ($.event.fix(event).which>=96 && $.event.fix(event).which<= 105))){$(this).val('');}\"> 页 <a class='go btn btn-default indent-collapse'  onclick=\"saxonPage($(this).siblings(\'.input-page\').val()>Number($(\'.totalPages\').text())?1:$(this).siblings(\'.input-page\').val());return false;\"  href='#'>确定</a></div>");

		return sb.toString();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////// below   test for delete 	
	/**
	 * @param 总页数
	 * @param 跳转主目录
	 * @param 查询参数
	 *            (格式:&name=xxxx)
	 */
	public void pageSetting(int listSize, String url, String other) {
		this.url = url;
		init(listSize, pageRecorders);// 通过对象集，记录总数划分
		this.other = other;
	}

	/** */
	/**
	 * 初始化list，并告之该list每页的记录数
	 * 
	 * @param 总数
	 * @param 显示条数
	 */
	public void init(int listSize, int pageRecorders) {
		this.pageRecorders = pageRecorders;
		totalRows = listSize;

		hasPreviousPage = false;

		if ((totalRows % pageRecorders) == 0) {
			totalPages = totalRows / pageRecorders;
		} else {
			totalPages = totalRows / pageRecorders + 1;
		}

		if (page >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		
		this.pageStartRow = (page - 1) * pageRecorders;
		this.pageEndRow = 0;
	}

	public String displayForPage() {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"<script type=\"text/javascript\">function saxonPage(number) { " 
//			    +"var url = window.location.href; url = url.substring(0, url.lastIndexOf('/'));"
			    + " var jumpUrl ='"
				+ url
				+ "?page_number='+number+'"
				+ "&page_size="
				+ pageRecorders
				+ "'; cadleo_data(jumpUrl); }</script>");

		sb.append("<ul class='pagination pull-left'>");

		// 判断是否有上一页
		if (this.page != 1) {
			sb.append("<li><a href=\"javascript:void(0)\" onclick=\""
					+ "saxonPage('" + String.valueOf(this.getPage() - 1)
					+ "');return false;\">上一页</a></li>");
		} else {
			sb.append("<li><span class=\"disabled\">上一页</span></li>");
		}
		
		// 中间显示 action
		for (int i = 1; i <= this.getTotalPages(); i++) {
			
			String spanClzz = "<li><a href=\"javascript:void(0)\" onclick=\""
					+ "saxonPage('" + String.valueOf(i) + "');return false;\">"
					+ i + "</a></li>";
			
			if (i == 1 && this.page > 5 ) {
				//当前页大于5时需要...	
				sb.append(spanClzz);
				sb.append("<li><a href='javascript:void(0);' class='page'>...</a></li>");

			} else if (i == this.page-1 || i == this.page-2 || i == this.page+1 || i == this.page+2 && this.page > 5){
				//当前页大于5时同时显示当前页的前后各两页
				sb.append(spanClzz);
			} else if (this.page == i) {
				//显示当前页
				spanClzz = "<li><span class='bg-theme border-theme color-white'>" + i + "</span></li>";
				sb.append(spanClzz);
			} else if (i <= 5 && this.page < 6) {
				//当前页在前5页
				sb.append(spanClzz);				
			} else if(i >= 6 && i <= 8){
				sb.append(spanClzz);				
			}

			// 当大于9页数的时候才进行分页显示
			if (this.getTotalPages() - 2 > 7 && this.getTotalPages() - this.page > 2 && i == this.getTotalPages()) {
//				if (i == 5) {
//					i = this.getTotalPages() - 2;
				if(this.getTotalPages() - this.page > 3){
					sb.append("<li><a href='javascript:void(0);' class='page'>...</a></li>");
				}
					sb.append(spanClzz);
//				}
			}
		}
		// 判断是否有下一页
		if (this.isHasNextPage()) {
			sb.append("<li><a href=\"javascript:void(0)\" onclick=\""
					+ "saxonPage('" + String.valueOf(this.getPage() + 1)
					+ "');return false;\">下一页</a></li>");

		} else {
			sb.append("<li><span class=\"disabled\">下一页</span></li>");
		}
		
		sb.append("</ul>");
//		sb.append("<div class='pull-left pagination indent'>共 "+ totalPages + " 页,到第 <input type='text' class='input-page indent-collapse'> 页 <a class='go btn btn-default indent-collapse'   onclick=\"saxonPage($(this).siblings(\'.input-page\').val());return false;\"    href='#'>确定</a></div>");
		sb.append("<div class='pull-left pagination indent'>共 <span class='totalPages'> "+ totalPages + " </span> 页,到第 <input type='text' class='input-page indent-collapse' onkeyup=\"if(!(($.event.fix(event).which>=48 && $.event.fix(event).which<= 57) || ($.event.fix(event).which>=96 && $.event.fix(event).which<= 105))){$(this).val('');}\"> 页 <a class='go btn btn-default indent-collapse'  onclick=\"saxonPage($(this).siblings(\'.input-page\').val()>Number($(\'.totalPages\').text())?1:$(this).siblings(\'.input-page\').val());return false;\"  href='#'>确定</a></div>");

		return sb.toString();
	}

	// 判断要不要分页
	public boolean isNext() {
		return totalRows > 5;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * 处理分页
	 */
	private void disposePage() {
		if (page == 0) {
			page = 1;
		}
		if ((page - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}

		if (page >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the pageRecorders
	 */
	public int getPageRecorders() {
		return pageRecorders;
	}

	/**
	 * @param pageRecorders
	 *            the pageRecorders to set
	 */
	public void setPageRecorders(int pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the pageStartRow
	 */
	public int getPageStartRow() {
		return pageStartRow;
	}

	/**
	 * @param pageStartRow
	 *            the pageStartRow to set
	 */
	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	/**
	 * @return the pageEndRow
	 */
	public int getPageEndRow() {
		return pageEndRow;
	}

	/**
	 * @param pageEndRow
	 *            the pageEndRow to set
	 */
	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	/**
	 * @return the hasNextPage
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	/**
	 * @param hasNextPage
	 *            the hasNextPage to set
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * @return the hasPreviousPage
	 */
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	/*
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();

		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("h");
		list.add("i");
		list.add("j");

		System.out.println(list.size());

//		PageModel pm = new PageModel(10, 1, 10, "/competition/index.htmls", "");// 每页显示条数
//		pm.getPageStartRow();
//
//		// pm.getObjects(6);
//
//		System.out.println(pm.displayForPage());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Object getModel_object() {
		return model_object;
	}

	public void setModel_object(Object model_object) {
		this.model_object = model_object;
	}

}
