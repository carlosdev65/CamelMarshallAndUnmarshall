package app.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class AppMarshallAndUnmarshall {
	public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
            	from("file:in?fileName=person.xml&delay=5s&noop=true")
                .unmarshal().jacksonxml(Person.class)
                .marshal().json()   
                .to("file:out?fileName=Person.json");
                
            }
        });
        context.start();
        Thread.sleep(10000); 
        context.stop();
        context.close();
	}

}
