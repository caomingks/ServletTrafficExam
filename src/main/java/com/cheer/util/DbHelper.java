package com.cheer.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbHelper
{
	private static final DbHelper INSTANCE=new DbHelper();
	
	private DbHelper(){}
	
	public static DbHelper getInstance()
	{
		return INSTANCE;
	}
	
	//读取数据库配置文件db.properties
	public Properties getDbConfig()
	{
		InputStream is=null;
		
		Properties pros=new Properties();
		
		try
		{
			// 读取当前程序classpath目录下的conf/db.properties，而此时的classpath为.
			is=DbHelper.class.getClassLoader().getResourceAsStream("db.properties");
			pros.load(is);
			System.out.println("成功读取配置文件！");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("找不到配置文件！");
			return null;
		}
		catch(IOException e)
		{
			System.out.println("读取失败");
			return null;
		}
		finally
		{
			if(null!=is)
			{
				try
				{
					is.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}	
			}
			
		}
		
		return pros;
	}
	
	//获取连接
	public Connection getConnection()
	{
		Properties pros=this.getDbConfig();
		
		if(null==pros)
		{
			return null;
		}
		
		Connection conn = null;
		String url = pros.getProperty("url");
		String userName = pros.getProperty("username");
		String password = pros.getProperty("password");
		
		try
		{
			System.out.println("开始连接数据库");
			//加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//获取连接
			conn=DriverManager.getConnection(url,userName,password);
			System.out.println("连接成功");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("没有找到驱动类：oracle.jdbc.driver.OracleDriver！");
			return null;
		}
		catch(SQLException e)
		{
			System.out.println("连接失败！");
			return null;
		}
		return conn;
	}
	
	// 关闭数据库连接Connection
	public void closeConnection(Connection conn)
	{
		if (null != conn)
		{
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				System.out.println("断开数据库连接！！");
			}			
		}	
	}

	// 关闭所有资源
	public void closeQuietly(Statement stat, ResultSet rs)
	{
		closeStatement(stat);
		closeResultSet(rs);
	}

	// 关闭Statement
	public void closeStatement(Statement stat)
	{
		if (null != stat)
		{
			try
			{
				stat.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}				
		}	
	}	

	// 关闭结果集ResultSet
	public void closeResultSet(ResultSet rs)
	{
		if (null != rs)
		{
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}				
		}	
	}	
	
	
}
