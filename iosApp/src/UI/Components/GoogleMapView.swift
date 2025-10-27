import SwiftUI
import GoogleMaps

struct GoogleMapView: UIViewRepresentable {
    func makeUIView(context: Context) -> GMSMapView {
        guard let apiKey = Bundle.main.object(forInfoDictionaryKey: "GMSApiKey") as? String else {
            fatalError("GMSApiKey is not set in Info.plist")
        }

        GMSServices.provideAPIKey(apiKey)
        
        // 東京を中心としたカメラ位置を設定
        let camera = GMSCameraPosition.camera(
            withLatitude: 35.6762,
            longitude: 139.6503,
            zoom: 10.0
        )
        
        let mapView = GMSMapView()
        mapView.camera = camera
        return mapView
    }
    
    func updateUIView(_ uiView: GMSMapView, context: Context) {
        // 必要に応じて更新処理を追加
    }
}

struct GoogleMapView_Previews: PreviewProvider {
    static var previews: some View {
        GoogleMapView()
    }
}
