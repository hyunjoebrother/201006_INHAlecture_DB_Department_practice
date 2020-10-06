package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.DepartmentDAO;
import examples.dto.Department;

import java.util.List;


/**
 * Servlet implementation class DepartmentTest1
 */
@WebServlet("/DepartmentTest1")
public class DepartmentTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentTest1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DAO add doGet() 호출!");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// Department table을 담당하는 DAO를 하나 새로 만들어 준다 -> DAO를 통해서 data를 가져온다
		DepartmentDAO dao = new DepartmentDAO();
		
		// (1) getDepartment 테스트
		Department department = null;
		department = dao.getDepartment(4); //B.L
		
		if (department == null)
			out.println("<h1> My Department is null </h1>");
		else {
			out.println("<h1> My Name is " + department.getDname() + "</h1>"); 
			out.println("<h1> My number is " + department.getDnumber() + "</h1>");
			out.println("<h1> My mgr_ssn is " + department.getMgr_ssn() + "</h1>");
			out.println("<h1> My mgr_start_date is " + department.getMgr_start_date() + "</h1>");
			}
		
		
		// (2) addDepartment 테스트
		Department department1 = new Department("Headquarters", 1, "888665555", "2020-09-25");
		
		int addCount = dao.addDepartment(department1);
		out.println("<h1> insert : " + addCount + "row(s) </h1>");
		
		
		// (3) deleteDepartment 테스트
		int delCount = dao.deleteDepartment(1);
		out.println("<h1> delete : " + delCount + "row(s) </h1>");
		
		
		// (4) updateDepartment 테스트
		Department department2 = new Department("GraduateStudent", 26, "123456789", "2020-09-24");
				
		int updateCount = dao.updateDepartment(department2);
		out.println("<h1> update : " + updateCount + "row(s) </h1>");
		
		
		// (5) getDepartments 테스트
		List<Department> departmentList = dao.getDepartments();
		
		for(Department department3 : departmentList)
		{
			out.println("<h1> My Name is " + department3.getDname() + "</h1>"); 
			out.println("<h1> My number is " + department3.getDnumber() + "</h1>");
			out.println("<h1> My mgr_ssn is " + department3.getMgr_ssn() + "</h1>");
			out.println("<h1> My mgr_start_date is " + department3.getMgr_start_date() + "</h1>");
		}
		out.close();
	}
}
