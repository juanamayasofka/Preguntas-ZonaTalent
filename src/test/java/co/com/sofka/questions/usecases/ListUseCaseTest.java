package co.com.sofka.questions.usecases;

import co.com.sofka.questions.controller.AnswerController;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListUseCaseTest {

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
    @DisplayName("Listar todas las answer")
    void findAll() {

        AnswerDTO answerDTO = AnswerDTO.builder()
                .id("1")
                .answer("Cali")
                .questionId("123")
                .position(8)
                .userId("346")
                .build();

        AnswerDTO answerDTO1 = AnswerDTO.builder()
                .id("1")
                .answer("Bogota")
                .questionId("123")
                .position(8)
                .userId("465")
                .build();

        AnswerDTO answerDTO2 = AnswerDTO.builder()
                .id("1")
                .answer("Medellin")
                .questionId("123")
                .position(8)
                .userId("754")
                .build();

        List<AnswerDTO> list = new ArrayList<>();

        list.add(answerDTO);
        list.add(answerDTO1);
        list.add(answerDTO2);

        when(answerService.getAllAnswer()).thenReturn(Flux.fromStream(list.stream()));

        this.client.get().exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(AnswerDTO.class)
                .isEqualTo(list);

    }
}