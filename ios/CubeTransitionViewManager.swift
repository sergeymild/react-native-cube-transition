import React

@objc(CubeTransitionViewManager)
class CubeTransitionViewManager: RCTViewManager {
    
    override func view() -> (CubeTransitionView) {
        return CubeTransitionView()
    }
    
    @objc override static func requiresMainQueueSetup() -> Bool {
        return false
    }
}

@objc(CubeItemViewManager)
class CubeItemViewManager: RCTViewManager {
    
    override func view() -> (CubeItem) {
        return CubeItem()
    }
    
    @objc override static func requiresMainQueueSetup() -> Bool {
        return false
    }
}

class CubeItem: RCTView {
    @objc
    var onModalDismiss: RCTDirectEventBlock?
    var rendered = false
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func onRender() {
        self.onModalDismiss!([:])
    }
}

class CubeTransitionView : UIView, CubeTransitionViewDelegate {
    
    @objc
    var totalCount: NSNumber?
    @objc
    var onTouch: RCTDirectEventBlock?
    @objc
    var onPageChange: RCTDirectEventBlock?
    
    fileprivate var childViews = [UIView]()
    private var transitionView = CubeTransitionInfiniteView(frame: .zero)
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubview(transitionView)
        transitionView.delegate = self
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        transitionView.frame = .init(origin: .zero, size: frame.size)
        transitionView.initialize()
    }
    
    func pageView(atIndex: Int) -> UIView? {
        if atIndex < 0 { return nil }
        if atIndex >= childViews.count { return nil }
        let v = childViews[atIndex]
        v.frame = .init(origin: .zero, size: RCTScreenSize())
        return v
    }
    
    func onGesture(event: GestureEvent) {
        onTouch?(["touchType": event.rawValue])
    }
    
    func numberofPages() -> Int {
        childViews.count
    }
    
    func pageDidChanged(index: Int, direction: Direction) {
        onPageChange?(["page": index, "direction": direction == Direction.right ? "right" : "left"])
    }
    
    
    override func addSubview(_ view: UIView) {
        if (view is CubeTransitionInfiniteView) {
            super.addSubview(view)
        } else {
            childViews.append(view)
            if childViews.count == (totalCount?.intValue ?? -1) {
                transitionView.reloadData()
            }
        }
    }
}
