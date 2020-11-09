package com.example.redcarpet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.flaviofaria.kenburnsview.KenBurnsView
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.github.curioustechizen.ago.RelativeTimeTextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_news_detail.*
import java.text.ParseException
import java.util.*


class NewsDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val w = activity?.window
        w?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        activity?.overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adi = AccelerateDecelerateInterpolator()
        val randomTransitionGenerator = RandomTransitionGenerator(5000, adi)
        kennyBurn.setTransitionGenerator(randomTransitionGenerator)
        val args = arguments?.let { NewsDetailFragmentArgs.fromBundle(it) }
        val articleTitle = args?.message
        val articleAuthor = args?.Author
        val articleContent = args?.content
        val articleUrl = args?.url
        val articleSource = args?.source1name
        val articleTime = args?.publisheddate
        val articleImage = args?.urltoimage
        val articleDescription = args?.description

            GlideApp.with(kennyBurn)
                .load(articleImage)
                .into(kennyBurn)



        if (articleTime != null) {
            var date: Date? = null
            try {
                date = ISO8601Parse.parse(articleTime)
            } catch (ex: ParseException) {
                ex.printStackTrace()
            }
            if (date != null) {
                time2.setReferenceTime(date.time)
            }
        }


//                if (articleTitle.length > 75) {
//                    articleTitle1!!.text = articleTitle.substring(0, 75) + "..."
//                } else {
        Title.text = articleTitle
        // }

        if (articleAuthor != null) {
            Author.text = articleAuthor.toString()
        } else if (articleSource != null) {
            Author.text = articleSource.toString()
        } else {
            Author.text = "unknown"
        }
        if (articleContent != null) {
            if (articleContent.length > 200) {
                content2.text = articleContent.substring(0, 200).toString() + "..."
            } else {
                content2.text = articleContent.toString()
            }
        } else if (articleDescription != null) {
            content2.text = articleDescription.toString()
        } else {
            content2.text = "click on the button to read the full article"
        }

        elaborateButton.setOnClickListener {
//            val intent = Intent(this, FullNewsActivity::class.java)
//            intent.putExtra("webURL", articleUrl)
//            this.startActivity(intent)
            findNavController().navigate(NewsDetailFragmentDirections.actionNewsDetailFragmentToWebFragment(articleUrl!!))
        }

    }
}