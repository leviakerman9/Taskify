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
<img src="![no image](https://github.com/leviakerman9/Taskify/assets/108329099/6efc856a-05d2-4b97-a8a1-46cc58510977)" alt="Home Screen" width="300">
<img src="![splash (2)](https://github.com/leviakerman9/Taskify/assets/108329099/0b56f9fc-32fe-4a5c-85eb-98578863b5fa)
" alt="Add Task" width="300">
<img src="![home](https://github.com/leviakerman9/Taskify/assets/108329099/abab14be-d2a1-4c22-b8bd-38f6780f1aac)
" alt="Edit Task" width="300">
<img src="![edit](https://github.com/leviakerman9/Taskify/assets/108329099/c2852856-a552-4df8-bafc-7aeb6ad76f74)
" alt="Delete Confirmation" width="300">

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

