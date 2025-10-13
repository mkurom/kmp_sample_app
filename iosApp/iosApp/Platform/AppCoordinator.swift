import SwiftUI

struct AppCoordinator: View {
    @EnvironmentObject var navigationState: NavigationState
    
    var body: some View {
        switch navigationState.currentDestination {
        case .splash:
            SplashScreenView(onComplete: navigationState.moveToMain)
        case .main:
            MainNavigationView()
        }
    }
}
