package test.controller;

public class ActionForward {
	//이동경로
	private String path;
	//redirect 이동여부
	private boolean isRedirect;//디폴트로 false가 들어간다.
	
	//생성자1
	public ActionForward(String path){
		this.path=path;
	}
	
	public ActionForward(String path, boolean isRedirect){
		this.path=path;
		this.isRedirect=isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
}
