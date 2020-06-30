package org.eclipse.basyx.testsuite.regression.aas.restapi;

import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelUrn;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.aas.restapi.AASModelProvider;
import org.eclipse.basyx.aas.restapi.VABMultiSubmodelProvider;
import org.eclipse.basyx.submodel.restapi.SubModelProvider;
import org.eclipse.basyx.testsuite.regression.submodel.restapi.SimpleAASSubmodel;
import org.eclipse.basyx.vab.protocol.http.server.VABHTTPInterface;

public class StubAASServlet extends VABHTTPInterface<VABMultiSubmodelProvider> {
	private static final long serialVersionUID = 8859337501045845823L;

	// Used short ids
	public static final String AASIDSHORT = "StubAAS";
	public static final String SMIDSHORT = "StubSM";

	// Used URNs
	public static final ModelUrn AASURN = new ModelUrn("urn:fhg:es.iese:aas:1:1:myAAS#001");
	public static final ModelUrn SMURN = new ModelUrn("urn:fhg:es.iese:aas:1:1:mySM#001");

	public StubAASServlet() {
		super(new VABMultiSubmodelProvider());

		SubmodelDescriptor smDesc = new SubmodelDescriptor(SMIDSHORT, SMURN, "");
		AssetAdministrationShell aas = new AssetAdministrationShell();
		aas.addSubModel(smDesc);
		aas.setIdShort(AASIDSHORT);
		aas.setIdentification(AASURN);

		getModelProvider().setAssetAdministrationShell(new AASModelProvider(aas));
		getModelProvider().addSubmodel(SMIDSHORT, new SubModelProvider(new SimpleAASSubmodel(SMIDSHORT)));
	}

}
