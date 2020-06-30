package org.eclipse.basyx.testsuite.regression.aas.registration.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.eclipse.basyx.aas.metamodel.map.descriptor.AASDescriptor;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelUrn;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.aas.registration.api.IAASRegistryService;
import org.eclipse.basyx.aas.registration.proxy.AASRegistryProxy;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration test for a registry. All registry provider implementations have to pass these tests.
 * 
 * @author espen
 * 
 */
public abstract class TestRegistryProvider {
	// The registry proxy that is used to access the sql servlet
	protected final IAASRegistryService proxy = new AASRegistryProxy(getProxyProvider());

	// Ids, shortIds and endpoints for registered AAS and submodel
	protected IIdentifier aasId1 = new ModelUrn("urn:de.FHG:devices.es.iese:aas:1.0:1:registryAAS#001");
	protected IIdentifier aasId2 = new ModelUrn("urn:de.FHG:devices.es.iese:aas:1.0:1:registryAAS#002");
	protected IIdentifier smId1 = new ModelUrn("urn:de.FHG:devices.es.iese:aas:1.0:1:statusSM#001");
	protected IIdentifier smId2 = new ModelUrn("urn:de.FHG:devices.es.iese:aas:1.0:1:testSM#001");
	protected String aasIdShort1 = "aasIdShort1";
	protected String aasIdShort2 = "aasIdShort2";
	protected String smIdShort1 = "smIdShort1";
	protected String smIdShort2 = "smIdShort2";
	protected String aasEndpoint1 = "http://www.registrytest.de/aas01/aas";
	protected String aasEndpoint2 = "http://www.registrytest.de/aas02/aas";
	protected String smEndpoint1 = "http://www.registrytest.de/aas01/aas/submodels/" + smIdShort1;
	protected String smEndpoint2 = "http://www.registrytest.de/aas01/aas/submodels/" + smIdShort2;
	
	/**
	 * Getter for the tested registry provider. Tests for actual registry provider
	 * have to realize this method.
	 */
	protected abstract IModelProvider getProxyProvider();

	/**
	 * Before each test, clean entries are created in the registry using a proxy
	 */
	@Before
	public void setUp() {
		// Create descriptors for AAS and submodels
		AASDescriptor aasDesc1 = new AASDescriptor(aasIdShort1, aasId1, aasEndpoint1);
		aasDesc1.addSubmodelDescriptor(new SubmodelDescriptor(smIdShort1, smId1, smEndpoint1));
		AASDescriptor aasDesc2 = new AASDescriptor(aasIdShort2, aasId2, aasEndpoint2);
		
		// Register Asset Administration Shells
		proxy.register(aasDesc1);
		proxy.register(aasDesc2);
	}
	
	/**
	 * Remove registry entries after each test
	 */
	@After
	public void tearDown() {
		proxy.delete(aasId1);
		proxy.delete(aasId2);
	}

	/**
	 * Tests getting single entries from the registry and validates the result.
	 */
	@Test
	public void testGetSingleAAS() {
		// Retrieve and check the first AAS
		AASDescriptor descriptor = proxy.lookupAAS(aasId1);
		validateDescriptor1(descriptor);
	}

	/**
	 * Tests getting all entries from the registry and validates the result.
	 */
	@Test
	public void testGetMultiAAS() {
		// Get all registered AAS
		List<AASDescriptor> result = proxy.lookupAll();
		// Check, if both AAS are registered. Ordering does not matter
		assertEquals(2, result.size());
		if (result.get(0).getIdShort().equals(aasIdShort1)) {
			validateDescriptor1(result.get(0));
			validateDescriptor2(result.get(1));
		} else {
			validateDescriptor2(result.get(0));
			validateDescriptor1(result.get(1));
		}
	}

	/**
	 * Checks, if the given descriptor is valid. Should contain the values of the first descriptor
	 * as given by the test setup
	 */
	private void validateDescriptor1(AASDescriptor descriptor) {
		assertEquals(aasId1.getId(), descriptor.getIdentifier().getId());
		assertEquals(aasId1.getIdType(), descriptor.getIdentifier().getIdType());
		assertEquals(aasId1.getIdType(), descriptor.getIdentifier().getIdType());
		assertEquals(aasEndpoint1, descriptor.getFirstEndpoint());

		// Check, if the SM descriptor in the AASDescriptor is correct
		SubmodelDescriptor smDescriptor = descriptor.getSubModelDescriptorFromIdentifierId(smId1.getId());
		assertEquals(smId1.getId(), smDescriptor.getIdentifier().getId());
		assertEquals(smId1.getIdType(), smDescriptor.getIdentifier().getIdType());
		assertEquals(smIdShort1, smDescriptor.get(Referable.IDSHORT));
		assertEquals(smEndpoint1, smDescriptor.getFirstEndpoint());
	}

	/**
	 * Checks, if the given descriptor is valid. Should contain the values of the second descriptor
	 * as given by the test setup
	 */
	private void validateDescriptor2(AASDescriptor descriptor) {
		assertEquals(aasId2.getId(), descriptor.getIdentifier().getId());
		assertEquals(aasId2.getIdType(), descriptor.getIdentifier().getIdType());
		assertEquals(aasId2.getIdType(), descriptor.getIdentifier().getIdType());
		assertEquals(aasEndpoint2, descriptor.getFirstEndpoint());
	}

	/**
	 * Tests deletion for aas entries
	 */
	@Test
	public void testDeleteCall() {
		// After the setup, both AAS should have been inserted to the registry
		assertNotNull(proxy.lookupAAS(aasId1));
		assertNotNull(proxy.lookupAAS(aasId2));
		
		proxy.delete(aasId2);
		
		// After aas2 has been deleted, only aas1 should be registered
		assertNotNull(proxy.lookupAAS(aasId1));
		assertNull(proxy.lookupAAS(aasId2));
		
		proxy.delete(aasId1);
		
		// After aas2 has been deleted, only aas1 should be registered
		assertNull(proxy.lookupAAS(aasId1));
		assertNull(proxy.lookupAAS(aasId2));
	}

	/**
	 * Tests addition, retrieval and removal of submodels
	 */
	@Test
	public void testSubmodelCalls() {
		// Add descriptor
		SubmodelDescriptor smDesc = new SubmodelDescriptor(smIdShort2, smId2, smEndpoint2);
		proxy.register(aasId1, smDesc);

		// Ensure that the submodel is correctly stored in the aas descriptor
		AASDescriptor aasDesc = proxy.lookupAAS(aasId1);
		assertEquals(smDesc.getEndpoints(), aasDesc.getSubmodelDescriptorFromIdShort(smIdShort2).getEndpoints());
		assertNotNull(aasDesc.getSubmodelDescriptorFromIdShort(smIdShort1));

		// Remove Submodel
		proxy.delete(aasId1, smIdShort2);

		// Ensure that the submodel was correctly removed
		aasDesc = proxy.lookupAAS(aasId1);
		assertNotNull(aasDesc.getSubmodelDescriptorFromIdShort(smIdShort1));
		assertNull(aasDesc.getSubmodelDescriptorFromIdShort(smIdShort2));
	}
}
