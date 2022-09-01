package co.com.sofka.questions.usecases;

import co.com.sofka.questions.controller.AnswerController;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeleteUseCaseTest {

    @Mock
    private AnswerService answerService;

    private WebTestClient client;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        AnswerController router = new AnswerController(answerService);

        this.client = WebTestClient.bindToController(router)
                .configureClient().baseUrl("/answer").build();
    }

    @Test
    void delete() {
        AnswerDTO answerDTO = AnswerDTO.builder()
                .id("1")
                .answer("Bogota")
                .questionId("123")
                .position(8)
                .userId("465")
                .build();

        when(answerService.deleteAnswer("1"));

        this.client.delete().uri("/1").exchange()
                .expectStatus()
                .isOk()
                .expectBody(AnswerDTO.class)
                .isEqualTo(null);
    }
}