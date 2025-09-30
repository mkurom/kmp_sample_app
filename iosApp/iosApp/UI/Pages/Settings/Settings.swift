import SwiftUI

struct Settings: View {
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                Image(systemName: "gear")
                    .font(.system(size: 56))
                    .foregroundColor(.purple)

                Text("Settings")
                    .font(.largeTitle)
                    .fontWeight(.bold)

                Text("This is the Settings screen implemented in SwiftUI.")
                    .font(.body)
                    .foregroundColor(.secondary)

                Spacer()
            }
            .padding()
            .navigationTitle("Settings")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.inline)
            #endif
        }
    }
}

struct SettingsPreviews: PreviewProvider {
    static var previews: some View {
        Settings()
    }
}
