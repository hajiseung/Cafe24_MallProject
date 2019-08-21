package com.example.project_frontend.frontend.security;

import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.project_frontend.frontend.service.AdminService;
import com.example.project_frontend.frontend.service.UserService;
import com.example.project_frontend.frontend.vo.AdminVo;
import com.example.project_frontend.frontend.vo.UserVo;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private AdminService service;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		AdminVo adminVo = null;
		UserVo userVo = null;
		if ("admin".equals(id)) {
			try {
				adminVo = service.login(id);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			try {
				userVo = userService.getUser(id);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

		SecurityUser securityUser = new SecurityUser();

		if (userVo != null) {
			securityUser.setNo(userVo.getNo());
			securityUser.setName(userVo.getName());
			securityUser.setUsername(userVo.getId());
			securityUser.setPassword(userVo.getPw());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userVo.getRole())));
		} else {
			String role = "ROLE_ADMIN";
			securityUser.setUsername(adminVo.getId());
			securityUser.setPassword(adminVo.getPw());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(role)));
		}

		return securityUser;
	}
}
