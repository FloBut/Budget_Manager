package org.example;
////21. Budget Manager
////Aplicatia le va permite utilizatorilor sa isi gestioneze bugetul.
////O cheltuiala (purchase) este caracterizata de nume, pret si categorie
////Categoriile pot fi: mancare, distractie, haine, utilitati, altele.

public class Purchase {
    private String name;
    private double price;
    private Category category;

    public Purchase(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
