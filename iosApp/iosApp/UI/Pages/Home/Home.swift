import SwiftUI

struct Home: View {
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                Image(systemName: "house.fill")
                    .font(.system(size: 56))
                    .foregroundColor(.blue)

                Text("Home")
                    .font(.largeTitle)
                    .fontWeight(.bold)

                Text("This is the Home screen implemented in SwiftUI.")
                    .font(.body)
                    .foregroundColor(.secondary)

                Spacer()
            }
            .padding()
            .navigationTitle("Home")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.inline)
            #endif
        }
    }
}

struct HomePreviews: PreviewProvider {
    static var previews: some View {
        Home()
    }
}


