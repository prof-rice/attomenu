package pizza;

import attomenu.Menu;
import attomenu.MenuItem;

import java.util.ArrayList;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class PizzaPearl {
    public static void main(String[] args) {
        // Menu.EXIT_CHAR = '!';   // Change the exit character to ! (NEVER set to ' ')
        // Menu.SHOW_INT = false;  // Don't show int keys unless no char is available
        // Menu.SHOW_CHAR = false; // Never show char keys even when available
        new PizzaPearl();       // Instance and run a Pizza Pearl franchise
    }
    public PizzaPearl() {
        // pizzaToppings.run() shows the interactive menu.
        // When a selection is made, the lambda (code after () -> ) is run
        String pizzaToppingsHelp = "Select as many toppings as you like!";
        pizzaToppings = new Menu("Pizza Toppings", toppings, pizzaToppingsHelp,
            new MenuItem("Remove a Topping", () -> removeToppingFromPizza(), 'r'),
            new MenuItem("Extra Cheese", () -> toppings.add("Extra Cheese"), 'c'),
            new MenuItem("Pepperoni", () -> toppings.add("Pepperoni"), 'p'),
            new MenuItem("Sausage", () -> toppings.add("Sausage"), 's', "Italian, of course!"),
            new MenuItem("Onions", () -> toppings.add("Onions"), 'o')
        );
        pizzaPearlMenu = new Menu("Place an Order", order,
            new MenuItem("Remove an Item", () -> removeItemFromOrder(), 'r'),
            new MenuItem("Pizza!", () -> orderPizza(), 'p'),
            new MenuItem("Salad", () -> orderSalad(), 's'),
            new MenuItem("Soda", () -> orderSoda(), 'd')
        );
        String menuHelp = "Welcome to Pizza Pearl, a true discovery in fine Italian dining!";
        menu = new Menu("==============================\n\nPizza Pearl Main Menu", orders, menuHelp,
            new MenuItem("List orders", () -> System.out.println(orders), 'l'),
            new MenuItem("Save orders", () -> saveOrders(), 's', "Write the orders to a file"),
            new MenuItem("Place an order", () -> placeAnOrder(), 'p', "Select your favorite foods!")
        );
        menu.run();
    }
    private void saveOrders() {
        File file = Menu.selectFile("Select Save File", null, null);
        if(file == null) return;
        System.out.println("Writing to: " + file);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(orders.toString());
        } catch(Exception e) {
            System.err.println("Unable to write orders: " + e);
        }
    }
    private void placeAnOrder() {
        order.clear();                // Start a new order
        pizzaPearlMenu.run();         // Interact with the user to specify the order
        if(order.isEmpty()) return;   // If nothing was ordered, we're done here
        orders.add(orderToString());  // Accept the order
        ++orderNumber;                // Ready for the next order number!
    }
    private void orderPizza() {
        toppings.clear();     // Start a new toppings list
        pizzaToppings.run();  // Interact with the user to build the toppings list
        order.add(toppings.isEmpty() ? "Cheese Pizza" : "Pizza with " + toppings);
    }
    private void removeToppingFromPizza() {
        int item = Menu.select("Select topping to remove", toppings);
        if(item != -1) toppings.remove(item);
    }
    private void orderSalad() {
        order.add("Salad");
    }
    private void orderSoda() {
        order.add("Soda");
    }
    private void removeItemFromOrder() {
        int item = Menu.select("Select item to remove", order);
        if(item != -1) order.remove(item);
    }
    // More user friendly formatting for an order
    public String orderToString() {
        StringBuilder sb = new StringBuilder("\nORDER #" + orderNumber + "\n");
        for(String s : order) sb.append(s + '\n');
        sb.append("\n");
        return sb.toString();
    }

    // These are the menu objects and the List each uses to collect data
    private Menu menu; // Main menu also shows list of orders
    private ArrayList<String> orders = new ArrayList<>();

    private Menu pizzaPearlMenu; // Order menu also shows contents of order in progress
    private ArrayList<String> order = new ArrayList<>();

    private Menu pizzaToppings; // Toppings menu shows list of pizza toppings in progress
    private ArrayList<String> toppings = new ArrayList<>();

    private static int orderNumber = 1; // Next order number
}
