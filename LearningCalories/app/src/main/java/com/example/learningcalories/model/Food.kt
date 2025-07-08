package com.example.learningcalories.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity//room icin verdik.
data class Food(
    @ColumnInfo(name="isim")//room kolon isimleri
    @SerializedName("isim")/*Json'dan donen key bu sekilde annotation yapilarak kendi degisken ismimize donusturulur.*/
    val foodName:String?,
    @ColumnInfo(name="kalori")
    @SerializedName("kalori")
    val foodCalory:String?,
    @ColumnInfo(name="karbonhidrat")
    @SerializedName("karbonhidrat")
    val carbonhydrat:String?,
    @ColumnInfo(name="protein")
    @SerializedName("protein")
    val protein:String?,
    @ColumnInfo(name="yag")
    @SerializedName("yag")
    val fat:String?,
    @ColumnInfo(name="gorsel")
    @SerializedName("gorsel")
    val gorsel:String?/*?-->nullable yapariz.*/
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}