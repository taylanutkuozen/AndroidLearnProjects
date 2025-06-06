fun main() {/*fun ile fonksiyonu oluşturuyoruz. main birebir olarak yazılması gerekiyor.*/
    println("merhaba dünya")
    println("Merhaba Kotlin") /*println Loglama yapar. LogCat icerisinde  package:mine System.out filtreleme işlemi yapmalıyız. Loglar çok fazla ise Clear ile silebiliriz. Ancak kod yeniden çalıştıgında tekrar geri gelecektir.*/
    println("Hello Kotlin")
    /*hoca degiskenler konusuyla baslayacagımızı soyledi hatalı bir kod yazılımı oldugu zaman build output penceresinde "Unsolved Reference" uzerinde gorunur.*/
    println("------Integer----")
    println(5*2)
    println(10/5)
    println(5/2) //sonuc 2.5 yerine 2 dir. Tamsayi vermez
    //implicit tanimlama
    var x=20 //variable(degisken) den gelir.
    println(x)
    println(x*20)
    x=30
    println(x)
    println(x)
    var y=5
    println(y)
    println(x+y)
    val z=20 //val constant olarak gecmektedir. z=30 olarak verirsek hataya dusecektir.
    println(z*50)
    //explicit tanimlama
    val ornek : Int =21476431
    println(ornek*10)
    val ornekByte : Byte = 15
    val ornekShort : Short=2000
    val ornekLong : Long=12369
    println(ornekLong*ornekByte)
    //ornekByte=10000 hata verir byte 10000 tasiyamaz.
    val kullaniciyasi=35
    val kullanici_yasi=35 //snake_case
    val kullaniciYasi=35 // camelCase
    //kullaniciSoyismiVeYasi--> camelCase
    //alti yesil cizili hicbir yerde kullanilmadigini belirtir
    val m=10
    val n=20
    val sonuc=m+n
    println(sonuc)
    //Double - Float Kesirli Sayilar
    println("-----Double&Float---------")
    var pi=3.14151618
    //default double algilar. Double ile float virgulden sonra ile ilgilidir.
    var ornek2Integer=20000000000
    println(pi*2)
    //integer/integer=integer
    //integer/integer=double(pyhton)
    println(5.0/2.0)//double doner
    val ornekDouble=3.0
    var sonucDouble=pi
    sonucDouble=sonucDouble*ornekDouble
    println(sonucDouble)
    val ornekFloat:Float =2.25f//Float icin sonuna f eklemeliyiz
    println(ornekFloat*2)
    //unsigned integers
    println("-----unsigned integers-----")
    val ornekUByte : UByte = 15u //Tanimlanirken sonunda u bulunur.
    val ornekUShort:UShort = 150u
    val ornekULong :ULong=1500U
    val ornekUInt:UInt=190u //Belirtilmezse default olarak UInt olarak atanir
    val ornekULong2=0xFFFF_FFFF_FFFFu
    //string
    println("-----String-----")
    val myString="Benim String'im"
    println(myString)
    val name="Utku"
    println(name.uppercase())
    val surname="ozen"
    println(name+" "+surname.uppercase())
    val age="15"
    val ornekDeger="20"
    //convert-cast-init
    println("----convert-cast-init-----")
    val ornekStr : String //string olarak bir degisken tanimlanacak ama herhangi bir degeri yok. Sonradan bir deger atayabiliriz.
    //ornekStr.uppercase() initalize,init,initalization hatasi. bir deger atanmadigi icin sistem hata verecektir. zorlarsak uygulamayi cokertebilir.
    ornekStr="ornek olarak bir string"
    val ageInt=age.toInt()
    println(ageInt*20) //conversion
    //Fatal Exception Logcat icerisinde hatayi gosterir. Cause icerisinde nedeni yazmaktadir Ornek olarak age="on beş" olarak int cast etmeye calissak hata olur.
    val ornLong=ornek2Integer.toLong()//Long cast edebiliriz.
    //Boolean
    println("-----Boolean------")
    //true or false
    var ornekBool:Boolean=true
    ornekBool=false
    println(3>5)
    println(3<5)
    println(4==4)//iki esittir ile esittir kontrol edilir
    val userAge="35"
    println(userAge.toInt()>18)
    //< kucuktur
    //> buyuktur
    //<= kucuk esit
    //>= buyuk esit
    //== esittir
    //!= esit degildir
    //&& ve
    //|| veya
    println("atil"=="atli")
    println(100!=(10*10))
    println(4>3 && 0<2)
    println(4>3 && 3>5)
    println(4>3 || 3>5)
    println(1>2 || 3>5)
    //data structures - collections
    println("------Diziler-Array---------")
    val strExp="ornek"
    val myArr  = arrayOf("taylan","utku","ozen","yusuf",strExp)
    println(myArr[0])
    println(myArr[1])
    println(myArr.last())
    myArr[2]="OZEN"//eleman degerini degistirmek bu sekildedir.
    println(myArr[2])
    //val ile tanimlanan array bir daha tanimlanamaz ancak icerisindeki elemanlarin degerleri degistirilebilir.
    println( myArr.get(3))//myArr[3] ile ayni isleve sahiptir.
    //myArr[5]="asdsadsa" index out of range hatasi gelmektedir.
    //Array icin tanimlandiktan sonra yeni bir eleman eklenmesi YAPILAMAZ.
    val numArr= arrayOf(1,10,20,15,2,4,2,2,2,15,15)
    println(numArr[3]*10)
    var mixedArr= arrayOf(10,3.14,20,"utku",false,true)//farkli data type'lar ile bir array olusturulabilmektedir.
    println(mixedArr[2])
    //ArrayList
    println("------ArrayList-------")
    val nameList= arrayListOf("taylan","utku","ozen","yusuf")//arrayListOf kullanilmasi gerekmektedir.
    println(nameList[0])
    println(nameList[1])
    println(nameList[2])
    println(nameList[3])
    println(nameList.size)//eleman sayisi size ile bulunabilmektedir.
    nameList.add("mahmut")//sonradan eleman eklemeye izin vermektedir.
    println(nameList.get(4))
    nameList[4]="hülya"
    println(nameList.get(4))
    //nameList.remove("taylan")//su elemani cikar
    //nameList.removeAt(1)//su index numarasindaki elemani cikar
    val numArrList= arrayListOf<Int>() //arrayListOf() bu sekilde initial da elemansiz birakilirsa type bulamayacagi icin hataya duser. Ancak komutta oldugu gibi eleman eklemeden type(Int) belirlenmis ise hataya dusmeyecektir.
    val othArrList=ArrayList<Int>()//bu sekilde de tanimlanabilir.
    othArrList.add(40)
    othArrList.add(50)
    othArrList.add(60)
    numArrList.add(10)
    numArrList.add(20)
    numArrList.add(30)
    println(othArrList[2]*numArrList[1])
    val mixArrList= arrayListOf<Any>()//karisik bir list yapabilmek icin Any kullanmaliyiz.
    mixArrList.add(10)
    mixArrList.add("utku")
    mixArrList.add(true)
    println(mixArrList.get(2))
    //Set=Elemanlar unique(essiz)[bir elemandan iki tane olamaz]-index yapisi yok
    println("-------Set------")
    val expArr= arrayOf(10,10,10,10,20,30,40)
    println(expArr[0])
    println(expArr[1])
    val expSet= setOf<Int>()//
    val expSet2= setOf(10,10,10,10,20,30,40)
    //expSet2.add komutu CALISMAZ. setOf sanki bir dizi gibidir.
    println(expSet2.size)//4 eleman gosterdi. Fazla olan 10 elemanlarini sildi.
    //get, [] komutlari yok cunku index mantigi yok. Unordered
    expSet2.forEach {it
        println(it)//10,20,30,40 elemanlari geldi. 10 gitti.
    }
    val empSetExp=HashSet<String>()//HashSet code ile setOf yazilimdir. Set bir interface, HashSet bir class. Hash fonksiyonu ile calisir. Veriler unique oldugu icin kullanici daha hizli bulur. Degerle arama yapabilir.
    empSetExp.add("taylan")
    empSetExp.add("yusuf")
    empSetExp.add("utku")
    empSetExp.add("ozen")
    empSetExp.add("ozen")
    empSetExp.add("yusuf")
    empSetExp.add("yusuf")
    empSetExp.forEach {it
        println(it)
    }
    val uniqueArr=numArr.toHashSet()//unique bir dizi yapabiliriz.
    uniqueArr.forEach {it
        println(it)
    }
    //Map-HashMap-Dictionary=Anahtar deger eslesmesi. index mantigi yoktur.
    println("------Map-------")
    val yemekDizisi= arrayListOf("Elma","Armut","Karpuz")
    val kaloriDizisi= arrayListOf(100,150,200)
    println("${yemekDizisi[0]}'nın kalorisi ${kaloriDizisi[0]}")
    val foodCaloryMap= hashMapOf<String,Int>()
    foodCaloryMap.put("Elma",100)//put komutu ile ekleme yapabiliyoruz.
    foodCaloryMap.put("Armut",150)
    foodCaloryMap.put("Karpuz",200)
    println(foodCaloryMap["Elma"])
    println(foodCaloryMap.get("Armut"))
    foodCaloryMap.put("Elma",300)//Value icin put ile guncelleme yapabiliriz ama key icin guncelleme yapamayiz.
    println(foodCaloryMap.get("Elma"))
    val ornekHashMap=HashMap<String,String>()
    ornekHashMap.put("taylan","ozen")
    ornekHashMap.put("utku","ozen")
    ornekHashMap.put("yusuf","ozen")
    ornekHashMap.put("taylan utku","ozen")
    println("----------IF-------")
println(3>5)
var sayi=10
sayi=sayi+1
println(sayi)
sayi++
println(sayi)
sayi--
println(sayi)
//kalanini bulma
println(10%3)
var skor=10
if(skor<10)
    {
        println("Oyunu kaybettiniz")
    }
else if (skor>=10 && skor<20)
    {
        println("oyunda idare bir skor aldınız")
    }
else if(skor>=20 && skor<30)
    {
        println("güzel bir skor elde ettiniz")
    }
else
    {
        println("çok güzel bir skor elde ettiniz")
    }
    println("-----------When--------")
/*cok fazla kosul kullanmak gerekiyorsa when'i kullanmak daha okunakli yapacaktir.*/
val notRakam=5
var notString=""
if(notRakam==10) {notString="gecersiz bir not"}
when(notRakam){
         0 -> notString="Gecersiz not"
         1 -> notString="Zayıf"
         2 -> notString="Kötü"
         3 -> notString="Orta"
         4 -> notString="İyi"
         5 -> notString="Çok İyi"
         else -> notString="Böyle bir not bilmiyoruz"
    }
    println(notString)
    println("------WHILE---------")
/*Donguler genelde belirli bir kosul tutmaya devam ettigi surece yapilan devam eden devamli yapilan islemlere denir.*/
 var j=0
 println("Döngü başladi")
 while(j<=10)
    {
        println(j)
        j=j+1
    }
 println("Döngü bitti")
 println("--------FOR Döngüsü-----------")
 var baskaDizi = arrayListOf(5,10,15,20,25,30)
 println(baskaDizi[0]/5*3)
 println(baskaDizi[1]/5*3)
    println("Döngü başladi")
    for (numara in baskaDizi)//numara=degisken_adi
    {
        println(numara/5*3) /*buradaki numara index degil dizideki asil degeri almaktadir.*/
    }
    println("Döngü bitti")
 for(num in 0..9) /*kotlinde Bir_sayi..(nokta nokta)IkinciSayi=iki sayi arasinda demek=range olarak gecmektedir.*/
    {
         println(num*10)
    }
val stringArray=ArrayList<String>()
stringArray.add("Yusuf")
stringArray.add("Taylan")
stringArray.add("Utku")
stringArray.add("ÖZEN")
  for(kelime in stringArray)
  {
      println(kelime.uppercase())
  }
  stringArray.forEach{kelime->
      println(kelime.uppercase())
  }
}