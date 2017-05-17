package sample;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Arrays;
import java.util.List;

/**
 * Creat per davidtorralbo el 17/5/17.
 */
public class Ranquing {

    public static boolean emplenaRanquing(TableView<Integer> taulaRanquing, TableColumn<Integer, Number> columnaNom, TableColumn<Integer, String> columnaPunts){
        boolean emplenat = true;

        try {
            List<Integer> intValues = Arrays.asList(1, 2, 3, 4, 5);
            List<String> stringValues = Arrays.asList("One", "Two", "Three", "Four", "Five");

            for (int i = 0; i < intValues.size() && i < stringValues.size(); i++) {
                taulaRanquing.getItems().add(i);
            }

            columnaNom.setCellValueFactory(cellData -> {
                Integer rowIndex = cellData.getValue();
                return new ReadOnlyIntegerWrapper(intValues.get(rowIndex));
            });

            columnaPunts.setCellValueFactory(cellData -> {
                Integer rowIndex = cellData.getValue();
                return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
            });

            taulaRanquing.getColumns().clear();
            taulaRanquing.getColumns().add(columnaNom);
            taulaRanquing.getColumns().add(columnaPunts);
        } catch (Exception e){
            emplenat = false;
        }

        return emplenat;
    }

}
