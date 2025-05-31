# Android Service and BroadcastReceiver Example

This Android project demonstrates how to use **Service** (both background and foreground) and **BroadcastReceiver** in an Android application. It covers:

- **Background Service**: A service that runs in the background to perform long-running operations without user interaction.
- **Foreground Service**: A service that runs in the foreground with an ongoing notification, often used for tasks that need to run even when the user is not interacting with the app.
- **BroadcastReceiver**: A component that listens for system-wide or application-specific broadcasts, allowing the app to respond to events like network changes, battery status, etc.

## Features

- **Background Service**: The app runs a background service that performs a task (e.g., downloading data) even when the app is not in the foreground.
- **Foreground Service**: The service shows a persistent notification while performing tasks like playing music, tracking location, etc.
- **BroadcastReceiver**: A receiver that listens for specific system events, such as changes in network connectivity, battery status, or custom broadcasts sent by other parts of the app.
