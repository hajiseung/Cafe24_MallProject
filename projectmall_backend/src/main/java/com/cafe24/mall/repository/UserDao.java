package com.cafe24.mall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.vo.NonUserVo;
import com.cafe24.mall.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public UserVo checkId(UserVo userVo) {
		return sqlSession.selectOne("user.userIdCheck", userVo);
	}

	// 회원 정보 수정
	public int modifyUser(UserVo userVo) {
		return sqlSession.update("user.modifyUser", userVo);
	}

	// 회원 가입
	public UserVo joinUser(UserVo userVo) {
		sqlSession.insert("user.insert", userVo);
		return userVo;
	}

	// 유저 로그인
	public UserVo loginUser(UserVo userVo) {
		UserVo pwUservo = sqlSession.selectOne("user.pwforlogin", userVo);
		return sqlSession.selectOne("user.loginUser", userVo);
	}

	// 비회원 주소 추가
	public NonUserVo insertNonMember(NonUserVo nonUserVo) {
		sqlSession.insert("user.nonMemberInsert", nonUserVo);
		return nonUserVo;
	}

	// 비회원 PK 리턴
	public NonUserVo getNonMemberNo(NonUserVo nonUserVo) {
		return sqlSession.selectOne("user.getNonMemberNo", nonUserVo);
	}

	// 회원 삭제
	public int deleteUser(UserVo userVo) {
		return sqlSession.delete("user.delete", userVo);
	}

	public UserVo getUser(UserVo uservo) {
		// id 들어있음
		UserVo result = sqlSession.selectOne("user.getuser", uservo);
		UserVo pwUservo = sqlSession.selectOne("user.pwforlogin", uservo);
		if (!pwUservo.getPw().equals(result.getPw())) {
			return null;
		}
		return result;
	}

	public UserVo getUserOne(UserVo uservo) {
		UserVo userno = sqlSession.selectOne("user.getuserone",uservo);
		return userno;
	}

	public UserVo getUserOneFromNo(UserVo uservo) {
		UserVo userno = sqlSession.selectOne("user.getuseronefromno",uservo);
		return userno;
	}
}
