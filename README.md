# Final Project Bank App
React Native and Java Spring-boot

Setup untuk running android :

Setup react-native CLI environment seperti di website https://reactnative.dev/docs/environment-setup

Jalankan command npm install
Buat emulator di android studio
Jika sudah terinstall react-native-cli di device, jalankan command react-native run-android. Jika belum command npx react-native run-android.
Jika emulator tidak terbuka, Buka android studio, buka bagian AVD dan jalankan emulator yang tersedia, kemudian kembali ke step 4.

Setup untuk running ios :

Setup react-native CLI environment seperti di website https://reactnative.dev/docs/environment-setup

Jalankan command npm install
Jalankan command cd ios
Jalankan command pod install
Jika sudah terinstall react-native-cli di device, jalankan command react-native run-ios. Jika belum command npx react-native run-ios.
Untuk dijalankan di specifik simulator yang tersedia, command nya react-native run-ios --simulator="iPhone 8"

Command untuk build release android :

Jalankan command react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle
Jalankan command cd android
Untuk windows jalankan command gradlew assembleRelease, untuk unix jalankan command ./gradlew assembleRelease
Hasil release apk nya ada di path android/app/build/outputs/apk/release/[namaAplikasi].apk
