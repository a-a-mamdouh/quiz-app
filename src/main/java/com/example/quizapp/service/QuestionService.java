package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    // تقوم هذه الدالة الآن بإرجاع قائمة الأسئلة مباشرة دون ResponseEntity
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    // تقوم هذه الدالة بإرجاع الأسئلة حسب الفئة مباشرة
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    // تقوم هذه الدالة بإرجاع السؤال الذي تم حفظه مباشرة
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }
}
