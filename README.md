# Home Recognition
## Grace Sobrinski, Cam Gallagher, Emily Trinh, Madison Stock, & Rory Giszter

Home Recognition will be a mobile application that allows users to take a photo of a house, upload it to the app, and receive data from the app that will 
inform them of the architectural style(s) of the house. When a photo is uploaded, the app will send the photo to the app back-end and the machine learning model 
will categorize the home into a percentage spread of different styles that fit the home the best. For convenience and doability, the app will be limited to 
American home architecture styles such as Colonial, Tudor Revival, etc. When a user takes a photo in-app, the app will log the location where they took the photo 
and store it in their database. In order for users to utilize this feature, they must have created an account so their information can be attached to their profile.
Users have the ability to see the map view and see nearby users’ uploads regardless of whether or not they have created an account. In addition, the app will have
another tab where users can read up on the different architectural styles the app detects, such as the history and distinct features of the style.

## Version 1.0

* created basic app 
* created home page 
* created log-in page
* Integrated camera functionality
* Integrated image file upload functionality
* Continued training Machine Learning image recognition model
* Integrated TFLite ML model into app
* Created PHP database access server
* Integrated database into app

## Installation instructions

1. Install Android Studio - **must be v.4.1.2**
2. clone the repository 

```git clone https://github.com/gsobrinski/home-recognition-app```

3. In Android Studio, click File > Open 
4. Locate the repository you cloned
5. **Cllick the arrow to the left to open the repository folder, and select HRapp1** as the project you want to open
6. Click OK
7. You may need to select Build > Make Project in the top menu
8. Click the Run icon and your emulator should appear!

## Testing Procedure

Now that you've entered the app, there are two implemented features. The first is the image recognition Classifier feature and the second is a login feature.

### Image Recognition Classifier
1. You have two options: Upload an image from your emulator's file system, or upload an image straight from the camera
2. Select either the UPLOAD button or the Camera floating action button
3. Now you can choose/take a photo and the Classifier will send your image to the model and display the results on the next page!

### Login feature
1. Click on Profile
2. Enter your username and password (anything for now) - note that there is error checking if you do not enter one of the required fields
3. Click LOGIN and the app will display a welcome message

