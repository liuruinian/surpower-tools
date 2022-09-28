package io.github.liuruinian.xxl.core.handler;

/**
 * job handler
 *
 * @author xuxueli 2015-12-19 19:06:38
 */
public abstract class IJobHandler {


	/**
	 * execute handler, invoked when executor receives a scheduling request
	 *
	 * @throws Exception java.lang.Exception
	 */
	public abstract void execute() throws Exception;


	/*@Deprecated
	public abstract ReturnT<String> execute(String param) throws Exception;*/

	/**
	 * init handler, invoked when JobThread init
	 * @throws Exception java.lang.Exception
	 */
	public void init() throws Exception {
		// do something
	}


	/**
	 * destroy handler, invoked when JobThread destroy
	 * @throws Exception java.lang.Exception
	 */
	public void destroy() throws Exception {
		// do something
	}


}
