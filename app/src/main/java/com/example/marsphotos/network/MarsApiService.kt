package com.example.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//1 Add constant for the base URL for the web service
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

//2. Add a Retrofit builder below that constant to build and create a Retrofit object.
//Retrofit needs the base URI for the web service and a converter factory to build a web services API.
// The converter tells Retrofit what to do with the data it gets back from the web service.
// you want Retrofit to fetch a JSON response from the web service and return it as a String.
private val retrofit = Retrofit.Builder()
    //3 Call addConverterFactory() on the builder with an instance of ScalarsConverterFactory.
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL) //Add the base URL for the web service using the baseUrl() method.
    .build() //Call build() to create the Retrofit object.

// 4. Define an interface - MarsApiService that defines how Retrofit talks to the web server
// using HTTP requests. Add getPhotos() to get the response string from the web service.
// Use @GET annotation to tell Retrofit that this is a GET request and specify an endpoint
// for that web service method. The endpoint is /photos.
//When the getPhotos() method is invoked, Retrofit appends the endpoint photos to the base URL—
// which you defined in the Retrofit builder—used to start the request.
interface MarsApiService {
    @GET("photos")
    fun getPhotos()
}

//5.In Kotlin, object declarations are used to declare singleton objects.
// Singleton pattern ensures that one, and only one, instance of an object is created and has one
// global point of access to that object. Object initialization is thread-safe and done at first access.
//The call to create() function on a Retrofit object is expensive in terms of memory, speed, and performance. The app needs only one instance of the Retrofit API service, so you expose the service to the rest of the app using object declaration.
//define a public object - MarsApi to initialize the Retrofit service.
// This object is the public singleton object that the rest of the app can access.
object MarsApi {
    //6. Add a lazily initialized retrofit object property named retrofitService type MarsApiService.
    // You make this lazy initialization to make sure it is initialized at its first usage.
    //"lazy initialization" when object creation is purposely delayed, until you actually need that object, to avoid unnecessary computation or use of other computing resources
    val retrofitService: MarsApiService by lazy {
        //Initialize the retrofitService variable using the retrofit.create() method with the MarsApiService interface.
        retrofit.create(MarsApiService::class.java)
    }
}

//The Retrofit setup is done! Each time your app calls MarsApi.retrofitService,
// the caller accesses the same singleton Retrofit object that implements MarsApiService,
// which is created on the first access. In the next task, you use the Retrofit object you implemented.