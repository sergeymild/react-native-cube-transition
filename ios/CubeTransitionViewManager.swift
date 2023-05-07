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

class CubeTransitionView : UIView, CubeTransitionViewDelegate {
    
    @objc
    var totalCount: NSNumber?
    
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
    
    override func didSetProps(_ changedProps: [String]!) {
        super.didSetProps(changedProps)
    }
    
    func pageView(atIndex: Int) -> UIView? {
        if atIndex < 0 { return nil }
        if atIndex >= childViews.count { return nil }
        let v = childViews[atIndex]
        v.frame = .init(origin: .zero, size: RCTScreenSize())
        return v
    }
    
    func numberofPages() -> Int {
        childViews.count
    }
    
    func pageDidChanged(index: Int, direction: Direction) {
        
    }
    
    
    override func addSubview(_ view: UIView) {
        if (view is CubeTransitionInfiniteView) {
            super.addSubview(view)
        } else {
            childViews.append(view)
            if childViews.count == (totalCount?.intValue ?? -1) {
                debugPrint("||----", childViews.count)
                transitionView.reloadData()
            }
        }
    }
}
