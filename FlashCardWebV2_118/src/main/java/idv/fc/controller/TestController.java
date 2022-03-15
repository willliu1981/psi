package idv.fc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import idv.fc.dao.abstraction.Dao;
import idv.fc.interceptorhandler.UserInterceptorHandler;
import idv.fc.model.User;
import idv.fc.model.UserFaker;
import idv.fc.model.Vocabulary;
import idv.fc.proxy.ProxyFactory;
import idv.fc.proxy.interceptor.InterceptorImpl;
import idv.fc.tool.Debug;
import idv.fc.tool.SpringUtil;

@Controller
@RequestMapping(value = "test")
public class TestController {

	@RequestMapping(value = "test")
	public String query(@ModelAttribute("user") User user, HttpSession session) {
		Debug.test(this, "test...");

		User proxy = ProxyFactory.getProxyInstance(ProxyFactory.USERPROXYFACTORY, user,
				session);

		Debug.test(this, "before :" + proxy.getAuthority());
		proxy.setAuthority("common");
		Debug.test(this, "after :" + proxy.getAuthority());

		return "test/test";
	}

	@RequestMapping(value = "test2")
	public String querySQL(User user, HttpSession session, RedirectAttributes rdAttr) {
		Debug.test(this, "test2...");

		session.setAttribute("auth", "admin");
		rdAttr.addAttribute("authority", user.getAuthority());
		return "redirect:/test/test";
	}

	@RequestMapping(value = "testsession")
	public String testSession(User user, HttpSession session) {

		return "test/test";
	}

	@RequestMapping(value = "add")
	public String add() {
		Dao<Vocabulary> dao = SpringUtil.getBean("VocabularyDao", Dao.class);
		Vocabulary v = new Vocabulary();
		v.setId("v_find");
		v.setVocabulary("find");
		v.setTranslation("查找");
		dao.create(v);
		return "test/test";
	}

	@RequestMapping(value = "update")
	public String update() {
		Dao<Vocabulary> dao = SpringUtil.getBean("VocabularyDao", Dao.class);
		Vocabulary v = new Vocabulary();
		v.setId("v_such");
		v.setVocabulary("such");
		v.setTranslation("這種");
		dao.update(v, "xxx");
		return "test/test";
	}
}
