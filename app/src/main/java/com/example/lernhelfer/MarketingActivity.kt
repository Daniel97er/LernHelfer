package com.example.lernhelfer

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.io.File
import java.io.FileOutputStream

class MarketingActivity : AppCompatActivity() {

    private lateinit var pdfRenderer: PdfRenderer
    private lateinit var parcelFileDescriptor: ParcelFileDescriptor
    private lateinit var pdfViewPager: ViewPager2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketing)

        pdfViewPager = findViewById(R.id.pdfViewPager)

        // Lade die PDF-Datei aus dem assets-Ordner
        openPdfRenderer("Marketing-Skript.pdf")

        // Adapter direkt in der Activity erstellen
        pdfViewPager.adapter = object : RecyclerView.Adapter<PdfPageViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfPageViewHolder {
                val imageView = ImageView(parent.context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }
                return PdfPageViewHolder(imageView)
            }

            override fun onBindViewHolder(holder: PdfPageViewHolder, position: Int) {
                val currentPage = pdfRenderer.openPage(position)
                val bitmap = Bitmap.createBitmap(
                    currentPage.width, currentPage.height,
                    Bitmap.Config.ARGB_8888
                )
                currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                holder.imageView.setImageBitmap(bitmap)
                currentPage.close()
            }

            override fun getItemCount(): Int = pdfRenderer.pageCount
        }
    }

    private fun openPdfRenderer(fileName: String) {
        // Lade die PDF-Datei aus den Assets und öffne sie
        val file = File(cacheDir, fileName)
        if (!file.exists()) {
            assets.open(fileName).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    val buffer = ByteArray(1024)
                    var read: Int
                    while (inputStream.read(buffer).also { read = it } != -1) {
                        outputStream.write(buffer, 0, read)
                    }
                }
            }
        }

        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        pdfRenderer = PdfRenderer(parcelFileDescriptor)
    }

    override fun onDestroy() {
        super.onDestroy()
        pdfRenderer.close()
        parcelFileDescriptor.close()
    }

    private inner class PdfPageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}