package org.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.component.infinispan.InfinispanOperation;

import org.springframework.stereotype.Component;


@Component
public class InfiRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("timer://foo?repeatCount=1")
				.setHeader(InfinispanConstants.OPERATION).constant(InfinispanOperation.PUT)
				.setHeader(InfinispanConstants.KEY).constant(rm"123")
				.to("infinispan:sap-test");

	}

}
