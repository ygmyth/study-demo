package com.myth;

import com.myth.entity.SysUser;
import com.myth.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		try (SqlSession sqlSession = getSqlSession()) {
			SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
			List<String> users = userMapper.selectAll();
			System.out.println(users.get(0));
		}
		//assertEquals(2,3,"hel");
	}

}