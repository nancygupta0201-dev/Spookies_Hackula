# Spooky Ed - Mobile App Conversion

This project contains **two complete implementations** of the Spooky Ed Halloween-themed educational
platform as mobile apps:

1. **Native Android App** (using Jetpack Compose + WebView) - Ready to build and run
2. **Flutter App** (cross-platform) - Requires Flutter SDK installation

---

## 🎃 What is Spooky Ed?

Spooky Ed is a Halloween-themed educational platform that makes learning fun through spooky games
and interactive activities. Students can engage with challenges while teachers track progress and
customize content.

### Features:

- **Student & Teacher Modes**: Toggle between student and teacher interfaces
- **Interactive Games**: Trick or Answer, Potion Brewing, and more
- **User Authentication**: Simple login system with username tracking
- **Navigation**: Seamless navigation between Home, About, Contact, and Menu pages
- **Responsive Design**: Beautiful Halloween-themed UI with custom styling

---

## 📱 Option 1: Native Android App (Current Implementation)

This is the **ready-to-use** implementation in the current Android Studio project.

### Project Structure:

```
app/src/main/
├── assets/
│   ├── home.html          # Login/Home page
│   ├── about.html         # About Spooky Ed
│   ├── contact.html       # Contact information
│   └── menu.html          # Game selection menu
├── java/com/runanywhere/startup_hackathon20/
│   ├── SpookyEdActivity.kt    # Main WebView activity
│   ├── MainActivity.kt        # Original AI chat (still available)
│   └── MyApplication.kt       # App initialization
└── res/
    └── values/
        ├── colors.xml         # Halloween-themed colors
        └── strings.xml        # App strings
```

### How to Build & Run:

#### Method 1: Android Studio

1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Click the green "Run" button or press `Shift + F10`
4. Select your device/emulator
5. The app will launch with Spooky Ed as the main screen

#### Method 2: Command Line

```bash
# Build debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Or combine both
./gradlew installDebug
```

The APK will be generated at: `app/build/outputs/apk/debug/app-debug.apk`

### Technical Details:

#### WebView Configuration:

- **JavaScript enabled**: For interactive features
- **DOM Storage enabled**: For localStorage (username tracking)
- **File access enabled**: To load HTML assets
- **Custom navigation**: Handles back button correctly

#### Key Android Components:

- **SpookyEdActivity**: Main activity using Jetpack Compose with WebView
- **Theme**: Material 3 theme with Halloween colors
- **Permissions**: INTERNET permission for loading external resources (fonts)

### Features Implemented:

✅ Full website functionality in mobile app  
✅ Student/Teacher mode toggle  
✅ Login with username persistence (localStorage)  
✅ Navigation between all pages  
✅ Game menu with placeholder integration points  
✅ Account dropdown with logout  
✅ Back button navigation support  
✅ Halloween-themed UI

---

## 🦋 Option 2: Flutter App (Cross-Platform)

This implementation allows you to build for **both Android and iOS** from a single codebase.

### Location:

```
flutter_spooky_ed/
├── lib/
│   └── main.dart          # Flutter app code
├── assets/
│   ├── html/              # HTML files (copied from Android assets)
│   └── images/            # Image assets
├── android/               # Android-specific configuration
├── ios/                   # iOS-specific configuration
└── pubspec.yaml           # Flutter dependencies
```

### Prerequisites:

Install Flutter SDK:

1. Download Flutter from: https://docs.flutter.dev/get-started/install/windows
2. Extract and add to PATH
3. Run `flutter doctor` to check setup

### How to Build & Run:

#### Setup (First Time Only):

```bash
cd flutter_spooky_ed

# Get dependencies
flutter pub get

# Check your setup
flutter doctor
```

#### Run on Android:

```bash
# Connect Android device or start emulator
flutter run

# Or build APK
flutter build apk --release
# APK location: build/app/outputs/flutter-apk/app-release.apk
```

#### Run on iOS (macOS only):

```bash
# Run on iOS simulator
flutter run

# Or build IPA
flutter build ios --release
```

### Flutter Dependencies:

- `webview_flutter`: ^4.4.2 - For WebView support
- `shared_preferences`: ^2.2.2 - For data persistence
- `cupertino_icons`: ^1.0.2 - iOS icons

### Advantages of Flutter Version:

✅ **Cross-platform**: Build for Android & iOS with same code  
✅ **Hot reload**: Faster development cycle  
✅ **Native performance**: Compiles to native code  
✅ **Smaller app size**: Typically smaller than native apps  
✅ **Modern UI framework**: Easy to customize and extend

---

## 🎨 Customization Guide

### Adding New HTML Pages:

#### For Android App:

1. Create HTML file in `app/src/main/assets/`
2. Include embedded CSS (external CSS not supported in assets)
3. Link from other pages using: `<a href="newpage.html">Link</a>`
4. Rebuild the app

