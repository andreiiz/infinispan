package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


public class ProvaJson  {
		public void createJson(Exchange exchange) throws Exception {
		JSONObject jos = new JSONObject();
		jos.put("name", "jon doe");
		jos.put("age", "22");
		jos.put("city", "chicago");
		exchange.getIn().setBody(jos.toString());
	}
	
}
