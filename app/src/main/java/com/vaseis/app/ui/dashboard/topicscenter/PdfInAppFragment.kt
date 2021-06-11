package com.vaseis.app.ui.dashboard.topicscenter

import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.view.View
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentPdfInAppBinding


class PdfInAppFragment : BaseFragment<FragmentPdfInAppBinding>() {


    override fun getViewBinding(): FragmentPdfInAppBinding = FragmentPdfInAppBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViews()    {
   /*     // create a new renderer
        // create a new renderer
        val renderer = PdfRenderer(getSeekableFileDescriptor())

        // let us just render all pages

        // let us just render all pages
        val pageCount = renderer.pageCount
        for (i in 0 until pageCount) {
            val page: PdfRenderer.Page = renderer.openPage(i)

            // say we render for showing on the screen
            page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

            // do stuff with the bitmap

            // close the page
            page.close()
        }

        // close the renderer
        renderer.close()*/
    }
}