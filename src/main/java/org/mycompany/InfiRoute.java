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
		builder.clientIntelligence(ClientIntelligence.BASIC);
		
		builder.addServer()
        .host("https://datagrid-external-datagrid.apps.integration.lab.local/rest/v2/caches/")
        .port(80)
        .security().authentication()
        .saslMechanism("DIGEST-MD5")
        .username("developer")
        .password("TcrlVPRLsCyfFgWI");
       
		
		RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());
		RemoteCache rm = cacheManager.getCache("test");
		// TODO Auto-generated method stub
		 from("timer://foo?repeatCount=1")
	    .setHeader(InfinispanConstants.OPERATION).constant(InfinispanOperation.PUT)
	    .setHeader(InfinispanConstants.KEY).constant("123")
	    .setHeader(InfinispanConstants.VALUE).constant("hello")
	   // .to("infinispan://datagrid-external-datagrid.apps.integration.lab.local/sap-test?username=developer&password=TcrlVPRLsCyfFgWI");	
	  .to("infinispan://sap-test&cacheContainerConfiguration=#cacheContainerConfiguration");
	}

}
