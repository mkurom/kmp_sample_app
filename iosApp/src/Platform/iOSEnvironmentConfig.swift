import Foundation

class iOSEnvironmentConfig {
    static let shared = iOSEnvironmentConfig()
    
    private init() {}
    
    var apiBaseUrl: String {
        return Bundle.main.object(forInfoDictionaryKey: "API_BASE_URL") as? String ?? "https://api.example.com"
    }
    
    var environmentName: String {
        return Bundle.main.object(forInfoDictionaryKey: "ENVIRONMENT_NAME") as? String ?? "Production"
    }
    
    var bundleIdSuffix: String {
        return Bundle.main.object(forInfoDictionaryKey: "BUNDLE_ID_SUFFIX") as? String ?? ""
    }
    
    var isDebug: Bool {
        #if DEBUG
        return true
        #else
        return false
        #endif
    }
}
