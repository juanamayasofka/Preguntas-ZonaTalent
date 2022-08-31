package co.com.sofka.questions.model;

import co.com.sofka.questions.collections.Answer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnswerMapper {


    public Mono<Answer> fromDto(AnswerDTO answerDTO) {
        Answer answer = new Answer();

        answer.setAnswer(answerDTO.getAnswer());
        answer.setId(answerDTO.getId());
        answer.setPosition(answerDTO.getPosition());
        answer.setUserId(answerDTO.getUserId());
        answer.setQuestionId(answerDTO.getQuestionId());

        return Mono.just(answer);
    }

    public Answer fromDtoAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();

        answer.setAnswer(answerDTO.getAnswer());
        answer.setId(answerDTO.getId());
        answer.setPosition(answerDTO.getPosition());
        answer.setUserId(answerDTO.getUserId());
        answer.setQuestionId(answerDTO.getQuestionId());

        return answer;
    }

    public Mono<AnswerDTO> fromCollectionMono(Mono<Answer> answer) {
        AnswerDTO answerDTO = new AnswerDTO();

      return answer.flatMap(answer1 -> {
            answerDTO.setAnswer(answer1.getAnswer());
            answerDTO.setId(answer1.getId());
            answerDTO.setPosition(answer1.getPosition());
            answerDTO.setUserId(answer1.getUserId());

            return Mono.just(answerDTO);
        });

    }


    public Mono<AnswerDTO> fromCollection(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setAnswer(answer.getAnswer());
        answerDTO.setId(answer.getId());
        answerDTO.setPosition(answer.getPosition());
        answerDTO.setUserId(answer.getUserId());
        answerDTO.setQuestionId(answer.getQuestionId());

        return Mono.just(answerDTO);
    }

    public Flux<AnswerDTO> fromCollectionList(Flux<Answer> collections){

        List<AnswerDTO> list = new ArrayList<>();

       return  collections
                .flatMap(answer -> fromCollection(answer));

    }

}
