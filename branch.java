package com.midterm;

public class branch {

	private int BRANCH_ID;
	private String ADDRESS;
	private String CITY;
	private String NAME;
	private String STATE;
	private int ZIP_CODE;
	
	public int getBRANCH_ID() {
		return BRANCH_ID;
	}
	public void setBRANCH_ID(int BRANCH_ID) {
		this.BRANCH_ID = BRANCH_ID;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String CITY) {
		this.CITY = CITY;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String STATE) {
		this.STATE = STATE;
	}
	
	public int getZIP_CODE() {
		return ZIP_CODE;
	}
	public void setZIP_CODE(int ZIP_CODE) {
		this.ZIP_CODE = ZIP_CODE;
	}
	
	
	@Override
	public String toString() {
		return "branch [BRANCH_ID=" + BRANCH_ID + ", ADDRESS=" + ADDRESS + ", CITY=" + CITY
				+ ", NAME=" + NAME + ", STATE=" + STATE + ", ZIP_CODE=" + ZIP_CODE + "]";
	}

}
