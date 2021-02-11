package gr.techzombie.foolingaround

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        // Write a message to the database
        // Write a message to the database
        //val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("message")

        //myRef.setValue("Hello, Worldj!")
        // Read from the database
        // Read from the database
//        myRef.child("midas").child("test").setValue("hello man")
//        myRef.child("midas").child("test").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
//// whenever data at this location is updated.
//
//                val value = dataSnapshot.getValue(String::class.java)!!
//                //Log.d(FragmentActivity.TAG, "Value is: $value")
//                textView4.text=value
//            }
//
//            override fun onCancelled(error: DatabaseError) { // Failed to read value
//              //  Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
//            }
//
//        })

    }

    fun buttonClicked(view:View){
        val email = editText.text.toString()
        val pass = editText4.text.toString()
        mAuth!!.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    //Log.d(FragmentActivity.TAG, "createUserWithEmail:success")
                    val user = mAuth!!.currentUser
                    updateUI(user)
                } else { // If sign in fails, display a message to the user.
                   // Log.w(FragmentActivity.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
                // ...
            }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser!=null) {
            var intent = Intent(this,kosmas::class.java)
            intent.putExtra("uid", currentUser!!.uid)
            intent.putExtra("email", currentUser!!.email)

            startActivity(intent)
        }
    }


}
