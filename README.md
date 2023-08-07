Atto Menu
============

Copyright 2023 by George F. Rice

[JavaDoc](https://prof-rice.github.io/attomenu/api/attomenu/package-summary.html) | [Example-Application](pizza/PizzaPearl.java)

Provides Java classes to simplify writing a multi-level menu-driven console application,
using simple menu constructs analogous to javax.swing.JMenu and JMenuItem. Support is also 
provided for easily selecting an element of an array or List, and for navigating the file system 
to select or create a file or directory.

The menus may be defined and run in the constructor, with each menu item specifying its
text and the action to take when selected. The run() method provides the main loop.

Here's a simple partial application to illustrate some attomenu capabilities.

```java
class Demo {
    public Demo() {
        private File file;
        private Double dog;
        private List<Dog> dogs;
        submenu = new Menu("Sub Menu", null,
            new MenuItem("Turn left", () -> turnLeft()),
            new MenuItem("Turn right", () -> turnRight()),
        );
        menu = new Menu("Main Menu", data, 
            new MenuItem("Open data", () -> openData()),
            new MenuItem("Turn", () -> submenu.runOnce()),
            new MenuItem("Select File", () -> file = Menu.selectFile("File?", null, null)),
            new MenuItem("Select from List", () -> dog = Menu.select("Dog?", dogs))
        );
        menu.run();
    }
    // Listener methods, e.g., turnLeft() and openData(), go here
    public static void main(String[] args) {
        new Demo();
    }
}
```

Yes, the name is a nod to picocli. Note that atto is *much* smaller than pico. :)

GitHub: https://github.com/prof-rice/attomenu.git
