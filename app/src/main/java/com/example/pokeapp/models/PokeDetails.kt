package com.example.pokeapp.models

import android.os.Parcel
import android.os.Parcelable

data class PokeDetails(val name:String, val sprites: PokeSprite , val moves: List<PokeMove>): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        TODO("sprites") ,
        TODO("moves")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
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