package Models;

//Класс описывает шаблон, по которому в дальнейшем ищется слово

public class WordPattern {

    private int pos_id;
    private int case_id;
    private int tense_id;
    private int gender_id;
    private int num_id;
    private int anim_id;
    private int tran_id;
    private int spec_id;
    private int person_id;
    private int incl_id;

    public int getPos_id() { return pos_id; }

    public void setPos_id(int pos_id) {
        this.pos_id = pos_id;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public int getTense_id() {
        return tense_id;
    }

    public void setTense_id(int tense_id) {
        this.tense_id = tense_id;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public int getNum_id() {
        return num_id;
    }

    public void setNum_id(int num_id) {
        this.num_id = num_id;
    }

    public int getAnim_id() {
        return anim_id;
    }

    public void setAnim_id(int anim_id) {
        this.anim_id = anim_id;
    }

    public int getTran_id() {
        return tran_id;
    }

    public void setTran_id(int tran_id) {
        this.tran_id = tran_id;
    }

    public int getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(int spec_id) {
        this.spec_id = spec_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getIncl_id() {
        return incl_id;
    }

    public void setIncl_id(int incl_id) {
        this.incl_id = incl_id;
    }

    public WordPattern() {
        this.case_id = 0;
        this.tense_id = 0;
        this.gender_id = 0;
        this.num_id = 0;
        this.anim_id = 0;
        this.tran_id = 0;
        this.spec_id = 0;
        this.person_id = 0;
        this.incl_id = 0;
    }

    // Существительные
    public static WordPattern noun (int case_id, int gender_id, int num_id, int anim_id, int spec_id) {
        WordPattern wordPattern = new WordPattern();
        wordPattern.setPos_id(1);
        wordPattern.setCase_id(case_id);
        wordPattern.setGender_id(gender_id);
        wordPattern.setNum_id(num_id);
        wordPattern.setAnim_id(anim_id);
        wordPattern.setSpec_id(spec_id);
        return wordPattern;
    }
    //прилагательные
    public static WordPattern adjective (int case_id, int gender_id, int num_id, int anim_id, int spec_id) {
        WordPattern wordPattern = new WordPattern();
        wordPattern.setPos_id(2);
        wordPattern.setCase_id(case_id);
        wordPattern.setGender_id(gender_id);
        wordPattern.setNum_id(num_id);
        wordPattern.setAnim_id(anim_id);
        wordPattern.setSpec_id(spec_id);
        return wordPattern;
    }

    // Глаголы
    public static WordPattern verb (int tense_id, int gender_id, int num_id, int tran_id, int spec_id, int person_id, int incl_id) {
        WordPattern wordPattern =new WordPattern();
        wordPattern.setPos_id(3);
        wordPattern.setTense_id(tense_id);
        wordPattern.setGender_id(gender_id);
        wordPattern.setNum_id(num_id);
        wordPattern.setTran_id(tran_id);
        wordPattern.setSpec_id(spec_id);
        wordPattern.setPerson_id(person_id);
        wordPattern.setIncl_id(incl_id);
        return wordPattern;
    }

    //Числительное
    public static WordPattern numeral (int case_id, int spec_id) {
        WordPattern wordPattern = new WordPattern();
        wordPattern.setPos_id(4);
        wordPattern.setCase_id(case_id);
        wordPattern.setSpec_id(spec_id);
        return wordPattern;
    }
    // Наречие
    public static WordPattern adverb (int spec_id) {
        WordPattern wordPattern = new WordPattern();
        wordPattern.setPos_id(6);
        wordPattern.setSpec_id(spec_id);
        return wordPattern;
    }

    // Деепричастие
    public static WordPattern participleVerb (int tense_id, int tran_id, int spec_id) {
        WordPattern wordPattern = new WordPattern();
        wordPattern.setPos_id(7);
        wordPattern.setTran_id(tran_id);
        wordPattern.setPos_id(spec_id);
        return wordPattern;
    }

    // краткое прилагательное
    public static WordPattern shortAdverb (int gender_id, int num_id, int spec_id) {
        WordPattern wordPattern = new WordPattern();
        wordPattern.setPos_id(8);
        wordPattern.setGender_id(gender_id);
        wordPattern.setNum_id(num_id);
        wordPattern.setSpec_id(spec_id);
        return wordPattern;
    }
    // сравнительная степень
    public static WordPattern comparative (int spec_id) {
        WordPattern wordPattern = new WordPattern();
        wordPattern.setPos_id(9);
        wordPattern.setSpec_id(spec_id);
        return wordPattern;
    }

}
