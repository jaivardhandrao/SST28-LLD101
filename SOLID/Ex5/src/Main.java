public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        // All exporters now behave consistently - no try/catch needed
        System.out.println("PDF: OK bytes=" + pdf.export(req).bytes.length);
        System.out.println("CSV: OK bytes=" + csv.export(req).bytes.length);
        System.out.println("JSON: OK bytes=" + json.export(req).bytes.length);
    }
}
