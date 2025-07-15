package com.example.museoartiglieriaapp.Models

import android.os.Parcel
import android.os.Parcelable

data class PortableFirearm(
    val id: String,
    val name: String,
    val image: String,
    val origin: String,
    val yearOfProduction: Int,
    val historicalUse: String,
    val firstAppearance: String,
    val briefHistory: String,
    val trivia: String,
    val technicalSpecifications: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
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
        parcel.writeInt(yearOfProduction)
        parcel.writeString(historicalUse)
        parcel.writeString(firstAppearance)
        parcel.writeString(briefHistory)
        parcel.writeString(trivia)
        parcel.writeString(technicalSpecifications)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<PortableFirearm> {
        override fun createFromParcel(parcel: Parcel): PortableFirearm = PortableFirearm(parcel)
        override fun newArray(size: Int): Array<PortableFirearm?> = arrayOfNulls(size)
    }
} 