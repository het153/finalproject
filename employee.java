package com.midterm;


import java.sql.Date;

public class employee {
	private int EMP_ID;
	private Date END_DATE;
	private String  FIRST_NAME;
	private String  LAST_NAME;
	private Date START_DATE;
	private char TITLE;
	private int ASSIGNED_BRANCH_ID;
	private int DEPT_ID;
	private int SUPERIOR_EMP_ID;
	
	public int getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(int eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public Date getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(Date eND_DATE) {
		END_DATE = eND_DATE;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public Date getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(Date sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public char getTITLE() {
		return TITLE;
	}
	public void setTITLE(char tITLE) {
		TITLE = tITLE;
	}
	public int getASSIGNED_BRANCH_ID() {
		return ASSIGNED_BRANCH_ID;
	}
	public void setASSIGNED_BRANCH_ID(int aSSIGNED_BRANCH_ID) {
		ASSIGNED_BRANCH_ID = aSSIGNED_BRANCH_ID;
	}
	public int getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(int dEPT_ID) {
		DEPT_ID = dEPT_ID;
	}
	public int getSUPERIOR_EMP_ID() {
		return SUPERIOR_EMP_ID;
	}
	public void setSUPERIOR_EMP_ID(int sUPERIOR_EMP_ID) {
		SUPERIOR_EMP_ID = sUPERIOR_EMP_ID;
	}
	@Override
	public String toString()
	{
		return "account [ EMP_ID=" + EMP_ID + ", END_DATE=" + END_DATE + ", FIRST_NAME=" + FIRST_NAME
				+ ", LAST_NAME=" + LAST_NAME + ", START_DATE=" + START_DATE + ", TITLE=" + TITLE + ", ASSIGNED_BRANCH_ID="
				+ ASSIGNED_BRANCH_ID + ", DEPT_ID=" + DEPT_ID +",SUPERIOR_EMP_ID="+SUPERIOR_EMP_ID +"]";
	}
}
