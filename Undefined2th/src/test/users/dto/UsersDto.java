package test.users.dto;

public class UsersDto {
	private String id;
	private String pwd;
	private String email;
	private String regdate;
	
	private int startRowNum; //시작 row 번호
	private int endRowNum;   //끝 row 번호
	private int prevNum;     //이전 글의 글번호
	private int nextNum;     //다음 글의 글번호 
	
	public UsersDto(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getPrevNum() {
		return prevNum;
	}

	public void setPrevNum(int prevNum) {
		this.prevNum = prevNum;
	}

	public int getNextNum() {
		return nextNum;
	}

	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}

	public UsersDto(String id, String pwd, String email, String regdate, int startRowNum, int endRowNum, int prevNum,
			int nextNum) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.regdate = regdate;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.prevNum = prevNum;
		this.nextNum = nextNum;
	}

	
}
