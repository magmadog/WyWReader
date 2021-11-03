package com.sarbaevartur.android.wywreader

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.List.of

private const val TAG = "BookListFragment"


class BookListFragment: Fragment() {

    private lateinit var bookRecyclerView: RecyclerView
    private var adapter: BookAdapter? = null

    private val bookListViewModel: BookListViewModel by lazy {
        ViewModelProviders.of(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${bookListViewModel.books.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)

        bookRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        bookRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI(){
        val books = bookListViewModel.books
        adapter = BookAdapter(books)
        bookRecyclerView.adapter = adapter
    }

    private inner class BookHolder(view: View): RecyclerView.ViewHolder(view){

        val nameTextView: TextView = itemView.findViewById(R.id.book_name)
        val pathTextView: TextView = itemView.findViewById(R.id.book_path)
    }

    private inner class BookAdapter(var books: List<BookFile>): RecyclerView.Adapter<BookHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
            val view = layoutInflater.inflate(R.layout.list_item_book, parent, false)
            return BookHolder(view)
        }

        override fun onBindViewHolder(holder: BookHolder, position: Int) {
            val book = books[position]
            holder.apply {
                nameTextView.text = book.filename
                pathTextView.text = book.path
            }
        }

        override fun getItemCount(): Int {
            return books.size
        }
    }

    companion object {
        fun newInstance(): BookListFragment {
            return BookListFragment()
        }
    }
}