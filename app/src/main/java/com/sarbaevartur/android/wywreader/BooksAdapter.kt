package com.sarbaevartur.android.wywreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BooksAdapter(books: List<BookFile>?, listener: BookListener) :
    RecyclerView.Adapter<BooksAdapter.BookHolder>() {
    interface BookListener {
        fun onBookOpen(bookFile: BookFile)
    }

    private val listener: BookListener
    private val books: List<BookFile>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(com.sarbaevartur.android.wywreader.R.layout.list_item_book, parent, false)
        return BookHolder(view)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val bookFile = books[position]
        holder.bind(bookFile)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    inner class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemLayout: LinearLayout
        private val tvPath: TextView
        private val tvName: TextView
        fun bind(bookFile: BookFile) {
            tvPath.text = bookFile.getPath()
            tvName.text = bookFile.getFilename()
            itemLayout.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    listener.onBookOpen(bookFile)
                }
            })
        }

        init {
            itemLayout = itemView.findViewById(com.sarbaevartur.android.wywreader.R.id.item_layout)
            tvName = itemView.findViewById(com.sarbaevartur.android.wywreader.R.id.book_name)
            tvPath = itemView.findViewById(com.sarbaevartur.android.wywreader.R.id.book_path)
        }
    }

    companion object {
        fun newInstance(): BookListFragment {
            return BookListFragment()
        }
    }

    init {
        this.books = ArrayList(books!!)
        this.listener = listener
    }
}