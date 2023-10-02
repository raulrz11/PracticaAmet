package controllers;

import models.Dia29;
import models.Dia30;
import models.Dia31;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Stream;


public class AmetController {
    private static AmetController instance;
    public static AmetController getInstance(){
        if(instance == null){
            instance = new AmetController();
        }
        return instance;
    }
    private AmetController(){
        System.out.println("Lectura del primer archivo");
        leerCsv29("data/Aemet20171029.csv");
        System.out.println();
        System.out.println("Lectura del segundo archivo");
        leerCsv30("data/Aemet20171030.csv");
        System.out.println();
        System.out.println("Lectura del tercer archivo");
        leerCsv31("data/Aemet20171031.csv");
        System.out.println();
    }
    public void leerCsv29(String ruta){
        try(BufferedReader bw = new BufferedReader(new FileReader(ruta))){
            Stream<Dia29> amet = bw.lines()
                    .map(linea -> linea.split(";"))
                    .map(valores -> new Dia29(valores[0], valores[1], Double.parseDouble(valores[2]), Timestamp.valueOf(valores[3]), Double.parseDouble(valores[4]), Timestamp.valueOf(valores[5]), Double.parseDouble(valores[6])));

            amet.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void leerCsv30(String ruta){
        try(BufferedReader bw = new BufferedReader(new FileReader(ruta))){
            Stream<Dia30> amet = bw.lines()
                    .map(linea -> linea.split(";"))
                    .map(valores -> new Dia30(valores[0], valores[1], Double.parseDouble(valores[2]),valores[3], Double.parseDouble(valores[4]), valores[5], Double.parseDouble(valores[6])));

            amet.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void leerCsv31(String ruta){
        try(BufferedReader bw = new BufferedReader(new FileReader(ruta))){
            Stream<Dia31> amet = bw.lines()
                    .map(linea -> linea.split(";"))
                    .map(valores -> new Dia31(valores[0], valores[1], Double.parseDouble(valores[2]),valores[3], Double.parseDouble(valores[4]), valores[5], Double.parseDouble(valores[6])));

            amet.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
