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

struct ContentView: View {
    @State private var showSwiftUIScreen = false
    
    var body: some View {
        NavigationView {
            VStack {
                ComposeView(onNavigateToSwiftUI: {
                    showSwiftUIScreen = true
                })
                .ignoresSafeArea()
            }
            .sheet(isPresented: $showSwiftUIScreen) {
                SwiftUIScreen()
            }
        }
    }
}



