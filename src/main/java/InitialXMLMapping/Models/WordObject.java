package InitialXMLMapping.Models;

import java.util.ArrayList;
import java.util.List;

//Класс описывает модель леммы и её атрибутов

public class WordObject {

    private String word;
    private int lemmaId;
    private List<String> attribList;


    public WordObject() {
        attribList = new ArrayList<String>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLemmaId() {
        return lemmaId;
    }

    public void setLemmaId(int lemmaId) {
        this.lemmaId = lemmaId;
    }

    public List<String> getAttribList() {
        return attribList;
    }

    public void setAttribList(List<String> attribList) {
        this.attribList = attribList;
    }

    public void addAttrib(String attrib){
            attribList.add(attrib);
    }

    @Override
    public String toString() {
        return "WordObject{" +
                "word='" + word + '\'' +
                ", lemmaId=" + lemmaId +
                ", attribList=" + attribList.toString() +
                '}';
    }
}
