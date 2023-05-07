package com.cubetransition

import android.view.View
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp

class CubeTransitionViewManager : ViewGroupManager<CubeView>() {
  override fun getName() = "CubeTransitionView"

  override fun createViewInstance(reactContext: ThemedReactContext): CubeView {
    return CubeView(reactContext)
  }

  @ReactProp(name = "totalCount")
  fun totalCount(view: CubeView, count: Int) {
    view.totalCount = count
  }

  override fun addView(parent: CubeView, child: View, index: Int) {
    //super.addView(parent, child, index)
    parent.views.add(child)
    if (parent.totalCount == parent.views.size) {
      parent.initialize()
    }

  }
}