#### For Flutter App:

1. Create HTML file in `flutter_spooky_ed/assets/html/`
2. Update `pubspec.yaml` if adding new asset directories
3. Update navigation logic in `main.dart` if needed
4. Run `flutter pub get` and rebuild

### Changing Colors:

#### Android:

Edit `app/src/main/res/values/colors.xml`:

```xml
<color name="purple">#FFD400FF</color>
<color name="cyan">#FF00FFD5</color>
<color name="student_mode">#D400FF</color>
<color name="teacher_mode">#2196F3</color>
```

#### Flutter:

Edit theme in `flutter_spooky_ed/lib/main.dart`:

```dart
theme: ThemeData(
  primarySwatch: Colors.purple,  // Change this
  useMaterial3: true,
),
```

### Adding Images:

#### Android:

1. Place images in `app/src/main/assets/images/`
2. Reference in HTML: `<img src="images/yourimage.png">`

#### Flutter:

1. Place images in `flutter_spooky_ed/assets/images/`
2. Ensure listed in `pubspec.yaml` under assets
3. Reference in HTML: `<img src="images/yourimage.png">`

---

## 🔧 Integrating Games

The menu page has placeholder game launch functions. To integrate actual games:

### Option 1: New Activities (Android)

```kotlin
// In menu.html, add Android interface
function launchGame(gameNumber) {
    Android.launchGame(gameNumber);
}

// In SpookyEdActivity.kt, add JavaScript interface
webView.addJavascriptInterface(object {
    @JavascriptInterface
    fun launchGame(gameNumber: Int) {
        when(gameNumber) {
            1 -> startActivity(Intent(this@SpookyEdActivity, TrickOrAnswerActivity::class.java))
            2 -> startActivity(Intent(this@SpookyEdActivity, PotionBrewingActivity::class.java))
        }
    }
}, "Android")
```

### Option 2: New HTML Pages

Simply create new HTML game pages and link to them:

```html
<a href="trick_or_answer.html">Play Game</a>
```

---

## 📦 Building Production Apps

### Android (APK):

```bash
# Debug version
./gradlew assembleDebug

# Release version (requires signing)
./gradlew assembleRelease
```

### Flutter (Android APK):

```bash
cd flutter_spooky_ed
flutter build apk --release --split-per-abi
```

### Flutter (iOS):

```bash
cd flutter_spooky_ed
flutter build ios --release
# Then open in Xcode to archive and upload
```

---

## 🐛 Troubleshooting

### Android App Issues:

**WebView not loading pages:**

- Check that HTML files are in `app/src/main/assets/`
- Verify INTERNET permission in AndroidManifest.xml
- Check JavaScript is enabled in WebView settings

**Back button not working:**

- `BackHandler` in Compose tracks WebView history
- Ensure `canGoBack` state is properly updated

**localStorage not working:**

- Verify `domStorageEnabled = true` in WebView settings
- Check browser compatibility in HTML files

### Flutter App Issues:

**flutter command not found:**

- Add Flutter to PATH: `C:\path\to\flutter\bin`
- Restart terminal/IDE after PATH update

**Pub get fails:**

- Check internet connection
- Try: `flutter pub cache repair`
- Update Flutter: `flutter upgrade`

**WebView not loading:**

- Ensure HTML files are in correct asset folder
- Check `pubspec.yaml` includes asset paths
- Run `flutter clean` and rebuild

---

## 📱 App Information

**Current Implementation**: Native Android with WebView  
**App Name**: Spooky Ed  
**Package**: com.runanywhere.startup_hackathon20  
**Launcher Activity**: SpookyEdActivity  
**Target SDK**: Android 7.0+ (API 24+)

**Flutter Implementation**: Ready to build  
**Package**: spooky_ed  
**Platforms**: Android 5.0+, iOS 12.0+

---

## 🎯 Next Steps

1. **Test the Android app**: Build and run the current implementation
2. **Customize content**: Update HTML pages with your specific content
3. **Add game implementations**: Create actual game activities/pages
4. **Design app icon**: Replace default launcher icons
5. **Set up Flutter** (optional): For iOS support
6. **Publish**: Prepare for Google Play Store / Apple App Store

---

## 📄 Original Website Files

The original website files are located at:

- `C:/Users/navee/OneDrive/Attachments/Desktop/Hackula/`

These have been converted and integrated into the mobile apps with all styling embedded for optimal
mobile performance.

---

## 🎃 Credits

**Creators**: Nancy Gupta, Ravi Prakash  
**Contact**: spookyed_creator@gmail.com  
**Address**: 123 Spooky Lane, Halloween City, HA 45678

---

## 📞 Support

For any issues or questions about the mobile app implementation, the original creators can be
reached at the contact information above.

**Happy Coding! 🎃👻🧙‍♀️**
