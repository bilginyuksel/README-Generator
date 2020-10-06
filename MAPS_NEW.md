# CORDOVA-PLUGIN-HMS-MAP

## Contents
  - [1. Introduction](#1-introduction)
  - [2. Installation Guide](#2-installation-guide)
  - [3. API Reference](#3-api-reference)
  - [4. Configuration and Description](#4-configuration-and-description)
  - [5. Sample Project](#5-sample-project)
  - [6. Questions or Issues](#6-questions-or-issues)
  - [7. Licencing and Terms](#7-licencing-and-terms)

## 1. Introduction

**HUAWEI Map Kit** is a messaging service provided by Huawei. It establishes a messaging channel from the cloud to devices. By integrating Map Kit, you can send messages to your apps on users' devices in real time. This helps you maintain closer ties with users and increases user awareness and engagement with your apps.

This module enables communication between HUAWEI Map Kit SDK and Cordova platform. It exposes all functionality provided by HUAWEI Map Kit SDK.

## 2. Installation Guide

Before you get started, you must register as a HUAWEI Developer and complete identity verification on the [HUAWEI Developer](https://developer.huawei.com/consumer/en/) website. For details, please refer to [Register a HUAWEI ID](https://developer.huawei.com/consumer/en/doc/10104).

### Creating a Project in AppGallery Connect
Creating an app in AppGallery Connect is required in order to communicate with the Huawei services. To create an app, perform the following steps:

**Step 1.** Sign in to [AppGallery Connect](https://developer.huawei.com/consumer/en/service/josp/agc/index.html)  and select **My projects**.

**Step 2.** Select your project from the project list or create a new one by clicking the **Add Project** button.

**Step 3.** Go to **Project Setting** > **General information**, and click **Add app**.
If an app exists in the project and you need to add a new one, expand the app selection area on the top of the page and click **Add app**.

**Step 4.** On the **Add app** page, enter the app information, and click **OK**.

### Configuring the Signing Certificate Fingerprint

A signing certificate fingerprint is used to verify the authenticity of an app when it attempts to access an HMS Core (APK) through the HMS SDK. Before using the HMS Core (APK), you must locally generate a signing certificate fingerprint and configure it in the **AppGallery Connect**. You can refer to 3rd and 4th steps of [Generating a Signing Certificate](https://developer.huawei.com/consumer/en/codelab/HMSPreparation/index.html#2) codelab tutorial for the certificate generation. Perform the following steps after you have generated the certificate.

**Step 1:** Sign in to [AppGallery Connect](https://developer.huawei.com/consumer/en/service/josp/agc/index.html) and select your project from **My Projects**. Then go to **Project Setting** > **General information**. In the **App information** field, click the  icon next to SHA-256 certificate fingerprint, and enter the obtained **SHA-256 certificate fingerprint**.

**Step 2:**  After completing the configuration, click **OK** to save the changes. (Check mark icon)
### Cordova

1. Download the Cordova Map SDK Plugin.
2. Add Platform To Project.

     ***```cordova platform add android```***

3. You can either install the plugin through npm or by downloading from downloads page.

    a. Run the following command in the root directory of your Cordova project to install it through npm.

    ***```cordova plugin add @hmscore/cordova-plugin-hms-map```***


    b. Run the following command in the root directuory of your Cordova project to install it manually after downloading the plugin. <br>

    ***```cordova plugin add <CORDOVA_MAP_PLUGIN_PATH>```***


4. Check whether the Cordova Map SDK is successfully added to Plugin folder in the root directory of the Cordova project.

5. Download **agconnect-services.json** file from AppGallery Connect.

6. Add **agconnect-services.json** and **jks** file to root directory.

7. Add build.json file to your project's root. Add following code to build.json file and edit  build.json file according to your own keystore file.
    ```json
    {
        "android": {
            "debug": {
                "keystore": "./storeFile.jks",
                "storePassword": "storePassword",
                "alias": "keyAlias",
                "password": "keyPassword"
            },
            "release": {
                "keystore": "./storeFile.jks",
                "storePassword": "storePassword",
                "alias": "keyAlias",
                "password": "keyPassword"
            }
        }
    }
    ```

8. Make sure widget id is same as your package name in config.xml. Otherwise demo project will not work properly.

9. Then run the Cordova app

     ***```cordova run android```***

### Ionic

1. Download the Cordova Map SDK Plugin through npm or by downloading from downloads page.

    a. Run the following command in the root directuory of your Cordova project to install it manually after downloading the plugin. <br>

    ***```npm install <CORDOVA_MAP_PLUGIN_PATH>```***
    <br>

    b. Run the following command in the root directory of your Ionic project to install it through npm.

    ***`npm install @hmscore/cordova-plugin-hms-map`***


2. Check whether the Cordova Map SDK is successfully added to the node_modules directory.

3. If you want full Ionic support with code completion etc, install"@ionic-native/core" in your project. <br>

    ***`npm install @ionic-native/core --save-dev`***

4. Copy the "ionic/dist/hms-map" folder from library to "node_modules/@ionic-native" folder under your Ionic project.

5. Compile ionic project.

    a. ***`ionic build`***

    b. ***`npx cap init [appName] [appId]`***

    > ***NOTE*** <br>
    > where appName is the name of your app, and appId is package_name in your agconnect-services.json file (ex: com.example.app).

6. Add a native platform to your project.

    ***`ionic capacitor add android`***

7. Configure the Maven repository address and AppGallery Connect plug-in address for the AppGallery Connect SDK.
    1. Open the build.gradle file in the root directory under `android` folder.
    2. Go to allprojects > repositories and configure the Maven repository address for the AppGallery Connect SDK.
        ```gradle
        allprojects {
            repositories {
                google()
                jcenter()
                maven {url 'https://developer.huawei.com/repo/'}
            }
        }
        ```

    3. Go to buildscript > repositories and configure the Maven repository address for the AppGallery Connect SDK.

        ```gradle
        buildscript {
            repositories {
                google()
                jcenter()
                maven {url 'https://developer.huawei.com/repo/'}
            }
        }
        ```
     4. Go to buildscript > dependencies and configure the AppGallery Connect plug-in address.

        ```gradle
        buildscript {
            dependencies {
                classpath 'com.huawei.agconnect:agcp:1.4.1.300'
            }
        }
        ```
8. Add build dependencies and the AppGallery Connect plug-in address.

    1. Open the build.gradle file in the app directory.
    2. Add the AppGallery Connect plug-in dependency to the bottom of the file.
        ```gradle
        apply plugin: 'com.huawei.agconnect'
        ```
    3. Add signingConfig inside the android section
        ```gradle
        android{
            signingConfigs{
                config{
                    keyAlias 'keyAlias'
                    keyPassword 'keyPassword'
                    storeFile file('storeFile')
                    storePassword 'storePassword'
                }
            }
            buildTypes {
            debug {
                signingConfig signingConfigs.config
            }
            release {
                signingConfig signingConfigs.config
            }
        }
        ```

    4. Add the following code to dependencies.
        ```gradle
        dependencies {
            // other dependencies
            implementation "com.huawei.hms:map:5.0.2.300"
        }
        ```

9. Add agconnect-services.json and jks file to the app directory in your Android project.

10. To update dependencies, and copy any web assets to your project, add following code.

    ***`npx capacitor sync`***

11. Then run the Ionic app.

    ***`ionic capacitor run android`***

    After this command, the demo will be opened android studio. Just run it.

---

## 3. API Reference
### Circle
|Field|Type|Description|
|---|---|---|
|getCenter()|`Promise<LatLng>`| |
|getFillColor()|`Promise<Color>`| |
|getId()|`string`| |
|getRadius()|`Promise<number>`| |
|getStrokeColor()|`Promise<Color>`| |
|getStrokePattern()|`Promise<PatternItem[]>`| |
|getStrokeWidth()|`Promise<number>`| |
|getTag()|`Promise<any>`| |
|getZIndex()|`Promise<number>`| |
|isClickable()|`Promise<boolean>`| |
|isVisible()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|setCenter(center:LatLng)|`Promise<void>`| |
|setFillColor(fillColor:Color)|`Promise<void>`| |
|setRadius(radius:number)|`Promise<void>`| |
|setStrokeColor(strokeColor:Color)|`Promise<void>`| |
|setStrokePattern(strokePattern:PatternItem[])|`Promise<void>`| |
|setStrokeWidth(strokeWidth:number)|`Promise<void>`| |
|setTag(tag:any)|`Promise<void>`| |
|setZIndex(zIndex:number)|`Promise<void>`| |
|setClickable(clickable:boolean)|`Promise<void>`| |
|setVisible(visible:boolean)|`Promise<void>`| |

### CircleImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(mapDivId:  string,  mapCapsuleId:  number,  options:  CircleOptions)|`any`| |
|getComponentOptions(func:  string)|`Promise<any>`| |
|getCenter()|`Promise<LatLng>`| |
|getFillColor()|`Promise<Color>`| |
|getId()|`string`| |
|getRadius()|`Promise<number>`| |
|getStrokeColor()|`Promise<number>`| |
|getStrokePattern()|`Promise<PatternItem[]>`| |
|getStrokeWidth()|`Promise<number>`| |
|getTag()|`Promise<any>`| |
|getZIndex()|`Promise<number>`| |
|isClickable()|`Promise<boolean>`| |
|isVisible()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|setCenter(center:  LatLng)|`Promise<void>`| |
|setFillColor(fillColor:  Color)|`Promise<void>`| |
|setRadius(radius:  number)|`Promise<void>`| |
|setStrokeColor(strokeColor:  number)|`Promise<void>`| |
|setStrokePattern(strokePattern:  PatternItem[])|`Promise<void>`| |
|setStrokeWidth(strokeWidth:  number)|`Promise<void>`| |
|setTag(tag:  any)|`Promise<void>`| |
|setZIndex(zIndex:  number)|`Promise<void>`| |
|setClickable(clickable:  boolean)|`Promise<void>`| |
|setVisible(visible:  boolean)|`Promise<void>`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### setComponentOptions

Function explanation field...

###### Parameters

do something

###### getComponentOptions

Function explanation field...

###### Parameters

do something

###### getCenter

Function explanation field...

###### Parameters

do something

###### getFillColor

Function explanation field...

###### Parameters

do something

###### getId

Function explanation field...

###### Parameters

do something

###### getRadius

Function explanation field...

###### Parameters

do something

###### getStrokeColor

Function explanation field...

###### Parameters

do something

###### getStrokePattern

Function explanation field...

###### Parameters

do something

###### getStrokeWidth

Function explanation field...

###### Parameters

do something

###### getTag

Function explanation field...

###### Parameters

do something

###### getZIndex

Function explanation field...

###### Parameters

do something

###### isClickable

Function explanation field...

###### Parameters

do something

###### isVisible

Function explanation field...

###### Parameters

do something

###### remove

Function explanation field...

###### Parameters

do something

###### setCenter

Function explanation field...

###### Parameters

do something

###### setFillColor

Function explanation field...

###### Parameters

do something

###### setRadius

Function explanation field...

###### Parameters

do something

###### setStrokeColor

Function explanation field...

###### Parameters

do something

###### setStrokePattern

Function explanation field...

###### Parameters

do something

###### setStrokeWidth

Function explanation field...

###### Parameters

do something

###### setTag

Function explanation field...

###### Parameters

do something

###### setZIndex

Function explanation field...

###### Parameters

do something

###### setClickable

Function explanation field...

###### Parameters

do something

###### setVisible

Function explanation field...

###### Parameters

do something


### GroundOverlay
|Field|Type|Description|
|---|---|---|
|getBearing()|`Promise<number>`| |
|getBounds()|`Promise<LatLngBounds>`| |
|getHeight()|`Promise<number>`| |
|getId()|`string`| |
|getPosition()|`Promise<LatLng>`| |
|getTag()|`Promise<any>`| |
|getTransparency()|`Promise<number>`| |
|getWidth()|`Promise<number>`| |
|getZIndex()|`Promise<number>`| |
|isClickable()|`Promise<boolean>`| |
|isVisible()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|setBearing(bearing:number)|`Promise<void>`| |
|setClickable(clickable:boolean)|`Promise<void>`| |
|setDimensions(width:numberheight:number)|`Promise<void>`| |
|setDimensions(width:number)|`Promise<void>`| |
|setImage(imageDescriptor:BitmapDescriptor)|`Promise<void>`| |
|setPosition(position:LatLng)|`Promise<void>`| |
|setPositionFromBounds(positionLatLngBounds:LatLngBounds)|`Promise<void>`| |
|setTag(tag:any)|`Promise<void>`| |
|setTransparency(transparency:number)|`Promise<void>`| |
|setVisible(visible:boolean)|`Promise<void>`| |
|setZIndex(zIndex:number)|`Promise<void>`| |

### GroundOverlayImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(mapDivId:  string,  mapCapsuleId:  number,  options:  GroundOverlayOptions)|`any`| |
|getComponentOptions(func:  string)|`Promise<any>`| |
|getBearing()|`Promise<number>`| |
|getBounds()|`Promise<LatLngBounds>`| |
|getHeight()|`Promise<number>`| |
|getId()|`string`| |
|getPosition()|`Promise<LatLng>`| |
|getTag()|`Promise<any>`| |
|getTransparency()|`Promise<number>`| |
|getWidth()|`Promise<number>`| |
|getZIndex()|`Promise<number>`| |
|isClickable()|`Promise<boolean>`| |
|isVisible()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|setBearing(bearing:  number)|`Promise<void>`| |
|setClickable(clickable:  boolean)|`Promise<void>`| |
|setDimensions(width:  number,  height?:  number)|`Promise<void>;setDimensions(widthnumber)Promise<void>;setDimensions(widthany,height?any)Promise<void>`| |
|setImage(imageDescriptor:  BitmapDescriptor)|`Promise<void>`| |
|setPosition(position:  LatLng)|`Promise<void>`| |
|setPositionFromBounds(positionLatLngBounds:  LatLngBounds)|`Promise<void>`| |
|setTag(tag:  any)|`Promise<void>`| |
|setTransparency(transparency:  number)|`Promise<void>`| |
|setVisible(visible:  boolean)|`Promise<void>`| |
|setZIndex(zIndex:  number)|`Promise<void>`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### setComponentOptions

Function explanation field...

###### Parameters

do something

###### getComponentOptions

Function explanation field...

###### Parameters

do something

###### getBearing

Function explanation field...

###### Parameters

do something

###### getBounds

Function explanation field...

###### Parameters

do something

###### getHeight

Function explanation field...

###### Parameters

do something

###### getId

Function explanation field...

###### Parameters

do something

###### getPosition

Function explanation field...

###### Parameters

do something

###### getTag

Function explanation field...

###### Parameters

do something

###### getTransparency

Function explanation field...

###### Parameters

do something

###### getWidth

Function explanation field...

###### Parameters

do something

###### getZIndex

Function explanation field...

###### Parameters

do something

###### isClickable

Function explanation field...

###### Parameters

do something

###### isVisible

Function explanation field...

###### Parameters

do something

###### remove

Function explanation field...

###### Parameters

do something

###### setBearing

Function explanation field...

###### Parameters

do something

###### setClickable

Function explanation field...

###### Parameters

do something

###### setDimensions

Function explanation field...

###### Parameters

do something

###### setImage

Function explanation field...

###### Parameters

do something

###### setPosition

Function explanation field...

###### Parameters

do something

###### setPositionFromBounds

Function explanation field...

###### Parameters

do something

###### setTag

Function explanation field...

###### Parameters

do something

###### setTransparency

Function explanation field...

###### Parameters

do something

###### setVisible

Function explanation field...

###### Parameters

do something

###### setZIndex

Function explanation field...

###### Parameters

do something


### HuaweiMap
|Field|Type|Description|
|---|---|---|
|destroyMap()|`Promise<void>`| |
|hideMap()|`Promise<void>`| |
|getComponent(key:string)|`any`| |
|removeComponent(key:string)|`void`| |
|getId()|`number`| |
|addCircle(circleOptions:CircleOptions)|`Promise<Circle>`| |
|addMarker(markerOptions:MarkerOptions)|`Promise<Marker>`| |
|addGroundOverlay(groundOverlayOptions:GroundOverlayOptions)|`Promise<GroundOverlay>`| |
|addTileOverlay(tileOverlayOptions:TileOverlayOptions)|`Promise<TileOverlay>`| |
|addPolygon(polygonOptions:PolygonOptions)|`Promise<Polygon>`| |
|addPolyline(polylineOptions:PolylineOptions)|`Promise<Polyline>`| |
|animateCamera(cameraUpdate:CameraUpdate)|`Promise<void>`| |
|animateCamera(cameraUpdate:CameraUpdatecancelableCallback?:CancelableCallback)|`Promise<void>`| |
|animateCamera(cameraUpdate:CameraUpdatedurationMs?:numbercancelableCallback?:CancelableCallback)|`Promise<void>`| |
|moveCamera(cameraUpdate:CameraUpdate)|`Promise<void>`| |
|clear()|`Promise<void>`| |
|resetMinMaxZoomPreference()|`Promise<void>`| |
|stopAnimation()|`Promise<void>`| |
|getCameraPosition()|`Promise<CameraPosition>`| |
|getMapType()|`Promise<MapType>`| |
|getMaxZoomLevel()|`Promise<number>`| |
|getMinZoomLevel()|`Promise<number>`| |
|getProjection()|`Projection`| |
|getUiSettings()|`UiSettings`| |
|isBuildingsEnabled()|`Promise<boolean>`| |
|isMyLocationEnabled()|`Promise<boolean>`| |
|isTrafficEnabled()|`Promise<boolean>`| |
|isIndoorEnabled()|`Promise<boolean>`| |
|setBuildingsEnabled(buildingsEnabled:boolean)|`Promise<void>`| |
|setContentDescription(contentDescription:string)|`Promise<void>`| |
|setInfoWindowAdapter(infoWindowAdapter:InfoWindowAdapter)|`Promise<void>`| |
|setLatLngBoundsForCameraTarget(latLngBoundsForCameraTarget:LatLngBounds)|`Promise<void>`| |
|setLocationSource(locationSource:LocationSource)|`Promise<void>`| |
|setMapStyle(mapStyle:MapStyleOptions)|`Promise<void>`| |
|setMapType(mapType:MapType)|`Promise<void>`| |
|setMarkersClustering(markersClustering:boolean)|`Promise<void>`| |
|setMaxZoomPreference(maxZoomPreference:number)|`Promise<void>`| |
|setMinZoomPreference(minZoomPreference:number)|`Promise<void>`| |
|setMyLocationEnabled(myLocationEnabled:boolean)|`Promise<void>`| |
|setPadding(left:numbertop:numberright:numberbottom:number)|`Promise<void>`| |
|setTrafficEnabled(trafficEnabled:boolean)|`Promise<void>`| |

### HuaweiMapImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(divId:  string,  options:  any)|`any`| |
|destroyMap()|`Promise<void>`| |
|hideMap()|`Promise<void>`| |
|on(event:  MapEvent,  callback:  (val)|`=>void)Promise<void>`| |
|addCircle(circleOptions:  CircleOptions)|`Promise<Circle>`| |
|addMarker(markerOptions:  MarkerOptions)|`Promise<Marker>`| |
|addGroundOverlay(groundOverlayOptions:  GroundOverlayOptions)|`Promise<GroundOverlay>`| |
|addTileOverlay(tileOverlayOptions:  TileOverlayOptions)|`Promise<TileOverlay>`| |
|addPolygon(polygonOptions:  PolygonOptions)|`Promise<Polygon>`| |
|addPolyline(polylineOptions:  PolylineOptions)|`Promise<Polyline>`| |
|animateCamera(cameraUpdate:  CameraUpdate,  cancelableCallback:  CancelableCallback)|`Promise<void>;//changedurationsMspositionanimateCamera(cameraUpdateCameraUpdate,cancelableCallback?CancelableCallback,durationMs?number)Promise<void>`| |
|moveCamera(cameraUpdate:  CameraUpdate)|`Promise<void>`| |
|clear()|`Promise<void>`| |
|resetMinMaxZoomPreference()|`Promise<void>`| |
|stopAnimation()|`Promise<void>`| |
|getCameraPosition()|`Promise<CameraPosition>`| |
|getMapType()|`Promise<MapType>`| |
|getMaxZoomLevel()|`Promise<number>`| |
|getMinZoomLevel()|`Promise<number>`| |
|getProjection()|`any`| |
|getUiSettings()|`UiSettings`| |
|isBuildingsEnabled()|`Promise<boolean>`| |
|isMyLocationEnabled()|`Promise<boolean>`| |
|isTrafficEnabled()|`Promise<boolean>`| |
|isIndoorEnabled()|`Promise<boolean>`| |
|setBuildingsEnabled(buildingsEnabled:  boolean)|`Promise<void>`| |
|setContentDescription(contentDescription:  string)|`Promise<void>`| |
|setInfoWindowAdapter(infoWindowAdapter:  InfoWindowAdapter)|`Promise<void>`| |
|setLatLngBoundsForCameraTarget(latLngBounds:  LatLngBounds)|`Promise<void>`| |
|setLocationSource(locationSource:  LocationSource)|`Promise<void>`| |
|setMapStyle(mapStyle:  MapStyleOptions)|`Promise<void>`| |
|setMapType(mapType:  MapType)|`Promise<void>`| |
|setMarkersClustering(markersClustering:  boolean)|`Promise<void>`| |
|setMaxZoomPreference(maxZoomPreference:  number)|`Promise<void>`| |
|setMinZoomPreference(minZoomPreference:  number)|`Promise<void>`| |
|setMyLocationEnabled(myLocationEnabled:  boolean)|`Promise<void>`| |
|setPadding(left:  number,  top:  number,  right:  number,  bottom:  number)|`Promise<void>`| |
|setTrafficEnabled(trafficEnabled:  boolean)|`Promise<void>`| |
|getComponent(key:  string)|`any`| |
|getId()|`number`| |
|snapshot(onReadyCallback:  (snapshot)|`=>void)Promise<void>`| |
|removeComponent(key:  string)|`void`| |
|getHuaweiMapOptions(func:  string)|`Promise<any>`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### getPos

Function explanation field...

###### Parameters

do something

###### destroyMap

Function explanation field...

###### Parameters

do something

###### hideMap

Function explanation field...

###### Parameters

do something

###### on

Function explanation field...

###### Parameters

do something

###### addCircle

Function explanation field...

###### Parameters

do something

###### addMarker

Function explanation field...

###### Parameters

do something

###### addGroundOverlay

Function explanation field...

###### Parameters

do something

###### addTileOverlay

Function explanation field...

###### Parameters

do something

###### addPolygon

Function explanation field...

###### Parameters

do something

###### addPolyline

Function explanation field...

###### Parameters

do something

###### animateCamera

Function explanation field...

###### Parameters

do something

###### moveCamera

Function explanation field...

###### Parameters

do something

###### clear

Function explanation field...

###### Parameters

do something

###### resetMinMaxZoomPreference

Function explanation field...

###### Parameters

do something

###### stopAnimation

Function explanation field...

###### Parameters

do something

###### getCameraPosition

Function explanation field...

###### Parameters

do something

###### getMapType

Function explanation field...

###### Parameters

do something

###### getMaxZoomLevel

Function explanation field...

###### Parameters

do something

###### getMinZoomLevel

Function explanation field...

###### Parameters

do something

###### getProjection

Function explanation field...

###### Parameters

do something

###### getUiSettings

Function explanation field...

###### Parameters

do something

###### isBuildingsEnabled

Function explanation field...

###### Parameters

do something

###### isMyLocationEnabled

Function explanation field...

###### Parameters

do something

###### isTrafficEnabled

Function explanation field...

###### Parameters

do something

###### isIndoorEnabled

Function explanation field...

###### Parameters

do something

###### setBuildingsEnabled

Function explanation field...

###### Parameters

do something

###### setContentDescription

Function explanation field...

###### Parameters

do something

###### setInfoWindowAdapter

Function explanation field...

###### Parameters

do something

###### setLatLngBoundsForCameraTarget

Function explanation field...

###### Parameters

do something

###### setLocationSource

Function explanation field...

###### Parameters

do something

###### setMapStyle

Function explanation field...

###### Parameters

do something

###### setMapType

Function explanation field...

###### Parameters

do something

###### setMarkersClustering

Function explanation field...

###### Parameters

do something

###### setMaxZoomPreference

Function explanation field...

###### Parameters

do something

###### setMinZoomPreference

Function explanation field...

###### Parameters

do something

###### setMyLocationEnabled

Function explanation field...

###### Parameters

do something

###### setPadding

Function explanation field...

###### Parameters

do something

###### setTrafficEnabled

Function explanation field...

###### Parameters

do something

###### getComponent

Function explanation field...

###### Parameters

do something

###### getId

Function explanation field...

###### Parameters

do something

###### snapshot

Function explanation field...

###### Parameters

do something

###### removeComponent

Function explanation field...

###### Parameters

do something

###### forceUpdateXAndY

Function explanation field...

###### Parameters

do something

###### setHuaweiMapOptions

Function explanation field...

###### Parameters

do something

###### getHuaweiMapOptions

Function explanation field...

###### Parameters

do something


### UiSettings
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(mapDivId:  string)|`any`| |
|isCompassEnabled()|`Promise<boolean>`| |
|isIndoorLevelPickerEnabled()|`Promise<boolean>`| |
|isMapToolbarEnabled()|`Promise<boolean>`| |
|isMyLocationButtonEnabled()|`Promise<boolean>`| |
|isRotateGesturesEnabled()|`Promise<boolean>`| |
|isScrollGesturesEnabled()|`Promise<boolean>`| |
|isScrollGesturesEnabledDuringRotateOrZoom()|`Promise<boolean>`| |
|isTiltGesturesEnabled()|`Promise<boolean>`| |
|isZoomControlsEnabled()|`Promise<boolean>`| |
|isZoomGesturesEnabled()|`Promise<boolean>`| |
|setAllGesturesEnabled(allGesturesEnabled:  boolean)|`Promise<void>`| |
|setCompassEnabled(compassEnabled:  boolean)|`Promise<void>`| |
|setIndoorLevelPickerEnabled(indoorLevelPickerEnabled:  boolean)|`Promise<void>`| |
|setMapToolbarEnabled(mapToolbarEnabled:  boolean)|`Promise<void>`| |
|setMyLocationButtonEnabled(myLocationButtonEnabled:  boolean)|`Promise<void>`| |
|setRotateGesturesEnabled(rotateGesturesEnabled:  boolean)|`Promise<void>`| |
|setScrollGesturesEnabled(scrollGesturesEnabled:  boolean)|`Promise<void>`| |
|setScrollGesturesEnabledDuringRotateOrZoom(scrollGesturesEnabledDuringRotateOrZoom:  boolean)|`Promise<void>`| |
|setTiltGesturesEnabled(tiltGesturesEnabled:  boolean)|`Promise<void>`| |
|setZoomControlsEnabled(zoomControlsEnabled:  boolean)|`Promise<void>`| |
|setZoomGesturesEnabled(zoomGesturesEnabled:  boolean)|`Promise<void>`| |
|getUiSettings(func:  string)|`any`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### isCompassEnabled

Function explanation field...

###### Parameters

do something

###### isIndoorLevelPickerEnabled

Function explanation field...

###### Parameters

do something

###### isMapToolbarEnabled

Function explanation field...

###### Parameters

do something

###### isMyLocationButtonEnabled

Function explanation field...

###### Parameters

do something

###### isRotateGesturesEnabled

Function explanation field...

###### Parameters

do something

###### isScrollGesturesEnabled

Function explanation field...

###### Parameters

do something

###### isScrollGesturesEnabledDuringRotateOrZoom

Function explanation field...

###### Parameters

do something

###### isTiltGesturesEnabled

Function explanation field...

###### Parameters

do something

###### isZoomControlsEnabled

Function explanation field...

###### Parameters

do something

###### isZoomGesturesEnabled

Function explanation field...

###### Parameters

do something

###### setAllGesturesEnabled

Function explanation field...

###### Parameters

do something

###### setCompassEnabled

Function explanation field...

###### Parameters

do something

###### setIndoorLevelPickerEnabled

Function explanation field...

###### Parameters

do something

###### setMapToolbarEnabled

Function explanation field...

###### Parameters

do something

###### setMyLocationButtonEnabled

Function explanation field...

###### Parameters

do something

###### setRotateGesturesEnabled

Function explanation field...

###### Parameters

do something

###### setScrollGesturesEnabled

Function explanation field...

###### Parameters

do something

###### setScrollGesturesEnabledDuringRotateOrZoom

Function explanation field...

###### Parameters

do something

###### setTiltGesturesEnabled

Function explanation field...

###### Parameters

do something

###### setZoomControlsEnabled

Function explanation field...

###### Parameters

do something

###### setZoomGesturesEnabled

Function explanation field...

###### Parameters

do something

###### setUiSettings

Function explanation field...

###### Parameters

do something

###### getUiSettings

Function explanation field...

###### Parameters

do something


### CameraUpdate
|Field|Type|Description|
|---|---|---|

### CameraUpdateImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|moveCamera(mapId:  string)|`Promise<any>`| |
|animateCamera(mapId:  string,  props:  any)|`Promise<any>`| |
#### Public Methods

###### moveCamera

Function explanation field...

###### Parameters

do something

###### animateCamera

Function explanation field...

###### Parameters

do something


### CameraUpdateFactory
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|newCameraPosition(cameraPosition:  CameraPosition)|`CameraUpdate`| |
|newLatLng(latLng:  LatLng)|`CameraUpdate`| |
|newLatLngBounds(latLngBounds:  LatLngBounds,  padding:  number)|`CameraUpdate;staticnewLatLngBounds(latLngBoundsLatLngBounds,paddingnumber,width?number,height?number)CameraUpdate`| |
|newLatLngZoom(latLng:  LatLng,  zoom:  number)|`CameraUpdate`| |
|scrollBy(xPixel:  number,  yPixel:  number)|`CameraUpdate`| |
|zoomBy(amount:  number)|`CameraUpdate;staticzoomBy(amountnumber,focus?Point)CameraUpdate`| |
|zoomIn()|`CameraUpdate`| |
|zoomOut()|`CameraUpdate`| |
|zoomTo(zoom:  number)|`CameraUpdate`| |
|constructCameraUpdateImpl(event:  string,  props:  any)|`CameraUpdateImpl`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### newCameraPosition

Function explanation field...

###### Parameters

do something

###### newLatLng

Function explanation field...

###### Parameters

do something

###### newLatLngBounds

Function explanation field...

###### Parameters

do something

###### newLatLngZoom

Function explanation field...

###### Parameters

do something

###### scrollBy

Function explanation field...

###### Parameters

do something

###### zoomBy

Function explanation field...

###### Parameters

do something

###### zoomIn

Function explanation field...

###### Parameters

do something

###### zoomOut

Function explanation field...

###### Parameters

do something

###### zoomTo

Function explanation field...

###### Parameters

do something

###### constructCameraUpdateImpl

Function explanation field...

###### Parameters

do something


### MapBounds
|Field|Type|Description|
|---|---|---|
|marginTop?|`number`| |
|marginBottom?|`number`| |

### HuaweiMapOptions
|Field|Type|Description|
|---|---|---|
|mapType?|`MapType`| |
|zoomControlsEnabled?|`boolean`| |
|compassEnabled?|`boolean`| |
|zoomGesturesEnabled?|`boolean`| |
|scrollGesturesEnabled?|`boolean`| |
|rotateGesturesEnabled?|`boolean`| |
|tiltGesturesEnabled?|`boolean`| |
|zOrderOnTop?|`boolean`| |
|liteMode?|`boolean`| |
|ambientEnabled?|`boolean`| |
|minZoomPreference?|`number`| |
|maxZoomPreference?|`number`| |
|cameraPosition?|`CameraPosition`| |
|latLngBounds?|`LatLngBounds`| |

### LatLngBounds
|Field|Type|Description|
|---|---|---|
|southwest|`LatLng`| |
|northeast|`LatLng`| |

### CameraPosition
|Field|Type|Description|
|---|---|---|
|target|`LatLng`| |
|zoom?|`number`| |
|bearing?|`number`| |
|tilt?|`number`| |

### LatLng
|Field|Type|Description|
|---|---|---|
|lat|`number`| |
|lng|`number`| |

### CircleOptions
|Field|Type|Description|
|---|---|---|
|readonlyid?|`string`| |
|center|`LatLng`| |
|clickable?|`boolean`| |
|fillColor?|`Color`| |
|radius|`number`| |
|strokeColor?|`Color`| |
|strokeWidth?|`number`| |
|strokePattern?|`PatternItem[]`| |
|visible?|`boolean`| |
|zIndex?|`number`| |

### POI
|Field|Type|Description|
|---|---|---|
|latLng|`LatLng`| |
|name?|`string`| |
|placeId?|`string`| |

### PatternItem
|Field|Type|Description|
|---|---|---|
|type|`PatternItemType`| |
|length|`number`| |

### MarkerOptions
|Field|Type|Description|
|---|---|---|
|readonlyid?|`string`| |
|anchorMarker?|`Anchor`| |
|infoWindowAnchor?|`WindowAnchor`| |
|infoWindowShown?|`boolean`| |
|alpha?|`number`| |
|clusterable?|`boolean`| |
|draggable?|`boolean`| |
|icon?|`BitmapDescriptor`| |
|flat?|`boolean`| |
|position|`LatLng`| |
|rotation?|`number`| |
|snippet?|`string`| |
|title?|`string`| |
|visible?|`boolean`| |
|zIndex?|`number`| |

### BitmapDescriptor
|Field|Type|Description|
|---|---|---|
|hue?|`Hue`| |
|asset?|`string`| |
|fileName?|`string`| |
|path?|`string`| |

### Anchor
|Field|Type|Description|
|---|---|---|
|u?|`number`| |
|v?|`number`| |

### WindowAnchor
|Field|Type|Description|
|---|---|---|
|u?|`number`| |
|v?|`number`| |

### PolygonOptions
|Field|Type|Description|
|---|---|---|
|readonlyid?|`string`| |
|points|`LatLng[]`| |
|holes?|`LatLng[][]`| |
|clickable?|`boolean`| |
|geodesic?|`boolean`| |
|fillColor?|`Color`| |
|strokeColor?|`Color`| |
|strokeJointType?|`JointType`| |
|strokePattern?|`PatternItem[]`| |
|strokeWidth?|`number`| |
|visible?|`true`| |
|zIndex?|`number`| |

### PolylineOptions
|Field|Type|Description|
|---|---|---|
|readonlyid?|`string`| |
|points|`LatLng[]`| |
|clickable?|`boolean`| |
|color?|`Color`| |
|startCap?|`Cap`| |
|pattern?|`PatternItem[]`| |
|endCap?|`Cap`| |
|geodesic?|`boolean`| |
|jointType?|`JointType`| |
|visible?|`boolean`| |
|width?|`number`| |
|zIndex?|`number`| |

### GroundOverlayOptions
|Field|Type|Description|
|---|---|---|
|readonlyid?|`string`| |
|width?|`number`| |
|height?|`number`| |
|anchor?|`Anchor`| |
|bearing?|`number`| |
|clickable?|`boolean`| |
|image?|`BitmapDescriptor`| |
|visible?|`boolean`| |
|transparency?|`number`| |
|zIndex?|`number`| |
|position|`LatLng`| |
|bounds?|`LatLngBounds`| |

### Position
|Field|Type|Description|
|---|---|---|
|latLng|`LatLng`| |
|width?|`number`| |
|height?|`number`| |

### URLTile
|Field|Type|Description|
|---|---|---|
|x|`number`| |
|y|`number`| |
|zoom|`number`| |
|URI|`string`| |

### Tile
|Field|Type|Description|
|---|---|---|
|x|`number`| |
|y|`number`| |
|width|`number`| |
|height|`number`| |
|zoom|`number`| |
|path|`string`| |

### TileOverlayOptions
|Field|Type|Description|
|---|---|---|
|readonlyid?|`string`| |
|tileProvider|`(Tile[] or URLTile[])`| |
|fadeIn?|`boolean`| |
|transparency?|`number`| |
|visible?|`boolean`| |
|zIndex?|`number`| |

### AnimationSet
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|addRotateAnimation(animation:  RotateAnimation)|`void`| |
|addScaleAnimation(animation:  ScaleAnimation)|`void`| |
|addTranslateAnimation(animation:  TranslateAnimation)|`void`| |
|addAlphaAnimation(animation:  AlphaAnimation)|`void`| |
#### Public Methods

###### addRotateAnimation

Function explanation field...

###### Parameters

do something

###### addScaleAnimation

Function explanation field...

###### Parameters

do something

###### addTranslateAnimation

Function explanation field...

###### Parameters

do something

###### addAlphaAnimation

Function explanation field...

###### Parameters

do something


### Animation
|Field|Type|Description|
|---|---|---|
|duration?|`number`| |
|repeatCounter?|`number`| |
|repeatMode?|`number`| |

### _control
|Field|Type|Description|
|---|---|---|
|isAnimationStart|`boolean`| |
|isAnimationEnd|`boolean`| |

### _rotateAnimationextendsRotateAnimation,_control
|Field|Type|Description|
|---|---|---|

### _translateAnimationextendsTranslateAnimation,_control
|Field|Type|Description|
|---|---|---|

### _scaleAnimationextendsScaleAnimation,_control
|Field|Type|Description|
|---|---|---|

### _alphaAnimationextendsAlphaAnimation,_control
|Field|Type|Description|
|---|---|---|

### RotateAnimationextendsAnimation
|Field|Type|Description|
|---|---|---|
|fromDegree|`number`| |
|toDegree|`number`| |

### ScaleAnimationextendsAnimation
|Field|Type|Description|
|---|---|---|
|fromX|`number`| |
|toX|`number`| |
|fromY|`number`| |
|toY|`number`| |

### TranslateAnimationextendsAnimation
|Field|Type|Description|
|---|---|---|
|target|`LatLng`| |

### AlphaAnimationextendsAnimation
|Field|Type|Description|
|---|---|---|
|fromAlpha|`number`| |
|toAlpha|`number`| |

### CancelableCallback
|Field|Type|Description|
|---|---|---|

### InfoWindowAdapter
|Field|Type|Description|
|---|---|---|
|file|`string`| |
|width|`number`| |
|height|`number`| |

### LocationSource
|Field|Type|Description|
|---|---|---|

### MapStyleOptions
|Field|Type|Description|
|---|---|---|

### ComputeDistanceResult
|Field|Type|Description|
|---|---|---|
|result|`number`| |

### Point
|Field|Type|Description|
|---|---|---|
|xPixel|`number`| |
|yPixel|`number`| |

### SnapshotResult
|Field|Type|Description|
|---|---|---|
|data|`string`| |

### VisibleRegion
|Field|Type|Description|
|---|---|---|
|farLeft|`LatLng`| |
|farRight|`LatLng`| |
|latLngBounds|`LatLngBounds`| |
|nearLeft|`LatLng`| |
|nearRight|`LatLng`| |

### Projection
|Field|Type|Description|
|---|---|---|
|fromScreenLocation(point:Point)|`Promise<LatLng>`| |
|getVisibleRegion()|`Promise<VisibleRegion>`| |
|toScreenLocation(latLng:LatLng)|`Promise<Point>`| |

### ProjectionImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(divId:  string)|`any`| |
|fromScreenLocation(point:  Point)|`Promise<LatLng>`| |
|getVisibleRegion()|`Promise<VisibleRegion>`| |
|toScreenLocation(latLng:  LatLng)|`Promise<Point>`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### fromScreenLocation

Function explanation field...

###### Parameters

do something

###### getVisibleRegion

Function explanation field...

###### Parameters

do something

###### toScreenLocation

Function explanation field...

###### Parameters

do something


### Enum Color
|Field|Value|Description|
|---|---|---|
|RED|-65536| |
|DARK_GRAY|-12303292| |
|GRAY|-7829368| |
|WHITE|-1| |
|BLUE|-16776961| |
|BLACK|-16777216| |
|LITE_GRAY|-3355444| |
|MAGENTA|-65281| |
|YELLOW|-256| |
|CYAN|-16711681| |
|GREEN|-16711936| |
|TRANSPARENT|0| |

### Enum JointType
|Field|Value|Description|
|---|---|---|
|ROUND|2| |
|BEVEL|1| |
|DEFAULT|0| |

### Enum MapEvent
|Field|Value|Description|
|---|---|---|
|ON_INDOOR_BUILDINGS_FOCUSED|"onIndoorBuildingsFocused"| |
|ON_INDOOR_LEVEL_ACTIVATED|"onIndoorLevelActivated"| |
|ON_MAP_LOADED|"onMapLoaded"| |
|ON_CAMERA_IDLE|"onCameraIdle"| |
|ON_CAMERA_MOVE_CANCELED|"onCameraMoveCanceled"| |
|ON_CAMERA_MOVE|"onCameraMove"| |
|ON_CAMERA_MOVE_STARTED|"onCameraMoveStarted"| |
|ON_CIRCLE_CLICK|"onCircleClick"| |
|ON_GROUND_OVERLAY_CLICK|"onGroundOverlayClick"| |
|ON_INFO_WINDOW_CLICK|"onInfoWindowClick"| |
|ON_INFO_WINDOW_CLOSE|"onInfoWindowClose"| |
|ON_INFO_WINDOW_LONG_CLICK|"onInfoWindowLongClick"| |
|ON_MAP_CLICK|"onMapClick"| |
|ON_MAP_LONG_CLICK|"onMapLongClick"| |
|ON_MARKER_CLICK|"onMarkerClick"| |
|ON_MARKER_DRAG_START|"onMarkerDragStart"| |
|ON_MARKER_DRAG|"onMarkerDrag"| |
|ON_MARKER_DRAG_END|"onMarkerDragEnd"| |
|ON_MY_LOCATION_BUTTON_CLICK|"onMyLocationButtonClick"| |
|ON_MY_LOCATION_CLICK|"onMyLocationClick"| |
|ON_POI_CLICK|"onPoiClick"| |
|ON_POLYGON_CLICK|"onPolygonClick"| |
|ON_POLYLINE_CLICK|"onPolylineClick"| |
|ON_CANCELABLE_CALLBACK_FINISH|"onCancelableCallbackFinish"| |
|ON_CANCELABLE_CALLBACK_CANCEL|"onCancelableCallbackCancel"| |
|ON_SNAPSHOT_READY_CALLBACK|"onSnapshotReadyCallback"| |

### ErrorCode
|Field|Type|Description|
|---|---|---|
|code|`number`| |
|message|`string`| |

### ErrorCodes
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|toString(error :  ErrorCode)|`string`| |
#### Public Methods

###### toString

Function explanation field...

###### Parameters

do something


### Enum MapType
|Field|Value|Description|
|---|---|---|
|MAP_TYPE_NONE|0| |
|MAP_TYPE_SATELLITE|2| |
|MAP_TYPE_NORMAL|1| |
|MAP_TYPE_HYBRID|4| |
|MAP_TYPE_TERRAIN|3| |

### Enum Hue
|Field|Value|Description|
|---|---|---|
|HUE_GREEN|120| |
|HUE_AZURE|210| |
|HUE_ROSE|330| |
|HUE_CYAN|180| |
|HUE_ORANGE|30| |
|HUE_MAGENTA|300| |
|HUE_VIOLET|270| |
|HUE_YELLOW|60| |
|HUE_BLUE|240| |
|HUE_RED|0| |

### Enum PatternItemType
|Field|Value|Description|
|---|---|---|
|TYPE_GAP|2| |
|TYPE_DOT|1| |
|TYPE_DASH|0| |

### Enum CameraMoveStartedReason
|Field|Value|Description|
|---|---|---|
|REASON_API_ANIMATION|2| |
|REASON_DEVELOPER_ANIMATION|3| |
|REASON_GESTURE|1| |

### Marker
|Field|Type|Description|
|---|---|---|
|getAlpha()|`Promise<number>`| |
|getId()|`string`| |
|getPosition()|`Promise<LatLng>`| |
|getRotation()|`Promise<number>`| |
|getSnippet()|`Promise<string>`| |
|getTag()|`Promise<any>`| |
|getTitle()|`Promise<string>`| |
|getZIndex()|`Promise<number>`| |
|isVisible()|`Promise<boolean>`| |
|isClusterable()|`Promise<boolean>`| |
|isDraggable()|`Promise<boolean>`| |
|isFlat()|`Promise<boolean>`| |
|isInfoWindowShown()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|hideInfoWindow()|`Promise<void>`| |
|showInfoWindow()|`Promise<void>`| |
|startAnimation()|`Promise<void>`| |
|setAlpha(alpha:number)|`Promise<void>`| |
|setAnimation(animation:AnimationSet)|`Promise<void>`| |
|setDraggable(draggable:boolean)|`Promise<void>`| |
|setFlat(flat:boolean)|`Promise<void>`| |
|setIcon(icon:BitmapDescriptor)|`Promise<void>`| |
|setInfoWindowAnchor(u:numberv:number)|`Promise<void>`| |
|setMarkerAnchor(u:numberv:number)|`Promise<void>`| |
|setPosition(latLng:LatLng)|`Promise<void>`| |
|setRotation(rotation:number)|`Promise<void>`| |
|setSnippet(snippet:String)|`Promise<void>`| |
|setTitle(title:string)|`Promise<void>`| |
|setTag(tag:any)|`Promise<void>`| |
|setVisible(visible:boolean)|`Promise<void>`| |
|setZIndex(zIndex:number)|`Promise<void>`| |

### MarkerImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(mapDivId:  string,  mapCapsuleId:  number,  options:  MarkerOptions)|`any`| |
|getComponentOptions(func:  string)|`Promise<any>`| |
|getAlpha()|`Promise<number>`| |
|getId()|`string`| |
|getPosition()|`Promise<LatLng>`| |
|getRotation()|`Promise<number>`| |
|getSnippet()|`Promise<string>`| |
|getTag()|`Promise<any>`| |
|getTitle()|`Promise<string>`| |
|getZIndex()|`Promise<number>`| |
|isVisible()|`Promise<boolean>`| |
|isClusterable()|`Promise<boolean>`| |
|isDraggable()|`Promise<boolean>`| |
|isFlat()|`Promise<boolean>`| |
|isInfoWindowShown()|`Promise<boolean>`| |
|startAnimation()|`Promise<void>`| |
|remove()|`Promise<void>`| |
|hideInfoWindow()|`Promise<void>`| |
|showInfoWindow()|`Promise<void>`| |
|setAlpha(alpha:  number)|`Promise<void>`| |
|setAnimation(animation:  AnimationSet)|`Promise<void>`| |
|setDraggable(draggable:  boolean)|`Promise<void>`| |
|setFlat(flat:  boolean)|`Promise<void>`| |
|setIcon(icon:  BitmapDescriptor)|`Promise<void>`| |
|setInfoWindowAnchor(u:  number,  v:  number)|`Promise<void>`| |
|setMarkerAnchor(u:  number,  v:  number)|`Promise<void>`| |
|setPosition(latLng:  LatLng)|`Promise<void>`| |
|setRotation(rotation:  number)|`Promise<void>`| |
|setSnippet(snippet:  String)|`Promise<void>`| |
|setTitle(title:  string)|`Promise<void>`| |
|setTag(tag:  any)|`Promise<void>`| |
|setVisible(visible:  boolean)|`Promise<void>`| |
|setZIndex(zIndex:  number)|`Promise<void>`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### setComponentOptions

Function explanation field...

###### Parameters

do something

###### getComponentOptions

Function explanation field...

###### Parameters

do something

###### getAlpha

Function explanation field...

###### Parameters

do something

###### getId

Function explanation field...

###### Parameters

do something

###### getPosition

Function explanation field...

###### Parameters

do something

###### getRotation

Function explanation field...

###### Parameters

do something

###### getSnippet

Function explanation field...

###### Parameters

do something

###### getTag

Function explanation field...

###### Parameters

do something

###### getTitle

Function explanation field...

###### Parameters

do something

###### getZIndex

Function explanation field...

###### Parameters

do something

###### isVisible

Function explanation field...

###### Parameters

do something

###### isClusterable

Function explanation field...

###### Parameters

do something

###### isDraggable

Function explanation field...

###### Parameters

do something

###### isFlat

Function explanation field...

###### Parameters

do something

###### isInfoWindowShown

Function explanation field...

###### Parameters

do something

###### startAnimation

Function explanation field...

###### Parameters

do something

###### remove

Function explanation field...

###### Parameters

do something

###### hideInfoWindow

Function explanation field...

###### Parameters

do something

###### showInfoWindow

Function explanation field...

###### Parameters

do something

###### setAlpha

Function explanation field...

###### Parameters

do something

###### setAnimation

Function explanation field...

###### Parameters

do something

###### setDraggable

Function explanation field...

###### Parameters

do something

###### setFlat

Function explanation field...

###### Parameters

do something

###### setIcon

Function explanation field...

###### Parameters

do something

###### setInfoWindowAnchor

Function explanation field...

###### Parameters

do something

###### setMarkerAnchor

Function explanation field...

###### Parameters

do something

###### setPosition

Function explanation field...

###### Parameters

do something

###### setRotation

Function explanation field...

###### Parameters

do something

###### setSnippet

Function explanation field...

###### Parameters

do something

###### setTitle

Function explanation field...

###### Parameters

do something

###### setTag

Function explanation field...

###### Parameters

do something

###### setVisible

Function explanation field...

###### Parameters

do something

###### setZIndex

Function explanation field...

###### Parameters

do something


### Polygon
|Field|Type|Description|
|---|---|---|
|getFillColor()|`Promise<Color>`| |
|getHoles()|`Promise<LatLng[][]>`| |
|getId()|`string`| |
|getPoints()|`Promise<LatLng[]>`| |
|getStrokeColor()|`Promise<Color>`| |
|getStrokeJointType()|`Promise<JointType>`| |
|getStrokePattern()|`Promise<PatternItem[]>`| |
|getStrokeWidth()|`Promise<number>`| |
|getTag()|`Promise<any>`| |
|getZIndex()|`Promise<number>`| |
|isClickable()|`Promise<boolean>`| |
|isVisible()|`Promise<boolean>`| |
|isGeodesic()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|setClickable(clickable:boolean)|`Promise<void>`| |
|setFillColor(color:Color)|`Promise<void>`| |
|setGeodesic(geodesic:boolean)|`Promise<void>`| |
|setHoles(holes:LatLng[][])|`Promise<void>`| |
|setPoints(points:LatLng[])|`Promise<void>`| |
|setStrokeColor(color:Color)|`Promise<void>`| |
|setStrokeJointType(jointType:JointType)|`Promise<void>`| |
|setStrokePattern(patternItem:PatternItem[])|`Promise<void>`| |
|setStrokeWidth(width:number)|`Promise<void>`| |
|setTag(tag:any)|`Promise<void>`| |
|setVisible(visible:boolean)|`Promise<void>`| |
|setZIndex(zIndex:number)|`Promise<void>`| |

### PolygonImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|	constructor(mapDivId:  string,  mapCapsuleId:  number,  options:  PolygonOptions)|`any`| |
|setComponentOptions(func:  string,  params:  any)|`Promise<any>`| |
|getComponentOptions(func:  string)|`Promise<any>`| |
|	getFillColor()|`Promise<Color>`| |
|	getHoles()|`Promise<LatLng[][]>`| |
|	getId()|`string`| |
|	getPoints()|`Promise<LatLng[]>`| |
|	getStrokeColor()|`Promise<Color>`| |
|	getStrokeJointType()|`Promise<JointType>`| |
|	getStrokePattern()|`Promise<PatternItem[]>`| |
|	getStrokeWidth()|`Promise<number>`| |
|	getTag()|`Promise<any>`| |
|	getZIndex()|`Promise<number>`| |
|	isClickable()|`Promise<boolean>`| |
|	isVisible()|`Promise<boolean>`| |
|	isGeodesic()|`Promise<boolean>`| |
|	remove()|`Promise<void>`| |
|	setClickable(clickable:  boolean)|`Promise<void>`| |
|	setFillColor(fillColor:  Color)|`Promise<void>`| |
|	setGeodesic(geodesic:  boolean)|`Promise<void>`| |
|	setHoles(holes:  LatLng[][])|`Promise<void>`| |
|	setPoints(points:  LatLng[])|`Promise<void>`| |
|	setStrokeColor(strokeColor:  Color)|`Promise<void>`| |
|	setStrokeJointType(strokeJointType:  JointType)|`Promise<void>`| |
|	setStrokePattern(strokePattern:  PatternItem[])|`Promise<void>`| |
|	setStrokeWidth(strokeWidth:  number)|`Promise<void>`| |
|	setTag(tag:  any)|`Promise<void>`| |
|	setVisible(visible:  boolean)|`Promise<void>`| |
|	setZIndex(zIndex:  number)|`Promise<void>`| |
#### Public Methods

###### 	constructor

Function explanation field...

###### Parameters

do something

###### setComponentOptions

Function explanation field...

###### Parameters

do something

###### getComponentOptions

Function explanation field...

###### Parameters

do something

###### 	getFillColor

Function explanation field...

###### Parameters

do something

###### 	getHoles

Function explanation field...

###### Parameters

do something

###### 	getId

Function explanation field...

###### Parameters

do something

###### 	getPoints

Function explanation field...

###### Parameters

do something

###### 	getStrokeColor

Function explanation field...

###### Parameters

do something

###### 	getStrokeJointType

Function explanation field...

###### Parameters

do something

###### 	getStrokePattern

Function explanation field...

###### Parameters

do something

###### 	getStrokeWidth

Function explanation field...

###### Parameters

do something

###### 	getTag

Function explanation field...

###### Parameters

do something

###### 	getZIndex

Function explanation field...

###### Parameters

do something

###### 	isClickable

Function explanation field...

###### Parameters

do something

###### 	isVisible

Function explanation field...

###### Parameters

do something

###### 	isGeodesic

Function explanation field...

###### Parameters

do something

###### 	remove

Function explanation field...

###### Parameters

do something

###### 	setClickable

Function explanation field...

###### Parameters

do something

###### 	setFillColor

Function explanation field...

###### Parameters

do something

###### 	setGeodesic

Function explanation field...

###### Parameters

do something

###### 	setHoles

Function explanation field...

###### Parameters

do something

###### 	setPoints

Function explanation field...

###### Parameters

do something

###### 	setStrokeColor

Function explanation field...

###### Parameters

do something

###### 	setStrokeJointType

Function explanation field...

###### Parameters

do something

###### 	setStrokePattern

Function explanation field...

###### Parameters

do something

###### 	setStrokeWidth

Function explanation field...

###### Parameters

do something

###### 	setTag

Function explanation field...

###### Parameters

do something

###### 	setVisible

Function explanation field...

###### Parameters

do something

###### 	setZIndex

Function explanation field...

###### Parameters

do something


### Polyline
|Field|Type|Description|
|---|---|---|
|getColor()|`Promise<Color>`| |
|getEndCap()|`Promise<Cap>`| |
|getStartCap()|`Promise<Cap>`| |
|getId()|`string`| |
|getJointType()|`Promise<JointType>`| |
|getPattern()|`Promise<PatternItem[]>`| |
|getPoints()|`Promise<LatLng[]>`| |
|getTag()|`Promise<any>`| |
|getWidth()|`Promise<number>`| |
|getZIndex()|`Promise<number>`| |
|isClickable()|`Promise<boolean>`| |
|isGeodesic()|`Promise<boolean>`| |
|isVisible()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|setClickable(clickable:boolean)|`Promise<void>`| |
|setColor(color:Color)|`Promise<void>`| |
|setStartCap(startCap:Cap)|`Promise<void>`| |
|setEndCap(endCap:Cap)|`Promise<void>`| |
|setGeodesic(geodesic:boolean)|`Promise<void>`| |
|setJointType(jointType:JointType)|`Promise<void>`| |
|setPattern(pattern:PatternItem[])|`Promise<void>`| |
|setPoints(points:LatLng[])|`Promise<void>`| |
|setTag(tag:any)|`Promise<void>`| |
|setVisible(visible:boolean)|`Promise<void>`| |
|setWidth(width:number)|`Promise<void>`| |
|setZIndex(zIndex:number)|`Promise<void>`| |

### PolylineImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|	constructor(mapDivId:  string,  mapCapsuleId:  number,  options:  PolylineOptions)|`any`| |
|setComponentOptions(func:  string,  params:  any)|`Promise<any>`| |
|getComponentOptions(func:  string)|`Promise<any>`| |
|	getColor()|`Promise<Color>`| |
|	getEndCap()|`Promise<Cap>`| |
|	getStartCap()|`Promise<Cap>`| |
|	getId()|`string`| |
|	getJointType()|`Promise<JointType>`| |
|	getPattern()|`Promise<PatternItem[]>`| |
|	getPoints()|`Promise<LatLng[]>`| |
|	getTag()|`Promise<any>`| |
|	getWidth()|`Promise<number>`| |
|	getZIndex()|`Promise<number>`| |
|	isClickable()|`Promise<boolean>`| |
|	isGeodesic()|`Promise<boolean>`| |
|	isVisible()|`Promise<boolean>`| |
|	remove()|`Promise<void>`| |
|	setClickable(clickable:  boolean)|`Promise<void>`| |
|	setColor(color:  Color)|`Promise<void>`| |
|parseCap(promise:  Promise<any>)|`Promise<Cap>`| |
|setCap(cap:  Cap,  methodName:  string)|`Promise<void>`| |
|	setStartCap(startCap:  Cap)|`Promise<void>`| |
|	setEndCap(endCap:  Cap)|`Promise<void>`| |
|	setGeodesic(geodesic:  boolean)|`Promise<void>`| |
|	setJointType(jointType:  JointType)|`Promise<void>`| |
|	setPattern(pattern:  PatternItem[])|`Promise<void>`| |
|	setPoints(points:  LatLng[])|`Promise<void>`| |
|	setTag(tag:  any)|`Promise<void>`| |
|	setVisible(visible:  boolean)|`Promise<void>`| |
|	setWidth(width:  number)|`Promise<void>`| |
|	setZIndex(zIndex:  number)|`Promise<void>`| |
#### Public Methods

###### 	constructor

Function explanation field...

###### Parameters

do something

###### setComponentOptions

Function explanation field...

###### Parameters

do something

###### getComponentOptions

Function explanation field...

###### Parameters

do something

###### 	getColor

Function explanation field...

###### Parameters

do something

###### 	getEndCap

Function explanation field...

###### Parameters

do something

###### 	getStartCap

Function explanation field...

###### Parameters

do something

###### 	getId

Function explanation field...

###### Parameters

do something

###### 	getJointType

Function explanation field...

###### Parameters

do something

###### 	getPattern

Function explanation field...

###### Parameters

do something

###### 	getPoints

Function explanation field...

###### Parameters

do something

###### 	getTag

Function explanation field...

###### Parameters

do something

###### 	getWidth

Function explanation field...

###### Parameters

do something

###### 	getZIndex

Function explanation field...

###### Parameters

do something

###### 	isClickable

Function explanation field...

###### Parameters

do something

###### 	isGeodesic

Function explanation field...

###### Parameters

do something

###### 	isVisible

Function explanation field...

###### Parameters

do something

###### 	remove

Function explanation field...

###### Parameters

do something

###### 	setClickable

Function explanation field...

###### Parameters

do something

###### 	setColor

Function explanation field...

###### Parameters

do something

###### parseCap

Function explanation field...

###### Parameters

do something

###### setCap

Function explanation field...

###### Parameters

do something

###### 	setStartCap

Function explanation field...

###### Parameters

do something

###### 	setEndCap

Function explanation field...

###### Parameters

do something

###### 	setGeodesic

Function explanation field...

###### Parameters

do something

###### 	setJointType

Function explanation field...

###### Parameters

do something

###### 	setPattern

Function explanation field...

###### Parameters

do something

###### 	setPoints

Function explanation field...

###### Parameters

do something

###### 	setTag

Function explanation field...

###### Parameters

do something

###### 	setVisible

Function explanation field...

###### Parameters

do something

###### 	setWidth

Function explanation field...

###### Parameters

do something

###### 	setZIndex

Function explanation field...

###### Parameters

do something


### Cap
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|	getType()|`number`| |
#### Public Methods

###### 	getType

Function explanation field...

###### Parameters

do something


### ButtCap
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|23	constructor()|`any`| |
#### Public Methods

###### 23	constructor

Function explanation field...

###### Parameters

do something


### RoundCap
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|23	constructor()|`any`| |
#### Public Methods

###### 23	constructor

Function explanation field...

###### Parameters

do something


### SquareCap
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|23	constructor()|`any`| |
#### Public Methods

###### 23	constructor

Function explanation field...

###### Parameters

do something


### CustomCap
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|	constructor(bitmapDescriptor:  BitmapDescriptor,  refWidth?:  number)|`any`| |
|	getRefWidth()|`number`| |
|	getBitmapDescriptor()|`BitmapDescriptor`| |
#### Public Methods

###### 	constructor

Function explanation field...

###### Parameters

do something

###### 	getRefWidth

Function explanation field...

###### Parameters

do something

###### 	getBitmapDescriptor

Function explanation field...

###### Parameters

do something


### TileOverlay
|Field|Type|Description|
|---|---|---|
|getFadeIn()|`Promise<boolean>`| |
|getId()|`string`| |
|getTransparency()|`Promise<number>`| |
|getZIndex()|`Promise<number>`| |
|isVisible()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|clearTileCache()|`Promise<void>`| |
|setFadeIn(fadeIn:boolean)|`Promise<void>`| |
|setTransparency(transparency:number)|`Promise<void>`| |
|setZIndex(zIndex:number)|`Promise<void>`| |
|setVisible(visible:boolean)|`Promise<void>`| |

### TileOverlayImpl
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(mapDivId:  string,  mapCapsuleId:  number,  options:  TileOverlayOptions)|`any`| |
|getComponentOptions(func:  string)|`Promise<any>`| |
|getFadeIn()|`Promise<boolean>`| |
|getId()|`string`| |
|getTransparency()|`Promise<number>`| |
|getZIndex()|`Promise<number>`| |
|isVisible()|`Promise<boolean>`| |
|remove()|`Promise<void>`| |
|clearTileCache()|`Promise<void>`| |
|setFadeIn(fadeIn:  boolean)|`Promise<void>`| |
|setTransparency(transparency:  number)|`Promise<void>`| |
|setZIndex(zIndex:  number)|`Promise<void>`| |
|setVisible(visible:  boolean)|`Promise<void>`| |
#### Public Methods

###### constructor

Function explanation field...

###### Parameters

do something

###### setComponentOptions

Function explanation field...

###### Parameters

do something

###### getComponentOptions

Function explanation field...

###### Parameters

do something

###### getFadeIn

Function explanation field...

###### Parameters

do something

###### getId

Function explanation field...

###### Parameters

do something

###### getTransparency

Function explanation field...

###### Parameters

do something

###### getZIndex

Function explanation field...

###### Parameters

do something

###### isVisible

Function explanation field...

###### Parameters

do something

###### remove

Function explanation field...

###### Parameters

do something

###### clearTileCache

Function explanation field...

###### Parameters

do something

###### setFadeIn

Function explanation field...

###### Parameters

do something

###### setTransparency

Function explanation field...

###### Parameters

do something

###### setZIndex

Function explanation field...

###### Parameters

do something

###### setVisible

Function explanation field...

###### Parameters

do something


### Public Method Summary
|Parameters|Return Type|Description|
|---|---|---|
|initialPropsOf(map:HTMLElement)|`any`||
|sync(mapId:number, mapDiv:string, components:any)|`any`||
|getMap(divId:string, huaweiMapOptions:HuaweiMapOptions, bounds?:MapBounds)|`Promise<HuaweiMap>`||
|showMap(divId:string)|`Promise<HuaweiMap>`||
|requestPermission()|`Promise<void>`||
|computeDistanceBetween(from:LatLng, to:LatLng)|`Promise<ComputeDistanceResult>`||
|setApiKey(apiKey:string)|`Promise<void>`||
|asyncExec(clazz:string, func:string, args:any[]=[])|`Promise<any>`||


---

---
## 4. Configuration and Description

### Receiving Custom Intent URI
To receive the custom intent URIs you need to declare an intent filter inside **AndroidManifest.xml** file as shown below. Replace the value for the **android:scheme** with your custom scheme (for example: myapp).

```xml
<manifest ...>
  <!-- Other configurations -->
  <application ...>
    <activity ...>
      <!-- Other configurations -->

      <!-- The Intent filter below is for receiving custom intents-->
      <intent-filter>
          <action android:name="android.intent.action.VIEW" />
          <category android:name="android.intent.category.DEFAULT" />
          <category android:name="android.intent.category.BROWSABLE" />
          <data android:scheme="<YOUR_SCHEME>"/>
      </intent-filter>
    </activity>
  </application>
</manifest>
```


### Auto-Initialization

The HMS Core Map SDK provides the capability of automatically generating AAIDs and automatically applying for tokens. After this capability is configured, the applied token is returned. You can configure automatic initialization by adding the meta-data section to the AndroidManifest.xml file or calling the [setAutoInitEnabled(boolean enable)](#futurestring-mapsetautoinitenabledbool-enabled) method from the Map SDK.

To enable Auto-Initialization with the configuration, add the meta-data section under application tag in the **AndroidManifest.xml** file.

```xml
<manifest ...>
    <!-- Other configurations -->
    <application ...>
        <activity ...>
          <!-- Other configurations -->

        </activity>
        <!-- Setting map kit auto enable to true -->
        <meta-data
            android:name="map_kit_auto_init_enabled"
            android:value="true" />
        <!-- Other configurations -->
    </application>
</manifest>
```

#### Vibration

In order to customize the vibration pattern of the local notifications you need to add the permission below to **AndroidManifest.xml** file

```xml
<uses-permission android:name="android.permission.VIBRATE" />
```

#### Playing Custom Sound

For playing a custom sound for a local notification you should add your sound file as a raw resource. The path for raw resources as follows: **<your_cordova_project>/android/app/src/main/res/raw**



---
## 5. Sample Project

You can find demo applications about using the library here

[Cordova Sample Code](https://developer.huawei.com/consumer/en/doc/development/HMS-Plugin-Examples/cordova-sample-code-0000001050135749)

[Ionic Sample Code](https://developer.huawei.com/consumer/en/doc/development/HMS-Plugin-Examples/ionic-sample-code-0000001052494278)

---
## 6. Questions or Issues

If you have questions about how to use HMS samples, try the following options:
- [Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services) is the best place for any programming questions. Be sure to tag your question with
**huawei-mobile-services**.
- [Github](https://github.com/HMS-Core/hms-cordova-plugin/) is the official repository for these plugins, You can open an issue or submit your ideas.
- [Huawei Developer Forum](https://forums.developer.huawei.com/forumPortal/en/home?fid=0101187876626530001) HMS Core Module is great for general questions, or seeking recommendations and opinions.
- [Huawei Developer Docs](https://developer.huawei.com/consumer/en/doc/overview/HMS-Core-Plugin) is place to official documentation for all HMS Core Kits, you can find detailed documentations in there.

If you run into a bug in our samples, please submit an issue to the Github Repository.

---
## 7. Licencing and Terms
Huawei Map Kit Cordova Plugin uses the Apache 2.0 license.

