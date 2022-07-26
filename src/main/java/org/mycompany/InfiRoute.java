package org.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.component.infinispan.InfinispanOperation;
import org.springframework.stereotype.Component;



@Component
public class InfiRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
//		JSONObject jos = new JSONObject();
//		jos.put("name", "jon doe");
//		jos.put("age", "22");
//		jos.put("city", "chicago");
		//from("timer://foo?repeatCount=1")
		from("direct:provare").startupOrder(2)
		.routeId("route2")
			.setHeader(InfinispanConstants.OPERATION).constant(InfinispanOperation.PUT)
			.setHeader(InfinispanConstants.KEY).constant("nonsooo")
		//	.setHeader(InfinispanConstants.VALUE).body() 
			.to("infinispan:sap-test");
	}
}
