import SwiftUI

@main
struct iOSApp: App {
    @StateObject private var navigationState = NavigationState()
    
    var body: some Scene {
        WindowGroup {
            AppCoordinator(navigationState: navigationState)
        }
    }
}