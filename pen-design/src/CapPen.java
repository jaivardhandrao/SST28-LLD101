public class CapPen implements Pen {
    private Refill refill;
    private boolean open;

    public CapPen(Refill refill) {
        this.refill = refill;
        this.open = false;
    }

    @Override
    public void start() {
        if (open) {
            System.out.println("[CapPen] Already uncapped.");
            return;
        }
        open = true;
        System.out.println("[CapPen] Cap removed. Ready to write with " + refill.getColor() + " ink.");
    }

    @Override
    public void write(String text) {
        if (!open) {
            System.out.println("[CapPen] Pen is capped. Call start() first.");
            return;
        }
        if (refill.isEmpty()) {
            System.out.println("[CapPen] Ink is empty. Cannot write.");
            return;
        }

        // cap pens (fountain-style) use a bit more ink per character
        int inkNeeded = text.length() * 2;
        int consumed = refill.consumeInk(inkNeeded);
        int charsWritten = consumed / 2;

        String written = text.substring(0, Math.min(charsWritten, text.length()));
        System.out.println("[CapPen] Wrote: \"" + written + "\" | " + refill);

        if (charsWritten < text.length()) {
            System.out.println("[CapPen] Ran out of ink mid-write!");
        }
    }

    @Override
    public void close() {
        if (!open) {
            System.out.println("[CapPen] Already capped.");
            return;
        }
        open = false;
        System.out.println("[CapPen] Cap on.");
    }

    @Override
    public void refill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println("[CapPen] Refill swapped. Now using: " + newRefill);
    }

    @Override
    public boolean isOpen() { return open; }
}
