import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import DB.DBConnection;
import Models.Mapping.AttribMapper;

import Models.WordObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTest {

    public static List<WordObject> wordList = new ArrayList<WordObject>();

    public static DBConnection db=new DBConnection();

    public static void main(String[] args) {
        try {
            Statement stmt;
            ResultSet rs;
            stmt = db.start();
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("src/main/resources/xml/lemmas3.xml");

            // Получаем корневой элемент
           // Node root = document.getDocumentElement();

            System.out.println("List of lemmas:");
            System.out.println();
          //  for (int i = 0; i < lemmas.getLength(); i++) {
                Node lemmata = document.getDocumentElement();
                NodeList lemmaList = lemmata.getChildNodes();
                //System.out.println(lemmata.getNodeName()+" "+lemmaList.getLength());
                for(int i = 0; i < lemmaList.getLength(); i++) {
                        Node lemma = lemmaList.item(i);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (lemma.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(lemma.getNodeName() + ":" + lemma.getAttributes().getNamedItem("id"));
                            Element eLemma = (Element) lemma;
                            NodeList attribList = lemma.getChildNodes();
                            for (int j = 1; j < attribList.getLength(); j++) {
                                WordObject wordObj = new WordObject();
                                wordObj.setLemmaId(Integer.parseInt(eLemma.getAttribute("id")));
                                Node attrib = attribList.item(j);
                                //нода с атрибутами части речи
                                    NodeList partAttribList = attribList.item(0).getChildNodes();
                                    for (int k = 0; k < partAttribList.getLength(); k++) {
                                        Node partAttrib = partAttribList.item(k);
                                        Element ePartAttrib = (Element) partAttrib;
                                        wordObj.addAttrib(ePartAttrib.getAttribute("v"));
                                    }
                                    //нода с атрибутами форм слова
                                if (attrib.getNodeName() == "f") {
                                    Element eForm = (Element) attrib;
                                    wordObj.setWord(eForm.getAttribute("t"));
                                    NodeList formAttribList = attrib.getChildNodes();
                                    for (int l = 0; l < formAttribList.getLength(); l++) {
                                        Node formAttrib = formAttribList.item(l);
                                        Element eFormAttrib = (Element) formAttrib;
                                        wordObj.addAttrib(eFormAttrib.getAttribute("v"));
                                    }

                                        // System.out.println(e.getAttribute("t"));
                                    wordList.add(wordObj);
                                    String query = AttribMapper.getInsertQuery(wordObj);
                                    //System.out.println(AttribMapper.attribMap.entrySet());
                                   // System.out.println(wordObj.toString());
                                   // if (wordObj.getLemmaId()>100) return;
                                    System.out.println(query);
                                    if (wordObj.getLemmaId()>333115) {
                                        try {


                                            // executing SELECT query
                                            if (query != null)
                                                stmt.execute(query);
                                            // rs = stmt.executeQuery("select 1 from dual");


                                        } catch (SQLException sqlEx) {
                                            sqlEx.printStackTrace();
                                        }
                                    }
                                }

                            }
                        }
                }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
