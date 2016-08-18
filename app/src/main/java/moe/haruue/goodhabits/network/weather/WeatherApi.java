package moe.haruue.goodhabits.network.weather;

import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.Weather;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by simonla on 2016/8/17.
 * Have a good day.
 */
public interface WeatherApi {

    // TODO: 2016/8/18 base url
    @GET(Const.WEATHER_API)
    Observable<Weather> getWeather();
}
