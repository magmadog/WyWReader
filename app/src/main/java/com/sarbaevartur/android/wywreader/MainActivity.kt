package com.sarbaevartur.android.wywreader

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val TAG = "MainActivity"
private const val REQUEST_PATH = 123

class MainActivity : AppCompatActivity() {

    lateinit var addButton: FloatingActionButton
    lateinit var currentBookFragment: View

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        hideKeyboard(this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_books)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BookRecyclerViewAdapter(fillList())

        currentBookFragment = findViewById(R.id.CurrentBookFragment)
        currentBookFragment.visibility = View.GONE

        addButton = findViewById(R.id.add_book_button)
        addButton.setOnClickListener{
            val intent = Intent(this, BookViewer::class.java)
            startActivity(intent)
        }
    }

    fun hideKeyboard(activity: Activity) {
        //Находим View с фокусом, так мы сможем получить правильный window token
        //Если такого View нет, то создадим одно, это для получения window token из него
        val view = activity.currentFocus ?: View(activity)
        val inputMethod =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.SHOW_IMPLICIT
        )
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