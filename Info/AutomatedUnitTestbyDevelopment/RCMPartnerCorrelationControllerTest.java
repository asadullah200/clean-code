package com.adesa.par.service.rest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import com.adesa.par.domain.AgentOutboundReprocess;
import com.adesa.par.domain.RCMCorrelation;
import com.adesa.par.service.business.RCMCorrelationService;
import com.adesa.par.service.rest.dto.PartnerCorrelationRequestDTO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RCMPartnerCorrelationControllerTest {

	private RCMCorrelationService rcmCorrelationService;
	private Model model;
	private RCMPartnerCorrelationController controller;

	@Before
	public void setup() {
		rcmCorrelationService = mock(RCMCorrelationService.class);
		model = mock(Model.class);

		controller = new RCMPartnerCorrelationController(rcmCorrelationService);
	}

	@Test
	public void partnerCorrelation_successful() {
		final PartnerCorrelationRequestDTO dto = partnerCorrelationRequest();

		when(rcmCorrelationService.correlate(any(RCMCorrelation.class)))
				.thenReturn(dto.toRCMCorrelation());

		assertEquals("negotiatingView",
				controller.partnerCorrelation(dto, model));

		isExpectedResponseResult(model, "SUCCESS");
	}

	@Test
	public void partnerCorrelation_unsuccessful() {
		final PartnerCorrelationRequestDTO dto = partnerCorrelationRequest();

		when(rcmCorrelationService.correlate(any(RCMCorrelation.class)))
				.thenThrow(new RuntimeException("Could be anything."));

		assertEquals("negotiatingView",
				controller.partnerCorrelation(dto, model));

		isExpectedResponseResult(model, "FAILURE");
	}

	private void isExpectedResponseResult(final Model model,
			final String responseResult) {
		final ArgumentCaptor<AgentOutboundReprocess> responseData = ArgumentCaptor
				.forClass(AgentOutboundReprocess.class);
		verify(model).addAttribute(eq("referenceData"), responseData.capture());
		assertEquals(responseResult, responseData.getValue().getResponse());
	}

	private PartnerCorrelationRequestDTO partnerCorrelationRequest() {
		final PartnerCorrelationRequestDTO dto = new PartnerCorrelationRequestDTO();
		dto.setAgentId(1L);
		dto.setAssignmentType(1L);
		dto.setCreatedBy("unit");
		dto.setDestCorrelationId("ABC");
		dto.setPartner(RCMCorrelation.PARTNER_NAME_RCM);
		dto.setParStockNumber(1L);
		dto.setUpdatedBy("unit");
		return dto;
	}

}
