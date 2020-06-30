package org.eclipse.basyx.testsuite.regression.vab.protocol.http;

import org.eclipse.basyx.testsuite.regression.vab.modelprovider.SimpleVABElement;
import org.eclipse.basyx.vab.modelprovider.map.VABMapProvider;
import org.eclipse.basyx.vab.protocol.http.server.VABHTTPInterface;

/**
 * Servlet interface for AAS
 * 
 * @author kuhn
 *
 */
public class SimpleVABElementServlet extends VABHTTPInterface<VABMapProvider> {

	/**
	 * Version information to identify the version of serialized instances
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public SimpleVABElementServlet() {
		// Invoke base constructor, instantiate a VABElement
		super(new VABMapProvider(new SimpleVABElement()));
	}
}
