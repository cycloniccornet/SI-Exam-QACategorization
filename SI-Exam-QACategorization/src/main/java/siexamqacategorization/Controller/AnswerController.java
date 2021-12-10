package siexamqacategorization.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import siexamqacategorization.repository.AnswerRepository;
import siexamqacategorization.model.Answers;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @GetMapping("/")
    public List<Answers> retrieveAllAnswers() {
        System.out.println(answerRepository.findAll());
        return answerRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getAnswerById(@PathVariable long id) {
        Answers answers = answerRepository.getById(id);
        System.out.println(answers);
        return "NO!!!";
    }
}
