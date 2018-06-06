package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import model.Horse;

public class DAO {
	
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/javaproject";
	String uid = "root";
	String upw = "juns4025!";
	
	public DAO() {
		try {
			Context context = new InitialContext();
			Class.forName(driver);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 테이블 생성
	public void createHorse() {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			
			String query = "CREATE TABLE horse( id INT NOT NULL AUTO_INCREMENT,"+
					"name VARCHAR(20) NOT NULL,"+
					" type VARCHAR(20) NOT NULL,"+ 
					" hp INT NOT NULL," + 
					" atk INT NOT NULL," + 
					" def INT NOT NULL," + 
					" hrange INT NOT NULL," + 
					" spdX INT NOT NULL," + 
					" spdY INT NOT NULL," + 
					" PRIMARY KEY (id));";
			preparedStatement = connection.prepareStatement(query);
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement!=null)preparedStatement.close();
				if(connection!=null)connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	// 컬럼 생성해주기!
	public void insert_record() {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			String query = "insert into horse(name, type, hp, atk, def, hrange, spdX, spdY)" + 
							"values ('폰', '인간형', 150, 10, 0, 1, 1, 1),"+ 
							"('폰', '기계형', 160, 15, 3, 1, 1, 1),"+
							"('룩', '인간형', 100, 30, 2, 2, 3, 3),"+ 
							"('룩', '기계형', 110, 35, 5, 2, 3, 3),"+
							"('킹', '인간형', 250, 5, 0, 1, 1, 1);";
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			int rn = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement!=null)preparedStatement.close();
				if(connection!=null)connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	// 전체 리스트 받아오기
	public ArrayList<Horse> list(){
		ArrayList<Horse> dtos = new ArrayList<Horse>();
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			String query = "select * from horse";
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String type = resultSet.getString("type");
				int hp = resultSet.getInt("hp");
				int atk = resultSet.getInt("atk");
				int def = resultSet.getInt("def");
				int hrange = resultSet.getInt("hrange");
				int spdX = resultSet.getInt("spdX");
				int spdY = resultSet.getInt("spdY");
				
				Horse dto = new Horse(id,name,type, hp,atk,def,hrange,spdX,spdY);
				dtos.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(resultSet!=null)resultSet.close();
				if(preparedStatement!=null)preparedStatement.close();
				if(connection!=null)connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	//이름과 타입이 일치하는 말 불러오기
	public Horse findHorse(String sname, String stype) {
		
		Horse dto = null;
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			String query = "select * from horse where name = ? and type =? ";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, sname);
			preparedStatement.setString(2, stype);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String type = resultSet.getString("type");
				int hp = resultSet.getInt("hp");
				int atk = resultSet.getInt("atk");
				int def = resultSet.getInt("def");
				int hrange = resultSet.getInt("hrange");
				int spdX = resultSet.getInt("spdX");
				int spdY = resultSet.getInt("spdY");
				
				
				dto = new Horse(id,name,type, hp,atk,def,hrange,spdX,spdY);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(resultSet!=null)resultSet.close();
				if(preparedStatement!=null)preparedStatement.close();
				if(connection!=null)connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	
}
