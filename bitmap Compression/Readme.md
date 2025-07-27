# Image Compression App

## Overview
This Android app allows the user to select an image from their device, compress it using bitmap compression, and save the compressed image to internal storage. The app uses `Bitmap` class to handle compression. This reduces the image size, making it more efficient for storage and usage.

## Features
- Select an image from the device's gallery.
- Compress the selected image with configurable quality .
- Save the compressed image to internal storage.

## What I Did in the Project
1. **Created a New Android Project:** Set up an Android project with an empty activity using Android Studio.
2. **UI Design:** Designed a simple layout with an `ImageView` to display the selected image and a `Button` to trigger the image selection and compression process.
3. **Added Dependencies:** Used the `ImagePicker` library to enable image selection and `Bitmap` for image compression.
4. **Permissions:** Handled the necessary permissions for reading and writing to external storage (for selecting and saving images).
5. **Image Selection and Compression:**
    - Implemented image selection using the `ImagePicker` library.
    - Used `BitmapFactory` to decode the selected image and then compress it using the `Bitmap.compress()` method.
6. **Stored the Compressed Image:** Saved the compressed image to the app's internal storage.
7. **Runtime Permissions Handling:** Ensured that storage permissions are requested and handled properly, particularly for devices running Android 6.0 (Marshmallow) and above.

## Future Enhancements
- Implement a feature to preview the image before compression.
- Allow users to set custom compression quality.
- Support for more image formats (e.g., PNG).
- Better error handling and user feedback for permission issues.

