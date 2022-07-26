package org.mycompany;


import java.lang.module.Configuration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.component.infinispan.InfinispanOperation;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.apache.camel.component.infinispan.remote.*;


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
	
	

	@Override
	public void configure() throws Exception {
		 
		// TODO Auto-generated method stub
		 from("direct:start")
	    .setHeader(InfinispanConstants.OPERATION).constant(InfinispanOperation.PUT)
	    .setHeader(InfinispanConstants.KEY).constant("123")
	   .setHeader(InfinispanConstants.VALUE).constant("hello")
	    .to("infinispan:datagrid-external-datagrid.apps.integration.lab.local/sap-test:11222?username=developer&password=TcrlVPRLsCyfFgWI");	 
	}

}
