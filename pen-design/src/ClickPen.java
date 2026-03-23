public class ClickPen implements Pen {
    private Refill refill;
    private boolean open;

    public ClickPen(Refill refill) {
        this.refill = refill;
        this.open = false;
    }

    @Override
    public void start() {
        if (open) {
            System.out.println("[ClickPen] Already clicked open.");
            return;
        }
        open = true;
        System.out.println("[ClickPen] Clicked open. Ready to write with " + refill.getColor() + " ink.");
    }

    @Override
    public void write(String text) {
        if (!open) {
            System.out.println("[ClickPen] Pen is closed. Call start() first.");
            return;
        }
        if (refill.isEmpty()) {
            System.out.println("[ClickPen] Ink is empty. Cannot write.");
            return;
        }

        int inkNeeded = text.length();
        int consumed = refill.consumeInk(inkNeeded);
        String written = text.substring(0, consumed);
        System.out.println("[ClickPen] Wrote: \"" + written + "\" | " + refill);

        if (consumed < inkNeeded) {
            System.out.println("[ClickPen] Ran out of ink mid-write!");
        }
    }

    @Override
    public void close() {
        if (!open) {
            System.out.println("[ClickPen] Already closed.");
            return;
        }
        open = false;
        System.out.println("[ClickPen] Clicked closed.");
    }

    @Override
    public void refill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println("[ClickPen] Refill swapped. Now using: " + newRefill);
    }

    @Override
    public boolean isOpen() { return open; }
}
