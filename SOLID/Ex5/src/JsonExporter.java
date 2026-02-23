import java.nio.charset.StandardCharsets;

// Fixed: consistent null handling, same as other exporters
public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : req.body;
        String json = "{\"title\":\"" + escape(title) + "\",\"body\":\"" + escape(body) + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }
}
