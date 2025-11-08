# Testing the Trick or Answer Integration

## Quick Test Steps

### 1. Launch Spooky Ed App

The app should be running on your device now. You can also launch it using:

```bash
adb shell am start -n com.runanywhere.startup_hackathon20/.SpookyEdActivity
```

### 2. Navigate to Menu

1. On the home page, enter a username (any name)
2. Click the button to proceed to the menu

### 3. Test Option 1 (Trick or Answer)

1. In the menu, you'll see two game options
2. Click on "Trick or Answer" (Game 1 icon on the left)
3. The external Trick_or_answer2 game should launch
4. This confirms the integration is working!

### 4. Test Option 2 (Potion Brewing)

1. Go back to the Spooky Ed menu
2. Click on "Potion Brewing" (Game 2 icon on the right)
3. The internal placeholder game should launch

## What to Verify

### ✅ Successful Integration Indicators:

- Clicking "Trick or Answer" launches a separate game app (Godot-based)
- The game has its own UI and gameplay
- You can return to Spooky Ed menu using the back button
- No error messages appear

### ❌ Potential Issues:

- If you see "Game not installed" error → Reinstall Trick_or_answer2.apk
- If app crashes → Check logcat for errors
- If nothing happens → Check if WebView JavaScript interface is working

## Reinstalling Apps (if needed)

### Reinstall Trick or Answer:

```bash
adb install -r Trick_or_answer2.apk
```

### Reinstall Spooky Ed:

```bash
cd Hackss
.\gradlew.bat assembleDebug --no-daemon
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

## Debugging

### Check if apps are installed:

```bash
adb shell pm list packages | findstr "Trick_or_answered startup_hackathon20"
```

### View real-time logs:

```bash
adb logcat | findstr "GameLauncher"
```

### Check WebView logs:

```bash
adb logcat | findstr "SpookyEdWebView"
```

## Expected Behavior

**Menu Screen:**

- Two game icons visible: "Trick or Answer" and "Potion Brewing"
- Account icon in top-right corner
- "Spooky Ed" title at the top

**Clicking Trick or Answer:**

- Brief transition
- External Godot game launches
- Game runs independently
- Can use device back button to return

**Clicking Potion Brewing:**

- Internal placeholder screen appears
- Shows "Coming Soon" message
- Has "Back to Menu" button

## Current Status

✅ **Integration Complete:**

- Option1 (Trick or Answer) → Launches external APK (com.Trick_or_answered)
- Option2 (Potion Brewing) → Launches internal activity
- Both apps installed on device
- Build successful
- Installation successful

🎮 **Ready to Test!**
