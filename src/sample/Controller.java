package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements javafx.fxml.Initializable {

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private HBox HBoxMenu;

    @FXML
    private Menu menuNovaPartida;

    @FXML
    private MenuItem mItemPrincipiant;

    @FXML
    private MenuItem mItemNormal;

    @FXML
    private MenuItem mItemDificil;

    @FXML
    private MenuItem mItemInfern;

    @FXML
    private MenuItem mItemMalson;

    @FXML
    private Label lblSegons;

    @FXML
    private Pane PanellJoc;

    @FXML
    private Circle puntVermell1;

    @FXML
    private Circle puntVermell2;

    @FXML
    private Circle puntVermell3;

    @FXML
    private Circle puntVermell4;

    @FXML
    private Circle puntVermell5;

    @FXML
    private Circle puntVermell6;

    @FXML
    private Circle puntVermell7;

    @FXML
    private Circle puntVermell8;

    @FXML
    private Circle puntVermell9;

    @FXML
    private Circle puntVermell10;

    @FXML
    private Circle puntVermell11;

    @FXML
    private Circle puntVermell12;

    @FXML
    private Circle puntVermell13;

    @FXML
    private Circle puntVermell14;

    @FXML
    private Circle puntVermell15;

    @FXML
    private Circle puntVermell16;

    @FXML
    private Circle puntVermell17;

    @FXML
    private Circle puntVermell18;

    @FXML
    private Circle puntVermell19;

    @FXML
    private Circle puntVermell20;

    @FXML
    private Label puntuacio;

    @FXML
    private Button btnGuardaRecord;

    @FXML
    private MenuItem menuCredits;

    @FXML
    private TableView<Integer> taulaRanquing = new TableView<>();

    @FXML
    private TableColumn<Integer, String> columnaNom = new TableColumn<>();

    @FXML
    private TableColumn<Integer, String> columnaPunts = new TableColumn<>();

    @FXML
    private TableColumn<Integer, String> columnaNivell = new TableColumn<>();

    @FXML
    private Tab tabRanking;

    private String menuNom;

    public Joc partida = new Joc();


    @FXML
    void iniciaJoc(ActionEvent event) {

        /* Desactiva el menú de nova partida un cop començada */
        menuNovaPartida.setDisable(true);

        Circle[] puntssArray = {puntVermell1, puntVermell2, puntVermell3, puntVermell4, puntVermell5, puntVermell6, puntVermell7, puntVermell8, puntVermell9, puntVermell10, puntVermell11, puntVermell12, puntVermell13, puntVermell14, puntVermell15, puntVermell16, puntVermell17, puntVermell18, puntVermell19, puntVermell20};

        /*
        Comprovem quin menú s'ha clicat i li afegim la dificultat en milisegons.
         */
        int dificultat = 0;
        MenuItem menuEscollit = (MenuItem) event.getSource();
        menuNom = menuEscollit.getText();

        switch (menuEscollit.getId()){
            case "mItemPrincipiant":
                System.out.println("Nivell seleccionat: Principiant");
                dificultat = 1500;
                break;
            case "mItemNormal":
                System.out.println("Nivell seleccionat: Normal");
                dificultat = 1250;
                break;
            case "mItemDificil":
                System.out.println("Nivell seleccionat: Difícil");
                dificultat = 750;
                break;
            case "mItemInfern":
                System.out.println("Nivell seleccionat: Infern");
                dificultat = 500;
                break;
            case "mItemMalson":
                System.out.println("Nivell seleccionat: Malson");
                dificultat = 250;
                break;
        }


        partida.comptador(lblSegons, btnGuardaRecord, puntssArray, menuNovaPartida);
        partida.dificultat(puntssArray, PanellJoc, btnGuardaRecord, dificultat, menuNovaPartida);

    }


    @FXML
    void desapareixerPunt1(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell1.setVisible(false);

    }

    @FXML
    void desapareixerPunt2(MouseEvent event) {

        partida.comptarPunts(puntuacio);
        puntVermell2.setVisible(false);

    }

    @FXML
    void desapareixerPunt3(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell3.setVisible(false);

    }

    @FXML
    void desapareixerPunt4(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell4.setVisible(false);

    }

    @FXML
    void desapareixerPunt5(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell5.setVisible(false);

    }

    @FXML
    void desapareixerPunt6(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell6.setVisible(false);

    }

    @FXML
    void desapareixerPunt7(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell7.setVisible(false);

    }

    @FXML
    void desapareixerPunt8(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell8.setVisible(false);

    }

    @FXML
    void desapareixerPunt9(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell9.setVisible(false);

    }

    @FXML
    void desapareixerPunt10(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell10.setVisible(false);

    }

    @FXML
    void desapareixerPunt11(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell11.setVisible(false);

    }

    @FXML
    void desapareixerPunt12(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell12.setVisible(false);

    }

    @FXML
    void desapareixerPunt13(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell13.setVisible(false);

    }

    @FXML
    void desapareixerPunt14(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell14.setVisible(false);

    }

    @FXML
    void desapareixerPunt15(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell15.setVisible(false);

    }

    @FXML
    void desapareixerPunt16(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell16.setVisible(false);

    }

    @FXML
    void desapareixerPunt17(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell17.setVisible(false);

    }

    @FXML
    void desapareixerPunt18(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell18.setVisible(false);

    }

    @FXML
    void desapareixerPunt19(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell19.setVisible(false);

    }

    @FXML
    void desapareixerPunt20(MouseEvent event) {
        partida.comptarPunts(puntuacio);
        puntVermell20.setVisible(false);

    }

    @FXML
    void guardaRecord(MouseEvent event) {
        /*
        Comprovem quin menú s'ha clicat
         */
        partida.mostraAlerta(menuNom);
        btnGuardaRecord.setVisible(false);
        Ranquing.emplenaRanquing(taulaRanquing, columnaNom, columnaPunts, columnaNivell);
    }

    @FXML
    void mostraCredits(ActionEvent event) {

        Notifications.create()
                .title("Joc de la Baricel·la")
                .text("\nDesenvolupat per David Torralbo mitjançant JavaFX.\n\u00a9 2017.")
                .position(Pos.BOTTOM_CENTER)
                .hideAfter(Duration.seconds(5))
                .darkStyle()
                .show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnaNom.prefWidthProperty().bind(taulaRanquing.widthProperty().divide(3));
        columnaPunts.prefWidthProperty().bind(taulaRanquing.widthProperty().divide(3));
        columnaNivell.prefWidthProperty().bind(taulaRanquing.widthProperty().divide(3));

        Ranquing.emplenaRanquing(taulaRanquing, columnaNom, columnaPunts, columnaNivell);
    }

}