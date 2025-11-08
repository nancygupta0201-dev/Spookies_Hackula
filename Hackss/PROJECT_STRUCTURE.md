# 📁 Spooky Ed - Project Structure Guide

## 🎯 Quick Navigation

This document shows you exactly where everything is located in the project.

---

## 🗂️ Complete Project Tree

```
Hackss/                                          # Root project folder
│
├── 📱 ANDROID APP (Native) - READY TO BUILD
│   │
│   ├── app/src/main/
│   │   ├── assets/                              # HTML files for the app
│   │   │   ├── home.html                        # ✅ Login/Home page
│   │   │   ├── about.html                       # ✅ About page
│   │   │   ├── contact.html                     # ✅ Contact page
│   │   │   ├── menu.html                        # ✅ Game menu
│   │   │   └── images/                          # Place your images here
│   │   │
│   │   ├── java/com/runanywhere/startup_hackathon20/
│   │   │   ├── SpookyEdActivity.kt              # ⭐ MAIN APP ACTIVITY
│   │   │   ├── MainActivity.kt                  # Original AI chat (still works)
│   │   │   ├── MyApplication.kt                 # App initialization
│   │   │   └── ChatViewModel.kt                 # For AI chat feature
│   │   │
│   │   ├── res/
│   │   │   ├── values/
│   │   │   │   ├── colors.xml                   # 🎨 Halloween colors
│   │   │   │   ├── strings.xml                  # 📝 App name: "Spooky Ed"
│   │   │   │   └── themes.xml                   # App theme
│   │   │   ├── mipmap-*/                        # App icons (change these!)
│   │   │   └── drawable/                        # Drawable resources
│   │   │
│   │   └── AndroidManifest.xml                  # ⚙️ Launcher config
│   │
│   ├── build.gradle.kts                         # App-level Gradle
│   └── proguard-rules.pro                       # ProGuard rules
│
├── 🦋 FLUTTER APP (Cross-Platform) - REQUIRES FLUTTER SDK
│   │
│   ├── flutter_spooky_ed/
│   │   ├── lib/
│   │   │   └── main.dart                        # ⭐ FLUTTER APP CODE
│   │   │
│   │   ├── assets/
│   │   │   ├── html/                            # HTML files (same as Android)
│   │   │   │   ├── home.html                    # ✅ Login/Home
│   │   │   │   ├── about.html                   # ✅ About
│   │   │   │   ├── contact.html                 # ✅ Contact
│   │   │   │   └── menu.html                    # ✅ Menu
│   │   │   └── images/                          # Image assets
│   │   │
│   │   ├── android/                             # Android platform config
│   │   ├── ios/                                 # iOS platform config
│   │   ├── pubspec.yaml                         # 📦 Dependencies
│   │   └── README.md                            # Flutter guide
│
├── 📚 DOCUMENTATION
│   ├── SPOOKY_ED_README.md                      # 📖 Complete guide (391 lines)
│   ├── QUICK_START_SPOOKY_ED.md                 # ⚡ Quick start (172 lines)
│   ├── IMPLEMENTATION_SUMMARY.md                # 📊 What was done (420 lines)
│   ├── PROJECT_STRUCTURE.md                     # 📁 This file
│   └── README.md                                # Original project README
│
├── 🔧 BUILD FILES
│   ├── build.gradle.kts                         # Root Gradle
│   ├── settings.gradle.kts                      # Gradle settings
│   ├── gradle.properties                        # Gradle properties
│   ├── gradlew                                  # Gradle wrapper (Unix)
│   └── gradlew.bat                              # Gradle wrapper (Windows)
│
└── 📄 OTHER FILES
    ├── local.properties                         # Local SDK paths
    └── Spookies                                 # Empty marker file
```

---

## 🎯 Key Files to Know

### For Android Development:

| File | Purpose | When to Edit |
|------|---------|--------------|
| `SpookyEdActivity.kt` | Main app activity | Add native features |
| `home.html` | Login page | Change login UI |
| `about.html` | About page | Update about text |
| `contact.html` | Contact page | Update contact info |
| `menu.html` | Game menu | Add/modify games |
| `colors.xml` | Color definitions | Change app colors |
| `strings.xml` | App strings | Change app name |
| `AndroidManifest.xml` | App configuration | Add permissions |

### For Flutter Development:

| File | Purpose | When to Edit |
|------|---------|--------------|
| `main.dart` | Flutter app code | Add features |
| `pubspec.yaml` | Dependencies | Add packages |
| `assets/html/*` | Web pages | Same as Android |

---

## 📍 Where to Find Things

### Want to change the app name?

```
app/src/main/res/values/strings.xml
```

### Want to change colors?

```
app/src/main/res/values/colors.xml
```

### Want to edit the home page?

```
app/src/main/assets/home.html
```

### Want to add a new page?

```
1. Create: app/src/main/assets/yourpage.html
2. Link from other pages: <a href="yourpage.html">Link</a>
```

### Want to add images?

```
1. Place in: app/src/main/assets/images/
2. Use in HTML: <img src="images/yourimage.png">
```

### Want to add native Android features?

```
Edit: app/src/main/java/.../SpookyEdActivity.kt
```

### Want to change app icon?

```
Replace files in: app/src/main/res/mipmap-*/
```

---

## 🚀 Build Commands Location

Run these from the **project root** directory (`Hackss/`):

### Android Commands:

```bash
# Build and install
./gradlew installDebug

# Build APK only
./gradlew assembleDebug

# Clean build
./gradlew clean

# Build release
./gradlew assembleRelease
```

