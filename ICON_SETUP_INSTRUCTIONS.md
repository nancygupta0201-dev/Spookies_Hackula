# Setting Up Spooky Ed Icon

## Quick Setup Using Icon Kitchen (Recommended - 5 minutes)

### Step 1: Save the Icon Image

Save the Spooky Ed icon image (the pumpkin with graduation cap) that was shared to your computer as
`spooky_ed_icon.png`

### Step 2: Generate Android Icons

1. Go to **https://icon.kitchen/**
2. Click on the **"Image"** tab
3. Upload `spooky_ed_icon.png`
4. Adjust settings:
    - Shape: Rounded Square or Square
    - Padding: 10-20% (to ensure the icon fits nicely)
5. Click **"Download"**
6. Extract the downloaded ZIP file

### Step 3: Copy Icons to Project

From the extracted folder, copy the icon files:

**For the main launcher app (edu_fun_2):**

```
Copy from: android/res/mipmap-*
To: app/src/main/res/
```

Replace all files in:

- `app/src/main/res/mipmap-mdpi/`
- `app/src/main/res/mipmap-hdpi/`
- `app/src/main/res/mipmap-xhdpi/`
- `app/src/main/res/mipmap-xxhdpi/`
- `app/src/main/res/mipmap-xxxhdpi/`

**For the Spooky Ed main app (Hackss):**

```
Copy from: android/res/mipmap-*
To: Hackss/app/src/main/res/
```

Replace all files in the same mipmap folders as above.

### Step 4: Build and Install

Build the launcher app:

```bash
.\gradlew.bat :app:assembleDebug --no-daemon
```

Install it:

```bash
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

Build and install the Hackss app (if needed):

```bash
cd Hackss
.\gradlew.bat assembleDebug --no-daemon
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

---

## Alternative: Using Android Studio

### Method 1: Image Asset Studio

1. Open the project in Android Studio
2. Right-click on `app` folder
3. Select **New → Image Asset**
4. Asset Type: **Launcher Icons (Adaptive and Legacy)**
5. Path: Browse to `spooky_ed_icon.png`
6. Name: `ic_launcher`
7. Adjust padding to ~10-15%
8. Click **Next** → **Finish**
9. Repeat for `Hackss/app` folder

### Method 2: Manual Replacement

If Icon Kitchen generated webp files and you prefer PNG:

1. Delete all `.webp` files in `app/src/main/res/mipmap-*/`
2. Copy the new `.png` files from Icon Kitchen
3. Ensure files are named:
    - `ic_launcher.png` or `ic_launcher.webp`
    - `ic_launcher_round.png` or `ic_launcher_round.webp`

---

## Required Icon Sizes

| Density | Folder         | Size (px) |
|---------|----------------|-----------|
| mdpi    | mipmap-mdpi    | 48x48     |
| hdpi    | mipmap-hdpi    | 72x72     |
| xhdpi   | mipmap-xhdpi   | 96x96     |
| xxhdpi  | mipmap-xxhdpi  | 144x144   |
| xxxhdpi | mipmap-xxxhdpi | 192x192   |

---

## Troubleshooting

### Icons not showing after install?

1. Uninstall the old app completely:
   ```bash
   adb uninstall com.example.edu_fun_2
   adb uninstall com.runanywhere.startup_hackathon20
   ```
2. Reinstall both apps
3. Clear launcher cache (reboot device if needed)

### Wrong icon showing?

- Make sure you replaced icons in the correct `mipmap-*` folders
- Check that `ic_launcher.png` and `ic_launcher_round.png` exist in all density folders
- Rebuild the project: `.\gradlew.bat clean assembleDebug`

### Build errors after adding icons?

- Ensure file names match exactly: `ic_launcher` and `ic_launcher_round`
- Check file extensions are consistent (all `.png` or all `.webp`)
- Verify files aren't corrupted (try re-downloading from Icon Kitchen)

---

## Current Status

✅ **Main Launcher App (app/):**

- Package: `com.example.edu_fun_2`
- App Name: "Spooky Ed"
- Default Activity: **FIXED** - MainActivity created
- Icon: Needs to be replaced with Spooky Ed icon

✅ **Spooky Ed Main App (Hackss/):**

- Package: `com.runanywhere.startup_hackathon20`
- App Name: "Spooky Ed"
- Icon: Needs to be replaced with Spooky Ed icon

---

## What Happens After Setup

1. User sees "Spooky Ed" icon on their device with the pumpkin+graduation cap image
2. Tapping the icon launches a splash screen
3. After 1.5 seconds, automatically launches the main Spooky Ed app
4. User sees the full Spooky Ed menu with Trick or Answer and Potion Brewing options

---

## Files Modified

**Root Project (app/):**

- ✅ `app/src/main/java/com/example/edu_fun_2/MainActivity.kt` - Created launcher activity
- ✅ `app/src/main/AndroidManifest.xml` - Added MainActivity and launcher intent
- ✅ `app/build.gradle.kts` - Added Compose dependencies
- ✅ `gradle/libs.versions.toml` - Added Compose libraries
- ✅ `app/src/main/res/values/strings.xml` - Changed app name to "Spooky Ed"
- ⏳ `app/src/main/res/mipmap-*/` - Icon files need to be replaced

**Hackss Project:**

- ⏳ `Hackss/app/src/main/res/mipmap-*/` - Icon files need to be replaced

---

## Next Steps

1. **Download the Spooky Ed icon** image to your computer
2. **Visit Icon Kitchen** and generate the icons
3. **Copy the generated icons** to both app projects
4. **Build both apps**
5. **Install and test** on your device

🎃 Your Spooky Ed app will have a professional, themed icon!
