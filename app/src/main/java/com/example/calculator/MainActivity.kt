package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var Javob = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener(View.OnClickListener {
            if (ekran.text != "0"){
                ekran.text = "${ekran.text}0"
            }

        })

        btn_1.setOnClickListener {
            raqamYozish(1)

        }

        btn_2.setOnClickListener {
            raqamYozish(2)

        }

        btn_3.setOnClickListener {
            raqamYozish(3)

        }

        btn_4.setOnClickListener {
            raqamYozish(4)
        }

        btn_5.setOnClickListener {
            raqamYozish(5)

        }

        btn_6.setOnClickListener {
            raqamYozish(6)

        }

        btn_7.setOnClickListener {
            raqamYozish(7)

        }

        btn_8.setOnClickListener {
            raqamYozish(8)

        }

        btn_9.setOnClickListener {
            raqamYozish(9)

        }

        btn_nuqta.setOnClickListener {
            val misol = ekran.text.toString()
            var amalIndex = -1
            for (i in misol.indices) {
                if (misol[i] == '+' || misol[i] == '-' || misol[i] == '*' || misol[i] == '/'){
                    amalIndex = i
                }
            }
            if (amalIndex == -1){
                if (!ekran.text.toString().contains('.')){
                    ekran.text = "${ekran.text}."
                }
            }else {
                val ekranLength = ekran.text.length
                val matn = ekran.text.toString().subSequence(amalIndex, ekranLength)
                if (!matn.contains('.')) {
                    ekran.text = "${ekran.text}."
                }
            }
        }

        btn_ac.setOnClickListener {
            ekran.text = "0"
            Javob = false

        }


        btn_delete.setOnClickListener {
            val a = ekran.text
            if (a.length == 1 || a == "-"){
                ekran.text = "0"
            }else
                ekran.text = a.subSequence(0, a.length-1)
        }


        plus.setOnClickListener {
            amalYozish("+")
        }

        minus.setOnClickListener {
            amalYozish("-")
        }

        kopaytirish.setOnClickListener {
            amalYozish("*")
        }

        btn_bolish.setOnClickListener {
            amalYozish("/")
        }

        teng.setOnClickListener {
            try {
                if (ekran.text.isNotEmpty()){
                    if (!Javob) hisoblash()
                }
            }catch (e:Exception){

            }


        }


    }

fun raqamYozish(raqam:Int){

    if (Javob){
        ekran.text = "$raqam"
        Javob=false
    }else {

        val r = raqam.toString()
        if (ekran.text == "0") {
            ekran.text = r
        } else {
            ekran.text = "${ekran.text}$r"
        }
    }
}

fun amalYozish(amalArg:String){
    if (Javob){
        val misol = ekran.text.toString()
        for (i in misol.indices) {
            if (misol[i] == '='){
                ekran.text = "${misol.subSequence(i+1, misol.length)}$amalArg"
                break
            }
        }
        Javob=false
    }else {
        ekran.text = "${ekran.text}$amalArg"
    }
}


fun hisoblash(){

    var sonlar = ArrayList<Double>()
    var amallar = ArrayList<Int>()

    val misol = ekran.text.toString()

    var index1 = 0
    for (i in misol.indices) {
        if (amallar.isEmpty()) {
            when (misol[i]) {
                '+' -> {
                    sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                    amallar.add(0)
                    index1 = i
                }
                '-' -> {
                    sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                    amallar.add(1)
                    index1 = i
                }
                '*' -> {
                    sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                    amallar.add(2)
                    index1 = i
                }
                '/' -> {
                    sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                    amallar.add(3)
                    index1 = i
                }
            }
        }else{
            when (misol[i]) {
                '+' -> {
                    sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                    amallar.add(0)
                    index1 = i
                }
                '-' -> {
                    sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                    amallar.add(1)
                    index1 = i
                }
                '*' -> {
                    sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                    amallar.add(2)
                    index1 = i
                }
                '/' -> {
                    sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                    amallar.add(3)
                    index1 = i
                }
            }
        }
    }

    sonlar.add(misol.subSequence(index1+1, misol.length).toString().toDouble())

    var count = 0
    var natija = sonlar.first()
    while (count < amallar.size) {

        when (amallar[count]) {
            0 -> {
                natija += sonlar[count + 1]
            }
            1 -> {
                natija -= sonlar[count + 1]
            }
            2 -> {
                natija *= sonlar[count + 1]
            }
            3 -> {
                natija /= sonlar[count + 1]
            }
        }

        count++
    }

    val intNatija:Int = natija.toInt()
    if (natija / intNatija == 1.0){

        ekran.text = "${ekran.text}= $intNatija"
    }else {

        ekran.text = "${ekran.text}= $natija"
    }
    Javob = true
}


}