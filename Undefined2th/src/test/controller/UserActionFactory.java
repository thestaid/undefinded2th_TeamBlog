package test.controller;

import test.action.HomeAction;
import test.admin.action.AdminListAction;
import test.archive.action.ArchiveDeleteAction;
import test.archive.action.ArchiveDownloadAction;
import test.archive.action.ArchiveListAction;
import test.archive.action.ArchiveUploadAction;
import test.archive.action.ArchiveUploadformAction;
import test.board.action.BoardCommentDelete;
import test.board.action.BoardCommentInsertAction;
import test.board.action.BoardCommentUpdate;
import test.board.action.BoardCommentUpdateform;
import test.board.action.BoardDeleteAction;
import test.board.action.BoardDetailAction;
import test.board.action.BoardInsertAction;
import test.board.action.BoardInsertformAction;
import test.board.action.BoardListAction;
import test.board.action.BoardUpdateAction;
import test.board.action.BoardUpdateformAction;
import test.users.action.CheckIdAction;
import test.users.action.SigninAction;
import test.users.action.SignoutAction;
import test.users.action.SignupAction;
import test.users.action.UsersDeleteAction;
import test.users.action.UsersInfoAction;
import test.users.action.UsersUpdateAction;
import test.users.action.UsersUpdateformAction;
import test.visitor.action.VisitorsAction;
import test.visitor.action.VisitorsDeleteAction;
import test.visitor.action.VisitorsInsertAction;
import test.visitor.action.VisitorsUpdateAction;
import test.visitor.action.VisitorsUpdateformAction;

//Action 객체를 만들어서 return하는 공장
public class UserActionFactory {
	private static UserActionFactory factory;
	private UserActionFactory(){}
	//자신의 참조값을 리턴해주는 메소드
	public static UserActionFactory getInstance(){
		if(factory==null){
			factory=new UserActionFactory();
		}
		return factory;
	}//getInstance END
	
	/*
	    *  인자로 전달되는 command를 수행할 Action Type 객체를 생성해서 리턴해주는 메소드.
	    *  액션 서블릿에서 아래 메소드를 호출하면서 command를 인자로 던저둔다.
	    *  각각의 command에 해당하는 객체를 생성하여 반환한다.
	    *  액션 객체는 action 클래스를 상속받아서 만든다.
	*/
	//인자로 전달되는 command를 수행할 Action type 객체를 생성해서 리턴해주는 메소드
	//모든 요청은 아래의 메소드에서 분류한다.
	public Action action(String command){
		Action action=null;
		if(command.equals("/home")){//home
			action=new HomeAction();
		}		if(command.equals("/home")){//home
			action=new HomeAction();
		}else if(command.equals("/users/signin")){
			//로그인 요청 처리
			action=new SigninAction();
		}else if(command.equals("/users/signout")){
			//로그아웃 요청 처리
			action=new SignoutAction();
		}else if(command.equals("/users/checkid")){
			//아이디 중복확인 Ajax 요청 처리
			action=new CheckIdAction();
		}else if(command.equals("/users/signup")){
			//회원 가입 요청 처리 
			action=new SignupAction();
		}else if(command.equals("/users/private/info")){
			//가입된 회원정보 자세히 보기 요청 처리
			action=new UsersInfoAction();
		}else if(command.equals("/users/private/delete")){
			//회원 탈퇴 요청 처리
			action=new UsersDeleteAction();
		}else if(command.equals("/users/private/updateform")){
			//회원정보 수정 폼 요청처리
			action=new UsersUpdateformAction();
		}else if(command.equals("/users/private/update")){
			//회원정보 수정 요청 처리
			action=new UsersUpdateAction();
		}else if(command.equals("/admin/list")){
			action = new AdminListAction();
		}else if(command.equals("/board/list")){
			// 게시물 목록 요청 처리
			action = new BoardListAction();
		}else if(command.equals("/board/private/insertform")){
			// 새 글 작성 폼 요청 처리
			action = new BoardInsertformAction();
		}else if(command.equals("/board/private/insert")){
			// 새 글 작성 요청 처리
			action = new BoardInsertAction();
		}else if(command.equals("/board/detail")){
			// 세부내용 요청 처리
			action = new BoardDetailAction();
		}else if(command.equals("/board/private/updateform")){
			// 글 수정 폼 요청 처리
			action = new BoardUpdateformAction();
		}else if(command.equals("/board/private/update")){
			// 글 수정 요청 처리
			action = new BoardUpdateAction();
		}else if(command.equals("/board/private/delete")){
			// 글 삭제 요청 처리
			action = new BoardDeleteAction();
		}else if(command.equals("/board/commentInsert")){
			action=new BoardCommentInsertAction();
		}else if(command.equals("/board/private/commentUpdateform")){
			action=new BoardCommentUpdateform();
		}else if(command.equals("/board/private/commentUpdate")){
			action=new BoardCommentUpdate();
		}else if(command.equals("/board/private/commentDelete")){
			action=new BoardCommentDelete();
		}else if(command.equals("/visitor/visitors")){
			action=new VisitorsAction();
		}else if(command.equals("/visitor/insert")){
			action=new VisitorsInsertAction();
		}else if(command.equals("/visitor/update")){
			action=new VisitorsUpdateAction();
		}else if(command.equals("/visitor/updateform")){
			action=new VisitorsUpdateformAction();
		}else if(command.equals("/visitor/delete")){
			action=new VisitorsDeleteAction();
		}else if(command.equals("/archive/list")){
			// 아카이브 리스트 요청 처리
			action=new ArchiveListAction();
		}else if(command.equals("/archive/uploadform")){
			// 아카이브 업로드 폼 요청 처리
			action=new ArchiveUploadformAction();
		}else if(command.equals("/archive/upload")){
			// 아카이브 업로드 요청 처리
			action=new ArchiveUploadAction();
		}else if(command.equals("/archive/delete")){
			// 아카이브 삭제 요청 처리
			action=new ArchiveDeleteAction();
		}else if(command.equals("/archive/download")){
			// 아카이브 다운로드 요청 처리
			action=new ArchiveDownloadAction();
		}
		return action;
	}
}
