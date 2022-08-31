package co.com.sofka.questions.controller;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/question")
public class QuestionController {

    final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public ResponseEntity<Flux<QuestionDTO>> findAll(){
        return new ResponseEntity<>(questionService.getAllAnswer(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Mono<QuestionDTO>> saveAnswer(@RequestBody QuestionDTO questionDTO){
        return new ResponseEntity<>(questionService.saveAnswer(questionDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<QuestionDTO>> findById(@PathVariable String id){
        return new ResponseEntity<>(questionService.getById(id), HttpStatus.OK);
    }

    public ResponseEntity<Mono<Void>> delete(@PathVariable String id){
        return new ResponseEntity<>(questionService.deleteAnswer(id), HttpStatus.OK);
    }
}
