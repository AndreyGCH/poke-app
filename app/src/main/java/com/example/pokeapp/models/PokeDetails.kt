package com.example.pokeapp.models

import android.os.Parcel
import android.os.Parcelable

data class PokeDetails(val id: String, val base_experience: String, val height: String ,
                       val weight: String, val name:String, val sprites: PokeSprite ,
                       val moves: List<PokeMove>, val stats: List<PokeStats>,
                       val types: List<PokeTypes>): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            TODO("sprites"),
            TODO("moves"),
            TODO("stats"),
            TODO("types")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(base_experience)
        parcel.writeString(height)
        parcel.writeString(weight)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokeDetails> {
        override fun createFromParcel(parcel: Parcel): PokeDetails {
            return PokeDetails(parcel)
        }

        override fun newArray(size: Int): Array<PokeDetails?> {
            return arrayOfNulls(size)
        }
    }

}