### Flutter Commands (from `flutter_spooky_ed/`):

```bash
# Get dependencies
flutter pub get

# Run app
flutter run

# Build APK
flutter build apk --release

# Build iOS
flutter build ios --release
```

---

## 📦 Build Output Locations

### Android APK:

```
app/build/outputs/apk/debug/app-debug.apk           # Debug version
app/build/outputs/apk/release/app-release.apk       # Release version
```

### Android App Bundle:

```
app/build/outputs/bundle/release/app-release.aab
```

### Flutter APK:

```
flutter_spooky_ed/build/app/outputs/flutter-apk/app-release.apk
```

---

## 🎨 Asset Locations

### Images:

```
Android:  app/src/main/assets/images/
Flutter:  flutter_spooky_ed/assets/images/
```

### HTML Files:

```
Android:  app/src/main/assets/*.html
Flutter:  flutter_spooky_ed/assets/html/*.html
```

### Icons:

```
Android:  app/src/main/res/mipmap-*hdpi/
Flutter:  flutter_spooky_ed/android/app/src/main/res/mipmap-*/
          flutter_spooky_ed/ios/Runner/Assets.xcassets/
```

---

## 🔍 Important Directories Explained

### `app/src/main/`

The heart of your Android app. Everything your app needs at runtime.

### `app/src/main/assets/`

Files bundled with the app. Access via `file:///android_asset/`

### `app/src/main/res/`

Android resources (layouts, values, drawables, etc.)

### `app/build/`

Generated files. Can delete (auto-regenerated on build). Git-ignored.

### `flutter_spooky_ed/lib/`

Dart source code for Flutter app.

### `flutter_spooky_ed/build/`

Flutter build outputs. Can delete. Git-ignored.

---

## 🎯 File Relationships

```
SpookyEdActivity.kt
    │
    ├──► Loads: home.html (from assets)
    │
    └──► Enables: JavaScript, DOM Storage
                  │
                  └──► Used by: home.html, menu.html
                                │
                                └──► Stores: username (localStorage)
                                             │
                                             └──► Displayed in: menu.html
```

---

## 📊 File Sizes

### Android Project:

- **Source Code**: ~10 KB (Kotlin)
- **HTML Assets**: ~20 KB (4 files)
- **Resources**: ~500 KB (icons, etc.)
- **Dependencies**: Downloaded by Gradle
- **Built APK**: 5-10 MB (debug), 3-5 MB (release)

### Flutter Project:

- **Source Code**: ~5 KB (Dart)
- **HTML Assets**: ~20 KB (4 files)
- **Dependencies**: ~50 MB (first time)
- **Built APK**: 15-20 MB (release)

---

## 🔐 Git Structure

### Tracked Files:

- All source code (`.kt`, `.dart`)
- All resources (`.xml`, `.html`)
- Gradle files
- Documentation

### Ignored Files (`.gitignore`):

- `build/` directories
- `.gradle/` cache
- `local.properties`
- IDE files (`.idea/`, `.vscode/`)
- Generated files

---

## 🎯 Quick Reference Map

```
Need to...                          Go to...
───────────────────────────────────────────────────────────────
Change app name                  → app/src/main/res/values/strings.xml
Change colors                    → app/src/main/res/values/colors.xml
Edit login page                  → app/src/main/assets/home.html
Edit about page                  → app/src/main/assets/about.html
Edit contact page                → app/src/main/assets/contact.html
Edit game menu                   → app/src/main/assets/menu.html
Add images                       → app/src/main/assets/images/
Change app icon                  → app/src/main/res/mipmap-*/
Add native features              → SpookyEdActivity.kt
Build APK                        → ./gradlew assembleDebug
Find built APK                   → app/build/outputs/apk/
Read quick start                 → QUICK_START_SPOOKY_ED.md
Read full guide                  → SPOOKY_ED_README.md
Use Flutter version              → cd flutter_spooky_ed/
```

---

## 📱 Multiple Implementations

You have **TWO separate, complete apps** in this project:

### 1. Native Android (Current Project)

- **Root**: This entire project
- **Entry**: `SpookyEdActivity.kt`
- **Build**: Use Gradle commands from root

### 2. Flutter App (Separate Folder)

- **Root**: `flutter_spooky_ed/`
- **Entry**: `lib/main.dart`
- **Build**: Use Flutter commands from that folder

Both apps have the **same HTML content** and **identical functionality**.

---

## 🎓 Learning the Structure

### New to Android?

1. Start with: `SpookyEdActivity.kt`
2. Then look at: HTML files in `assets/`
3. Modify: `colors.xml` and see changes

### New to Flutter?

1. Start with: `flutter_spooky_ed/lib/main.dart`
2. Then check: `pubspec.yaml` for dependencies
3. Run: `flutter run` and see it work

---

## ✅ Structure Verification Checklist

Verify your project structure is correct:

- [ ] `app/src/main/assets/home.html` exists
- [ ] `app/src/main/assets/about.html` exists
- [ ] `app/src/main/assets/contact.html` exists
- [ ] `app/src/main/assets/menu.html` exists
- [ ] `SpookyEdActivity.kt` exists
- [ ] `flutter_spooky_ed/` folder exists
- [ ] All documentation files exist
- [ ] Can build with `./gradlew assembleDebug`

---

**Now you know where everything is! 🎃**

**Quick Start**: See `QUICK_START_SPOOKY_ED.md`  
**Full Guide**: See `SPOOKY_ED_README.md`
