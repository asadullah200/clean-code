package com.adesa.par.integration.listener.rdn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException; 
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test; 
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.ProducerCallback;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.core.support.JmsGatewaySupport;
import com.adesa.par.domain.AccountUpdate;
import com.adesa.par.domain.Repossession;
import com.adesa.par.integration.event.AccountUpdateCreateEvent;
import com.adesa.par.integration.event.message.AccountUpdateCreateMessage;
import com.adesa.par.service.business.AccountUpdateService;
import com.adesa.par.service.business.RepossessionService;
import com.adesa.par.service.ihub.IHubApplicationDataServiceGateway;
import com.adesa.par.service.ihub.IHubManifest;
import com.ibm.websphere.sib.api.jms.JmsMsgProducer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
  
public class TestAccountUpdateCreateListener {
 
 
	@Test
	public void testIsRDNAccount(){
		
		AccountUpdateCreateListener accountUpdateCreateListener = new AccountUpdateCreateListener();
		IHubApplicationDataServiceGateway ihubApplicationDataServiceGateway = mock(IHubApplicationDataServiceGateway.class);
		Long parStockNumber =0L;
		accountUpdateCreateListener.ihubApplicationDataServiceGateway = ihubApplicationDataServiceGateway;
   	    assertFalse(accountUpdateCreateListener.isRDNAccount(parStockNumber, 1));
   	    
	}
}
