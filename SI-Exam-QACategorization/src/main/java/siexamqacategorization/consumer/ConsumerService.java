package siexamqacategorization.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
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


    //Get logger
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    List<String> questionList = new ArrayList<String>();
    public String question;

    @KafkaListener(topics = "question-messages", groupId = "question-group")
    public void consume(String message) throws IOException {


        System.out.println("Consumed message : " + message);
        logger.info("&&& Message [{}] consumed", message);
        System.out.println("Wrote from kafka method " + message);
        searchDB(message);
    }

    // List of all questions
    public void searchDB(String question) {
        List<Answers> myList = answerRepository.findAll();
        // List for similar questions to the one which has been asked

        List<String> splitWord = Arrays.asList(question.split(" "));
        List<Answers> arr = new ArrayList<Answers>();
        System.out.println(myList);
        System.out.println("Wrote from searchDB method " + question);

        for (int j = 0; j < splitWord.size(); j++) {
            for (int i = 0; i < myList.size(); i++) {
                if (myList.get(i).getQuestion().contains(splitWord.get(j))) {
                    System.out.println(myList.get(i));
                    arr.add(myList.get(i));
                }
                //System.out.println(arr);
            }
        }
    }

}
