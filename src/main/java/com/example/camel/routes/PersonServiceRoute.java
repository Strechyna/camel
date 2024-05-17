package com.example.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:find-all-persons")
            .log("Find all persons in the system")
            .to("bean:personService?method=findAll()");

        from("direct:find-person")
            .log("Find person with id ${header.id}")
            .to("bean:personService?method=findById(${header.id})");

        from("direct:update-persons-map")
                .to("bean:personService?method=update")
                .log("Persons map was successfully updated");

        from("direct:save-person-as-xml-file")
                .setHeader(Exchange.FILE_NAME, simple("person${body.id}.xml"))
                .to("direct:object-to-xml-file");

    }
}
