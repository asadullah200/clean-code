package com.adesa.par.service.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.adesa.par.domain.Audit;
import com.adesa.par.domain.RCMCorrelation;
import com.adesa.par.domain.dao.RcmCorrelationDao;

public class RCMCorrelationServiceTest {

	private RcmCorrelationDao rcmCorrelationDao;
	private RCMCorrelationService service;

	@Before
	public void setup(){
		rcmCorrelationDao = mock(RcmCorrelationDao.class);

		service = new RCMCorrelationService();
		service.setDao(rcmCorrelationDao);
	}

	@Test
	public void correlate_new(){
		final RCMCorrelation rcmCorrelation = rcmCorrelation();

		when(rcmCorrelationDao.getActiveRcmCorrelation(rcmCorrelation)).thenReturn(null);

		when(rcmCorrelationDao.saveOrUpdate(rcmCorrelation)).thenReturn(rcmCorrelation);

		assertNotNull(service.correlate(rcmCorrelation));

		expectThisWasSaved(rcmCorrelation);
	}

	@Test
	public void correlate_update(){
		final RCMCorrelation requestedRcmCorrelation = rcmCorrelation();
		requestedRcmCorrelation.setDestCorrelationId("123ABC");

		final RCMCorrelation existingRcmCorrelation = rcmCorrelation();
		existingRcmCorrelation.setRcmCorrelationId(1L);
		existingRcmCorrelation.setDestCorrelationId(null);
		final Audit existingAudit = existingRcmCorrelation.getAudit();
		existingAudit.setUpdatedBy("another");
		existingAudit.setUpdatedDate(new Date());

		when(rcmCorrelationDao.getActiveRcmCorrelation(requestedRcmCorrelation)).thenReturn(existingRcmCorrelation);

		when(rcmCorrelationDao.saveOrUpdate(existingRcmCorrelation)).thenReturn(existingRcmCorrelation);

		assertNotNull(service.correlate(requestedRcmCorrelation));

		expectThisWasSaved(existingRcmCorrelation);

		expectThisWasUpdated(requestedRcmCorrelation, existingRcmCorrelation);
	}

	private void expectThisWasUpdated(
			final RCMCorrelation requestedRcmCorrelation,
			final RCMCorrelation existingRcmCorrelation) {
		assertEquals(requestedRcmCorrelation.getDestCorrelationId(), existingRcmCorrelation.getDestCorrelationId());

		final Audit requestAudit = requestedRcmCorrelation.getAudit();
		final Audit existingAudit = existingRcmCorrelation.getAudit();

		assertEquals(requestAudit.getUpdatedBy(), existingAudit.getUpdatedBy());
		assertEquals(requestAudit.getUpdatedDate(), existingAudit.getUpdatedDate());
	}

	private RCMCorrelation rcmCorrelation() {
		final RCMCorrelation correlation = new RCMCorrelation(1L, 1L, 1L, RCMCorrelation.PARTNER_NAME_RCM, "ABC123");
		correlation.setMbsiAgentId("1");

		final Audit audit = correlation.getAudit();
		audit.setCreatedBy("unit");
		audit.setCreatedDate(new Date());
		audit.setUpdatedBy("unit");
		audit.setUpdatedDate(new Date());

		return correlation;
	}

	private void expectThisWasSaved(final RCMCorrelation rcmCorrelation) {
		final ArgumentCaptor<RCMCorrelation> rcmToSaveOrUpdate = ArgumentCaptor.forClass(RCMCorrelation.class);
		verify(rcmCorrelationDao).saveOrUpdate(rcmToSaveOrUpdate.capture());
		assertEquals(rcmCorrelation, rcmToSaveOrUpdate.getValue());
	}

}
