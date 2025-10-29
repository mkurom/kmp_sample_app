import SwiftUI
import ComposeApp
import GoogleMaps

@main
struct iOSApp: App {
    
    init() {
        guard let apiKey = Bundle.main.object(forInfoDictionaryKey: "GMSApiKey") as? String else {
            fatalError("GMSApiKey is not set in Info.plist")
        }
        GMSServices.provideAPIKey(apiKey)
    }

    var body: some Scene {
        WindowGroup {
            ComposeView()
        }
    }
}

struct ComposeView: UIViewControllerRepresentable {
    // 2. 必須のメソッド。作成したいViewControllerを返すメソッドを実装する
    func makeUIViewController(context: Context) -> UIViewController {
        let mapViewController = MapViewController()
        return MainViewControllerKt.MainViewController(mapViewController: mapViewController)
    }

    // 3. 必須のメソッド。Viewが更新された場合に必要な処理を実装する
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}

    // // 4. Coordinatorのファクトリーメソッドを実装する。
    // func makeCoordinator() -> Coordinator {
    //     Coordinator()
    // }

    // // 5. Coordinatorを定義する
    // class Coordinator {

    // }
}