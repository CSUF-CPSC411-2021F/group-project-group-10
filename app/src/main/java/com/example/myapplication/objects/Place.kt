package com.example.myapplication.objects

// A Feature object that contains a name and a center (longitude, latitude).
data class Feature(
    val place_name: String,
    val center: Array<Double>,
)


// Represents a Place root object that contains an array of Feature objects
data class Place (
    val features: Array<Feature>
)