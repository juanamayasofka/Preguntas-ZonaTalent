package co.com.sofka.questions.controller;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AnswerControllerTest {

    @Mock
    private AnswerService answerService;

    private WebTestClient client;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        AnswerController router = new AnswerController(answerService);

        this.client = WebTestClient.bindToController(router)
                .configureClient().baseUrl("/answer/crear").build();
    }

    @Test
    void findAll() {
    }

    @Test
    void saveAnswer() {
        AnswerDTO answerDTO = AnswerDTO.builder()
                .id("1")
                .answer("Bogota")
                .questionId("123")
                .position(8)
                .userId("465")
                .build();

        when(answerService.saveAnswer(any(AnswerDTO.class))).thenReturn(Mono.just(answerDTO));

        this.client.post().bodyValue(answerDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(AnswerDTO.class)
                .isEqualTo(answerDTO);
    }

    @Test
    void findById() {

        AnswerDTO answerDTO = AnswerDTO.builder()
                .id("1")
                .answer("Bogota")
                .questionId("123")
                .position(8)
                .userId("465")
                .build();


    }

    @Test
    void delete() {
    }
}