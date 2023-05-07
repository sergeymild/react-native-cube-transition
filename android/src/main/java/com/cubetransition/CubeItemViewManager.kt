package com.cubetransition

import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager


class CubeItemViewManager : ViewGroupManager<CubeItemView>() {
  override fun getName() = "CubeItemView"

  override fun createViewInstance(reactContext: ThemedReactContext): CubeItemView {
    return CubeItemView(reactContext)
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
    return MapBuilder.builder<String, Any>()
      .put("onPrepareForRender", MapBuilder.of("registrationName", "onPrepareForRender"))
      .build()
  }
}
