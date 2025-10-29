import UIKit
import GoogleMaps

class MapViewController: UIViewController {

    @objc dynamic var latitude: Double = 35.6762
    @objc dynamic var longitude: Double = 139.6503
    @objc dynamic var zoom: Float = 10.0

    private var mapView: GMSMapView?

    override func viewDidLoad() {
        super.viewDidLoad()

        self.view.backgroundColor = .red

        let newMapView = GMSMapView()
        newMapView.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(newMapView)
        self.mapView = newMapView

        NSLayoutConstraint.activate([
            newMapView.topAnchor.constraint(equalTo: self.view.topAnchor),
            newMapView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            newMapView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            newMapView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor)
        ])

        let camera = GMSCameraPosition.camera(
            withLatitude: latitude,
            longitude: longitude,
            zoom: zoom
        )
        newMapView.camera = camera

        addObserver(self, forKeyPath: #keyPath(latitude), options: .new, context: nil)
        addObserver(self, forKeyPath: #keyPath(longitude), options: .new, context: nil)
        addObserver(self, forKeyPath: #keyPath(zoom), options: .new, context: nil)
    }

    deinit {
        removeObserver(self, forKeyPath: #keyPath(latitude))
        removeObserver(self, forKeyPath: #keyPath(longitude))
        removeObserver(self, forKeyPath: #keyPath(zoom))
    }

    override func observeValue(forKeyPath keyPath: String?, of object: Any?, change: [NSKeyValueChangeKey : Any]?, context: UnsafeMutableRawPointer?) {
        updateMap()
    }

    private func updateMap() {
        guard let mapView = self.mapView else { return }
        let camera = GMSCameraPosition.camera(
            withLatitude: latitude,
            longitude: longitude,
            zoom: zoom
        )
        mapView.camera = camera
    }
}
