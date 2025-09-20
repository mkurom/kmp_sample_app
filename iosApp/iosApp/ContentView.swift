import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    let onNavigateToSwiftUI: () -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(onNavigateToSwiftUI: onNavigateToSwiftUI)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

// SwiftUIで実装する新しい画面
struct SwiftUIScreen: View {
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationView {
            VStack(spacing: 20) {
                Text("SwiftUI Screen")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                
                Text("This is a native SwiftUI screen")
                    .font(.body)
                    .foregroundColor(.secondary)
                
                Button("Go Back to Compose") {
                    dismiss()
                }
                .buttonStyle(.borderedProminent)
                
                Spacer()
            }
            .padding()
            .navigationTitle("SwiftUI")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct TabBar: View {
    @ObservedObject var navigationState: IOSNavigationState
    let onNavigateToSwiftUI: () -> Void
    
    var body: some View {
        TabView(selection: $navigationState.selectedTab) {
            ForEach(IOSTabItem.allCases, id: \.rawValue) { tab in
                TabContentView(
                    tab: tab,
                    onNavigateToSwiftUI: onNavigateToSwiftUI
                )
                .tabItem {
                    Image(systemName: tab.icon)
                    Text(tab.title)
                }
                .tag(tab.rawValue)
            }
        }
        .accentColor(.primary)
        .background(.ultraThinMaterial) // Liquid Glass効果
        .preferredColorScheme(.light)
    }
}

struct TabContentView: View {
    let tab: IOSTabItem
    let onNavigateToSwiftUI: () -> Void
    
    var body: some View {
        NavigationView {
            VStack(spacing: 20) {
                Image(systemName: tab.icon)
                    .font(.system(size: 60))
                    .foregroundColor(tab.color)
                
                Text(tab.title)
                    .font(.largeTitle)
                    .fontWeight(.bold)
                
                Text("This is the \(tab.title.lowercased()) screen")
                    .font(.body)
                    .foregroundColor(.secondary)
                
                // Home画面でのみSwiftUI遷移ボタンを表示
                if tab == .home {
                    Button("Open SwiftUI Screen") {
                        onNavigateToSwiftUI()
                    }
                    .buttonStyle(.borderedProminent)
                    .padding()
                }
                
                Spacer()
            }
            .padding()
            .navigationTitle(tab.title)
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct ContentView: View {
    @StateObject private var navigationState = IOSNavigationState()
    @State private var showSwiftUIScreen = false
    @State private var showNativeTabBar = false
    
    var body: some View {
        if showNativeTabBar {
            TabBar(
                navigationState: navigationState,
                onNavigateToSwiftUI: {
                    showSwiftUIScreen = true
                }
            )
            .sheet(isPresented: $showSwiftUIScreen) {
                SwiftUIScreen()
            }
        } else {
            ComposeView(onNavigateToSwiftUI: {
                showNativeTabBar = true
            })
            .ignoresSafeArea()
        }
    }
}
