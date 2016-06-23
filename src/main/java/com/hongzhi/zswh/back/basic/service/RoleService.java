package com.hongzhi.zswh.back.basic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongzhi.zswh.back.basic.dao.RoleDao;
import com.hongzhi.zswh.back.basic.entity.Menu;
import com.hongzhi.zswh.back.basic.entity.RoleInfo;
import com.hongzhi.zswh.back.basic.entity.RoleMenu;
import com.hongzhi.zswh.util.basic.DictionaryUtil;
import com.hongzhi.zswh.util.basic.sessionDao.SessionProperty;
import com.hongzhi.zswh.util.exception.HongZhiException;

/**
 * Twitter : @taylorwang789 Creat time : Mar 24, 2016 2:09:15 PM
 */
@Repository
public class RoleService {

	@Autowired
	private DictionaryUtil dicUtil;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuService menuService;

	/**
	 * Twitter : @taylorwang789 Creat time : Mar 24, 2016 2:13:08 PM
	 * 
	 * @param language
	 */
	public String list(String language, String role_name) {
		List<RoleInfo> roleList = roleDao.allRolesByRoleName(role_name);
		Map<String, Object> out = new HashMap<>();
		out.put("role_list", roleList);
		return dicUtil.appOut("0", language, out);
	}

	/**
	 * Twitter : @taylorwang789 Creat time : Mar 24, 2016 2:22:19 PM
	 * 
	 * @param params
	 * @return
	 */
	public String newRoleSave(Map<String, String> params) {
		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setRole_name(params.get("role_name"));
		roleInfo.setRemark("remark");
		int name_count = roleDao.countByRoleName(params.get("role_name"));
		try {
			if(name_count==0){
				dicUtil.verifyData(name_count == 0, "1030");
				int insert_count = roleDao.saveNewRole(roleInfo);
				if (insert_count == 1) {
					return dicUtil.appOut(params.get("code"), params.get("language"), "true");
				} else {
					return dicUtil.appOut(params.get("code"), params.get("language"), "false");
				}
			}else{
					return dicUtil.appOut("1030", params.get("language"), "false");
			}
		} catch (HongZhiException e) {
			return dicUtil.appOut(e.getCode(), params.get("language"), "false");
		}

	}

	/**
	 * Twitter : @taylorwang789 Creat time : Mar 24, 2016 2:29:04 PM
	 * 
	 * @param params
	 *            role_id language
	 * @return
	 */
	public String modifyLoad(Map<String, String> params) {
		RoleInfo role = roleDao.selectById(Integer.parseInt(params.get("role_id")));
		Map<String, Object> out = new HashMap<>();
		out.put("role", role);
		return dicUtil.appOut(params.get("code"), params.get("language"), out);
	}

	/**
	 * Twitter : @taylorwang789 Creat time : Mar 24, 2016 2:38:17 PM
	 * 
	 * @param params
	 * @return
	 */
	public String modifyRoleSave(Map<String, String> params) {
		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setRole_id(Integer.parseInt(params.get("role_id")));
		roleInfo.setRole_name(params.get("role_name"));
		roleInfo.setRemark(params.get("remark"));
		int update_count = roleDao.updateRole(roleInfo);
		if (update_count == 1) {
			return dicUtil.appOut(params.get("code"), params.get("language"), "true");
		} else {
			return dicUtil.appOut("1033", params.get("language"), "false");
		}
		/*
		 * int name_count=roleDao.countByRoleName(params.get("role_name"));try {
		 * dicUtil.verifyData(name_count==0, "1030"); int
		 * update_count=roleDao.updateRole(roleInfo); if(update_count==1){
		 * return dicUtil.appOut( params.get("code"), params.get("language") ,
		 * "true"); }else{ return dicUtil.appOut( "1033", params.get("language")
		 * , "false"); } } catch (HongZhiException e) { return dicUtil.appOut(
		 * e.getCode() , params.get("language") , "false"); }
		 */
	}

	/**
	 * Twitter : @taylorwang789 Creat time : Mar 24, 2016 2:41:48 PM
	 * 
	 * @param params
	 * @return
	 */
	public String delete(Map<String, String> params) {
		int delete_count = roleDao.logicDelete(Integer.parseInt(params.get("role_id")));
		if (delete_count == 1) {
			return dicUtil.appOut(params.get("code"), params.get("language"), "true");
		} else {
			return dicUtil.appOut("1033", params.get("language"), "false");
		}

	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    10:49:53 AM
	 * @param sp
	 * @param role_id
	 * @return
	 */
	public String roleMenu(SessionProperty sp, String role_id) {
		Menu menu = new Menu();
		menu.setMenu_id(27);
		menu = menuService.menuTreeAll(menu);
		List<Integer > menu_ids = roleDao.roleMenuIds(Integer.parseInt(role_id));
		Map<String,Object> out = new HashMap<>();
		out.put("menu_ids", menu_ids);
		out.put("menu_tree", menu.getChildren());
		out.put("role_id", role_id);
		return dicUtil.appOut("0", out);
	}

	/**   Twitter : @taylorwang789 
	 * Creat time : Apr 7, 2016    11:12:00 AM
	 * @param sp
	 * @param role_id
	 * @param menu_ids
	 * @return
	 */
	@Transactional
	public String roleMenuSave(SessionProperty sp, String role_id, String menu_ids) {
		int roleid = Integer.parseInt(role_id);
		
		String[] menus = menu_ids.split(",");
		List<RoleMenu> role_menu_list = new ArrayList<>();
		for (int i=0;i <menus.length;i++){
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRole_id(roleid);
			roleMenu.setMenu_id(Integer.parseInt(menus[i]));
			role_menu_list.add(roleMenu);
		}
		
		roleDao.deleteRoleMenu(roleid);
		int insert_count = roleDao.insertRoleMenu(role_menu_list);
		if(insert_count==menus.length){
			return dicUtil.appOut("0","ok");
		}else{
			return dicUtil.appOut("0","fail");
		}
	}

}
