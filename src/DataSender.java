import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class DataSender {
    public static void sendData(String account, String OSInfo, String GPUInfo, String CPUInfo, String RAMInfo) {
        HttpClient client = HttpClient.newHttpClient();
        String json = String.format("{\n" +
                "    \"account\": \"%s\",\n" +
                "    \"OSInfo\": \"%s\",\n" +
                "    \"GPUInfo\": \"%s\",\n" +
                "    \"CPUInfo\": \"%s\",\n" +
                "    \"RAMInfo\": \"%s\"\n" +
                "}", account, OSInfo, GPUInfo, CPUInfo, RAMInfo);
        System.out.println("json : \n" + json);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/member/updateSpec"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}