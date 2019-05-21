package com.hxzy.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import oracle.jdbc.OracleTypes;

public class TestOracle {

	@Test
	public void test1() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "jjhk", "gshk");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from dept");
		while (rs.next()) {
			System.out.println(rs.getInt("DEPTNO"));
			System.out.println(rs.getString("DENAME"));
		}
	}

	@Test
	public void test2() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "jjhk", "gshk");
		String sql = "{?= call fun_test(?)}";
		CallableStatement state = conn.prepareCall(sql);
		// 注册返回类型的参数
		state.registerOutParameter(1, OracleTypes.NUMBER);
		state.setInt(2, 1);
		state.execute();

		int total = state.getInt(1);
		System.out.println(total);
		state.close();
		conn.close();
	}

	@Test
	public void test3() throws Exception {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "jjhk", "gshk");
			String sql = "{ call dingshi()}";
			CallableStatement state = conn.prepareCall(sql);

			state.execute();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}