
noize — Android Icon Pack
=========================

What’s inside
-------------
- master/noize_icon_master_1024.png (base asset)
- playstore/noize_play_store_512.png (upload to Play Console)
- android/legacy/mipmap-*/ic_launcher.png (mdpi–xxxhdpi legacy icons)
- android/adaptive/mipmap-anydpi-v26/:
    • ic_launcher.xml & ic_launcher_round.xml (adaptive icon definitions)
    • ic_launcher_foreground.png (transparent white "N" + waveform)
    • ic_launcher_background.png (purple gradient background)

How to use (Android Studio)
---------------------------
1) Copy the **mipmap-*** folders from `android/legacy` into your project's `app/src/main/res/`.
2) Copy the entire `mipmap-anydpi-v26` folder from `android/adaptive` into `app/src/main/res/`.
3) Ensure your `AndroidManifest.xml` references the launcher icon like this:
       android:icon="@mipmap/ic_launcher"
       android:roundIcon="@mipmap/ic_launcher_round"
4) Clean/Rebuild the project.

Notes
-----
- The adaptive layers are auto-extracted from your chosen design: foreground is pure white glyph, background uses the sampled purple gradient.
- If you need a **flat color** instead of the gradient background, replace `ic_launcher_background.png` with a solid square or switch the XML to reference a color resource.

Generated automatically from: 038EA66A-6C98-4797-967E-8EAD1DF84349.png
