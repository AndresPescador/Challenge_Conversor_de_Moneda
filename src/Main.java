import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            Menu();

            try {
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion >= 1 && opcion <= 8) {
                    double cantidad = 0;
                    boolean entradaValida = false;

                    while (!entradaValida) {
                        try {
                            System.out.print("Ingrese la cantidad a convertir: ");
                            cantidad = scanner.nextDouble();
                            scanner.nextLine();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                            scanner.nextLine();
                        }
                    }

                    String[] codigos = obtenerCodigosConversion(opcion);
                    if (codigos != null) {
                        realizarConversion(codigos[0], codigos[1], cantidad);
                    }

                } else if (opcion != 9) {
                    System.out.println("Opción inválida. Por favor, ingrese un número del menú..");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número del menú.");
                scanner.nextLine();
            }

        } while (opcion != 9);

        System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
        scanner.close();
    }

    public static void Menu() {
        System.out.println("""
        
        Bienvenido al Conversor de Monedas

        1) Dólar => Peso argentino (USD -> ARS)
        2) Peso argentino => Dólar (ARS -> USD)
        3) Dólar => Real brasileño (USD -> BRL)
        4) Dólar => Peso colombiano (USD -> COP)
        5) Dólar => Euro (USD -> EUR)
        6) Euro => Dólar (EUR -> USD)
        7) Yen japonés => Dólar (JPY -> USD)
        8) Dólar => Yen japonés (USD -> JPY)
        9) Salir

        Por favor,elija una opción válida de las mostradas previamente:
        """);
    }

    public static String[] obtenerCodigosConversion(int opcion) {
        return switch (opcion) {
            case 1 -> new String[]{"USD", "ARS"};
            case 2 -> new String[]{"ARS", "USD"};
            case 3 -> new String[]{"USD", "BRL"};
            case 4 -> new String[]{"USD", "COP"};
            case 5 -> new String[]{"USD", "EUR"};
            case 6 -> new String[]{"EUR", "USD"};
            case 7 -> new String[]{"JPY", "USD"};
            case 8 -> new String[]{"USD", "JPY"};
            default -> null;
        };
    }

    private static final String URL = "https://v6.exchangerate-api.com/v6/bf433d33ab002674d8e3aa68";

    public static void realizarConversion(String base, String destino, double cantidad) {
        try {
            String endpoint = URL + "/pair/" + base + "/" + destino + "/" + cantidad;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            if ("success".equals(json.get("result").getAsString())) {
                double tasa = json.get("conversion_rate").getAsDouble();
                double resultado = json.get("conversion_result").getAsDouble();
                String fecha = json.get("time_last_update_utc").getAsString();

                System.out.printf("Tasa de cambio (%s -> %s): %.4f\n", base, destino, tasa);
                System.out.printf("Resultado de la conversión: %.2f %s = %.2f %s\n",
                        cantidad, base, resultado, destino);
                System.out.println("Última actualización de tasas: " + fecha + "\n");
            } else {
                System.out.println("Error en la conversión: " + json.get("error-type").getAsString());
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al realizar la conversión: " + e.getMessage());
        }
    }
}
