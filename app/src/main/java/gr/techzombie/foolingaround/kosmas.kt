package gr.techzombie.foolingaround

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_kosmas.*
import java.lang.Exception

var myRef:DatabaseReference?=null
var uid:String?=null
var father:String?=null
var mother:String?=null
class kosmas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kosmas)
        val bundle = intent.extras
        uid = bundle!!.getString("uid")
        val email = bundle!!.getString("email")

        textView.text = uid
        textView2.text = email

        // Write a message to the database
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference()

        //myRef.setValue("Hello, World!")
        myRef!!.child(uid!!).setValue(DaObject(uid!!,email!!))

        getDaValues()


    }

    fun btn_clicked(view:View){
        father = editText2.text.toString()
        mother = editText3.text.toString()
        myRef!!.child(uid!!).child("fathers name").setValue(father)
        myRef!!.child(uid!!).child("mothers name").setValue(mother)
//kog
    }
    var uidText = ""
    var fathers = ""
    var mothers = ""
    var emailText = ""
    fun getDaValues(){
        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                //nothing yet :(
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val td = snapshot.value as HashMap<String,Any>
                for (key in td.keys){
                    val daValueUID = td[key] as HashMap<String,Any>
                    for (key in daValueUID.keys){
                        uidText = daValueUID["uid"].toString()
                        try {
                            fathers = daValueUID["fathers name"].toString()
                            mothers = daValueUID["mothers name"].toString()
                        }catch (ex:Exception){}
                        emailText = daValueUID["email"].toString()
//kog
                    }
                    textView5.text = uidText
                    textView6.text = fathers
                    try {
                        textView7.text = mothers
                        textView8.text = emailText
                    }catch (ex:Exception){

                    }


                }
            }
        })
    }

}
