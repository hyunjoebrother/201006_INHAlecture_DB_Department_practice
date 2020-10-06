package examples.dao;

//DAO (Data Access Object)

//DB 관련 작업을 전담하는 클래스
//DB에 연결하여 CRUD 작업을 하는 클래스

//getJob, addJob, deleteJob, updateJob, getJobs 클래스를 만들어주자 (5개 필수)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Department;

public class DepartmentDAO {
	
	private static String dburl = "jdbc:mysql://localhost/dbHomeWork2?serverTimezone=Asia/Seoul";
	private static String dbUser = "hyunjoe2";
	private static String dbpasswd = "mei831";


	public Department getDepartment(Integer Dnumber) // 1개를 select -> Dnumber = ?
	{
		Department department = null;
	
		// (1) JDBC 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// (2) 질의문 설정
		String sql = "SELECT Dname, Dnumber, Mgr_ssn, Mgr_start_date FROM Department WHERE Dnumber = ?";
	
		// (3) MySQL 연결 및 질의 수행
		// try문에 conn, ps를 여는 코드를 넣으면 finally 블럭에서 close를 안해줘도 된다 (Closable 객체)
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql))
		{
			// (4) 질의문 설정 및 ResultSet 처리 (결과가 여러 개가 있을 수 있음)
			ps.setInt(1, Dnumber);
		
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					String Dname = rs.getString(1);
					int id = rs.getInt(2); /* Dnumber */
					String Mgr_ssn = rs.getString(3);
					String Mgr_start_date = rs.getString(4);
					
					department = new Department(Dname, id, Mgr_ssn, Mgr_start_date);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		// 밑에 finally 블럭을 쓰지 않아도 된다
	
		return department;
	}



	public int addDepartment(Department department) // 4개를 insert -> values (?, ?, ?, ?)
	{
		int insertCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "INSERT INTO department (Dname, Dnumber, Mgr_ssn, Mgr_start_date) VALUES ( ?, ?, ?, ? )";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setNString(1,  department.getDname());
			ps.setInt(2,  department.getDnumber());
			ps.setNString(3,  department.getMgr_ssn());
			ps.setNString(4,  department.getMgr_start_date());
			
			insertCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return insertCount;
	}
			
	public int deleteDepartment(Integer Dnumber) // Dnumber 1개를 delete
	{
		int deleteCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM department WHERE Dnumber = ?";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(2, Dnumber);

			deleteCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return deleteCount;
	}

	
	public int updateDepartment(Department department) // 4개를 update
	{
		int updateCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "update department set Dname = ?, Mgr_ssn = ?, Mgr_start_date = ?, where Dnumber = ?";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setNString(1,  department.getDname());
			ps.setInt(2,  department.getDnumber());
			ps.setNString(3,  department.getMgr_ssn());
			ps.setNString(4,  department.getMgr_start_date());
		
			updateCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return updateCount;
	}

	
	public List<Department> getDepartments() // select한 모든 Department들을 list로 만든다 -> list.add()
	{
		// ArrayList 생성
		List<Department> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "SELECT Dname, Dnumber, Mgr_ssn, Mgr_start_date FROM department order by Dnumber desc";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) 
		{
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					String Dname = rs.getString(1);
					int id = rs.getInt(2);
					String Mgr_ssn = rs.getString(3);
					String Mgr_start_date = rs.getString(4);
					
					Department department = new Department(Dname, id, Mgr_ssn, Mgr_start_date);
					list.add(department); // list에 반복할 때마다 Department 인스턴스를 생성하여 list에 추가한다
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return list;
	}

}
