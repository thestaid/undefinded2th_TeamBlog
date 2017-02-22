package test.archive.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;

public class ArchiveUploadformAction extends Action {
		
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		return new ActionForward("/views/archive/uploadform.jsp");
	}

}
