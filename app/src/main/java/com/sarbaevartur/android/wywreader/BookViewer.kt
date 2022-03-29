package com.sarbaevartur.android.wywreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView


class BookViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_viewer)

        val filename = "1505.01072.pdf"
        showPdfFile(filename)
    }

    private fun showPdfFile(filename: String){
        val pdfView: PDFView = findViewById(R.id.pdfView)
        pdfView.fromAsset(filename)
            .enableSwipe(true)
            .swipeHorizontal(true)
            .enableDoubletap(true)
            .defaultPage(0)
            .enableAnnotationRendering(false)
            .password(null)
            .scrollHandle(null)
            .load()

    }
}