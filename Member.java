package com.hansin.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member")
public class Member extends HttpServlet{
	
	public Member() {
		
	}
	int id;
	String pwd;
	String name;
	String tel;
	String email;
	String dept;
	String gender;
	String birth;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	
	Connection con = null;
	public static void main(String[] args) {
	
		Connection con = null;
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";

		String jdbc_url = "jdbc:mysql://127.0.0.1:3306/addressbook?serverTimezone=UTC";
		 
			try {
				Class.forName(jdbc_driver);
				con = DriverManager.getConnection(jdbc_url, "root", "123456");

				Statement st = con.createStatement();
				
				StringBuilder sb = new StringBuilder();
	            String sql = sb.append("create table if not exists member(")
	                    .append("id int,")
	                    .append("name varchar(45),")
	                    .append("pwd varchar(45),")
	                    .append("tel varchar(45),")
	                    .append("email varchar(45),")
	                    .append("dept varchar(45),")
	                    .append("gender varchar(45),")
	                    .append("birth varchar(45),")
	                    .append(");").toString();
	  
				st.execute(sql);
			} 
			catch(ClassNotFoundException e){
	            e.printStackTrace();
	        }
	        catch(SQLException e){
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                if(con != null && !con.isClosed())
	                    con.close();
	            } catch(SQLException e){
	                e.printStackTrace();
	            }
	        }
	}
			
	private void view() {
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";

		String jdbc_url = "jdbc:mysql://127.0.0.1:3306/memberdb?serverTimezone=UTC";

		try {

			Class.forName(jdbc_driver).newInstance();

			Connection con = DriverManager.getConnection(jdbc_url, "root", "123456");

			Statement st = con.createStatement();

			String sql = "SELECT * FROM jdbcmember.memberdb";

			ResultSet rs = st.executeQuery(sql);
			

			while(rs.next()) {

				int id = rs.getInt("id");

				String name = rs.getString("name");

				String pwd = rs.getString("pwd");
				
				String tel = rs.getString("tel");

				String email = rs.getString("email");
				
				String dept = rs.getString("dept");

				String gender = rs.getString("gender");
				
				String birth = rs.getString("birth");

				System.out.printf("id:  %d, name: %s, pwd: %s, tel: %s, email: %s, dept: %s, gender: %s, birth: %s"

						+ "\n", id, name, pwd, tel, email, dept, gender, birth);

			}
			rs.close();

			st.close();

			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		} 
		
	}	
	
	public void insert(Member m) {
        String sql = "insert into member values(?,?,?,?,?,?,?,?);";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, m.getId()); 

			pst.setString(2, m.getName());
			
			pst.setString(3, m.getPwd());
			
			pst.setString(4, m.getTel()); 

			pst.setString(5, m.getEmail());
				
			pst.setString(6, m.getDept());
			
			pst.setString(7, m.getGender());
			
			pst.setString(8, m.getBirth());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null && !pst.isClosed())
                    pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		
	}

}
