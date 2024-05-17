package com.example.camel.routes;

import com.example.camel.records.Person;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestTestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/persons")
            .description("Person REST service")
            .consumes("application/json")
            .produces("application/json")

            .get()
            .description("Find all persons")
            .outType(Person[].class)
            .responseMessage().code(HttpStatus.OK.value()).endResponseMessage()
            .to("direct:find-all-persons")

            .get("/{id}")
            .description("Find person by ID")
            .outType(Person.class)
            .param().name("id").type(RestParamType.path).description("The ID of the person").dataType("integer").endParam()
            .responseMessage().code(HttpStatus.OK.value()).endResponseMessage()
            .to("direct:find-person")

            .put("/{id}")
            .description("Update a person")
            .type(Person.class)
            .param().name("id").type(RestParamType.path).description("The ID of the person to update").dataType("integer").endParam()
            .param().name("body").type(RestParamType.body).description("The person to update").endParam()
            .to("direct:update-person")

            .post()
            .description("Update a person")
            .type(Person.class)
            .param().name("body").type(RestParamType.body).description("The person to update").endParam()
            .to("direct:add-person");

        from("direct:add-person")
            .to("direct:save-person-as-xml-file")
            .to("direct:no-content-response");

        from("direct:update-person")
                .multicast()
                .to("direct:update-persons-map", "direct:save-person-as-xml-file")
                .to("direct:no-content-response");

        from("direct:no-content-response")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.NO_CONTENT.value()));

    }
}
