package app;

public class Question {
    private String quest;
    private String correctAnswer;
    private String[] incorrectAnswers;


    public Question() {
        
    }

    public Question(String quest, String correctAnswer, String[] incorrectAnswers) {
        setQuest(quest);
        setCorrectAnswer(correctAnswer);
        setIncorrectAnswers(incorrectAnswers);
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(String[] incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void mostrar(){
        System.out.println(getQuest());
        System.out.println("*"+getCorrectAnswer());                
        for (String inAnsw : getIncorrectAnswers()) {
            System.out.println(inAnsw);
        }
    }
    
}