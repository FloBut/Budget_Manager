package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

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
      User user = new User("John", 2000);
        user.addNewPurchase(new Purchase("Legume", 25, Category.FOOD));
        user.addNewPurchase(new Purchase("tricou", 50, Category.CLOTHES));
        user.addNewPurchase(new Purchase("Fructe", 30, Category.FOOD));
        user.addNewPurchase(new Purchase("tenesi", 80, Category.CLOTHES));
        user.addNewPurchase(new Purchase("Factura1", 100, Category.UTILITIES));
        user.addNewPurchase(new Purchase("Factura2", 70, Category.UTILITIES));
      //definesc o lista in care voi adauga aceste cheltuieli
        List <Purchase> purchases = new ArrayList<>();

      //Vizualizarea tututor cheltuielilor
        List<Purchase> allPurcahses = user.viewPurchases();
        for (Purchase purchase:allPurcahses) {
            System.out.println(purchase);
        }
        System.out.println("------------------------");
      //Vizualizarea cheltuielilor dintr-o anumita categorie
      System.out.println(user.viewPurchaseByCategory(Category.FOOD));
      System.out.println("----------------------------");

      //Vizualizarea totalului cheltuielilor grupate pe categorii
      Map<Category, Double> totalPriceByCategory = user.getTotalPriceByCategory();
      for (Map.Entry<Category, Double> entry : totalPriceByCategory.entrySet()) {
        System.out.println(entry.getKey() + ":" + entry.getValue());
        System.out.println("----------------------------");
      }
      //Vizualizarea categoriei in care s-a cheltuit cel mai mult
      Category maxPriceByCategory = user.getMaxPurchase();
      System.out.println("Category with maximum purchase is " +  maxPriceByCategory);
      System.out.println("----------------------------");

      // Vizualizarea categoriei in care s-a cheltuit cel mai putin
      Category minPriceByCategory = user.getMinPurchase();
      System.out.println("Category with minimum purchase is " + minPriceByCategory);
      System.out.println("----------------------------");

      //Vizualizarea tuturor cheltuielilor dintr-un interval de pret
     double minPrice = 50;
     double maxPrice = 150;
     List<Purchase> purchasesInRange = user.getPurchaseBetweenTwoValue(minPrice, maxPrice);
     for (Purchase purchase:purchasesInRange) {
        System.out.println(purchase);
     }
      System.out.println("----------------------------");

      //Sortarea cheltuielilor dintr-o anumita categorie dupa pret
      List<Purchase> ascendingPriceByCategory = user.ascendingSortingByPrice();
     for (Purchase purchase:ascendingPriceByCategory) {
       System.out.println(purchase);
     }
      System.out.println("----------------------------");

      //Vizualizarea bugetului disponibil
      System.out.println(user.getDisponibleBudget());
      System.out.println("----------------------------");

      //Stergerea unei cheltuieli
      System.out.println(user.removePurchase("tricou"));
      System.out.println("----------------------------");

    }
}