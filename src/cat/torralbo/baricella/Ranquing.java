package cat.torralbo.baricella;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Creat per davidtorralbo el 17/5/17.
 */
public class Ranquing {

    /*
    Mètode booleà que retornarà true o false depenent de si ha pogut emplenar el rànquing mitjançant l'xml.
     */
    public static boolean emplenaRanquing(TableView<Integer> taulaRanquing, TableColumn<Integer, String> columnaNom, TableColumn<Integer, String> columnaPunts, TableColumn<Integer, String> columnaNivell){
        boolean emplenat = true;

        try {

            taulaRanquing.getItems().clear();

            List<String> intValues = retornaNomsXML()[0];
            List<String> stringValues = retornaNomsXML()[1];
            List<String> recordsNivell = retornaNomsXML()[2];

            for (int i = 0; i < intValues.size() && i < stringValues.size(); i++) {
                taulaRanquing.getItems().add(i);
            }

            columnaNom.setCellValueFactory((TableColumn.CellDataFeatures<Integer, String> cellData) -> {
                Integer rowIndex = cellData.getValue();
                return new ReadOnlyStringWrapper(intValues.get(rowIndex));
            });

            columnaPunts.setCellValueFactory((TableColumn.CellDataFeatures<Integer, String> cellData) -> {
                Integer rowIndex = cellData.getValue();
                return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
            });

            columnaNivell.setCellValueFactory((TableColumn.CellDataFeatures<Integer, String> cellData) -> {
                Integer rowIndex = cellData.getValue();
                return new ReadOnlyStringWrapper(recordsNivell.get(rowIndex));
            });

            taulaRanquing.getColumns().clear();
            taulaRanquing.getColumns().add(columnaNom);
            taulaRanquing.getColumns().add(columnaPunts);
            taulaRanquing.getColumns().add(columnaNivell);
        } catch (Exception e){
            emplenat = false;
        }

        return emplenat;
    }

    public static List[] retornaNomsXML(){

        List[] list1 = new List[3];
        String[] nom = null;
        String[] puntuacio = null;
        String[] nivell = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File("src/records.xml"));
            NodeList nodeList = document.getElementsByTagName("usuari");

            nom = new String[nodeList.getLength()];
            puntuacio = new String[nodeList.getLength()];
            nivell = new String[nodeList.getLength()];

            for (int i = 0, size = nodeList.getLength(); i < size; i++) {
                nom[i] = nodeList.item(i).getAttributes().getNamedItem("nom").getNodeValue().toString();
                puntuacio[i] = nodeList.item(i).getAttributes().getNamedItem("puntuacio").getNodeValue().toString();
                nivell[i] = nodeList.item(i).getAttributes().getNamedItem("nivell").getNodeValue().toString();
            }
        }catch (Exception e){
            System.out.println("error");
        }


        list1[0] = Arrays.asList(nom);
        list1[1] = Arrays.asList(puntuacio);
        list1[2] = Arrays.asList(nivell);

        return list1;

    }


}
