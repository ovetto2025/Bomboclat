package com.example.museoartiglieriaapp.Models

import android.os.Parcel
import android.os.Parcelable

data class StorageItem(
    val id: String,
    val name: String,
    val image: String,
    val origin: String,
    val yearOfProduction: String, // Cambiato in String per gestire "1930s", "1933-1945", ecc.
    val historicalUse: String,
    val firstAppearance: String,
    val briefHistory: String,
    val trivia: String,
    val technicalSpecifications: String,
    val category: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(origin)
        parcel.writeString(yearOfProduction)
        parcel.writeString(historicalUse)
        parcel.writeString(firstAppearance)
        parcel.writeString(briefHistory)
        parcel.writeString(trivia)
        parcel.writeString(technicalSpecifications)
        parcel.writeString(category)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<StorageItem> {
        override fun createFromParcel(parcel: Parcel): StorageItem = StorageItem(parcel)
        override fun newArray(size: Int): Array<StorageItem?> = arrayOfNulls(size)
    }
} 