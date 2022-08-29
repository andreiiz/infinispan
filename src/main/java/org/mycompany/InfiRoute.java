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

/*	org.infinispan.client.hotrod.configuration.Configuration conf = new ConfigurationBuilder().addServer().host("datagrid-external-datagrid.apps.integration.lab.local").port(11222).build();
			RemoteCacheManager manager = new RemoteCacheManager(conf);
			RemoteCache defaultCache = manager.getCache();
		
			org.infinispan.client.hotrod.configuration.Configuration cacheContainerConfiguration = new org.infinispan.client.hotrod.configuration.ConfigurationBuilder()
				    .addServer()
				        .host("datagrid-external-datagrid.apps.integration.lab.local")
				        .port(11222)
				     //   .version(org.infinispan.client.hotrod.ProtocolVersion.PROTOCOL_VERSION_25)
				    .build();
*/
/*	RemoteCacheManagerFactory rf = new RemoteCacheManagerFactory("datagrid-external-datagrid.apps.integration.lab.local", 11222 );
	RemoteCacheManager  cm =  rf.newRemoteCacheManager();
    */
	
	//  InfinispanRemoteConfiguration infinispanRemoteConfiguration = new InfinispanRemoteConfiguration();
	
	//ConfigurationBuilder builder = new ConfigurationBuilder();
	//builder.clientIntelligence(ClientIntelligence.BASIC);
	
	
	
	@Override
	public void configure() throws Exception {
		
		/*		//CamelContext cm = getContext();
		ConfigurationBuilder builder = new ConfigurationBuilder();
		
		//CamelContext ccontext = new SpringCamelContext(applicationContext);
		builder.addServer()
  //   .host("datagrid-external-datagrid.apps.integration.lab.local") //cache-service-hotrod-route-datagrid.apps.integration.lab.local
	//	.host("cache-service-hotrod-route-datagrid.apps.integration.lab.local")
     .host("datagrid.datagrid.svc.cluster.local")
     .port(11222)
     //.port(443)
        .clientIntelligence(ClientIntelligence.BASIC)
        .security().authentication().enable()
        .username("developer")
        .password("TcrlVPRLsCyfFgWI")
        .serverName("datagrid")
        .realm("default")
		.saslMechanism("PLAIN")
		.saslQop(SaslQop.AUTH)
		.ssl()
	//	.sniHostName("cache-service-hotrod-route-datagrid.apps.integration.lab.local")
	//	.sniHostName("datagrid-external-datagrid.apps.integration.lab.local")
		//.trustStorePath("" + getClass().getResource("/tls.crt"));
		.trustStorePath("/var/run/secrets/kubernetes.io/serviceaccount/service-ca.crt");
		
		
		Registry registry = CamelContext.
		RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());
		//cm..getRegistry().bind("cacheManager", cacheManager);
	//	RemoteCache rm = cacheManager.getCache("sap-test");
		// TODO Auto-generated method stub
		 from("direct:saveKey")
	    .setHeader(CamelInfinispan , constant(InfinispanOperation.PUT))
	    .setHeader(InfinispanConstants.KEY).constant("12")  //o simple
	    .setHeader(InfinispanConstants.VALUE).constant("hello")
	 //   .convertBodyTo(String.class)
	   // .to("infinispan://datagrid-external-datagrid.apps.integration.lab.local/sap-test?username=developer&password=TcrlVPRLsCyfFgWI");	
	  .marshal().json() 
	  .to("infinispan:sap-test?cacheContainer=#cacheManager")
	 ;
	//	 .to("log: riri");
	//  .to("log: message");
	}
	@Bean(name = "stringDecoder") 
	public StringEncoder getStringEncoder() {
	    return new StringEncoder();
	}

	@Bean(name = "stringEncoder") 
	public StringDecoder getStringDecoder() {
	    return new StringDecoder();
	    
*/	
		JSONObject jos = new JSONObject();
		jos.put("name", "jon doe");
		jos.put("age", "22");
		jos.put("city", "chicago");
		
		from("timer://foo?repeatCount=1")
			.setHeader(InfinispanConstants.OPERATION).constant(InfinispanOperation.PUT)
			.setHeader(InfinispanConstants.KEY).constant("123456")
			.setHeader(InfinispanConstants.VALUE).constant(jos.toString())
			.to("infinispan:sap-test");
	

}
}
