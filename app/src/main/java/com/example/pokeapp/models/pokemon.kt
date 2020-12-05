package com.example.pokeapp.models

import android.os.Parcel
import android.os.Parcelable

data class pokemon(val name:String, val image: String, val description: String, val isFavorite: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<pokemon> {
        override fun createFromParcel(parcel: Parcel): pokemon {
            return pokemon(parcel)
        }

        override fun newArray(size: Int): Array<pokemon?> {
            return arrayOfNulls(size)
        }
    }

}