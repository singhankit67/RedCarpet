package com.example.redcarpet

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true) which will generate a JsonAdapter to handle serializing/deserializing to and from JSON of the specified type.
@JsonClass(generateAdapter = true)
data class News(
    @Json(name = "status")
    val status:String,
    //The@Json(name = “value”) annotation defines the JSON key name for serialisation and the property to set the value on with deserialization.
    @Json(name = "totalResults")
var totalResult:Int,
    @Json(name = "articles")
var article:List<Article1>
)
@JsonClass(generateAdapter = true)
data class Article1(
    @Json(name = "source")
    var source1:Source1,
    @Json(name="author")
    val author:String?,
    @Json(name = "title")
    val title:String? ,
    @Json(name = "description")
    val description:String?,
    @Json(name = "url")
    val url:String?,
    @Json(name = "urlToImage")
    val urlToImage:String?,
    @Json(name = "publishedAt")
    val publishedAt:String?,
    @Json(name = "content")
    val content1:String?
)
@JsonClass(generateAdapter = true)
data class Source1(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?

)
