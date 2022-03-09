import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {
        private static final String BASE_URL = "http://api.weatherapi.com/v1";
        private static final String API_KEY = ""; // put API key here

        public static void main(String[] args) throws IOException {
            // Print instructions for user
            System.out.println("Hello! Please enter a city to display the current weather there: ");

            //  Get city from user
            String city = "";
            Scanner scanner = new Scanner(System.in);

            while (city.isBlank()) {
                city = scanner.nextLine();
            }

            // Query Api with city
            Weather weather = getCurrentWeather(city);
            System.out.println("Current temperature: " + weather.getCurrent().getTempC());
            System.out.println("Air quality index: " + weather.getCurrent().getAirQuality().getUsEpaIndex());
        }

        public static Weather getCurrentWeather(String city) throws IOException {
            Retrofit retrofit = buildRetrofit();
            WeatherService service = retrofit.create(WeatherService.class);
            Call<Weather> call = service.getWeather(API_KEY, city, "yes");
            Response response = call.execute();
            System.out.println(response.code());
            return (Weather) response.body();
        }

    private static Retrofit buildRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit;
    }
}
