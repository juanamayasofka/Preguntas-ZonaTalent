package co.com.sofka.questions.controller;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    final AnswerService answerService;


    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping()
    public ResponseEntity<Flux<AnswerDTO>> findAll(){
        return new ResponseEntity<>(answerService.getAllAnswer(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Mono<AnswerDTO>> saveAnswer(@RequestBody AnswerDTO answerDTO){
        return new ResponseEntity<>(answerService.saveAnswer(answerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<AnswerDTO>> findById(@PathVariable String id){
        return new ResponseEntity<>(answerService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> delete(@PathVariable String id){
        return new ResponseEntity<>(answerService.deleteAnswer(id), HttpStatus.OK);
    }

}
