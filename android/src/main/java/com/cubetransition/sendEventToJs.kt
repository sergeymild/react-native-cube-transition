package com.cubetransition

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.RCTEventEmitter

internal fun ReactContext.sendEventToJs(
  viewId: Int,
  eventName: String,
  event: WritableMap?
) {
  getJSModule(RCTEventEmitter::class.java)
    .receiveEvent(viewId, eventName, event ?: Arguments.createMap())
}
