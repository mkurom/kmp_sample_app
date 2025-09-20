import Foundation
import SwiftUI

// iOS側のナビゲーション状態管理
class IOSNavigationState: ObservableObject {
    @Published var selectedTab: Int = 0
    
    func selectTab(_ tab: Int) {
        selectedTab = tab
    }
}

// iOS側のタブ定義
enum IOSTabItem: Int, CaseIterable {
    case home = 0
    case search = 1
    case profile = 2
    case settings = 3
    
    var title: String {
        switch self {
        case .home: return "Home"
        case .search: return "Search"
        case .profile: return "Profile"
        case .settings: return "Settings"
        }
    }
    
    var icon: String {
        switch self {
        case .home: return "house.fill"
        case .search: return "magnifyingglass"
        case .profile: return "person.fill"
        case .settings: return "gear"
        }
    }
    
    var color: Color {
        switch self {
        case .home: return .blue
        case .search: return .green
        case .profile: return .orange
        case .settings: return .purple
        }
    }
}
