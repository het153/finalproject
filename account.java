package com.midterm;
import java.sql.Date;

public class account {
	private int ACCOUNT_ID;
	private float AVAIL_BALANCE;
	private Date CLOSE_DATE;
	private Date LAST_ACTIVITY_DATE;
	private Date OPEN_DATE;
	private float PENDING_BALANCE;
	private int CUST_ID;
	private String STATUS;
	private int OPEN_BRANCH_ID;
	private int OPEN_EMP_ID;
	private String PRODUCT_CD;

	public int getACCOUNT_ID() {
		return ACCOUNT_ID;
	}
	public void setACCOUNT_ID(int ACCOUNT_ID) {
		this.ACCOUNT_ID = ACCOUNT_ID;
	}
	public float getAVAIL_BALANCE() {
		return AVAIL_BALANCE;
	}
	public void setAVAIL_BALANCE(float AVAIL_BALANCE) {
		this.AVAIL_BALANCE = AVAIL_BALANCE;
	}
	public Date getCLOSE_DATE() {
		return CLOSE_DATE;
	}
	public void setCLOSE_DATE(Date CLOSE_DATE) {
		this.CLOSE_DATE = CLOSE_DATE;
	}
	public Date getLAST_ACTIVITY_DATE() {
		return LAST_ACTIVITY_DATE;
	}
	public void setLAST_ACTIVITY_DATE(Date LAST_ACTIVITY_DATE) {
		this.LAST_ACTIVITY_DATE = LAST_ACTIVITY_DATE;
	}
	public Date getOPEN_DATE() {
		return OPEN_DATE;
	}
	public void setOPEN_DATE(Date OPEN_DATE) {
		this.OPEN_DATE = OPEN_DATE;
	}
	public float getPENDING_BALANCE() {
		return PENDING_BALANCE;
	}
	public void setPENDING_BALANCE(float PENDING_BALANCE) {
		this.PENDING_BALANCE= PENDING_BALANCE;
	}
	public int getOPEN_EMP_ID() {
		return OPEN_EMP_ID;
	}
	public void setOPEN_EMP_ID(int OPEN_EMP_ID) {
		this.OPEN_EMP_ID = OPEN_EMP_ID;
	}
	public String getPRODUCT_CD() {
		return PRODUCT_CD;
	}
	public void setPRODUCT_CD(String PRODUCT_CD) {
		this.PRODUCT_CD = PRODUCT_CD;
	}
	public int OPEN_BRANCH_ID() {
		return OPEN_BRANCH_ID;
	}
	public void setOPEN_BRANCH_ID(int OPEN_BRANCH_ID) {
		this.OPEN_BRANCH_ID = OPEN_BRANCH_ID;
	}
	public int getCUST_ID() {
		return CUST_ID;
	}
	public void setCUST_ID(int CUST_ID) {
		this.CUST_ID = CUST_ID;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS( String STATUS) {
		this.STATUS = STATUS;
	}
	@Override
	public String toString() {
		return "account [ ACCOUNT_ID=" + ACCOUNT_ID + ", AVAIL_BALANCE=" + AVAIL_BALANCE + ", CLOSE_DATE=" + CLOSE_DATE
				+ ", LAST_ACTIVITY_DATE=" + LAST_ACTIVITY_DATE + ", OPEN_DATE=" + OPEN_DATE + ", PENDING_BALANCE=" + PENDING_BALANCE + ", CUST_ID="
				+ CUST_ID + ", PRODUCT_CD=" + PRODUCT_CD +",OPEN_BRANCH_ID="+OPEN_BRANCH_ID + ", OPEN_EMP_ID=" + OPEN_EMP_ID  + ", STATUS=" + STATUS +"]";
	}
}
