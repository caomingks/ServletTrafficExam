package com.cheer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.cheer.dao.ExerciseDao;
import com.cheer.domian.Exercise;
import com.cheer.util.DbHelper;

public class ExerciseDaoImpl implements ExerciseDao
{
	
	private Connection conn = null;
	
	public ExerciseDaoImpl()
	{
		
	}	
	public ExerciseDaoImpl(Connection conn)
	{
		this.conn = conn;
	}


	//将解析的试题保存到数据库
	public void save(Exercise exe)
	{
		PreparedStatement stat = null;
		
		try
		{
			String sql="insert into exercise values(?,?,?,?,?,?,?)";
			stat=conn.prepareStatement(sql);
			stat.setInt(1, exe.getNo());
			stat.setString(2, exe.getTitle());			
			stat.setString(3, exe.getSelectA());
			stat.setString(4, exe.getSelectB());
			stat.setString(5, exe.getSelectC());
			stat.setString(6, exe.getSelectD());
			stat.setString(7, exe.getAnswer());
			stat.addBatch();
			stat.executeBatch();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbHelper.getInstance().closeQuietly(stat, null);
		}
	}
	
	//apache DBUtils 通过id查找一行记录，即拿到一个对象
	public Exercise find(int id)
	{
		ResultSetHandler<Exercise> handler = new BeanHandler<Exercise>(Exercise.class);
		QueryRunner runner = new QueryRunner();

		Exercise ex = null;
		try
		{
			ex = runner.query(conn, "select * from exercise where id = ?", handler, id);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if (null == ex)
		{
			System.out.println("没有查询到记录！");
		}
		
		return ex;
		
	}
}
