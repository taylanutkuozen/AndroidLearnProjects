package com.example.kotlinoop

class Sanatci(var isim:String, val yas:Int,val enstruman:String):Insan(),Sarki,Dans {
    var sacRengi=""
    private var tur="insan" /*private setter*/
    var gozRengi=""
        private set/*Encapsulation boyle yapabiliriz. class uzerinde tanimlayip baska classtan read islemi yapabiliriz*/
        public get
    private var sesTonu=""
    fun setSesTonu(yeniSesTonu:String,parola:String)
    {
        if(parola=="utku")
        {
            this.sesTonu=yeniSesTonu
        }
        else
        {
            println("parolan yanlis")
        }
    }
    fun getSesTonu()
    {
        println(this.sesTonu)
    }
    fun turuYazdir()
    {
        println(this.tur)/*public getter-->oku ama degistirme*/
    }
    fun enstrumanGoster()
    {
        println(this.enstruman)
    }
    fun sarkiSoyle()
    {
        println("Su sanatci sarki soyledi:${this.isim}")
    }
    init/*constructor gibi. init bir nesne olustururken yapmak istedigimiz herhangi bir islem
    varsa,primary constructor kullandiysak init kullanabiliriz.*/
    {
        println("init cagrildi")
    }
    override fun test2() {
        println("test2 calisti")
        //abstract class'tan gelip abstract fonksiyonun override edilmis hali
    }

    override fun sarkiSoyleFonksiyonu() {
        println("${this.isim} sarki soylerken")
    }

    override fun dansEtmeFonksiyonu() {
        println("${this.isim} dans ediyordu.")
    }
}
/* Visibility modifiers
private=sadece o class icerisinden erisilebilir.(ozel)
protected=sadece dosya icerisinden erisilebilecek(korumali)
internal=sadece ayni modul icerisinden erisilebilir.(ic)
public=her yerden erisilebilecek hale getirilebilir.(kamu)-->default
*/
/* Inheritance
MainActivity:AppCompatActivity() --> onCreate,setContentView gibi yerler AppCompatActivity'den inherit edilir.
*/