package com.example.project_frontend.frontend.repository;

import org.springframework.stereotype.Repository;

import com.example.project_frontend.frontend.vo.UserVo2;


@Repository
public class UserDao {

//	@Autowired
//	private SqlSession sqlSession;
	
	public UserVo2 get(String email) {
		return null;
	}
	
	public UserVo2 get(Long no){
		return null;
	}
	
	public UserVo2 get(String email, String password) {
		return null;
	}	
	
	public Boolean insert(UserVo2 vo) {
		return false;
	}
	
	public int update( UserVo2 userVo ) {
		return 0;
	}	
}