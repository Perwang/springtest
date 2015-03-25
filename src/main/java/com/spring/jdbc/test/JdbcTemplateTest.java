package com.spring.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 
 * @author wangcanpei
 * 
 */
public class JdbcTemplateTest {

	private static JdbcTemplate jdbcTemplate;

	@BeforeClass
	public static void setUpClass() {
		String url = "jdbc:hsqldb:mem:test";
		String username = "sa";
		String password = "";
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url,
				username, password);
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void test() {
		// 1.声明SQL
		String sql = "select * from INFORMATION_SCHEMA.SYSTEM_TABLES";
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				// 2.处理结果集
				String value = rs.getString("TABLE_NAME");
				System.out.println("Column TABLENAME:" + value);
			}
		});
	}

	@Test
	public void testCURD() {
		insert();
		delete();
		update();
		select();
	}

	/**
	 * 测试jdbcTemplate插入
	 */
	private void insert() {
		jdbcTemplate.update("insert into test(name) values('name1')");
		jdbcTemplate.update("insert into test(name) values('name2')");
		Assert.assertEquals(2,
				jdbcTemplate.queryForInt("select count(*) from test"));
	}

	/**
	 * 测试jdbcTemplate删除
	 */
	private void delete() {
		jdbcTemplate.update("delete from test where name=?",
				new Object[] { "name2" });
		Assert.assertEquals(1,
				jdbcTemplate.queryForInt("select count(*) from test"));
	}

	/**
	 * 测试jdbcTemplate修改
	 */
	private void update() {
		jdbcTemplate.update("update test set name='name3' where name=?",
				new Object[] { "name1" });
		Assert.assertEquals(1, jdbcTemplate
				.queryForInt("select count(*) from test where name='name3'"));
	}
	
	private void select() {
		jdbcTemplate.query("select * from test", new RowCallbackHandler(){
		public void processRow(ResultSet rs) throws SQLException {
		      System.out.print("====id:" + rs.getInt("id"));
		      System.out.println(",name:" + rs.getString("name"));
		    }
		  });
		}

	/**
	 * 预编译语句及存储过程创建回调、自定义功能回调使用：
	 */
	@Test
	public void testPpreparedStatement1() {
		insert();
	int count = jdbcTemplate.execute(new PreparedStatementCreator() {
	     public PreparedStatement createPreparedStatement(Connection conn)
	         throws SQLException {
	         return conn.prepareStatement("select count(*) from test where name=?");
	     }}, new PreparedStatementCallback<Integer>() {
	    	 
	     public Integer doInPreparedStatement(PreparedStatement pstmt)
	         throws SQLException, DataAccessException {
	    	 pstmt.setString(1, "name1");
	         pstmt.execute();
	       
	         ResultSet rs = pstmt.getResultSet();
	         rs.next();
	         return rs.getInt(1);
	      }});     
	
	   Assert.assertEquals(1, count);
	}

	
	/**
	 * 预编译语句设值回调使用
	 */
	@Test
	public void testPreparedStatement2() {
	String insertSql = "insert into test(name) values (?)";
	  int count = jdbcTemplate.update(insertSql, new PreparedStatementSetter() {
	      public void setValues(PreparedStatement pstmt) throws SQLException {
	          pstmt.setObject(1, "name4");
	  }});
	  Assert.assertEquals(1, count);     
	  String deleteSql = "delete from test where name=?";
	  count = jdbcTemplate.update(deleteSql, new Object[] {"name4"});
	  Assert.assertEquals(1, count);
	}

	/**
	 * ResultSet 不需要在循环了
	 */
	@Test
	public void testResultSet1() {
		  jdbcTemplate.update("insert into test(name) values('name4')");
		  jdbcTemplate.update("insert into test(name) values('name5')");
	String listSql = "select * from test";
	  List result = jdbcTemplate.query(listSql, new RowMapper<Map>() {
	      public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	  System.out.println(rowNum);
	          Map row = new HashMap();
	          row.put(rs.getInt("id"), rs.getString("name"));
	          return row;
	  }});
	  Assert.assertEquals(1, result.size());
	  jdbcTemplate.update("delete from test where name='name5'");      
	}
	
	/**
	 * RowCallbackHandler里的rows
	 */
	@Test
	public void testResultSet2() {
		  jdbcTemplate.update("insert into test(name) values('name4')");
		  jdbcTemplate.update("insert into test(name) values('name5')");
	String listSql = "select * from test";
	  final List result = new ArrayList();
	jdbcTemplate.query(listSql, new RowCallbackHandler() {
	      
	      public void processRow(ResultSet rs) throws SQLException {
	          Map row = new HashMap();
	          row.put(rs.getInt("id"), rs.getString("name"));
	          result.add(row);
	  }});
	  Assert.assertEquals(1, result.size());
	}

	/**
	 *ResultSetExtractor 这个需要循环里面的resultSet
	 */
	@Test
	public void testResultSet3() {
	  jdbcTemplate.update("insert into test(name) values('name4')");
	  jdbcTemplate.update("insert into test(name) values('name5')");
	String listSql = "select * from test";
	  List result = jdbcTemplate.query(listSql, new ResultSetExtractor<List>() {
	     
	      public List extractData(ResultSet rs)
	     throws SQLException, DataAccessException {
	          List result = new ArrayList();
	          while(rs.next()) {
	              Map row = new HashMap();
	              row.put(rs.getInt("id"), rs.getString("name"));
	              result.add(row);
	           }
	           return result;
	  }});
	Assert.assertEquals(0, result.size());
	jdbcTemplate.update("delete from test where name='name5'");
	}

	
	
	/**
	 * 支持了
	 */
	@Test
	public void testNamedParameterJdbcTemplate1() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
		//namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	    String insertSql = "insert into test(name) values(:name)";
	    String selectSql = "select * from test where name=:name";
	    String deleteSql = "delete from test where name=:name";
	    
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("name", "name5");
	    namedParameterJdbcTemplate.update(insertSql, paramMap);
	    
	    final List<Integer> result = new ArrayList<Integer>();
	    
	    namedParameterJdbcTemplate.query(selectSql, paramMap,new RowCallbackHandler() {
	    		public void processRow(ResultSet rs) throws SQLException {
	    			result.add(rs.getInt("id"));
	    		}
	    	});
	    Assert.assertEquals(1, result.size());
	}
	
	
	
	@Test
	public void testBatchUpdate2() {
	    String insertSql = "insert into test(name) values(?)";
	    final String[] batchValues = new String[] {"name5", "name6"};
	    jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	            ps.setString(1, batchValues[i]);//传参数
	        }
	        public int getBatchSize() {
	            return batchValues.length;
	        }
	    });
	    Assert.assertEquals(2, jdbcTemplate.queryForInt("select count(*) from test"));
	}

	
	
	@Before
	public void setUp() {
		String createTableSql = "create memory table test"
				+ "(id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, "
				+ "name varchar(100))";
		jdbcTemplate.update(createTableSql);
	}

	@After
	public void tearDown() {
		String dropTableSql = "drop table test";
		jdbcTemplate.execute(dropTableSql);
	}
}
