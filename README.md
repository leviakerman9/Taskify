# ToDo App

A feature-rich Android application built to help users manage their tasks efficiently. This app allows users to add, edit and delete tasks with a user-friendly interface. It also includes Firebase integration for data storage and retrieval, ensuring tasks are synced across devices.

## Features

- **Add Tasks:** Create new tasks with titles and descriptions.
- **Edit Tasks:** Update existing tasks.
 - **Delete Tasks:** Remove tasks with a confirmation dialog.
- **User Authentication:** Secure authentication using Firebase.
- **Firebase Integration:** Store and retrieve tasks from Firebase Realtime Database.
- **Responsive UI:** User-friendly interface with adaptive design for various screen sizes.
- **Custom Navigation:** Independently change the status bar and navigation bar colors for each fragment.

## Screenshots

![Home Screen](![home](https://github.com/leviakerman9/Taskify/assets/108329099/d3ffc08e-5af4-47dd-9526-3475520a070d))
![Edit Task](![edit](https://github.com/leviakerman9/Taskify/assets/108329099/8bdd3447-805d-4a33-90d7-eded86ae6ac5))
![Add Task](![no image](https://github.com/leviakerman9/Taskify/assets/108329099/fb3cbac4-2f79-4fbe-9e15-beac327cec4f))
![Delete Confirmation](![delete](https://github.com/leviakerman9/Taskify/assets/108329099/7bd446e4-b472-4bf4-867b-cee273faf3e8))

## Tech Stack

- **Kotlin:** The primary language for app development.
- **Android Jetpack Components:** Navigation, etc.
- **Firebase:** Firebase Authentication and Realtime Database.
- **Material Design:** For modern and sleek UI components.
- **ConstraintLayout:** For responsive and adaptive UI design.
- **RecyclerView:** For efficient task listing.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/leviakerman9/todo-app.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Set up Firebase:
    - Create a Firebase project.
    - Add your Android app to the Firebase project.
    - Download the `google-services.json` file and place it in the `app` directory.
    - Enable Firebase Authentication and Realtime Database.

5. Build and run the app on an emulator or a physical device.

## Usage

1. **Sign Up / Sign In:**
    - Create a new account or sign in with existing credentials.

2. **Add a Task:**
    - Click on the floating action button to open the Add Task screen.
    - Enter the task title and description and save the task.

3. **Edit a Task:**
    - Tap on any task from the list to open the Edit Task screen.
    - Update the task details and save the changes.

4. **Delete a Task:**
    - Long press on a task to trigger the delete confirmation dialog.
    - Confirm the deletion to remove the task.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any features, bug fixes, or enhancements.

