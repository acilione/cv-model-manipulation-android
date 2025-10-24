## 👋 CV-Model-Manipulation-Android: Hand-Gesture Control for OpenGL Graphics

This project showcases the use of the **MediaPipe Hand-Gesture Recognition model** to interact with and manipulate a basic **OpenGL ES** scene on Android. Specifically, it allows a user to control the movement and rotation of a rendered 3D cube using simple hand gestures captured via the device's camera.

---

## ✨ Features

The application currently supports the following four hand gestures to control the 3D cube:

| Gesture | Action | Description |
| :--- | :--- | :--- |
| **✋ Open Palm** | **Move Left** | Translates the cube negatively along the X-axis. |
| **✊ Closed Fist** | **Move Right** | Translates the cube positevely along the X-axis. |
| **👍 Thumb Up** | **Rotate Y (Anti-Clockwise)** | Rotates the cube about its vertical (Y) axis in a counter-clockwise direction. |
| **👎 Thumb Down** | **Rotate Y (Clockwise)** | Rotates the cube about its vertical (Y) axis in a clockwise direction. |



---

## 🛠️ Technologies Used

* **Android SDK (Kotlin):** Core application development platform.
* **MediaPipe:** Used for real-time hand detection and gesture recognition.
* **OpenGL ES:** Graphics API for rendering the 3D cube.

---

## 🚀 Getting Started

### Prerequisites

* Android Studio (Latest Version)
* An Android device or emulator with a camera.

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [repository-url]
    cd cv-model-manipulation-android
    ```
2.  **Open in Android Studio:**
    Open the cloned directory as an existing Android Studio project.
3.  **Build and Run:**
    Sync Gradle, build the project, and run it on your connected Android device or emulator. The app will require **camera permission** upon launch.

---

## 🔮 Future Enhancements (TODO)

We plan to expand the functionality of this project in future iterations:

* **More Gestures:** Implement handling for additional gestures (e.g., "V-sign," "Peace-sign," "Pointer Finger") to unlock more complex manipulations (e.g., Z-axis movement, scaling).
* **gLTF Support:** Add support for loading and rendering 3D models in the widely-used **gLTF format**, moving beyond the basic cube. This will allow for the manipulation of more detailed and complex assets.
* **Multi-hand Tracking:** Explore controlling different aspects of the scene or multiple objects simultaneously using two hands.

---

## 🤝 Contribution

Contributions are welcome! If you'd like to help with the TODO list, fix a bug, or suggest a new feature, please feel free to open an issue or submit a pull request.

---

## 📄 License

This project is licensed under the [Specify License, e.g., MIT License] - see the `LICENSE` file for details.
