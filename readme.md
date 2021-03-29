
# SimpleWeather  
  
A simple Android app written in Kotlin that consumes the OpenWeatherMap API using Retrofit and Moshi.  
  
Given below are the step by step instructions on how to get started with making this app. Refer to this repo whenever you get stuck!  

## Pre-requisite Knowledge
To effectively follow this tutorial, you must have completed at least Unit 1 and 2 from [this free course by Android Developers](https://developer.android.com/courses/android-basics-kotlin/course), which is the official site for Android developers by Google.

# Setup Steps

## Step 1  
  
Create a new Android Studio project with an empty activity. Select API 19. Give a cool name to your app.  
  
## Step 2  
  
1. Add ViewBinding to your newly created project as shown [here](https://developer.android.com/topic/libraries/view-binding)  
2. Get Retrofit from [here](https://square.github.io/retrofit). This library is used to make network requests to, in this case, the OpenWeatherMap API.  
3. Add Moshi from [here](https://github.com/square/moshi/). Make sure you get the Kotlin flavor of the library, otherwise if you use the Java flavor, the app will compile but crash at runtime!
4. Add Moshi converter for Retrofit  from [here](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi).
5. Add Kotlin coroutines support from [here](https://developer.android.com/kotlin/coroutines).
6. Add Navigation Component from [here](https://developer.android.com/guide/navigation/navigation-getting-started)
7. Add SafeArgs gradle plugin from [here](https://developer.android.com/guide/navigation/navigation-pass-data)
8. Add Picasso for loading images from [here](http://square.github.io/picasso/)

## Step 3  
  
Register on OpenWeatherMap's [website](https://https://openweathermap.org/) and generate an API key (it's **free**).  
  
## Step 4  
  
1. Open the Gradle Scripts section in the "Android" project files view mode, then open the `local.properties` file.  
2. Append the following code snippet to the end of the opened `local.properties` file, replacing the blank with your OpenWeatherMap API key:  
`  
apiKey = ______________  
`  
3. Now open the ***Module-level*** `build.gradle` file. Notice there are two kinds of `build.gradle` files, the other one is Project-level.  
4. Add the following lines to the Module-level `build.gradle` file below the last line **inside** the defaultConfig element area shown in bold here: `android{..., defaultConfig{ `**...**` } }`:  
<pre>
android {  
  ...
  defaultConfig {  
  ...
  def localProperties = new Properties()  
        localProperties.load(new FileInputStream(rootProject.file("local.properties")))  
        resValue "string", "api_key", localProperties.getProperty("api_key", "")  
    }  
  ...
}
</pre>

## Step 5  
  
Start coding the app!  
  
  
## References  
  
1. [For learning how to save API key securely without leaking it into Git/other version control systems](https://blog.mindorks.com/using-local-properties-file-to-avoid-api-keys-check-in-into-version-control-system)  
2. [How to read properties defined in local.properties file](https://stackoverflow.com/questions/21999829/how-do-i-read-properties-defined-in-local-properties-in-build-gradle)
3. [Working with Retrofit and Moshi in Kotlin](https://www.pushing-pixels.org/2019/12/04/working-with-retrofit-and-moshi-in-kotlin.html)
4. [Parsing JSON with Moshi converter for Retrofit](https://johncodeos.com/how-to-parse-json-with-retrofit-converters-using-kotlin/)
5. [How-to: Retrofit Singleton in Kotlin](https://stackoverflow.com/questions/61729790/retrofit-singleton-in-kotlin)