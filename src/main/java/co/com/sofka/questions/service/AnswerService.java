package co.com.sofka.questions.service;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.AnswerMapper;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class AnswerService {

    final AnswerRepository answerRepository;

    final AnswerMapper answerMapper = new AnswerMapper();

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Flux<AnswerDTO> getAllAnswer(){
        return answerMapper.fromCollectionList(answerRepository.findAll());
    }

    public Mono<AnswerDTO> getById(String id){
        return answerMapper.fromCollectionMono(answerRepository.findById(id));
    }

    public Mono<AnswerDTO> saveAnswer(AnswerDTO answerDTO){
        Answer mono = answerMapper.fromDtoAnswer(answerDTO);
        return answerMapper.fromCollectionMono(answerRepository.save(mono));
    }

    public Mono<Void> deleteAnswer(String questionId){
        return answerRepository.deleteByQuestionId(questionId);
    }
}
