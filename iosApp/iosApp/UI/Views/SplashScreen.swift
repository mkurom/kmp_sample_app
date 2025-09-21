import SwiftUI

struct SplashScreen: View {
    @State private var isActive = false

    var body: some View {
        if isActive {
            MainNavigationView()
        } else {
            SplashScreenView(isActive: $isActive)
        }
    }
}

struct SplashScreenView: View {
    @Binding var isActive: Bool
    @State private var opacity = 0.5

    var body: some View {
        VStack {
            Image(systemName: "globe")
                .font(.system(size: 100))
                .foregroundColor(.accentColor)
            Text("Welcome to Compose Demo")
                .font(.largeTitle)
                .fontWeight(.bold)
        }
        .opacity(opacity)
        .onAppear {
            withAnimation(.easeIn(duration: 1.0)) {
                self.opacity = 1.0
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
                withAnimation {
                    self.isActive = true
                }
            }
        }
    }
}

#Preview {
    SplashScreen()
}