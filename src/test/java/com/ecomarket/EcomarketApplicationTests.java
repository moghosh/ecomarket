package com.ecomarket;

import com.ecomarket.backend.service.I18NService;
import com.ecomarket.backend.service.kafka.Receiver;
import com.ecomarket.backend.service.kafka.Sender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcomarketApplicationTests {

	private final static String SITECONFIG_TOPIC ="siteconfig.t";

	@Autowired
	private I18NService i18NService;

	@Autowired
	private Receiver receiver;

	@Autowired
	private Sender sender;

	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	@ClassRule
	public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, SITECONFIG_TOPIC);

	@Before
	public void runBeforeTestMethod() throws Exception {
		//wait until all the partitions are assigned
		for (MessageListenerContainer messageListenerContainer: kafkaListenerEndpointRegistry.getListenerContainers()){
			ContainerTestUtils.waitForAssignment(messageListenerContainer, kafkaEmbedded.getPartitionsPerTopic());
		}
	}

	@Test
	public void testReceive() throws Exception {
		sender.send(SITECONFIG_TOPIC, "Changes made to Site Config");

		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		Assert.assertEquals(receiver.getLatch().getCount(), 0);
	}

	@Test
	public void testMessageByLocaleService(){
		String expectedResult = "Bootstrap starter template";
		String messageId = "index.main.callout";
		String actual = i18NService.getMessage(messageId);
		Assert.assertEquals("The expected and actual strings don't match", expectedResult, actual);

	}

}
