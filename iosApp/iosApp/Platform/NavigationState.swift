import Foundation
import SwiftUI

enum AppDestination {
    case splash
    case main
}

class NavigationState: ObservableObject {
    @Published var currentDestination: AppDestination = .splash
    
    func moveToMain() {
        withAnimation {
            currentDestination = .main
        }
    }
}
