package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

public class UDao {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/javaproject";
	String uid = "dahye";
	String upw = "konkuk";
	
	public UDao() {
		try {
			Context context = new InitialContext();
			Class.forName(driver);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public ArrayList<User> list(){
		ArrayList<User> dtos = new ArrayList<User>();
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			String query = "select * from user";
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int total = resultSet.getInt("total");
				int win = resultSet.getInt("win");
				int lose = resultSet.getInt("lose");
				
				
				User dto = new User(id,name, total, win, lose);
				
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
	
	public void createUser() {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			
			String query = "CREATE TABLE user(" + 
						" id INT NOT NULL AUTO_INCREMENT," + 
						" name VARCHAR(20) NOT NULL," + 
						" total INT NULL DEFAULT 0," + 
						" win INT NULL DEFAULT 0," + 
						" lose INT NULL DEFAULT 0," + 
						"  PRIMARY KEY (id));";
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
	// 이름이 똑같은게 존재하지 않으면 삽입한다.
	public int insert_record(String uName) {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		int rn=-1;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			String query = "INSERT INTO user (name) " + 
							"SELECT ? FROM DUAL "+
							"WHERE NOT EXISTS (SELECT * FROM user WHERE name=?); ";
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, uName);
			preparedStatement.setString(2, uName);
			// rn에는 새로 저장되면 1(중복아니면) 아니면 0반환(중복되면)
			rn = preparedStatement.executeUpdate();
			System.out.println(rn);
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
		return rn;
	}
	//이름과 타입이 일치하는 사람 불러오기
	public User findUser(String uName) {
			
			User dto = null;
			Connection connection=null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = DriverManager.getConnection(url,uid,upw);
				String query = "select * from user where name = ? ";
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setString(1, uName);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					int total = resultSet.getInt("total");
					int win = resultSet.getInt("win");
					int lose = resultSet.getInt("lose");
					
					
					dto = new User(id,name, total, win, lose);
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
	
	public void updateResult(int uId, String res ) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(url,uid,upw);
			String query;
			if(res.equals("win")) {
				query= "update user set total = total + 1 , win = win+1 where id = ?";
			}else {
				query= "update user set total = total + 1 , lose = lose+1 where id = ?";
			}
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setInt(1, uId);
			
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
	

}
