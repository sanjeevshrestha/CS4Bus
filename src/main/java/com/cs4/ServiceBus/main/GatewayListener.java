package com.cs4.ServiceBus.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Gateway Listener main program
 * @author sanjeev
 *
 */
@Component
public class GatewayListener {
	/**
	 * 
	 */
	private final static String[] configFilesGatewayDemo = { "/META-INF/spring/integration/cs4bus-common.xml",
			"/META-INF/spring/integration/gateway-listener-context.xml",
			"/META-INF/spring/integration/gateway-appointment.xml",
			"/META-INF/spring/integration/mailContext.xml"
};

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Gateway Listener");
		System.out.println("Waiting for events...");
		System.out.println("-----------------------");

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configFilesGatewayDemo, GatewayListener.class);
		applicationContext.getBean(GatewayListener.class).mainInternal(applicationContext);
	}

	/**
	 * @param applicationContext
	 */
	private void mainInternal(ApplicationContext applicationContext) {

	}

}