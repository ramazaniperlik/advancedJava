package com.ramazaniperlik;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class Main {

    static String[] kayitlar;
    public static void main(String[] args) {
	var dosyaYolu = "C:/Users/ramaz/Desktop/boun.txt";
//	var uri = URI.create(dosyaYolu);
	Path p = Paths.get(dosyaYolu);
	try{
        kayitlar = Files.readAllLines(p).toArray(new String[]{});
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
	menu();
}

    private static void menu() {
        while (true) {
            System.out.println("Arama Türü:");
            System.out.println("1.Tarih Ara:");
            System.out.println("2.İl Ara:");
            System.out.println("3.Şiddet Ara:");
            System.out.println("4.Çıkış:");
            System.out.println("Seçiminizi Giriniz");
            int secim = new Scanner(System.in).nextInt();
            switch (secim) {
                case 1 -> tarihAra();
                case 2 -> ilAra();
                case 3 -> siddetAra();
                case 4 -> System.exit(0);
            }
        }
    }

    private static void tarihAra(){
        System.out.println("Tarih giriniz. (ex: 2022.05.10)");
        var alinanTarihStringi = new Scanner(System.in).nextLine();
        var alinanTarih = LocalDate.parse(alinanTarihStringi, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        for ( var kayit: kayitlar){
            var il = LocalDate.parse(kayit.split(" ")[8],DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            if(alinanTarih.equals(il)){
                System.out.println(kayit);
            }
        }
    }
    private static void ilAra(){
        System.out.println("İl giriniz.");
        var alinanIl = new Scanner(System.in).nextLine().toLowerCase(Locale.forLanguageTag("tr"));
        for ( var kayit: kayitlar){
            var il = kayit.split(" ")[8].toLowerCase(Locale.forLanguageTag("tr"));
            if(il.contains(alinanIl)){
                System.out.println(kayit);
            }
        }

    }
    private static void siddetAra(){
        System.out.println("Şiddet giriniz.");
        var alinanSiddet = new Scanner(System.in).nextDouble();
        for ( var kayit: kayitlar){
            var siddet = Double.parseDouble(kayit.split(" ")[6]);
            if(alinanSiddet<=siddet){
                System.out.println(kayit);
            }
        }
    }
}
