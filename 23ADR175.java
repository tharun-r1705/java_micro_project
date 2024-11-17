import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private String name;
    private double pricePerKg;

    public Item(String name, double pricePerKg) {
        this.name = name;
        this.pricePerKg = pricePerKg;
    }

    public String getName() {
        return name;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    @Override
    public String toString() {
        return name + " - ₹" + pricePerKg + "/kg";
    }
}

class CartItem {
    private Item item;
    private double quantityKg;

    public CartItem(Item item, double quantityKg) {
        this.item = item;
        this.quantityKg = quantityKg;
    }

    public String getName() {
        return item.getName();
    }

    public double getTotalPrice() {
        return item.getPricePerKg() * quantityKg;
    }

    @Override
    public String toString() {
        return item.getName() + " - " + quantityKg + " kg - ₹" + getTotalPrice();
    }
}

class Cart {
    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(Item item, double quantityKg) {
        items.add(new CartItem(item, quantityKg));
        System.out.println("\n"+quantityKg + " kg of " + item.getName() + " has been added to the cart.\n");
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Items in your cart:");
        for (CartItem cartItem : items) {
            System.out.println(cartItem);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem cartItem : items) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();

        // Sample items available in the market
        Item[] availableItems = {
            new Item("Tomato", 45.0),
            new Item("Potato", 30.0),
            new Item("Onion", 40.0),
            new Item("Carrot", 50.0),
            new Item("Apple", 150.0),
            new Item("Banana", 60.0)
        };

        // Print the welcome message only once
        System.out.println("\nWelcome to the Vegetable and Grocery Market!");

        boolean running = true;
        while (running) {
            System.out.println("Available items:");
            for (int i = 0; i < availableItems.length; i++) {
                System.out.println((i + 1) + ". " + availableItems[i]);
            }
            System.out.println("0. Checkout\n");
            System.out.print("Select an item number to add to the cart (or 0 to checkout): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                running = false;
                break;
            } else if (choice > 0 && choice <= availableItems.length) {
                System.out.print("Enter quantity in kilograms: ");
                double quantityKg = scanner.nextDouble();
                if (quantityKg > 0) {
                    cart.addItem(availableItems[choice - 1], quantityKg);
                } else {
                    System.out.println("Invalid quantity. Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Display cart items and total
        System.out.println("\nThank you for shopping with us!");
        cart.displayItems();
        System.out.printf("Total amount: ₹%.2f%n", cart.calculateTotal());

        scanner.close();
    }
}
