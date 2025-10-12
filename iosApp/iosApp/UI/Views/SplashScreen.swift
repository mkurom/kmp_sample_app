import SwiftUI

struct SplashScreenView: View {
    let onComplete: () -> Void
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
            
            // スプラッシュ画面の表示時間後に完了を通知
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
                onComplete()
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
    }
}

#Preview {
    SplashScreenView {
        print("Splash completed")
    }
}