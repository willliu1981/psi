package com.flashcard.control.account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.flashcard.dao.IDao;
import com.flashcard.dao.user.IUserdataDao;
import com.flashcard.factory.BeanFactory;
import com.flashcard.factory.dao.DaoFactoryType;
import com.flashcard.model.user.User;
import com.flashcard.model.user.Userdata;
import com.flashcard.security.authority.AuthorityFactory;
import com.flashcard.security.authorization.AdminAuthorization;

public class MemberController extends Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IUserdataDao<Userdata> dao = (IUserdataDao<Userdata>) BeanFactory
				.getBean(DaoFactoryType.USERDATADAO);

		User user = null;

		user = (User) request.getSession(false)
				.getAttribute(name("sessionUser"));
		Userdata data = null;

		try {
			data = dao.queryByID(user.getUserdata_id());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName(name("target"));

		boolean isAdmin = AuthorityFactory.hasKey(user.getAuthority(), "admin",
				AdminAuthorization.READ_MEMBER_USER);
		if (isAdmin) {
			IDao<?> userDao = BeanFactory.getBean(DaoFactoryType.USERDAO);
			List<?> users = userDao.queryAll();
			mv.addObject(name("varUsers"), users);

		} else {
		}

		return mv;
	}

}
