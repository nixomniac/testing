To use this app the running MySQL instance is required. 

DB schema should be created as well.


Default DB settings are configurable under application-srv.yml:

User: weather

Pass: weather123

Schema: weather



Application accepting REST request in this format:
http://localhost:8080/api/weather/ip/197.93.12.72

IP path parameter is required.



Application can be tested through built-in swagger or any other REST-compatible app.

Swagger can be accessed here: 
http://localhost:8080/swagger-ui.html#/weather-provider-controller/getWeatherByIP

