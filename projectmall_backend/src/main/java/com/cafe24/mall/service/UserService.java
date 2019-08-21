package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.UserDao;
import com.cafe24.mall.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 회원 가입
	public UserVo joinUser(UserVo userVo) {
		userDao.joinUser(userVo);
		return userVo;
	}

	// 회원 로그인
	public UserVo loginUser(UserVo userVo) {
		return userDao.loginUser(userVo);
	}

	// 회원 아이디 중복 체크 및 유효성 검사
	public boolean checkUserId(UserVo userVo) {
		UserVo result = userDao.checkId(userVo);
		if (result != null && userVo.getId().equals(result.getId())) {
			return false;
		}
		return true;
	}

	// 회원 정보 수정
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}

	// 회원 탈퇴
	public int secessionUser(UserVo userVo) {
		return userDao.deleteUser(userVo);
	}

	public UserVo getUser(UserVo uservo) {
		return userDao.getUser(uservo);
	}

	public UserVo getUserOne(UserVo uservo) {
		return userDao.getUserOne(uservo);
	}

	public UserVo getUserOneFromNo(UserVo uservo) {
		return userDao.getUserOneFromNo(uservo);
	}

}
