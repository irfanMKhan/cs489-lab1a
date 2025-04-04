package com.secured;

import com.secured.model.Product;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new Product(3128874119L, "Banana", LocalDate.of(2023, 1, 24), 124, 0.55),
                new Product(2927458265L, "Apple", LocalDate.of(2022, 12, 9), 18, 1.09),
                new Product(9189927460L, "Carrot", LocalDate.of(2023, 3, 31), 89, 2.99)
        };

        printProducts(products);
    }

    public static void printProducts(Product[] products) {
        List<Product> sortedProducts = Arrays.stream(products)
                .sorted(Product.BY_UNIT_PRICE_DESC)
                .collect(Collectors.toList());

        System.out.println("JSON Format:");
        System.out.println("[");
        for (int i = 0; i < sortedProducts.size(); i++) {
            Product p = sortedProducts.get(i);
            System.out.printf("  { \"productId\": %d, \"name\": \"%s\", \"dateSupplied\": \"%s\", \"quantityInStock\": %d, \"unitPrice\": %.2f }%s\n",
                    p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice(),
                    (i < sortedProducts.size() - 1) ? "," : "");
        }
        System.out.println("]\n");

        System.out.println("XML Format:");
        System.out.println("<products>");
        for (Product p : sortedProducts) {
            System.out.printf("  <product><id>%d</id><name>%s</name><dateSupplied>%s</dateSupplied><quantity>%d</quantity><price>%.2f</price></product>%n",
                    p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice());
        }
        System.out.println("</products>\n");

        System.out.println("CSV Format:");
        System.out.println("ProductId,Name,DateSupplied,QuantityInStock,UnitPrice");
        for (Product p : sortedProducts) {
            System.out.printf("%d,%s,%s,%d,%.2f%n",
                    p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice());
        }
    }
}