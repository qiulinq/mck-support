package com.xiangjia.common.util;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * mybaits配置文件保存检查方法
 * @author  x1ao_ 
 *
 */
public class SessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean{

	
	@Override
	protected SqlSessionFactory buildSqlSessionFactory() {
		// TODO Auto-generated method stub
		 SqlSessionFactory buildSqlSessionFactory = null;
		try {
			buildSqlSessionFactory  = super.buildSqlSessionFactory();
		} catch (Exception e) {
			System.err.println("mybaits配置文件出错：" + e.getMessage());
//			e.printStackTrace();
		}
		return buildSqlSessionFactory;
	}
	
		
}
