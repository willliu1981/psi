package idv.fc.concretion.proxy;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import idv.fc.dao.abstraction.Dao;
import idv.fc.exception.FindErrorException;
import idv.fc.model.User;
import idv.fc.proxy.ProxyFactory;
import idv.fc.proxy.interceptor.Shuttle;
import idv.fc.tool.Debug;
import idv.fc.tool.SpringUtil;

public class UserFaker {

	private User user;
	private Shuttle shuttle = new Shuttle();

	Dao<User> dao;

	private Map<String, String> sessionValues = new HashMap<>();

	public UserFaker() {
		init();
	}

	private void init() {
		User user = new User();
		dao = (Dao<User>) SpringUtil.getBean("UserDao");

		try {
			this.user = ProxyFactory.getProxyInstance("UserProxyFactory", user,
					this.shuttle);
		} catch (FindErrorException e) {
			e.printStackTrace();
		}

	}

	public String getToken() {
		return (String) this.shuttle.get("token");
	}

	public UserFaker setToken(String token) {
		Debug.test(this, "user faker ", token);
		this.shuttle.put("token", token);
		return this;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dao<User> getDao() {
		return dao;
	}

	public void setDao(Dao<User> dao) {
		this.dao = dao;
	}

	public String getId() {
		return user.getId();
	}

	public UserFaker setId(String id) {
		user.setId(id);
		return this;
	}

	public Date getCreate_date() {
		return user.getCreate_date();
	}

	public UserFaker setCreate_date(Date create_date) {
		user.setCreate_date(create_date);
		return this;
	}

	public Integer getAge() {
		return user.getAge();
	}

	public UserFaker setAge(Integer age) {
		user.setAge(age);
		return this;
	}

	public String getUsername() {
		return user.getUsername();
	}

	public UserFaker setUsername(String username) {
		user.setUsername(username);
		return this;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public UserFaker setPassword(String password) {
		user.setPassword(password);
		return this;
	}

	public String getDisplay_name() {
		return user.getDisplay_name();
	}

	public UserFaker setDisplay_name(String dsiplay_name) {
		user.setDisplay_name(dsiplay_name);
		return this;
	}

	public Integer getGender() {
		return user.getGender();
	}

	public UserFaker setGender(Integer gender) {
		user.setGender(gender);
		return this;
	}

	public String getAuth() {
		return user.getAuth();
	}

	public UserFaker setAuth(String auth) {
		user.setAuth(auth);
		return this;
	}

	public String getTag() {
		return user.getTag();
	}

	public UserFaker setTag(String age) {
		user.setTag(age);
		return this;
	}

	public void create() {
		dao.create(this.user);
		this.queryByUsernameAndPassword();
	}

	public void update(Object id) {
		dao.update(this.user, id);
	}

	public void delete() {
		dao.delete(this.user.getId());
	}

	public User queryById() throws FindErrorException {
		return dao.queryById(this.user.getId());
	}

	public boolean queryByUsernameAndPassword() {
		List<User> list = dao.querySQL(
				"select * from user where username=? and password=? ",
				user.getUsername(), user.getPassword());
		boolean isEmpty = list.isEmpty();
		if (isEmpty) {
			return false;
		} else {
			this.user = list.get(0);
			return true;
		}
	}

	public List<User> queryAll() {
		return dao.queryAll();
	}

}
