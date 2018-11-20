package InitialXMLMapping;

import InitialXMLMapping.Models.WordAttrib;
import InitialXMLMapping.Models.WordObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//маппер 4-символьных атрибутов из файла на значения из базы
public class AttribMapper {

    public static Map attribMap = new HashMap<String, WordAttrib>();
    static {
        attribMap.put("NOUN", new WordAttrib("pos_id",1));
        attribMap.put("ADJF", new WordAttrib("pos_id",2));
        attribMap.put("ADJS", new WordAttrib("pos_id",8));
        attribMap.put("COMP", new WordAttrib("pos_id",9));
        attribMap.put("VERB", new WordAttrib("pos_id",3));
        attribMap.put("INFN", new WordAttrib("pos_id",10));
        attribMap.put("PRTF", new WordAttrib("pos_id",11));
        attribMap.put("PRTS", new WordAttrib("pos_id",12));
        attribMap.put("GRND", new WordAttrib("pos_id",7));
        attribMap.put("NUMR", new WordAttrib("pos_id",4));
        attribMap.put("ADVB", new WordAttrib("pos_id",6));
        attribMap.put("NPRO", new WordAttrib("pos_id",13));
        attribMap.put("PRED", new WordAttrib("pos_id",14));
        attribMap.put("PREP", new WordAttrib("pos_id",15));
        attribMap.put("CONJ", new WordAttrib("pos_id",16));
        attribMap.put("PRCL", new WordAttrib("pos_id",18));
        attribMap.put("INTJ", new WordAttrib("pos_id",17));
        attribMap.put("anim", new WordAttrib("anim_id",1));
        attribMap.put("inan", new WordAttrib("anim_id",2));
        attribMap.put("masc", new WordAttrib("gender_id",1));
        attribMap.put("femn", new WordAttrib("gender_id",2));
        attribMap.put("neut", new WordAttrib("gender_id",3));
        attribMap.put("ms-f", new WordAttrib("gender_id",4));
        attribMap.put("sing", new WordAttrib("num_id",1));
        attribMap.put("plur", new WordAttrib("num_id",2));
        attribMap.put("nomn", new WordAttrib("case_id",1));
        attribMap.put("gent", new WordAttrib("case_id",2));
        attribMap.put("datv", new WordAttrib("case_id",3));
        attribMap.put("accs", new WordAttrib("case_id",4));
        attribMap.put("ablt", new WordAttrib("case_id",5));
        attribMap.put("loct", new WordAttrib("case_id",6));
        attribMap.put("voct", new WordAttrib("case_id",7));
        attribMap.put("gen1", new WordAttrib("case_id",2));
        attribMap.put("gen2", new WordAttrib("case_id",2));
        attribMap.put("acc2", new WordAttrib("case_id",4));
        attribMap.put("loc1", new WordAttrib("case_id",6));
        attribMap.put("loc2", new WordAttrib("case_id",6));
        attribMap.put("Name", new WordAttrib("spec_id",1));
        attribMap.put("Geox", new WordAttrib("spec_id",2));
        attribMap.put("Orgn", new WordAttrib("spec_id",3 ));
        attribMap.put("Trad", new WordAttrib("spec_id",4 ));
        attribMap.put("Subx", new WordAttrib("spec_id",5));
        attribMap.put("Anum", new WordAttrib("spec_id",6));
        attribMap.put("tran", new WordAttrib("tran_id",1));
        attribMap.put("intr", new WordAttrib("tran_id",1));
        attribMap.put("1per", new WordAttrib("person_id",1));
        attribMap.put("2per", new WordAttrib("person_id",2));
        attribMap.put("3per", new WordAttrib("person_id",3));
        attribMap.put("pres", new WordAttrib("tense_id",2));
        attribMap.put("past", new WordAttrib("tense_id",1));
        attribMap.put("futr", new WordAttrib("tense_id",3));
        attribMap.put("indc", new WordAttrib("incl_id",1));
        attribMap.put("impr", new WordAttrib("incl_id",2));
        /*attribMap.put("Infr", new WordAttrib("spec_id",7));
        attribMap.put("Slng", new WordAttrib("spec_id",8));
        attribMap.put("Arch", new WordAttrib("spec_id",9));
        attribMap.put("Litr", new WordAttrib("spec_id",10));*/
        attribMap.put("Ques", new WordAttrib("spec_id",13));
        attribMap.put("Dmns", new WordAttrib("spec_id",14));
        attribMap.put("Prnt", new WordAttrib("spec_id",12));
        attribMap.put("Coun", new WordAttrib("spec_id",15));
    }

    public static List<String> stopWords = new ArrayList<String>();
    static{
        stopWords.add("Abbr"); //аббревиатуры
        stopWords.add("Surn"); //фамилии
        stopWords.add("Patr"); //отчества
        stopWords.add("Erro"); //ошибочные
        stopWords.add("Infr"); //неформальные
        stopWords.add("Slng"); //слэнговые
        stopWords.add("Arch"); //устаревшие
        stopWords.add("Litr"); //литературные
    }

    public static String getInsertQuery(WordObject wordObject){
        String query = "INSERT INTO words SET word='"+wordObject.getWord()+"', xml_id="+wordObject.getLemmaId()+", ";
        for (String attrib: wordObject.getAttribList()) {
            if (stopWords.contains(attrib))
                return null;
            if (attribMap.containsKey(attrib)){
                query+= ((WordAttrib) attribMap.get(attrib)).getColName()+"="+((WordAttrib) attribMap.get(attrib)).getColValue()+",";
            }
        }
        query = query.substring(0, query.length() - 1)+";";
        return query;
    }

    public AttribMapper() {

    }
}
