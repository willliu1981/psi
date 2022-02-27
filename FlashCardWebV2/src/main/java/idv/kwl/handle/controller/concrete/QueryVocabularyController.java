package idv.kwl.handle.controller.concrete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import idv.kwl.handle.controller.DataProcessController;
import idv.kwl.model.proxy.VocabularyProxy;

public class QueryVocabularyController extends DataProcessController<VocabularyProxy> {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String qID = request.getParameter("vid");

		if (qID == null || qID.equals("")) {
			this.getModelAndView().addObject("list", this.getDao().queryAll());
			this.setViewName("testlist");
		}

		return super.handleRequest(request, response);
	}

}
