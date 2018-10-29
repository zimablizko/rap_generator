package Models;

public class Lemma {
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
    

    public int getPos_id() {
        return pos_id;
    }

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

    public Lemma() {
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
    public static Lemma noun (int case_id, int gender_id, int num_id, int anim_id, int spec_id) {
        Lemma lemma = new Lemma();
        lemma.setPos_id(1);
        lemma.setCase_id(case_id);
        lemma.setGender_id(gender_id);
        lemma.setNum_id(num_id);
        lemma.setAnim_id(anim_id);
        lemma.setSpec_id(spec_id);
        return lemma;
    }
    //прилагательные
    public static Lemma adjective (int case_id, int gender_id, int num_id, int anim_id, int spec_id) {
        Lemma lemma = new Lemma();
        lemma.setPos_id(2);
        lemma.setCase_id(case_id);
        lemma.setGender_id(gender_id);
        lemma.setNum_id(num_id);
        lemma.setAnim_id(anim_id);
        lemma.setSpec_id(spec_id);
        return lemma;
    }

    // Глаголы
    public static Lemma verb (int tense_id, int gender_id, int num_id, int tran_id, int spec_id, int person_id, int incl_id) {
        Lemma lemma =new Lemma();
        lemma.setPos_id(3);
        lemma.setTense_id(tense_id);
        lemma.setGender_id(gender_id);
        lemma.setNum_id(num_id);
        lemma.setTran_id(tran_id);
        lemma.setSpec_id(spec_id);
        lemma.setPerson_id(person_id);
        lemma.setIncl_id(incl_id);
        return lemma;
    }

    //Числительное
    public static Lemma numeral (int case_id, int spec_id) {
        Lemma lemma = new Lemma();
        lemma.setPos_id(4);
        lemma.setCase_id(case_id);
        lemma.setSpec_id(spec_id);
        return lemma;
    }
    // Наречие
    public static Lemma adverb (int spec_id) {
        Lemma lemma = new Lemma();
        lemma.setPos_id(6);
        lemma.setSpec_id(spec_id);
        return lemma;
    }

    // Деепричастие
    public static Lemma participleVerb (int tense_id, int tran_id, int spec_id) {
        Lemma lemma = new Lemma();
        lemma.setPos_id(7);
        lemma.setTran_id(tran_id);
        lemma.setPos_id(spec_id);
        return lemma;
    }

    // краткое прилагательное
    public static Lemma shortAdverb (int gender_id, int num_id, int spec_id) {
        Lemma lemma = new Lemma();
        lemma.setPos_id(8);
        lemma.setGender_id(gender_id);
        lemma.setNum_id(num_id);
        lemma.setSpec_id(spec_id);
        return lemma;
    }
    // сравнительная степень
    public static Lemma comparative ( int spec_id) {
        Lemma lemma = new Lemma();
        lemma.setPos_id(9);
        lemma.setSpec_id(spec_id);
        return lemma;
    }

}
