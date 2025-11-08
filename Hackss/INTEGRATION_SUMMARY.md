# Integration Summary: Trick or Answer APK

## Overview

Successfully integrated the external **Trick_or_answer2.apk** with the Hackss (Spooky Ed)
application. When users click on "Trick or Answer" (option1) in the menu, it now launches the
external game app instead of the placeholder activity.

## Changes Made

### 1. Modified GameLauncher Class

**File:** `Hackss/app/src/main/java/com/runanywhere/startup_hackathon20/SpookyEdActivity.kt`

The `GameLauncher` class was updated to:

- Launch the external APK (package: `com.Trick_or_answered`) when game 1 (Trick or Answer) is
  selected
- Show a user-friendly error message if the app is not installed
- Keep the internal `PotionBrewingActivity` for game 2

```kotlin
when (gameNumber) {
    1 -> {
        // Launch external Trick or Answer APK
        val packageName = "com.Trick_or_answered"
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else {
            // Show error if app not installed
            Toast.makeText(context, 
                "Trick or Answer game not installed. Please install it first.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    2 -> {
        // Launch internal Potion Brewing activity
        val intent = Intent(context, PotionBrewingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
```

### 2. Updated AndroidManifest.xml

**File:** `Hackss/app/src/main/AndroidManifest.xml`

Added package visibility declaration to allow querying the external app (required for Android 11+):

```xml
<queries>
    <package android:name="com.Trick_or_answered" />
</queries>
```

### 3. Updated Gradle Configuration

**File:** `Hackss/gradle.properties`

Set Java 17 for Gradle build:

```properties
org.gradle.java.home=C\:\\Program Files\\Java\\jdk-17
```

**File:** `Hackss/local.properties`

Updated SDK path to current user:

```properties
sdk.dir=C\:\\Users\\Ravi\\AppData\\Local\\Android\\Sdk
```

## APK Information

### Trick_or_answer2.apk

- **Package Name:** com.Trick_or_answered
- **Main Activity:** com.godot.game.GodotApp
- **Engine:** Built with Godot Engine
- **Status:** Installed on device

### Hackss (Spooky Ed)

- **Package Name:** com.runanywhere.startup_hackathon20
- **Main Activity:** SpookyEdActivity
- **Status:** Rebuilt and installed with integration

## How It Works

1. User opens the Spooky Ed app
2. User navigates to the menu page (menu.html)
3. User clicks on "Trick or Answer" game icon (Game 1)
4. JavaScript calls `GameLauncher.launchGame(1)`
5. The GameLauncher checks if the Trick_or_answer2 app is installed
6. If installed: Launches the external game
7. If not installed: Shows error message prompting user to install it

## Testing

Both apps are currently installed on the device:

- ✅ Spooky Ed (Hackss) - v1.0 (updated)
- ✅ Trick or Answer - v1.0.0

To test:

1. Launch the Spooky Ed app
2. Navigate through the menu
3. Click on "Trick or Answer" icon
4. The external Trick or Answer game should launch

## Future Enhancements

Potential improvements:

1. Add a deep link to Google Play Store if the app is not installed
2. Add a "download" button in the error message
3. Implement in-app APK installation (requires additional permissions)
4. Add version checking to ensure compatible versions are installed

## Build Commands

To rebuild the Hackss app:

```bash
cd Hackss
.\gradlew.bat assembleDebug --no-daemon
```

To install:

```bash
C:\Users\Ravi\AppData\Local\Android\Sdk\platform-tools\adb.exe install -r "app\build\outputs\apk\debug\app-debug.apk"
```

## Notes

- The integration uses Android's standard package manager to launch installed apps
- No modifications were needed to the Trick_or_answer2.apk itself
- The internal TrickOrAnswerActivity placeholder is still present but no longer used for game 1
- The menu.html and WebView setup remain unchanged
