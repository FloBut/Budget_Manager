package org.example;

import java.util.ArrayList;
import java.util.List;

//21. Budget Manager
//Aplicatia le va permite utilizatorilor sa isi gestioneze bugetul.
//O cheltuiala (purchase) este caracterizata de nume, pret si categorie
//Categoriile pot fi: mancare, distractie, haine, utilitati, altele.
//Un utilizator va avea o lista de cheltuieli si un buget maxim.
//Ca si utilizator in aplicatie, vei avea acces la mai multe functionalitati:
//•	Vizualizarea tututor cheltuielilor
//•	Vizualizarea cheltuielilor dintr-o anumita categorie
//•	Vizualizarea cheltuielilor grupate pe categorii
//•	Vizualizarea categoriei in care a cheltuil cel mai mult/mai putin
//•	Vizualizarea tuturor cheltuielilor dintr-un interval de pret
//•	Sortarea tuturor cheltuielilor dupa pret
//•	Sortarea cheltuielilor dintr-o anumita categorie dupa pret
//•	Salvarea tuturor cheltuielilor intr-un fisier
//•	Incarcarea in aplicatie a tuturor cheltuielilor dintr-un fisier
//•	Setarea unui buget
//•	Vizualizarea bugetului disponibil
//•	Adaugarea unei cheltuieli
//•	Stergerea unei cheltuieli.
public class Main {
    public static void main(String[] args) {
        //imi definesc niste cheltuieli
      Purchase purchase1 = new Purchase("Legume", 25, Category.FOOD);
      Purchase purchase2 = new Purchase("tricou", 50, Category.CLOTHES);
      Purchase purchase3 = new Purchase("Fructe", 30, Category.FOOD);
      Purchase purchase4 = new Purchase("tenesi", 80, Category.CLOTHES);
      Purchase purchase5 = new Purchase("Fructe", 100, Category.UTILITIES);
      Purchase purchase6 = new Purchase("tenesi", 70, Category.UTILITIES);
      //definesc o lista in care voi adauga aceste cheltuieli
        List <Purchase> purchases = new ArrayList<>();
        purchases.add(purchase1);
        purchases.add(purchase2);
        purchases.add(purchase3);
        purchases.add(purchase4);
        // definsex un utilizator
      User user = new User("John", 2000);

      //•	Vizualizarea tututor cheltuielilor
      System.out.println(user.viewPurchases().toString());// aici imi returneaza lista goala
      //•	Vizualizarea cheltuielilor dintr-o anumita categorie
      System.out.println(user.viewPurchaseByCategory(Category.FOOD)); //ar trebui sa imi returneze cheltuielile din categoria mancare
      //Vizualizarea cheltuielilor grupate pe categorii
      System.out.println(user.viewPurchaseForAllCategory());// ar trebui sa returnez toata lista de cheltuilei
      System.out.println(user.getTotalPriceByCategory());
      System.out.println(user.getMaxPurcahse());
      System.out.println(user.getMinPurcahse());
     System.out.println(user.getPurchaseBetweenTwoValue(90,30));
     System.out.println(user.getAscendingPriceByCategory(Category.UTILITIES));
      System.out.println(user.getDisponibleBudget());
    // afisez ->
      // []
      //[]
      //{}
      //{}



    }
}