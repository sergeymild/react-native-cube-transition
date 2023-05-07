package com.cubetransition

import android.view.View
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp


class CubeTransitionViewManager : ViewGroupManager<CubeView>() {
  override fun getName() = "CubeTransitionView"

  override fun createViewInstance(reactContext: ThemedReactContext): CubeView {
    return CubeView(reactContext)
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
    return MapBuilder.builder<String, Any>()
      .put("onTouch", MapBuilder.of("registrationName", "onTouch"))
      .put("onPageChange", MapBuilder.of("registrationName", "onPageChange"))
      .build()
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
