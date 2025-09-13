package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Response;
import com.example.quizapp.repo.QuestionRepo;
import com.example.quizapp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;

    // تقوم هذه الدالة الآن بإرجاع كائن Quiz مباشرة
    public Quiz createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return quizRepo.save(quiz);
    }

    // تقوم هذه الدالة بإرجاع قائمة QuestionWrapper مباشرة
    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Optional<Quiz> quizOptional = quizRepo.findById(id);

        // التعامل مع Optional بطريقة آمنة
        if (quizOptional.isPresent()) {
            List<Question> questions = quizOptional.get().getQuestions();
            List<QuestionWrapper> questionForUsers = new ArrayList<>();
            for (Question q : questions) {
                QuestionWrapper qw = new QuestionWrapper(
                        q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()
                );
                questionForUsers.add(qw);
            }
            return questionForUsers;
        }
        return new ArrayList<>(); // إرجاع قائمة فارغة إذا لم يتم العثور على Quiz
    }

    // تقوم هذه الدالة بإرجاع النتيجة مباشرة كـ Integer
    public Integer calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizOptional = quizRepo.findById(id);

        if (quizOptional.isPresent()) {
            List<Question> questions = quizOptional.get().getQuestions();

            // تحويل قائمة الأسئلة إلى Map للبحث السريع عن الإجابات الصحيحة
            Map<Integer, String> correctAnswersMap = questions.stream()
                    .collect(Collectors.toMap(Question::getId, Question::getRightAnswer));

            int right = 0;
            for (Response r : responses) {
                // استخدام id الإجابة للبحث عن السؤال الصحيح
                String correctAnswer = correctAnswersMap.get(r.getId());
                if (correctAnswer != null && r.getResponse().equals(correctAnswer)) {
                    right++;
                }
            }
            return right;
        }
        return 0; // إرجاع 0 إذا لم يتم العثور على Quiz
    }
}
