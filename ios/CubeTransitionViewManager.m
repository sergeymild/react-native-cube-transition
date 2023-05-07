#import <React/RCTViewManager.h>

@interface RCT_EXTERN_MODULE(CubeTransitionViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(totalCount, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(onTouch, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPageChange, RCTDirectEventBlock)
@end


@interface RCT_EXTERN_MODULE(CubeItemViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(onPrepareForRender, RCTDirectEventBlock)
@end
