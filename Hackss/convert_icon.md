# Converting Spooky Ed Icon for Android

## Required Icon Sizes for Android

Android requires different icon sizes for different screen densities:

| Density | Folder | Size (px) |
|---------|--------|-----------|
| mdpi    | mipmap-mdpi    | 48x48   |
| hdpi    | mipmap-hdpi    | 72x72   |
| xhdpi   | mipmap-xhdpi   | 96x96   |
| xxhdpi  | mipmap-xxhdpi  | 144x144 |
| xxxhdpi | mipmap-xxxhdpi | 192x192 |

## Option 1: Using Android Studio (Easiest)

1. Right-click on `app` folder in Android Studio
2. Select `New` → `Image Asset`
3. Choose `Launcher Icons (Adaptive and Legacy)`
4. Click on the path field and browse to your image
5. Adjust padding if needed
6. Click `Next` and `Finish`

## Option 2: Using Online Tools

1. Go to: https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html
2. Upload your Spooky Ed icon image
3. Adjust settings (padding, shape, etc.)
4. Download the generated icons
5. Replace files in the mipmap folders

## Option 3: Manual Conversion

If you have your image saved as `spooky_ed_icon.png`, you can use ImageMagick or similar tools:

```bash
# Install ImageMagick first if not available

# Generate all sizes
magick spooky_ed_icon.png -resize 48x48 Hackss/app/src/main/res/mipmap-mdpi/ic_launcher.png
magick spooky_ed_icon.png -resize 72x72 Hackss/app/src/main/res/mipmap-hdpi/ic_launcher.png
magick spooky_ed_icon.png -resize 96x96 Hackss/app/src/main/res/mipmap-xhdpi/ic_launcher.png
magick spooky_ed_icon.png -resize 144x144 Hackss/app/src/main/res/mipmap-xxhdpi/ic_launcher.png
magick spooky_ed_icon.png -resize 192x192 Hackss/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png

# For round icons
magick spooky_ed_icon.png -resize 48x48 Hackss/app/src/main/res/mipmap-mdpi/ic_launcher_round.png
magick spooky_ed_icon.png -resize 72x72 Hackss/app/src/main/res/mipmap-hdpi/ic_launcher_round.png
magick spooky_ed_icon.png -resize 96x96 Hackss/app/src/main/res/mipmap-xhdpi/ic_launcher_round.png
magick spooky_ed_icon.png -resize 144x144 Hackss/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png
magick spooky_ed_icon.png -resize 192x192 Hackss/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png
```

## Steps After Icon Conversion

1. Replace the current `.webp` files in each mipmap folder with the new `.png` files
2. The AndroidManifest.xml already references `@mipmap/ic_launcher` and `@mipmap/ic_launcher_round`
3. Rebuild the app:
   ```bash
   cd Hackss
   .\gradlew.bat clean assembleDebug --no-daemon
   ```
4. Install the updated app:
   ```bash
   adb install -r app\build\outputs\apk\debug\app-debug.apk
   ```

## Current Icon Files to Replace

- `mipmap-mdpi/ic_launcher.webp` and `ic_launcher_round.webp`
- `mipmap-hdpi/ic_launcher.webp` and `ic_launcher_round.webp`
- `mipmap-xhdpi/ic_launcher.webp` and `ic_launcher_round.webp`
- `mipmap-xxhdpi/ic_launcher.webp` and `ic_launcher_round.webp`
- `mipmap-xxxhdpi/ic_launcher.webp` and `ic_launcher_round.webp`

## Quick Method (Recommended)

**Use the Android Asset Studio online tool:**

1. Visit: https://icon.kitchen/
2. Upload your Spooky Ed icon
3. Choose "Android" platform
4. Select "Launcher Icon"
5. Download the generated package
6. Extract and copy the `mipmap-*` folders to `Hackss/app/src/main/res/`
7. Rebuild and reinstall the app

This will automatically create all the required sizes and formats!
