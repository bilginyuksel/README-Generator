### CallableObj
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|constructor(mapId:  string,  obj:  any)|`any`| |
|call(funcName:  string,  arg?:  any)|`any`| |

### HuaweiMap
#### Public Method Summary
|Field|Return Type|Description|
|---|---|---|
|init(id:  string,  options:  HuaweiMapOptions)|`any`| |
|set(propName:  string or object,  value?:  any)|`Promise<void>`| |
|get(propName?:  string)|`any`| |
|on(eventName:  HuaweiMapEvent,  handler:  (data,  data2?:  any)|`=>void)`| |
|scroll()|`any`| |
|clear()|`any`| |
|resetMinMaxZoomPreference()|`any`| |
|moveCamera(opts:  CameraUpdate)|`any`| |
|animateCamera(opts:  CameraUpdate)|`any`| |
|addPolyline(opts:  PolylineOptions)|`Promise<Polyline>`| |
|addPolygon(opts:  PolygonOptions)|`Promise<Polygon>`| |
|addMarker(opts:  MarkerOptions)|`Promise<Marker>`| |
|addGroundOverlay(opts:  GroundOverlayOptions)|`Promise<GroundOverlay>`| |
|addCircle(opts:  CircleOptions)|`Promise<Circle>`| |
|dispatch(obj:  any)|`any`| |
|runAction(act:  string,  opts:  any = null)|`Promise<any>`| |

### LatLng
|Field|Type|Description|
|---|---|---|
|lat|`number`| |
|lng|`number`| |

### PatternItem
|Field|Type|Description|
|---|---|---|
|type?|`PatternItemType`| |
|length?|`number`| |

### Anchor
|Field|Type|Description|
|---|---|---|
|u|`number`| |
|v|`number`| |

### Bitmap
|Field|Type|Description|
|---|---|---|
|hue?|`Hue`| |
|asset?|`string`| |
|fileName?|`string`| |
|path?|`string`| |

### LatLngBounds
|Field|Type|Description|
|---|---|---|
|southwest|`LatLng`| |
|northeast|`LatLng`| |
|center?|`LatLng`| |

### CameraPosition
|Field|Type|Description|
|---|---|---|
|width?|`number`| |
|height?|`number`| |
|latLng?|`LatLng`| |
|latLngBounds?|`LatLngBounds`| |

### Cap
|Field|Type|Description|
|---|---|---|
|type?|`CapType`| |
|refWidth?|`number`| |
|bitmapDescriptor?|`Bitmap`| |

### CircleOptions
|Field|Type|Description|
|---|---|---|
|center?|`LatLng`| |
|clickable?|`boolean`| |
|fillColor?|`number`| |
|radius?|`number`| |
|strokeWidth?|`number`| |
|strokePattern?|`PatternItem`| |
|visible?|`boolean`| |
|zIndex?|`number`| |

### GroundOverlayOptions
|Field|Type|Description|
|---|---|---|
|anchor?|`Anchor`| |
|bearing?|`number`| |
|clickable?|`boolean`| |
|image?|`Bitmap`| |
|visible?|`boolean`| |
|transparency?|`number`| |
|zIndex?|`number`| |
|position?|`CameraPosition`| |

### MarkerOptions
|Field|Type|Description|
|---|---|---|
|anchorMarker?|`Anchor`| |
|infoWindowAnchor?|`Anchor`| |
|alpha?|`number`| |
|clusterable?|`boolean`| |
|draggable?|`boolean`| |
|icon?|`Bitmap`| |
|flat?|`boolean`| |
|position?|`LatLng`| |
|rotation?|`number`| |
|snippet?|`string`| |
|title?|`string`| |
|visible?|`boolean`| |
|zIndex?|`number`| |

### PolygonOptions
|Field|Type|Description|
|---|---|---|
|points?|`LatLng[]`| |
|holes?|`LatLng[][]`| |
|clickable?|`boolean`| |
|geodesic?|`boolean`| |
|fillColor?|`number`| |
|strokeColor?|`number`| |
|strokeJointType?|`number`| |
|strokeWidth?|`number`| |
|visible?|`boolean`| |
|zIndex?|`number`| |

### PolylineOptions
|Field|Type|Description|
|---|---|---|
|points?|`LatLng[]`| |
|clickable?|`boolean`| |
|color?|`number`| |
|endCap?|`Cap`| |
|startCap?|`Cap`| |
|geodesic?|`boolean`| |
|jointType?|`number`| |
|visible?|`boolean`| |
|zIndex?|`number`| |

### ComputeDistance
|Field|Type|Description|
|---|---|---|
|from?|`LatLng`| |
|to?|`LatLng`| |

### CameraUpdate
|Field|Type|Description|
|---|---|---|
|method?|`string`| |
|[other:string]|`any//...Otherstuffdependsonthemethod`| |

### BaseMapObject<T>
|Field|Type|Description|
|---|---|---|
|set(propName:stringpropValue:any)|`Promise<any>`| |
|set(keyVals:any)|`Promise<any>`| |
|update()|`Promise<T>`| |
|remove()|`Promise<any>`| |
|[other:string]|`any`| |

### PolylineextendsBaseMapObject<Polyline>
|Field|Type|Description|
|---|---|---|

### PolygonextendsBaseMapObject<Polygon>
|Field|Type|Description|
|---|---|---|

### CircleextendsBaseMapObject<Circle>
|Field|Type|Description|
|---|---|---|

### GroundOverlayextendsBaseMapObject<GroundOverlay>
|Field|Type|Description|
|---|---|---|

### MarkerextendsBaseMapObject<Marker>
|Field|Type|Description|
|---|---|---|
|showInfoWindow()|`Promise<void>`| |
|hideInfoWindow()|`Promise<void>`| |

### Enum PatternItemType
|Field|Value|Description|
|---|---|---|
|TYPE_GAP|2| |
|TYPE_DOT|1| |
|TYPE_DASH|0| |

### Enum JointType
|Field|Value|Description|
|---|---|---|
|ROUND|2| |
|BEVEL|1| |
|DEFAULT|0| |

### Enum CapType
|Field|Value|Description|
|---|---|---|
|TYPE_BUTT_CAP|0| |
|TYPE_SQUARE_CAP|1| |
|TYPE_ROUND_CAP|2| |
|TYPE_CUSTOM_CAP|3| |

### Enum CameraMoveReason
|Field|Value|Description|
|---|---|---|
|REASON_DEVELOPER_ANIMATION|3| |
|REASON_API_ANIMATION|2| |
|REASON_GESTURE|1| |

### Enum Color
|Field|Value|Description|
|---|---|---|
|RED|-65536| |
|DKGRAY|-12303292| |
|GRAY|-7829368| |
|WHITE|-1| |
|BLUE|-16776961| |
|BLACK|-16777216| |
|LTGRAY|-3355444| |
|MAGENTA|-65281| |
|YELLOW|-256| |
|CYAN|-16711681| |
|GREEN|-16711936| |
|TRANSPARENT|0| |

### Enum MapType
|Field|Value|Description|
|---|---|---|
|MAP_TYPE_NONE|0| |
|MAP_TYPE_SATELLITE|2| |
|MAP_TYPE_NORMAL|1| |
|MAP_TYPE_HYBRID|4| |
|MAP_TYPE_TERRAIN|3| |

### Enum MarkerConstants
|Field|Value|Description|
|---|---|---|
|MARKER_DRAG|2| |
|MARKER_DRAG_END|3| |
|MARKER_DRAG_START|1| |

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

### Enum HuaweiMapEvent
|Field|Value|Description|
|---|---|---|
|CIRCLE_CLICK|"circleClick"| |
|MAP_CLICK|"mapClick"| |
|MAP_LONG_CLICK|"mapLongClick"| |
|INFO_WINDOW_CLICK|"infoWindowClick"| |
|CAMERA_MOVE_STARTED|"cameraMoveStarted"| |
|POLYGON_CLICK|"polygonClick"| |
|POLYLINE_CLICK|"polylineClick"| |
|CAMERA_MOVE|"cameraMove"| |
|CAMERA_IDLE|"cameraIdle"| |
|MAP_LOADED|"mapLoaded"| |
|MARKER_CLICK|"markerClick"| |
|MY_LOCATION_BUTTON_CLICK|"myLocationButtonClick"| |
|MY_LOCATION_CLICK|"myLocationClick"| |
|MARKER_DRAG|"markerDrag"| |

### HuaweiMapProps
|Field|Type|Description|
|---|---|---|
|x?|`number`| |
|y?|`number`| |
|width?|`number`| |
|height?|`number`| |
|paddingLeft?|`number`| |
|paddingTop?|`number`| |
|borderLeft?|`number`| |
|borderTop?|`number`| |

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

### LocationPermissionResult
|Field|Type|Description|
|---|---|---|
|hasLocationPermission|`boolean`| |

### ComputeDistanceResult
|Field|Type|Description|
|---|---|---|
|result|`number`| |

### Window
|Field|Type|Description|
|---|---|---|
|hmsEventHandlers|`{`| |
|[key:string]|`Handler[]`| |

### Public Method Summary
|Parameters|Return Type|Description|
|---|---|---|
|handleDisplacement(event:any)|`any`||
|dispatcher(mapId:string, promise:Promise<any>)|`any`||
|init()|`Promise<void>`||
|setApiKey(apiKey:string)|`Promise<void>`||
|create(divId:string, huaweiMapOptions:HuaweiMapOptions)|`Promise<HuaweiMap>`||
|computeDistanceBetween(arg:ComputeDistance)|`Promise<ComputeDistanceResult>`||
|hasLocationPermission()|`Promise<LocationPermissionResult>`||
|requestLocationPermission()|`Promise<void>`||
|asyncExec(clazz:string, func:string, args:any[]=[])|`Promise<any>`||
|initHms()|`any`||
|initEventHandler()|`any`||
|initConstantSetter()|`any`||
