package com.example.kasat.qrfirebasegenerator

import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //public static final String TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val etText = findViewById<EditText>(R.id.et_text)
        val btnGen = findViewById<Button>(R.id.btn_generate)
        val ivBarcode = findViewById<ImageView>(R.id.iv_barcode)
        var a = 0



        btnGen.setOnClickListener {
            try {
                val encoder = BarcodeEncoder()
                val bitmap = encoder.encodeBitmap(etText.text.toString(), BarcodeFormat.QR_CODE, 500, 500)
                ivBarcode.setImageBitmap(bitmap)
                val str = etText.text.toString()
                val receipts = HashMap<String, Any>()
                val item_num = HashMap<String, Any>()
                val nameTable = mutableMapOf<String, String>()
                val nameTable_2 = mutableMapOf<String, String>()
                val nameTable_3 = mutableMapOf<String, String>()


                if( a == 0){
                    nameTable["item"] = "sausages"
                    nameTable["price"] = "100"
                    item_num["0"] = nameTable

                }
                if(a == 1){


                    nameTable["item"] =  "shawarma"
                    nameTable["price"] = "5"
                    item_num["0"] = nameTable
                    nameTable_2["item"] = "fries"
                    nameTable_2["price"] = "11"
                    item_num["1"] = nameTable_2

                }
                if(a == 2){
                    nameTable["item"] = "curry"
                    nameTable["price"] = "1"
                    item_num["0"] = nameTable
                    nameTable_2["item"] = "roti"
                    nameTable_2["price"] = "2"
                    item_num["1"] = nameTable_2
                    nameTable_3["item"] = "mango lassi"
                    nameTable_3["price"] = "3"
                    item_num["2"] = nameTable_3
                }
                a += 1

                receipts["items"] = item_num
                val db = FirebaseFirestore.getInstance()
                db.collection("receipt").document(str)
                    .set(receipts)

            } catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
}
