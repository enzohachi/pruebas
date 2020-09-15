package cl.grupo2.copeteandroid.copete.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Copete(

    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = ""
) : Parcelable

data class CopeteList (
    @SerializedName ("drinks")
    val copeteList: List<Copete>
)


