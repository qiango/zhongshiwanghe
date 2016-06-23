package com.hongzhi.zswh.back.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongzhi.zswh.back.basic.entity.Menu;



public interface MenuDao {
	

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    3:23:20 PM
	 * @param parent_id
	 * @param user_id
	 * @return  List<Menu>   with user role  authorize
	 */
	List<Menu> getChildrens(@Param("parent_id") int parent_id, @Param("user_id") int user_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    3:24:25 PM
	 * @param parent_id
	 * @return  List<Menu>  list all children node 
	 */
	List<Menu> getChildrensAll(int parent_id);

	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 28, 2016    7:15:36 PM
	 * @param menu_name 
	 * @return
	 */
	List<Menu> allMenus(String menu_name);
	
	List<Menu> getUserMenus(@Param("user_id") int user_id);

	
	
	
}
