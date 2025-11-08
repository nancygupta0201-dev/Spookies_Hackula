# Spooky Ed - Flutter App

A cross-platform mobile app for the Spooky Ed Halloween-themed educational platform.

## 🎃 About

This is the Flutter implementation of Spooky Ed, which allows you to build for both **Android** and
**iOS** from a single codebase. The app uses WebView to display the Halloween-themed educational
content with full interactivity.

## 🚀 Quick Start

### Prerequisites

1. **Install Flutter SDK**
    - Download from: https://docs.flutter.dev/get-started/install
    - Add Flutter to your system PATH
    - Verify installation: `flutter doctor`

2. **Install IDE** (Choose one)
    - Android Studio with Flutter plugin
    - VS Code with Flutter extension
    - IntelliJ IDEA with Flutter plugin

3. **Set up platforms**
    - **For Android**: Install Android SDK
    - **For iOS** (macOS only): Install Xcode

### Installation

```bash
# Navigate to project directory
cd flutter_spooky_ed

# Get dependencies
flutter pub get

# Verify setup
flutter doctor
```

### Running the App

```bash
# Run on connected device/emulator
flutter run

# Run in release mode
flutter run --release

# Run on specific device
flutter devices  # List available devices
flutter run -d <device-id>
```

### Building APK/IPA

```bash
# Build Android APK
flutter build apk --release

# Build Android App Bundle (for Play Store)
flutter build appbundle --release

# Build iOS (macOS only)
flutter build ios --release
```

## 📁 Project Structure

```
flutter_spooky_ed/
├── lib/
│   └── main.dart              # Main app code
├── assets/
│   ├── html/
│   │   ├── home.html          # Login/Home page
│   │   ├── about.html         # About page
│   │   ├── contact.html       # Contact page
│   │   └── menu.html          # Game menu
│   └── images/                # Image assets
├── android/                   # Android platform files
├── ios/                       # iOS platform files
├── pubspec.yaml              # Dependencies and assets
└── README.md                 # This file
```

## 📦 Dependencies

- **webview_flutter**: For displaying web content
- **shared_preferences**: For storing user data
- **cupertino_icons**: iOS-style icons

## ✨ Features

- ✅ Cross-platform (Android & iOS)
- ✅ Student/Teacher mode toggle
- ✅ User login with persistent username
- ✅ Navigation between pages
- ✅ Halloween-themed UI
- ✅ Game menu interface
- ✅ Account management
- ✅ Back button support

## 🎨 Customization

### Adding New Pages

1. Create HTML file in `assets/html/`
2. Update navigation logic in `lib/main.dart`:

```dart
onNavigationRequest: (NavigationRequest request) {
  if (request.url.contains('yourpage.html')) {
    return NavigationDecision.navigate;
  }
  return NavigationDecision.prevent;
}
```

3. Run `flutter pub get` and restart

### Changing Theme

Edit `lib/main.dart`:

```dart
theme: ThemeData(
  primarySwatch: Colors.purple,  // Change color
  brightness: Brightness.light,   // or Brightness.dark
  useMaterial3: true,
),
```

### Adding Assets

1. Place files in appropriate `assets/` subdirectory
2. Update `pubspec.yaml`:

```yaml
flutter:
  assets:
    - assets/html/
    - assets/images/
    - assets/your_new_folder/
```

3. Run `flutter pub get`

## 🐛 Troubleshooting

### Common Issues

**Issue**: `flutter: command not found`  
**Solution**: Add Flutter to PATH and restart terminal

**Issue**: WebView not displaying content  
**Solution**:

- Ensure HTML files are in `assets/html/`
- Check `pubspec.yaml` includes asset paths
- Run `flutter clean` then `flutter pub get`

**Issue**: Build errors on Android  
**Solution**:

```bash
cd android
./gradlew clean
cd ..
flutter clean
flutter pub get
```

**Issue**: iOS build fails  
**Solution**:

```bash
cd ios
pod install
cd ..
flutter clean
flutter build ios
```

## 📱 Platform-Specific Configuration

### Android

Minimum SDK is set to 21 (Android 5.0). To change:

- Edit `android/app/build.gradle`
- Modify `minSdkVersion`

### iOS

Minimum iOS version is 12.0. To change:

- Edit `ios/Podfile`
- Modify `platform :ios, '12.0'`

## 🚀 Deployment

### Google Play Store (Android)

1. Create a keystore for signing:

```bash
keytool -genkey -v -keystore ~/spooky-ed-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias spooky-ed
```

2. Create `android/key.properties`:

```properties
storePassword=<your-store-password>
keyPassword=<your-key-password>
keyAlias=spooky-ed
storeFile=<path-to-keystore>
```

3. Build signed APK:

```bash
flutter build appbundle --release
```

4. Upload to Play Console

### Apple App Store (iOS)

1. Open Xcode:

```bash
open ios/Runner.xcworkspace
```

2. Configure signing & capabilities
3. Archive the app
4. Upload to App Store Connect

## 📊 Performance

- **App Size**: ~15-20 MB (release build)
- **Cold Start**: <2 seconds
- **Hot Reload**: <1 second (development)

## 🔐 Permissions

### Android (`android/app/src/main/AndroidManifest.xml`)

- INTERNET: For loading external resources

### iOS (`ios/Runner/Info.plist`)

- NSAppTransportSecurity: For loading web content

## 🧪 Testing

```bash
# Run unit tests
flutter test

# Run widget tests
flutter test test/widget_test.dart

# Run integration tests
flutter drive --target=test_driver/app.dart
```

## 📝 License

This project is part of the Spooky Ed educational platform.

## 👥 Credits

**Creators**: Nancy Gupta, Ravi Prakash  
**Contact**: spookyed_creator@gmail.com

## 🆘 Support

For issues specific to this Flutter implementation, check:

- Flutter Documentation: https://docs.flutter.dev
- Flutter Community: https://flutter.dev/community

For Spooky Ed content questions, contact the creators above.

---

**Happy Coding! 🎃👻🧙‍♀️**
