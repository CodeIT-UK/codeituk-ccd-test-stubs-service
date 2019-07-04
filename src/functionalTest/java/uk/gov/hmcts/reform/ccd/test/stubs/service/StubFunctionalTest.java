package uk.gov.hmcts.reform.ccd.test.stubs.service;

import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

/**
 * Dummy functional test class (for use till proper smoke tests exist). Required to pass the "Functional Test" stage of the build
 * pipeline in Jenkins.
 */
class StubFunctionalTest {

    private static final String URL = "/case_type/aat/about_to_start";

    @Test
    void stubTest() {
        withDefaultRequestSpec()
            .contentType(ContentType.JSON)
            .post(URL)
            .then()
            .statusCode(200)
            .body("case_data.CallbackText", is("test"))
            .body("case_data.PersonLastName", is("LastName"))
            .log()
            .all();
    }

    private RequestSpecification withDefaultRequestSpec() {
        return RestAssured.given(new RequestSpecBuilder()
                                     .setBaseUri(TestHelper.getTestUrl())
                                     .build());
    }

}
