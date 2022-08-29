package org.mycompany;


import java.lang.module.Configuration;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.component.infinispan.InfinispanOperation;
import org.apache.camel.language.Bean;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.Registry;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import org.apache.camel.component.infinispan.*;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.*;
import org.json.JSONObject;


@Component
public class InfiRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
//		JSONObject jos = new JSONObject();
//		jos.put("name", "jon doe");
//		jos.put("age", "22");
//		jos.put("city", "chicago");
		
		//from("timer://foo?repeatCount=1")
		from("direct:provare")
			.setHeader(InfinispanConstants.OPERATION).constant(InfinispanOperation.PUT)
			.setHeader(InfinispanConstants.KEY).constant("123456")
			.setHeader(InfinispanConstants.VALUE).constant(body()) //era jos.tostring
			.to("infinispan:sap-test");
	}
}
