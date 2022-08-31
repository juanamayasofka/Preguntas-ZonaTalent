package co.com.sofka.questions.model;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionMapper {


    public Mono<Question> fromDto(QuestionDTO questionDTO) {
        Question question = new Question();

        question.setQuestion(questionDTO.getQuestion());
        question.setId(questionDTO.getId());
        question.setType(questionDTO.getType());
        question.setUserId(questionDTO.getUserId());
        question.setCategory(questionDTO.getCategory());

        return Mono.just(question);
    }

    public Question fromDtoQuestion(QuestionDTO questionDTO) {
        Question question = new Question();

        question.setQuestion(questionDTO.getQuestion());
        question.setId(questionDTO.getId());
        question.setType(questionDTO.getType());
        question.setUserId(questionDTO.getUserId());
        question.setCategory(questionDTO.getCategory());

        return question;
    }

    public Mono<QuestionDTO> fromCollectionMono(Mono<Question> question) {
            QuestionDTO questionDTO = new QuestionDTO();

        return question.flatMap(question1 -> {
            questionDTO.setQuestion(question1.getQuestion());
            questionDTO.setId(question1.getId());
            questionDTO.setCategory(question1.getCategory());
            questionDTO.setType(question1.getType());
            questionDTO.setUserId(question1.getUserId());

            return Mono.just(questionDTO);
        });

    }


    public Mono<QuestionDTO> fromCollection(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setType(question.getType());
        questionDTO.setId(question.getId());
        questionDTO.setCategory(question.getCategory());
        questionDTO.setUserId(question.getUserId());
        questionDTO.setQuestion(question.getQuestion());

        return Mono.just(questionDTO);
    }

    public Flux<QuestionDTO> fromCollectionList(Flux<Question> collections){

        List<QuestionDTO> list = new ArrayList<>();

        return  collections
                .flatMap(question -> fromCollection(question));

    }

}
