package com.cubetransition

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext

class DemoObjectFragment(private val presentView: View) : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    ((presentView as CubeItemView).context as ReactContext).sendEventToJs(
      presentView.id,
      "onPrepareForRender",
      Arguments.createMap()
    )
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

  private val callback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

    override fun onPageScrollStateChanged(state: Int) {
      if (state == ViewPager2.SCROLL_STATE_IDLE) {
        sendOnTouchEvent("end")
      }
    }

    override fun onPageSelected(position: Int) {
      (context as ReactContext).sendEventToJs(id, "onPageChange", Arguments.createMap().also {
        it.putInt("page", position)
      })
    }
  }

  init {
    addView(pager, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    pager.registerOnPageChangeCallback(callback)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    pager.unregisterOnPageChangeCallback(callback)
  }

  fun initialize() {
    val a = (context as ReactContext).currentActivity as FragmentActivity
    pager.adapter = StoryPagerAdapter(a, views)
    pager.setPageTransformer(CubeOutTransformer())
    pager.adapter?.notifyDataSetChanged()
  }

  fun sendOnTouchEvent(touchType: String) {
    val viewId = id
    (context as ReactContext).sendEventToJs(
      viewId,
      "onTouch",
      Arguments.createMap().also {
        it.putString("touchType", touchType)
      }
    )
  }
}
