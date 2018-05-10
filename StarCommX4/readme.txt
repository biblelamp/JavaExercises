1. Download and install

    - Java JDK 8 (to make JAVA_HOME in Windows)
    - IntelliJ IDEA Community Edition (free)
    - libGDX http://libgdx.badlogicgames.com/download.html

2. Create init project using gdx-setup.jar

3. Import and start project in IntelliJ IDEA

    - use build.gradle file for import
    - create configuration for Run (Application)
        - Name: DeskApp
        - Main class: com.mygdx.game.desktop.DesktopLauncher
        - Working directory: ...\StarCommX4\core\assets
        - Use classpath of module: desktop

    Note! List of possibility issues:
    - profile name is not in Latin letters
    - the environment variable JAVA_HOME is not defined
    - using JDK 9 or 10
    - not updated video drivers
    - in 32bit system isn't changed gradle.properties (-Xmx512m) 

4. Changing init project

    - changing DesktopLauncher class (1024/576)
    - explanations by class MyGdxGame
    - adding Background class
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
