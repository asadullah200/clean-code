package com.adesa.par.service.rest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import com.adesa.par.domain.RCMCorrelation;
import com.adesa.par.service.business.RCMCorrelationService;
import com.adesa.par.service.rest.exception.NotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PartnerOutageControllerTest {
	
	private PartnerOutageController controller;
	private RCMCorrelationService rcmCorrelationService;
	private Model model;

	@Before
	public void setup(){
		rcmCorrelationService = mock(RCMCorrelationService.class);

		controller = new PartnerOutageController();
		controller.setRcmCorrelationService(rcmCorrelationService);

		model = mock(Model.class);		
	}

	@Test(expected = NotFoundException.class)
	public void getRCMCorrelation_noDataResult() {
		final List<RCMCorrelation> rcmCorrelations = Collections.emptyList();

		when(rcmCorrelationService.getRcmCorrelations(any(RCMCorrelation.class))).thenReturn(rcmCorrelations);

		controller.getRCMCorrelation(1L, 1L, 1L, model);
	}

	@Test
	public void getRCMCorrelation_hasDataResult(){
		final RCMCorrelation expectedRCMCorrelation = newRCMCorrelation(Boolean.TRUE);
		final List<RCMCorrelation> rcmCorrelations = Arrays.asList(expectedRCMCorrelation);

		when(rcmCorrelationService.getRcmCorrelations(any(RCMCorrelation.class))).thenReturn(rcmCorrelations);

		assertEquals("negotiatingView", controller.getRCMCorrelation(1L, 1L, 1L, model));

		isExpectedRCMCorrelation(expectedRCMCorrelation);
	}
	
	@Test
	public void getRCMCorrelation_hasEnabledAndDisabledData(){
		final RCMCorrelation expectedRCMCorrelation = newRCMCorrelation(Boolean.TRUE);
		final List<RCMCorrelation> rcmCorrelations = Arrays.asList(newRCMCorrelation(Boolean.FALSE), expectedRCMCorrelation, newRCMCorrelation(Boolean.FALSE));

		when(rcmCorrelationService.getRcmCorrelations(any(RCMCorrelation.class))).thenReturn(rcmCorrelations);

		assertEquals("negotiatingView", controller.getRCMCorrelation(1L, 1L, 1L, model));

		isExpectedRCMCorrelation(expectedRCMCorrelation);
	}
	
	@Test
	public void getRCMCorrelation_hasOnlyDisabledData(){
		final RCMCorrelation expectedRCMCorrelation = newRCMCorrelation(Boolean.FALSE);
		final List<RCMCorrelation> rcmCorrelations = Arrays.asList(expectedRCMCorrelation, newRCMCorrelation(Boolean.FALSE), newRCMCorrelation(Boolean.FALSE));

		when(rcmCorrelationService.getRcmCorrelations(any(RCMCorrelation.class))).thenReturn(rcmCorrelations);

		assertEquals("negotiatingView", controller.getRCMCorrelation(1L, 1L, 1L, model));

		isExpectedRCMCorrelation(expectedRCMCorrelation);
	}

	private RCMCorrelation newRCMCorrelation(final Boolean enabled) {
		final RCMCorrelation rcmCorrelation = new RCMCorrelation(1L, 1L, 1L, RCMCorrelation.PARTNER_NAME_RCM);
		rcmCorrelation.setEnabled(enabled);
		return rcmCorrelation;
	}

	private void isExpectedRCMCorrelation(
			final RCMCorrelation expectedRCMCorrelation) {
		final ArgumentCaptor<RCMCorrelation> foundRcmCorrelation = ArgumentCaptor.forClass(RCMCorrelation.class);
		verify(model).addAttribute(eq("referenceData"), foundRcmCorrelation.capture());

		assertEquals(expectedRCMCorrelation, foundRcmCorrelation.getValue());
	}

}
