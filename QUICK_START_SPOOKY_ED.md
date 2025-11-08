# 🎃 Quick Start - Spooky Ed Mobile App

Get the Spooky Ed app running in **5 minutes**!

## ⚡ Fastest Way (Android App)

The native Android app is **already configured** and ready to run!

### Step 1: Open Project

- Open this project in **Android Studio**
- Wait for Gradle sync to complete (first time may take a few minutes)

### Step 2: Run the App

- Click the green **▶ Run** button in the toolbar
- Or press `Shift + F10`
- Select your device or emulator
- That's it! 🎉

The app will launch showing the Spooky Ed home page with login screen.

## 📱 What You'll See

1. **Home Page**: Login screen with Student/Teacher toggle
2. **Navigation**: Home, About, Contact menu
3. **Menu Page**: After login, see the game selection screen
4. **Account**: Username displayed with logout option

## 🎮 Testing the App

### Test Flow:

1. Toggle between **Student** and **Teacher** modes (background color changes)
2. Enter any username
3. Enter any password
4. Click "Get Started"
5. You'll be redirected to the **Menu page** with game icons
6. Click the account icon (👻) to see username and logout
7. Use navigation links to explore About and Contact pages
8. Press back button to navigate backwards

## 🔧 Building APK for Distribution

```bash
# In Android Studio terminal or command prompt:
./gradlew assembleDebug

# APK will be at:
# app/build/outputs/apk/debug/app-debug.apk
```

You can share this APK with others to install on their Android devices!

## 🎨 Quick Customizations

### Change App Name

Edit `app/src/main/res/values/strings.xml`:

```xml
<string name="app_name">Your App Name</string>
```

### Change Colors

Edit `app/src/main/res/values/colors.xml`:

```xml
<color name="purple">#FFD400FF</color>  <!-- Student mode color -->
<color name="cyan">#FF00FFD5</color>    <!-- Title color -->
```

### Edit Content

HTML files are in `app/src/main/assets/`:

- `home.html` - Login page
- `about.html` - About page
- `contact.html` - Contact page
- `menu.html` - Game menu

Just edit these files and rebuild!

## 🚀 Next Steps

### Add Your Games

The menu page has two game placeholders:

- **Trick or Answer** (Game 1)
- **Potion Brewing** (Game 2)

To integrate real games:

1. Create new HTML files for your games in `app/src/main/assets/`
2. Update `menu.html` game icons to link to your new pages
3. Or create native Android activities for more complex games

### Add Images

1. Place images in `app/src/main/assets/images/`
2. Reference in HTML: `<img src="images/yourimage.png">`
3. Rebuild the app

### Customize Design

All HTML files have embedded CSS. Just edit the `<style>` sections to change:

- Colors
- Fonts
- Layout
- Animations

## 🐛 Common Issues

**Problem**: App shows blank screen  
**Solution**: Make sure all HTML files are in `app/src/main/assets/`

**Problem**: Styles not working  
**Solution**: CSS must be embedded in `<style>` tags (external .css files don't work in assets)

**Problem**: JavaScript not working  
**Solution**: Ensure JavaScript is enabled in `SpookyEdActivity.kt` (already configured)

**Problem**: Can't go back  
**Solution**: Back button should work automatically - if not, check `BackHandler` in
`SpookyEdActivity.kt`

## 📱 Want iOS Support?

Use the **Flutter version** in `flutter_spooky_ed/` folder:

```bash
cd flutter_spooky_ed
flutter pub get
flutter run
```

Requires Flutter SDK installed. See `flutter_spooky_ed/README.md` for details.

## ✅ Checklist

Before you start customizing:

- [ ] App runs successfully on device/emulator
- [ ] Can log in with any username
- [ ] Navigation works (Home, About, Contact)
- [ ] Menu page shows after login
- [ ] Account dropdown works
- [ ] Back button navigates correctly
- [ ] All pages display properly

Once all these work, you're ready to customize! 🎉

## 📚 Full Documentation

- **Complete Guide**: See `SPOOKY_ED_README.md`
- **Flutter Guide**: See `flutter_spooky_ed/README.md`
- **Original Website**: `C:/Users/navee/OneDrive/Attachments/Desktop/Hackula/`

## 💬 Need Help?

Check the troubleshooting section in `SPOOKY_ED_README.md` or contact the Spooky Ed creators:

- Email: spookyed_creator@gmail.com
- Creators: Nancy Gupta, Ravi Prakash

---

**Now go build something spooktacular! 🎃👻🧙‍♀️**
