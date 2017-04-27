package com.zq.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zq.pojo.Menu;
import com.zq.pojo.User;
import com.zq.service.MenuService;
import com.zq.service.RoleOrgRelService;
import com.zq.service.RoleUserRelService;
import com.zq.service.UserService;
import com.zq.utils.SecurityMd5;

@Controller
public class LoginController {

	@Resource(name = "userService")
	private UserService userService;
	@Resource
	private MenuService menuService;
	@Resource
	private RoleUserRelService roleUserRelService;
	@Resource
	private RoleOrgRelService roleOrgRelService;

	/**
	 * 访问跳转
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		return "login";
	}

	/**
	 * 检查登录,核对登陆者身份
	 * 
	 * @return
	 */
	@RequestMapping("doLogin")
	public String checkLogin(HttpServletRequest request) {
		System.out.println("doLogin>>>>>>>>>>");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		password = SecurityMd5.md5(password);
		System.out.println("username:" + name + "password:" + password);
		User record = new User();
		record.setUserName(name);
		record.setUserPassword(password);
		User user = userService.selectByUser(record);
		System.out.println(record.toString() + ">>>>>>>>>>>>>>>>>");
		if (user != null) {
			Long userId = user.getUserId();
			Long orgId = user.getOrgId();
			// 查询到角色集合
			String roles1 = roleUserRelService.findRolesByUserId(userId);
			// 根据组织id查询的角色id
			String roles2 = roleOrgRelService.findRolesByOrgId(orgId);
			// 得到总的角色编号的集和
			System.out.println(roles1 + "---" + roles2);
			Map<String, String[]> map = new HashMap<String, String[]>();
			StringBuffer sBuffer = new StringBuffer();
			if (roles1 != null && !"".equals(roles1) && roles2 != null && !"".equals(roles2)) {
				sBuffer.append(roles1);//
				sBuffer.append(",");
				sBuffer.append(roles2);
			} else if (roles1 != null && !"".equals(roles1)) {
				sBuffer.append(roles1);
			} else if (roles2 != null && !"".equals(roles2)) {
				sBuffer.append(roles2);// 1
			}
			// 3,1,9 { 3 , 1, 9}
			String idString[] = null;
			if (sBuffer.toString().contains(",")) {
				idString = sBuffer.toString().split(",");
			} else {
				idString = new String[] { sBuffer.toString() }; // {1}
			}
			System.out.println("idString" + Arrays.toString(idString));
			map.put("roles", idString);// 作为查询权限菜单条件

			// 根据角色id 查询 权限菜单
			/**
			 * sys_menu sys_role_menu_rel SELECT * FROM SYS_MENU
			 * M,SYS_ROLE_MENU_REL R where R.MENU_ID = M.MENU_ID AND IS_PUBLISH
			 * =1 and ROLE_ID in (1,9,11)
			 * 
			 */
			List<Menu> list = menuService.findAuthorizationMenuByRoleId(map);
			System.out.println("list" + list);

			request.setAttribute("menuList", list);
			return "main";
		} else {
			return "login.do";
		}

	}
}
