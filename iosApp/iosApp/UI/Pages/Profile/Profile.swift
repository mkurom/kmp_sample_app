import SwiftUI

struct Profile: View {
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                Image(systemName: "person.fill")
                    .font(.system(size: 56))
                    .foregroundColor(.orange)

                Text("Profile")
                    .font(.largeTitle)
                    .fontWeight(.bold)

                Text("This is the Profile screen implemented in SwiftUI.")
                    .font(.body)
                    .foregroundColor(.secondary)

                Spacer()
            }
            .padding()
            .navigationTitle("Profile")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.inline)
            #endif
        }
    }
}

struct ProfilePreviews: PreviewProvider {
    static var previews: some View {
        Profile()
    }
}
