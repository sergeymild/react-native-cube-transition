package com.cubetransition

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

import androidx.viewpager2.widget.ViewPager2
import com.facebook.react.bridge.ReactContext

class DemoObjectFragment(val presentView: View) : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return presentView
  }
}

class StoryPagerAdapter(fragmentActivity: FragmentActivity, val views: List<View>) : FragmentStateAdapter(fragmentActivity) {
  override fun getItemCount(): Int {
    return views.size
  }

  override fun createFragment(position: Int): Fragment {
    return DemoObjectFragment(views[position])
  }

}

class CubeView(context: Context) : FrameLayout(context) {
  var totalCount = 0
  val views = mutableListOf<View>()
  private val pager = ViewPager2(context)

  init {
    addView(pager, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    pager.setBackgroundColor(Color.DKGRAY)
  }

  fun initialize() {
    val a = (context as ReactContext).currentActivity as FragmentActivity
    pager.adapter = StoryPagerAdapter(a, views)
    pager.setPageTransformer(CubeOutTransformer())
    pager.adapter?.notifyDataSetChanged()
  }
}
