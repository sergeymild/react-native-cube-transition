package com.cubetransition

import android.content.Context
import android.view.MotionEvent
import com.facebook.react.views.view.ReactViewGroup

class CubeItemView(context: Context?) : ReactViewGroup(context) {

  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
      var p = parent
      while (p !is CubeView && p != null) p = p.parent
      (p as? CubeView)?.sendOnTouchEvent("start")
    }

    if (ev.actionMasked == MotionEvent.ACTION_UP) {
      var p = parent
      while (p !is CubeView && p != null) p = p.parent
      (p as? CubeView)?.sendOnTouchEvent("end")
    }
    return super.onInterceptTouchEvent(ev)
  }
}
