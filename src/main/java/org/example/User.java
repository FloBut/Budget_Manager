package org.example;
//////Un utilizator va avea o lista de cheltuieli si un buget maxim.
//////Ca si utilizator in aplicatie, vei avea acces la mai multe functionalitati:

import java.util.*;
import java.util.stream.Collectors;

//////•	Salvarea tuturor cheltuielilor intr-un fisier//ma folosesc de un file writer in care ii voi da calea
//////•	Incarcarea in aplicatie a tuturor cheltuielilor dintr-un fisier
//////•	Setarea unui buget
//////•	Vizualizarea bugetului disponibil
//////•	Adaugarea unei cheltuieli
//////•	Stergerea unei cheltuieli.
public class User {
    private static String userName;
    private static double maximumBudget;
    private static List<Purchase> purchases;

    public User(String userName, double maximumBudget) {
        this.userName = userName;
        this.maximumBudget = maximumBudget;
        purchases = new ArrayList<>();

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getMaximumBudget() {
        return maximumBudget;
    }

    public void setMaximumBudget(double maximumBudget) {
        this.maximumBudget = maximumBudget;
    }

    //////•	Vizualizarea tuturor cheltuielilor
    public static List<Purchase> viewPurchases() {
       return purchases;
    }

    //////•	Vizualizarea cheltuielilor dintr-o anumita categorie
    //primesc ca parametru o categorie si verific categoria din care face parte si afisez
    // cheltuielile din acea categorie
    public static List<Purchase> viewPurchaseByCategory (Category category) {
        List<Purchase> purchasesByCategory = new ArrayList<>();
        for (int i = 0; i < purchases.size(); i++) {
            if (category.equals(purchases.get(i).getCategory())){
                purchasesByCategory.add(purchases.get(i));
            }
        }
        return purchasesByCategory;
    }

    //////•	Vizualizarea cheltuielilor grupate pe categorii,
    //categoria sa fie cheie in mapa si pretul din fiecare categorie sa fie valoarile din acea categorie ca o lista  in mapa
    public Map<Category, List<Purchase>> viewPurchaseForAllCategory(){
        Map<Category, List<Purchase>> purchaseByCategory = purchases.stream()
                .collect(Collectors.groupingBy(purchase -> purchase.getCategory(),Collectors.toList()));
        return purchaseByCategory;

    }
    //fac o metoda in care pe o mapa voi pune categoria si totalul cheltuielilor pe acea categorie si voi returna mapa
    public static  Map<Category, Double> getTotalPriceByCategory() {
        //parcurg lista de cheltuieli si fac pentru fiecare categorie un total
        //ma folosesc de o mapa in care pun in cheie categoria si in valoare pun totalul la final returnez totalul cel mai mare
        //verific daca in mapa exista categoria si daca nu este pun pretul la valoare si categoria la cheie
        //daca exista acea categorie atunci la valoare existenta in mapa adaug acel pret
        //compar fiecare pret total din fiecare categorie din mapa si il aleg pe cel maxim
        Map<Category, Double> totalPriceByCategory = new HashMap<>();
        for (int i = 0; i < purchases.size(); i++) {
            Category category = purchases.get(i).getCategory();
            Double price = purchases.get(i).getPrice();
            if (totalPriceByCategory.containsKey(category)) {
                totalPriceByCategory.put(category, price);
            } else {
                totalPriceByCategory.put(category, totalPriceByCategory.get(category) + price);
            }
        }
        return totalPriceByCategory;
    }
    ////////•	Vizualizarea categoriei in care s -a cheltuit cel mai mult
    public static Category getMaxPurcahse() {
        double maxPrice = 0;
        Category maxPriceCategory = null;
        //apelez metoda getTotalPriceByCategory care imi returneaza o mapa de categorii si preturi
        //din acea mapa voi scoate pretul maxim si categoria pe care o voi returna
        Map<Category, Double> priceByCategory = getTotalPriceByCategory();
        Map.Entry<Category, Double> entry = (Map.Entry<Category, Double>) priceByCategory.entrySet();
            if (entry.getValue() > maxPrice) {
                maxPrice = entry.getValue();
                maxPriceCategory = entry.getKey();
            }
        return maxPriceCategory;
    }
    ////////•	Vizualizarea categoriei in care s -a cheltuit cel mai mai putin
    public static Category getMinPurcahse() {
        double minPrice = 1000000;
        Category minPriceCategory = null;
        //apelez metoda getPriceByCategory care imi returneaza o mapa de categorii si preturi
        //din acea mapa voi scoate pretul maxim si categoria pe care o voi returna
        Map<Category, Double> priceByCategory = getTotalPriceByCategory();
        Map.Entry<Category,Double> entry = (Map.Entry<Category, Double>) priceByCategory.entrySet();
            if(entry.getValue() < minPrice) {
                minPrice = entry.getValue();
                minPriceCategory = entry.getKey();
            }
        return minPriceCategory;
    }
    //////•	Vizualizarea tuturor cheltuielilor dintr-un interval de pret
    //primesc ca parametru 2 preturi si in acest interval afisez cheltuielile din acel interval
    //ma folsesc de mapa returnata la metoda getPriceByCategory si compar valorile din mapa cu cele 2 valori de
    // cheltuieli primite ca parametru
    public static List<Double> getPurchaseBetweenTwoValue (double firstValue, double secondValue) {
        //mai intai ar trebui sa verific care din cele 2 valori este cea mai mica si pe aceeea sa o iau
        // ca valoarea minima si celalat paramertru ca valaore maxima
        double minValue = 0, maxValue = 0;
        if (firstValue < secondValue) {
            minValue = firstValue;
            maxValue = secondValue;
        } else {
            minValue = secondValue;
            maxValue = firstValue;
        }
        //ma foloesc de mapa in care am stocat cheltuielile pe categorii si voi lua pe rand fiecare valoare si vad
        //daca se incadreaza in intervalul dintre cei doi parametri si voi salva valorile intr o lista pe
        // care o voi returna
        List<Double> purchaseBetweenTwoValue = new ArrayList<>();
        Map<Category, Double> newMap = getTotalPriceByCategory();
        Map.Entry<Category, Double> entry = (Map.Entry<Category, Double>) newMap.entrySet();
            if (entry.getValue() >= minValue && entry.getValue() <= maxValue) {
                purchaseBetweenTwoValue.add(entry.getValue());
            }
        return purchaseBetweenTwoValue;
    }

    //////•	Sortarea tuturor cheltuielilor dupa pret
    // iau lista de cheltuieli si o sortez crescator dupa pret
    public static List<Purchase> ascendingSortingByPrice() {
        purchases.sort((purchase1, purchase2) -> Double.compare(purchase1.getPrice(), purchase2.getPrice()));
        return purchases;
    }

    //////•	Sortarea cheltuielilor dintr-o anumita categorie dupa pret
    //intr - o metoda primesc ca parametru o categorie si sortez crescator preturile din acea categorie si returnez o lista
    public static List<Double> getAscendingPriceByCategory(Category category) {
        List<Double> ascendingPriceByCategory = new ArrayList<>();
        //parcurg lista  de cheltuieli si
            // daca categoria se afla in mapa  atunci preturile de la acea categorie le pun intr o lista si le sortez
            //altfel arunc o exceptie in care spun ca acea categorie nu se afla in mapa
        for (int i = 0; i < purchases.size(); i++) {
            if (category.equals(purchases.get(i).getCategory())) {
                ascendingPriceByCategory.add(purchases.get(i).getPrice());
            }
        }
        Collections.sort(ascendingPriceByCategory);
        return ascendingPriceByCategory;
    }

    //////•	Salvarea tuturor cheltuielilor intr-un fisier
//////•	Incarcarea in aplicatie a tuturor cheltuielilor dintr-un fisier

    //////•	Setarea unui buget
    //il setez atunci cand setez utilizatorul si lista lui de cheltuieli

    //////•	Vizualizarea bugetului disponibil
    //fac totalul cheltuielilor din lista de cheltuieli si apoi scad din bugetul total cheltuielile
        // apelez metoda in care am toate categoriile cu pretul total si fac un total pe cheltuieli
        //din bugetul total scad chltuielile

    public static Double getDisponibleBudget () {
        Map<Category, Double> priceByCategory = getTotalPriceByCategory();
        //parcurg mapa si adun valorile
        Double totalPurchasesPrice = 0.0;
        for (Double totalPrice: priceByCategory.values()) {
            totalPurchasesPrice +=totalPrice;
        }
        return (maximumBudget - totalPurchasesPrice);
    }
//////•	Adaugarea unei cheltuieli
    public static List<Purchase> addNewPurchase (Purchase purchase) {
        purchases.add(purchase);
        return purchases;
    }

//////•	Stergerea unei cheltuieli.
    public static List<Purchase> removePurchase (Purchase purchase) {
        //parcurg lista de cheltuieli si daca gasesc o cheltuiala o sterg din lista
        for (int i = 0; i < purchases.size() ; i++) {
            if (purchases.contains(purchase)) {
                purchases.remove(purchase);
            }
        }
        return purchases;
    }

}
