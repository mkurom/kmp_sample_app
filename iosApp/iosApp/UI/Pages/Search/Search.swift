import SwiftUI

struct Search: View {
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                Image(systemName: "magnifyingglass")
                    .font(.system(size: 56))
                    .foregroundColor(.green)

                Text("Search")
                    .font(.largeTitle)
                    .fontWeight(.bold)

                Text("This is the Search screen implemented in SwiftUI.")
                    .font(.body)
                    .foregroundColor(.secondary)

                Spacer()
            }
            .padding()
            .navigationTitle("Search")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.inline)
            #endif
        }
    }
}

struct SearchPreviews: PreviewProvider {
    static var previews: some View {
        Search()
    }
}
