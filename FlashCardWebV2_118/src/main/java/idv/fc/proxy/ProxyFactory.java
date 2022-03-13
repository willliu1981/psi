package idv.fc.proxy;

import net.sf.cglib.proxy.Enhancer;

public class ProxyFactory {

	private ProxyFactory instance;
	private Object target;

	private ProxyFactory getInstance() {
		if (this.instance == null) {
			instance = new ProxyFactory();
		}

		return this.instance;
	}

	public Object getTarget() {
		return getInstance().target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public static ProxyFactory createNewFactoryInstance(Object target) {
		ProxyFactory instance = new ProxyFactory();
		instance.setTarget(target);
		return instance;
	}

	public Object getProxyInstance(BaseInterceptor instance) {
		Enhancer enhancer = new Enhancer();
		if(instance.getTarget()==null) {
			instance.setTarget(this.getTarget());
		}
		enhancer.setSuperclass(instance.getTarget().getClass());
		enhancer.setCallback(instance);

		return enhancer.create();
	}

}
