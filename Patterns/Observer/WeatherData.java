public class WeatherData {

    // declaration of instance variables

    public void measurementsChanged () {

        float temp = getTemperature();      // get the updated data
        float humidity = getHumidity();
        float pressure = getPressure();

        currentConditionDisplay.update(temp, humidity, pressure);
        statisticDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);
    }

    // other methods WeatherData
}