package app;

public class Question {
    private String quest;
    private String[] correctAnswer;
    private String[] incorrectAnswers;

    public Question() {

    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String[] getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String[] correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(String[] incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
    
}