package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Creat per davidtorralbo el 11/5/17.
 */
public class Joc {

    private static int iterate, figuresSortints, punts, figuresRestantsEnPantalla, segons;
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static Timeline comptadorSegons;
    private static Timer figuresPantalla;


    /*
            Donem un valor per defecte a les variables.
             */
    public Joc() {
        iterate = 1;
        figuresSortints = 1;
        punts = 0;
        figuresRestantsEnPantalla = 0;
        segons = 60;
    }

    private static Timer getFiguresPantalla() {
        return figuresPantalla;
    }

    private static void setFiguresPantalla(Timer figuresPantalla) {
        Joc.figuresPantalla = figuresPantalla;
    }

    private static int getIterate() {
        return iterate;
    }

    private static void setIterate(int iterate) {
        Joc.iterate = iterate;
    }

    private static int getFiguresSortints() {
        return figuresSortints;
    }

    private static void setFiguresSortints(int figuresSortints) {
        Joc.figuresSortints = figuresSortints;
    }

    private static int getPunts() {
        return punts;
    }

    private static void setPunts(int punts) {
        Joc.punts = punts;
    }

    private static int getFiguresRestantsEnPantalla() {
        return figuresRestantsEnPantalla;
    }

    private static void setFiguresRestantsEnPantalla(int figuresRestantsEnPantalla) {
        Joc.figuresRestantsEnPantalla = figuresRestantsEnPantalla;
    }

    private static int getSegons() {
        return segons;
    }

    private static void setSegons(int segons) {
        Joc.segons = segons;
    }

    private static void setScheduler(ScheduledExecutorService scheduler) {
        Joc.scheduler = scheduler;
    }

    private static Timeline getComptadorSegons() {
        return comptadorSegons;
    }

    private static void setComptadorSegons(Timeline comptadorSegons) {
        Joc.comptadorSegons = comptadorSegons;
    }

    /*
              Comptador de temps.
                 */
    public static void comptador(Label lblSegons, Button btnGuardar, Circle puntsArray[]) {

        setComptadorSegons(new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            comptarSegons(lblSegons);
        })));
        getComptadorSegons().setCycleCount(61);
        getComptadorSegons().play();
        getComptadorSegons().setOnFinished((ActionEvent event) -> {
            finalitzarJoc(btnGuardar, puntsArray);
        });

    }


    /*
        Nivells de dificultat.
     */
    public static void dificultat(Circle punts[], Pane PanellJoc, Button btnGuardar, int dificultat) {

        /* reinicialitza el joc */
        Joc joc = new Joc();

        btnGuardar.setVisible(false);

        setFiguresPantalla(new Timer());
        getFiguresPantalla().schedule(

                new TimerTask() {
                    @Override
                    public void run() {
                        apareixerFigures(punts, PanellJoc, btnGuardar);

                    }
                },
                1000,
                //afegeix els milisegons en que es repetirà el procés
                dificultat
        );








    }

    /*
        Fa aparèixer figures a la pantalla aleatòriament
     */
    public static void apareixerFigures(Circle figura[], Pane PanellJoc, Button btnGuardar) {

        //Fa visible la figura corresponent a l'array.
        figura[iterate].setVisible(true);

        //Crea un double random en el que el nombre màxim serà l'altura i l'amplada corresponent al panell de joc.

        Random random = new Random();

        /* Generem la X i la Y on la coordenada mínima serà el radi dels cercles, i la màxima serà la mida de la pantalla del joc menys el radi multiplicat per el nombre aleatori */
        double randomX = figura[0].getRadius() + ((PanellJoc.getLayoutBounds().getMaxX() -figura[0].getRadius()) - figura[0].getRadius()) * random.nextDouble();
        double randomY = figura[0].getRadius() + ((PanellJoc.getLayoutBounds().getMaxY() - figura[0].getRadius()) - figura[0].getRadius()) * random.nextDouble();

        /*double randomX = (PanellJoc.getLayoutBounds().getMaxX()) * r.nextDouble();
        double randomY = (PanellJoc.getLayoutBounds().getMaxY()) * r.nextDouble();*/

        //System.out.println("X: "+randomX + " Y:" + randomY);

        //Mou la figura a un lloc aleatori del panell de joc gràcies a les variables random que hem creat.
        figura[iterate].setLayoutX(randomX);
        figura[iterate].setLayoutY(randomY);

        //Comptem les figures restant que hi ha en pantalla restant de totes les figures que han sortit, els punts aconseguits.
        setFiguresRestantsEnPantalla(getFiguresSortints()-getPunts());
        setIterate(getIterate()+1);
        setFiguresSortints(getFiguresSortints()+1);

        //Si les figures que hi ha en pantalla son iguals o superiors a 20, finalitzem el joc i parem el procés.
        if (getFiguresRestantsEnPantalla() >= 20) {
            finalitzarJoc(btnGuardar, figura);
            System.out.println("El Joc ha acabat per: 20 figures a la pantalla.");
        }

        //Si ja han sortit 20 figures, tornem a 0 iteracions.
            if (getIterate() >= 20) {
            setIterate(0);
        }

    }

    /*
        Compta la puntuació i la mostra en pantalla
     */
    public static int comptarPunts(Label puntuacio) {
        setPunts(getPunts()+1);

        puntuacio.setText(String.valueOf(punts));
        return punts;
    }

    /*
    Compta els segons
    */
    public static void comptarSegons(Label lblSegons) {

        lblSegons.setText(formataTemps(segons));
        if(getSegons()!=0) {
            setSegons(getSegons()-1);
        } else {
            System.out.println("El Joc ha acabat per fora de temps.");
        }
    }

    public static void finalitzarJoc(Button btnGuardar, Circle figura[]) {

        getFiguresPantalla().cancel();
        getFiguresPantalla().purge();
        getComptadorSegons().stop();


        for (Circle x : figura){
                x.setVisible(false);
            }

        btnGuardar.setVisible(true);

    }

    public static void mostraAlerta(){
         /*diàleg de gravar la partida*/
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Heu aconseguit "+punts+" punts!");
        dialog.setHeaderText("Heu aconseguit "+punts+" punts!");
        dialog.setContentText("Entri el seu nom per a guardar el rècord: ");

        Optional<String> result = dialog.showAndWait();

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> {
            try {
                guardaResultats(name, punts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

         /*reinicialitza el joc*/
        Joc joc = new Joc();
    }

    /*
        Guarda els rècords de l'usuari en un arxiu XML.
     */
    public static void guardaResultats(String nom, int punts) throws IOException {

        try {

            final String RECORDS_XML = "src/records.xml";
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(RECORDS_XML);

            Document doc = (Document) builder.build(xmlFile);
            Element rootNode = doc.getRootElement();

            // Afegirem els rècords dins de USUARIS.
            Element root = rootNode.getChild("USUARIS");

            //Afegirem un nou element usuari
            Element usuari = new Element("usuari");
            usuari.setAttribute("nom", nom);
            usuari.setAttribute("puntuacio", String.valueOf(punts));
            root.addContent(usuari);

            XMLOutputter xmlOutput = new XMLOutputter();

            // Apliquem dins l'arxiu xml.
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(RECORDS_XML));

            // xmlOutput.output(doc, System.out);

            System.out.println("Registre de rècord guardat a XML.");
        } catch (IOException io) {
            io.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }


    }


    /*
    Passa els segons a hores, minuts i segons.
     */
    public static String formataTemps(int segons)
    {
        int segonsInicials = segons;
        int hours = (int) segonsInicials / 3600;
        int remainder = (int) segonsInicials - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        String str = String.format("%d:%02d", mins, secs);

        return str;
    }



}