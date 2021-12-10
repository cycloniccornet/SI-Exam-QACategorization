package siexamqacategorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siexamqacategorization.model.Answers;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
    // Notice, there are no methods here, but we still can use all those, which we inherit from JpaRepository
}
