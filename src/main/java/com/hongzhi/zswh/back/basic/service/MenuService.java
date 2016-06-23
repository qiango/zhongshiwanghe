package com.hongzhi.zswh.back.basic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongzhi.zswh.back.basic.dao.MenuDao;
import com.hongzhi.zswh.back.basic.entity.Menu;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.ObjectUtil;



/**   Twitter : @taylorwang789 
 * Creat time : Mar 22, 2016    11:02:50 AM
 */
@Repository
public class MenuService {
	

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private DictionaryUtil dictionaryUtil;

	

	private Menu tree(Menu root,  List<Menu> menus,  Map<Integer,List<Integer>> pid_map ){
		List<Menu> children =  new ArrayList<>();
		List<Integer> child_index = pid_map.get(root.getMenu_id());
		if(null != child_index ) {
			for(int i=0;i< child_index.size() ;i++){
				children.add(  menus.get( child_index.get(i)   )  );
			}
		  	root.setChildren(children);
		  	for(int i=0;i<children.size();i++){
		  		children.set(i, tree(children.get(i),menus,pid_map) );
		  	}
		}
		return root;
	}
	
	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 22, 2016    11:50:52 AM
	 * @param menu  ,  root node 
	 * @return menu tree with user role authorize  
	 */
	public Menu menuTree(Menu menu,int user_id){
		List<Menu> menus = menuDao.getUserMenus(user_id);
		Map<Integer,List<Integer>> pid_map = new HashMap<>();
		//  p_id     index in menus
		for(int i=0;i<menus.size();i++){
			if(ObjectUtil.isEmpty( pid_map.get( Integer.parseInt(menus.get(i).getMenu_parent_id())) )){
				List<Integer>  indexs = new ArrayList<>();
				indexs.add(i);
				pid_map.put(Integer.parseInt(menus.get(i).getMenu_parent_id()) , indexs );
			}else{
				List<Integer> indexa =  pid_map.get( Integer.parseInt(menus.get(i).getMenu_parent_id())   );
				indexa.add(i);
				pid_map.put( Integer.parseInt(menus.get(i).getMenu_parent_id()) , indexa );
			}
		}

		menu = tree(menu, menus,pid_map);
		return menu;
	}
	
	
	public Menu menuTreeAll(Menu menu){
		List<Menu> menus = menuDao.allMenus(null);
		Map<Integer,List<Integer>> pid_map = new HashMap<>();
		//  p_id     index in menus
		for(int i=0;i<menus.size();i++){
			if(ObjectUtil.isEmpty( pid_map.get( Integer.parseInt(menus.get(i).getMenu_parent_id())) )){
				List<Integer>  indexs = new ArrayList<>();
				indexs.add(i);
				pid_map.put(Integer.parseInt(menus.get(i).getMenu_parent_id()) , indexs );
			}else{
				List<Integer> indexa =  pid_map.get( Integer.parseInt(menus.get(i).getMenu_parent_id())   );
				indexa.add(i);
				pid_map.put( Integer.parseInt(menus.get(i).getMenu_parent_id()) , indexa );
			}
		}

		menu = tree(menu, menus,pid_map);
		return menu;
	}


	/**   Twitter : @taylorwang789 
	 * Creat time : Mar 28, 2016    7:10:01 PM
	 * @param menu_name 
	 * @return
	 */
	public String allMenus(String menu_name) {
		List<Menu> allMenus = menuDao.allMenus(menu_name);
		return dictionaryUtil.appOut("0","" , allMenus);
	}

}
