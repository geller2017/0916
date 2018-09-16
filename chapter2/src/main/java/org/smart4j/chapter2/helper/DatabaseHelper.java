package org.smart4j.chapter2.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.config.PropsUtil;

public final class DatabaseHelper {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
	
	private static final QueryRunner QUERY_RUNNER;
	
	private static final BasicDataSource DATA_SOURCE ;
	
	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();
	
	static{
		Properties props = PropsUtil.loadProps("config.properties");
		String driver = props.getProperty("jdbc.driver");
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		QUERY_RUNNER = new QueryRunner();
		DATA_SOURCE = new BasicDataSource();
		
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}
	
	//获取数据库连接
	public static Connection getConnection(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn==null){
			try {
				conn = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				logger.error("获取连接失败!");
				logger.error(ExceptionUtils.getStackTrace(e));
			}finally{
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}
	
	public static void closeConnection(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("数据库连接关闭失败！");
			}finally{
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object...params){
		List<T> entityList;
		try {
			Connection conn  = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			logger.error("查询实体失败");
			throw new RuntimeException();
		}finally {
			closeConnection();
		}
		return entityList;
	}
	
	/***
	 * 查询，返回List<Map<String,Object>>
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String,Object>> excuteQuery(String sql,Object...params){
		List<Map<String,Object>> entityList;
		try {
			Connection conn  = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			logger.error("查询实体失败");
			throw new RuntimeException();
		}finally {
			closeConnection();
		}
		return entityList;
	}
	
	//更新记录
	public static int executeUpdate(String sql,Object...params){
		Connection conn = getConnection();
		int res = 0;
		try {
			res = QUERY_RUNNER.update(conn, sql);
		} catch (SQLException e) {
			logger.error("更新数据失败");
			logger.error(e.getMessage());
		}finally{
			closeConnection();
		}
		return res;
	}
	
	//插入实体
	public static <T> boolean insertIntity(Class<?> entityClass,Map<String,Object> fieldMap){
		//先判断字段map是不是空的，如果是空的，就不做插入。
		if(MapUtils.isEmpty(fieldMap)){
			logger.info("传入的字段map为空");
			return false;
		}
		String sql = "insert into " + entityClass.getSimpleName();
		//对传入字段做遍历，拼接sql
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for(String fieldName:fieldMap.keySet()){
			columns.append(fieldName).append(", ");
			values.append("?").append(", ");
		}
		columns.replace(columns.lastIndexOf(", "),columns.length(),")");
		values.replace(columns.lastIndexOf(", "),columns.length(),")");
		sql += columns + " values " + values;
		int counts = executeUpdate(sql,fieldMap.values().toArray());
		return counts == 1;
	}
	
	public static <T> boolean updateEntity(Class<?> entityClass,Map<String,Object> fieldMap,int id){
		//先判断字段参数是不是为空，如果为空，就不做更新操作
		if(MapUtils.isEmpty(fieldMap)){
			logger.info("参入的字段参数为空，不更新数据");
			return false;
		}
		//通过传入的参数，拼接sql
		String sql = "UPDATE "+entityClass.getSimpleName()+" SET ";
		StringBuilder fields = new StringBuilder();
		for(String fieldName:fieldMap.keySet()){
			fields.append(fieldName+"=?, ");
		}
		String updateFileds = fields.subSequence(0, fields.lastIndexOf(",")).toString();
		sql += updateFileds+" WHERE ID=?";
		List<Object> params = new ArrayList<Object>();
		params.addAll(fieldMap.values());
		params.add(id);
		return executeUpdate(sql,params.toArray())>0;
	}
	
	//根据id删除数据
	public static <T> boolean deleteEntity(Class<T> entityClass,int id){
		String sql = "DELETE FROM "+ entityClass.getSimpleName()+" WHERE ID=?";
		return executeUpdate(sql,id)>0;
	}
	
	//通过sql文件执行dml语句
	public static void executeSqlFile(String filePath){
		InputStream is = null;
		BufferedReader br = null;
		String sql = null;
		
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
			br = new BufferedReader(new InputStreamReader(is));
			while((sql = br.readLine())!=null){
				executeUpdate(sql, new HashMap<String,Object>());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	
}
