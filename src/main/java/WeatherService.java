import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface WeatherService {
    @GET("current.json")
    public Call<Weather> getWeather(
            @Query("key") String api_key,
            @Query("q") String city,
            @Query("aqi") String aqi
    );

}
