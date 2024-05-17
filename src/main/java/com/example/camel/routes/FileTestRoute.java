package com.example.camel.routes;

import com.example.camel.records.Person;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileTestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:data?antInclude=person*.xml&noop=true")
            .log("The file ${file:name} is processing")
            .unmarshal()
            .jacksonXml(Person.class)
            .to("direct:update-persons-map");

        from("direct:object-to-xml-file")
                .marshal()
                .jacksonXml()
                .to("file:data")
                .log("Object ${body} saved to the file ${header.CamelFileName} successfully");
    }
}
