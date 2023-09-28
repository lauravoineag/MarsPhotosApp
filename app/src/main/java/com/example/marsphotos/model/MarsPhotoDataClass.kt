package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Step 16. deserialization
//each var in the class corresponds to a key name in the JSON object.
//@Serializable - To match the types in JSON response, you use String objects for all the values.
//When kotlinx serialization parses the JSON, it matches the keys by name and fills the data objects with appropriate values.
//@SerialName Annotation - img_src key uses an underscore, whereas Kotlin convention for properties uses upper and lowercase letters (camel case).
//The variable can be mapped to the JSON attribute img_src using @SerialName(value = "img_src").
//This data class defines a Mars photo which includes an ID, and the image URL.
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
