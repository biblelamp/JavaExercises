1. Download and install

    1.1 Java JDK 8 (to make JAVA_HOME in Windows)

    1.2 IntelliJ IDEA Community Edition (free)
        Initial settings:
        - After first start Configure/Project Defaults/Project Structure:
          in the section Project to add Project SDK (button New...)
        - In the drop-down menu: View/Toolbar, View/Navigation Bar

    If you want to work with Android SDK:
    - From https://developer.android.com/studio/ download proper zip file from section "Command line tools only".
    - Extract zip to some folder, for example D:\AndrodSDK
    - Open command line (Run.../cmd) and go to the \bin folder (D:\AndroidSDK\tools\bin)
    - Execute the following commands:
      ...\bin>sdkmanager.bat "platforms;android-25"
      ...\bin>sdkmanager.bat "build-tools;25.0.3"

    1.3 libGDX framework http://libgdx.badlogicgames.com/download.html

2. Create init project using gdx-setup.jar

3. Import and start project in IntelliJ IDEA

    - use build.gradle file for import
    - create configuration for Run (Desktop Application)
        - Name: DeskApp
        - Main class: com.mygdx.game.desktop.DesktopLauncher
        - Working directory: ...\StarCommX4\core\assets
        - Use classpath of module: desktop

    - if you want to create profile for Android App:
        - Name: AndroidApp
        - Module: android
        - Target: Emulator
        - Prefer Android Virtual Device: <create_and_choice>

    Note! List of possibility issues:
    - profile name is not in Latin letters
    - the environment variable JAVA_HOME is not defined
    - using JDK 9 or 10
    - not updated video drivers
    - in 32bit system isn't changed gradle.properties (-Xmx512m)

4. Changing init project

    - changing DesktopLauncher class (1024/576)
    - explanations by class MyGdxGame
    - to move 4 images to the folder assets (in core or android)
    - writing Background class
    - adding Hero class
    - adding Asteroids class
    - adding Bullets class
    - adding collision handling

5. Building jar file

    open in work window: MyGdxGame.java
    menu: Top Menu/View/Tool Windows/Gradle
    menu: desktop/Tasks/other/dist
    waiting...
    find jar in the folder: desktop/build/libs (desktop-1.0.jar)
