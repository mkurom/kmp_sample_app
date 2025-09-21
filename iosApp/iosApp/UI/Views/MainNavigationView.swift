import SwiftUI
import UIKit
import ComposeApp

struct MainNavigationView: View {
    @State private var selectedTab: TabItem = .home
    
    var body: some View {
        TabView(selection: $selectedTab) {
            ForEach(TabItem.allCases, id: \.rawValue) { tab in
                NavigationView {
                    ComposeScreenView(screenType: tab)
                        .navigationTitle(tab.title)
                        .navigationBarTitleDisplayMode(.inline)
                }
                .tabItem {
                    Image(systemName: tab.icon)
                    Text(tab.title)
                }
                .tag(tab)
            }
        }
        .accentColor(.blue)
    }
}

enum TabItem: String, CaseIterable {
    case home = "home"
    case search = "search"
    case profile = "profile"
    case settings = "settings"
    
    var title: String {
        rawValue.capitalized
    }
    
    var icon: String {
        switch self {
        case .home: return "house.fill"
        case .search: return "magnifyingglass"
        case .profile: return "person.fill"
        case .settings: return "gearshape.fill"
        }
    }
    
    var color: Color {
        switch self {
        case .home: return .blue
        case .search: return .green
        case .profile: return .orange
        case .settings: return .gray
        }
    }
}

struct ComposeScreenView: UIViewControllerRepresentable {
    let screenType: TabItem
    
    func makeUIViewController(context: Context) -> UIViewController {
        return ComposeScreenViewControllerKt.createComposeScreen(screenType: screenType.rawValue)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
