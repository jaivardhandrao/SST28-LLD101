public class App {
    public static void main(String[] args) {

        // --- Click Pen Demo ---
        System.out.println("=== Click Pen ===");
        Refill blueRefill = new Refill(InkColor.BLUE, 20);
        Pen clickPen = new ClickPen(blueRefill);

        clickPen.write("test");           // should warn: pen is closed
        clickPen.start();
        clickPen.write("Hello World");    // uses 11 ink
        clickPen.write("More text");      // uses 9 ink, should empty out
        clickPen.write("nothing");        // should warn: ink empty

        // change color by swapping refill
        clickPen.refill(new Refill(InkColor.RED, 20));
        clickPen.write("Now in red!");
        clickPen.close();

        // --- Cap Pen Demo ---
        System.out.println("\n=== Cap Pen ===");
        Refill blackRefill = new Refill(InkColor.BLACK, 30);
        Pen capPen = new CapPen(blackRefill);

        capPen.start();
        capPen.write("Elegant");          // 7 chars x 2 = 14 ink
        capPen.write("Writing");          // 7 chars x 2 = 14 ink
        capPen.write("More text");        // 9 chars x 2 = 18 ink, only 2 left
        capPen.close();

        // swap refill to change color
        System.out.println();
        capPen.refill(new Refill(InkColor.GREEN, 30));
        capPen.start();
        capPen.write("Green ink now");
        capPen.close();
    }
}
