package siexamqacategorization.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import siexamqacategorization.ProducerService;
import siexamqacategorization.model.Answers;
import siexamqacategorization.repository.AnswerRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    ProducerService producerService;


    //Get logger
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    List<String> questionList = new ArrayList<String>();
    public String question;

    @KafkaListener(topics = "question-messages", groupId = "question-group")
    public List<?> consume(String message) throws IOException {

        System.out.println("Consumed message : " + message);
        logger.info("&&& Message [{}] consumed", message);
        System.out.println("Wrote from kafka method " + message);

        for (int i = 0; i < searchDB(message).size(); i++){
            System.out.println(searchDB(message).get(i));
            producerService.sendMessage((String) searchDB(message).get(i));
        }

        return searchDB(message);
    }

    // List of all questions
    public List<?> searchDB(String question) {

        List<Answers> myList = answerRepository.findAll();
        // List for similar questions to the one which has been asked

        List<String> splitWord = Arrays.asList(question.split(" "));
        List<Answers> similarQuestions = new ArrayList<Answers>();
        System.out.println(myList);
        System.out.println("Wrote from searchDB method " + question);

        for (String s : splitWord) {
            for (Answers answers : myList) {
                if (answers.getQuestion().contains(s)) {
                    System.out.println(answers);
                    similarQuestions.add(answers);
                }
            }
        }
        return similarQuestions;
    }

}
