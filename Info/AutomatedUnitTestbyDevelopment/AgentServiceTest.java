package com.adesa.par.service.business;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.adesa.par.domain.PartnerCorrelation;
import com.adesa.par.domain.RCMCorrelation;
import com.adesa.par.domain.dto.ServiceMessage;
import com.adesa.par.domain.dto.rcm.response.SendNewAssignmentResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AgentServiceTest {

	private AgentService service;

	@Before
	public void setup(){
		service = new AgentService();
	}

	@Test
	public void displayErrorMessage() {
		final ServiceMessage serviceMessage = mock(ServiceMessage.class);

		service.displayErrorMessage(serviceMessage, "any", "anything");

		verify(serviceMessage).addGlobalError(any(String.class), any(String.class));
	}

	@Test
	public void rcmCorrelated(){
		assertFalse(service.rcmCorrelated(null));
		assertFalse(service.rcmCorrelated(new SendNewAssignmentResponse()));

		final SendNewAssignmentResponse correlatedResponse = new SendNewAssignmentResponse();
		correlatedResponse.setRcmCorrelation(new RCMCorrelation());
		assertFalse(service.rcmCorrelated(correlatedResponse));

		correlatedResponse.getRcmCorrelation().setDestCorrelationId("1234");
		assertTrue(service.rcmCorrelated(correlatedResponse));
	}

	@Test
	public void serviceAssignmentErrorMessage() {
		assertTrue(StringUtils
				.equals(String.format(AgentService.SERVICE_ASSIGNMENT_ERROR,
						PartnerCorrelation.PARTNER_NAME_RDN),
						service.serviceAssignmentErrorMessage(PartnerCorrelation.PARTNER_NAME_RDN)));
		assertTrue(StringUtils
				.equals(String.format(AgentService.SERVICE_ASSIGNMENT_ERROR,
						RCMCorrelation.PARTNER_NAME_RCM),
						service.serviceAssignmentErrorMessage(RCMCorrelation.PARTNER_NAME_RCM)));
	}

}
