# Service & WorkManager Example

This project demonstrates how to use **Service** and **WorkManager** to manage background tasks in an Android app.

## Key Concepts

- **Service**: Used for long-running tasks (e.g., file downloads) while the app is in the background.
- **WorkManager**: Handles guaranteed background tasks that need to be deferred, retried, or need to run even after a device restart.

## Usage

1. **Service**: Runs background tasks like data fetching or computation.
2. **WorkManager**: Schedules background work with constraints (e.g., network, charging).

### Example Flow:

- A task is triggered by the user.
- If the task needs to run immediately, it’s handled by a `Service`.
- For deferred or guaranteed tasks (like periodic sync), `WorkManager` is used.