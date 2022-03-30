package com.sarbaevartur.android.wywreader

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File

private const val TAG = "MainActivity"
private const val REQUEST_PATH = 123

class MainActivity : AppCompatActivity() {

    lateinit var addButton: FloatingActionButton
    lateinit var currentBookFragment: View

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_books)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BookRecyclerViewAdapter(fillList())

        currentBookFragment = findViewById(R.id.CurrentBookFragment)
        currentBookFragment.visibility = View.GONE

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val filename = data?.data
                val file = File("$filename")
                Log.d(TAG, file.readText())
            }
        }

        addButton = findViewById(R.id.add_book_button)
        addButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = ""
            intent.categories
            resultLauncher.launch(intent)
        }
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..25).forEach { i -> data.add("$i element") }
        return data
    }
}

class BookRecyclerViewAdapter(private val book_titles: List<String>):
    RecyclerView.Adapter<BookRecyclerViewAdapter.BookRecyclerViewHolder>() {

    class BookRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.list_book_title)
        val authorTextView: TextView = itemView.findViewById(R.id.list_book_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.book_list_item,
            parent,
            false
        )
        return BookRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookRecyclerViewHolder, position: Int) {
        holder.titleTextView.text = book_titles[position]
        holder.authorTextView.text = "книга"
    }

    override fun getItemCount(): Int {
        return book_titles.size
    }
}