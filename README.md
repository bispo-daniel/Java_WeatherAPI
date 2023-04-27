# Java_WeatherAPI
    Java application to query a city weather.

    The API used is from Weather API.
    //Get your key here:

    https://www.weatherapi.com/my/

# About
    This project was developed as a need of mine to learn to developed and handle HTTP requisitions with Java.
    This project used Maven to use some dependencies.
    Swing was used to build a user-friendly GUI.

    3 dependencies has been used:
        com.squareup.okhttp3 -- To get the API's response
        org.json -- To transform the API's Response into a JSON
        com.jayway.jsonpath -- To get values from the JSON
    
    The greatest challenge here was the JSON handling, then jsonpath was implemented and things got easier.

    A separate class was created (MySecretKey.java) to hide my API key that is used in Main.java

# Screenshots

## `Main menu`
![all-text](https://github.com/bispo-daniel/Java_WeatherAPI/blob/main/src/Screenshots/MenuScreenshot.png)

## `Query menu`
![all-text](https://github.com/bispo-daniel/Java_WeatherAPI/blob/main/src/Screenshots/QueryPageScreenshot.png)