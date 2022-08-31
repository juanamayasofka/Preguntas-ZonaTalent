package co.com.sofka.questions.service;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.QuestionMapper;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuestionService {

    final QuestionRepository questionRepository;

    final QuestionMapper questionMapper = new QuestionMapper();

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Flux<QuestionDTO> getAllAnswer(){
        return questionMapper.fromCollectionList(questionRepository.findAll());
    }

    public Mono<QuestionDTO> getById(String id){
        return questionMapper.fromCollectionMono(questionRepository.findById(id));
    }

    public Mono<QuestionDTO> saveAnswer(QuestionDTO questionDTO){
        Question mono = questionMapper.fromDtoQuestion(questionDTO);
        return questionMapper.fromCollectionMono(questionRepository.save(mono));
    }

    public Mono<Void> deleteAnswer(String questionId){
        return questionRepository.deleteQuestionById(questionId);
    }
}
