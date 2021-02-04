package by.dzikovskiy.idt.component;

import by.dzikovskiy.idt.entity.Owner;
import by.dzikovskiy.idt.entity.StackResponse;
import by.dzikovskiy.idt.service.OwnerService;
import io.netty.handler.codec.http.HttpResponseStatus;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
class StackResponseClientTest {

    private static MockWebServer mockBackEnd;
    @Autowired
    private StackResponseClient stackResponseClient;
    @Autowired
    private OwnerService ownerService;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
    }

    @Test
    void getStackResponse() throws IOException {
        InputStream jsonStream = StackResponseClientTest.class.getClassLoader().getResourceAsStream("mappings/stackexchange.json");
        byte[] jsonBytes = IOUtils.toByteArray(jsonStream);
        mockBackEnd.enqueue(new MockResponse()
                .setBody(new String(jsonBytes))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpResponseStatus.OK.code())
        );


        //почему-то не мокается webclient и запрос уходит на реальный сервер 

        StepVerifier.create(stackResponseClient.getStackResponse())
                .assertNext(res -> {
                    List<Owner> owners = ownerService.getOwnersFromStackResponse(res);
                    Assertions.assertTrue(owners.size() <= 10);
                    for (Owner owner : owners) {
                        assertNotNull(owner);
                        assertEquals("https://stackoverflow.com/users/"
                                + owner.getUserId() + "/"
                                + owner.getDisplayName().toLowerCase().replace(" ","-"), owner.getLink());
                    }
                })
                .verifyComplete();

//        Mono<StackResponse> stackResponseMono = this.stackResponseClient.getStackResponse();
//        StackResponse stackResponse = stackResponseMono.block();
//        List<Owner> owners = ownerService.getOwnersFromStackResponse(stackResponse);
//
//        Assertions.assertTrue(owners.size() <= 10);
//
//        for (Owner owner : owners) {
//            assertNotNull(owner);
//            assertEquals("https://stackoverflow.com/users/" + owner.getUserId() + "/" + owner.getDisplayName(), owner.getLink());
//        }

    }
}