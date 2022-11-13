package com.example.roomdemo

import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity.apply
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.db.UseEntity
import java.util.EnumSet.of


//hello world git test next
class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdaptor
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)

            recyclerViewAdapter = RecyclerViewAdaptor(this@MainActivity)
            adapter = recyclerViewAdapter

            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()

        })

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val name2 = findViewById<EditText>(R.id.nameInput)
            val name1  = findViewById<EditText>(R.id.nameInput).text.toString()
            val email  = findViewById<EditText>(R.id.emailInput).text.toString()


//            val phone = phoneInput.text.toString()
            if(saveButton.text.equals("Save")) {
                val user = UseEntity(0, name2.text.toString(), email)
                viewModel.insertUserInfo(user)
            }else {
                val user = UseEntity(name.getTag(name.id).toString().toInt(), name, email)
                viewModel.updateUserInfo(user)
                saveButton.setText("Save")
            }
            name2.hint
            email.setText("")
        }
    }



    override fun onDeleteUserClickListener(user: UseEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UseEntity) {
        nameInput.setText(user.name)
        emailInput.setText(user.email)
//        phoneInput.setText(user.phone)
        nameInput.setTag(nameInput.id, user.id)
        saveButton.setText("Update")
    }

    }
}