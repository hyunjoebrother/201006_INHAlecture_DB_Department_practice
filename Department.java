package examples.dto;

public class Department {
	
	private String Dname;
	private Integer Dnumber;
	private String Mgr_ssn;
	private String Mgr_start_date;
	
	/* Constructor */
	public Department(String dname, Integer dnumber, String mgr_ssn, String mgr_start_date) {
		super();
		Dname = dname;
		Dnumber = dnumber;
		Mgr_ssn = mgr_ssn;
		Mgr_start_date = mgr_start_date;
	}

	/* Getter & Setter */
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	public Integer getDnumber() {
		return Dnumber;
	}
	public void setDnumber(Integer dnumber) {
		Dnumber = dnumber;
	}
	public String getMgr_ssn() {
		return Mgr_ssn;
	}
	public void setMgr_ssn(String mgr_ssn) {
		Mgr_ssn = mgr_ssn;
	}
	public String getMgr_start_date() {
		return Mgr_start_date;
	}
	public void setMgr_start_date(String mgr_start_date) {
		Mgr_start_date = mgr_start_date;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "Department [Dname=" + Dname + ", Dnumber=" + Dnumber + ", Mgr_ssn=" + Mgr_ssn + ", Mgr_start_date="
				+ Mgr_start_date + "]";
	}

}
