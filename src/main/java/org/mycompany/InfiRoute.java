package org.mycompany;


import java.lang.module.Configuration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.component.infinispan.InfinispanOperation;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.apache.camel.component.infinispan.*;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.*;
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
		
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		
		
		builder.addServer()
  //   .host("datagrid-external-datagrid.apps.integration.lab.local") //cache-service-hotrod-route-datagrid.apps.integration.lab.local
	//	.host("cache-service-hotrod-route-datagrid.apps.integration.lab.local")
     .host("datagrid.datagrid.svc.cluster.local")
     .port(11222)
     //.port(443)
       // .clientIntelligence(ClientIntelligence.BASIC)
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
		
		
		RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());
		RemoteCache<String, String> rm = cacheManager.getCache("sap-test");
		// TODO Auto-generated method stub
		 from("timer://foo?repeatCount=1")
	    .setHeader(InfinispanConstants.OPERATION).constant(InfinispanOperation.PUT)
	    .setHeader(InfinispanConstants.KEY).constant("123")  //o simple
	    .setHeader(InfinispanConstants.VALUE).constant("hello")
	   // .to("infinispan://datagrid-external-datagrid.apps.integration.lab.local/sap-test?username=developer&password=TcrlVPRLsCyfFgWI");	
	  .to("infinispan://rm");
	//  .to("log: message");
	}

}
