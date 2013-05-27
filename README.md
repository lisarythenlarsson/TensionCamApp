TensionCamApp
=============

An app for taking a picture and analyse number of blobs and total sum pixels' area. 

## Getting Started

`git clone git:// github.com/lisarythenlarsson/TensionCamApp`

##Dependencies

- Android SDK
- An actual (preferred) or virtual Android device
- Java XX XX (6 SE) development environment

###Android SDK Targets
- Minimum SDK: 16
- Target SDK: 16

##Building and installing
A build.xml is included in the root directory which may be used for building the project. The default output directory is bin in the project root. By default, Eclipse is set to build automatically. To build the project manually, go to Project in the menu bar.
To install the TensionCamApp on an Android device, the project has to be run as an Android Application in Eclipse. In order for this to work, the device has to be connected via Android Debug Bridge (ADB).
To uninstall the TensionCamApp on an Android device, standard procedures should be followed. I.e. using the device’s default tool for app-removal is recommended.
To be able to send the picture to the analysis program there has to be a web server which the program lies on. The solution that has been used for this project is an Apache Tomcat web server that has been used locally. In order for the devices to be able to communicate with the web server the Spring MVC project has to be deployed on the web server. To make the project deployable it has to go through two steps:
-  Run the Pom.xml as “Maven clean”.
-	Run the Pom.xml as “Maven install”.

To be able to connect the device to the local web server two additional steps has to be followed:
-	Create a hot spot on the device.
-	Connect the local server to this hot spot.

##Tests
Automatic tests are included in a separate project folder called TensionCamAppTester. To run the tests using Eclipse ADT, you should mark the project folder and choose to run it as “Android JUnit Test”. Eclipse will then automatically run included tests and display the test results.
The tests can also be tested individually. To do so; expand the project folder in the package explorer, mark desired test, and choose to run it as “Android JUnit Test”.
Note that the main project ‘TensionCamApp’ needs to be included in the build path for the ‘TensionCamAppTester’.


##License
See the `Licens` file in the project root directory

##Project Team
- Lisa Rythén Larsson
- Max Dubois
- Martin Falk Danauskis
- Fredrik Johansson

Developed at Chalmers University of Technology, Gothenburg 2013.



