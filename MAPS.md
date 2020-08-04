### Class CallableObj
|Field|Return Type|Description|
|---|---|---|
|constructor(mapId:  string,  obj:  any)|`any`| |
|call(funcName:  string,  arg?:  any)|`any`| |

### Class HuaweiMap
|Field|Return Type|Description|
|---|---|---|
|init(id:  string,  initialProps:  InitialProps)|`any`| |
|set(propName:  string or object,  value?:  any)|`any`| |
|get(propName?:  string)|`any`| |
|on(eventName:  string,  handler:  Handler)|`any`| |
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
|refreshProps()|`any`| |
|handleMutation(mutationRecords:  any)|`any`| |

### Interface LatLng
|Field|Type|Description|
|---|---|---|
|lat|`number`| |
|lng|`number`| |

### Interface PatternItem
|Field|Type|Description|
|---|---|---|
|type?|`PatternItemType`| |
|length?|`number`| |

### Interface Anchor
|Field|Type|Description|
|---|---|---|
|u|`number`| |
|v|`number`| |

### Interface Bitmap
|Field|Type|Description|
|---|---|---|
|hue?|`Hue`| |
|asset?|`string`| |
|fileName?|`string`| |
|path?|`string`| |

### Interface LatLngBounds
|Field|Type|Description|
|---|---|---|
|southwest|`LatLng`| |
|northeast|`LatLng`| |
|center?|`LatLng`| |

### Interface Position
|Field|Type|Description|
|---|---|---|
|width?|`number`| |
|height?|`number`| |
|latLng?|`LatLng`| |
|latLngBounds?|`LatLngBounds`| |

### Interface Cap
|Field|Type|Description|
|---|---|---|
|type?|`CapType`| |
|refWidth?|`number`| |
|bitmapDescriptor?|`Bitmap`| |

### Interface CircleOptions
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

### Interface GroundOverlayOptions
|Field|Type|Description|
|---|---|---|
|anchor?|`Anchor`| |
|bearing?|`number`| |
|clickable?|`boolean`| |
|image?|`Bitmap`| |
|visible?|`boolean`| |
|transparency?|`number`| |
|zIndex?|`number`| |
|position?|`Position`| |

### Interface MarkerOptions
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

### Interface PolygonOptions
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

### Interface PolylineOptions
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

### Interface ComputeDistance
|Field|Type|Description|
|---|---|---|
|from?|`LatLng`| |
|to?|`LatLng`| |

### Interface CameraUpdate
|Field|Type|Description|
|---|---|---|
|method?|`string`| |
|[other|`string]`| |

### Interface InitialProps
|Field|Type|Description|
|---|---|---|
|x|`number`| |
|y|`number`| |
|width|`number`| |
|height|`number`| |
|paddingLeft|`number`| |
|paddingTop|`number`| |
|borderLeft|`number`| |
|borderTop|`number`| |
|[other|`string]`| |

### Interface BaseMapObject<T>
|Field|Type|Description|
|---|---|---|
|set(propName|`stringpropValue`| |
|set(keyVals|`any)`| |
|update()|`Promise<T>`| |
|remove()|`Promise<any>`| |
|[other|`string]`| |

### Interface PolylineextendsBaseMapObject<Polyline>
|Field|Type|Description|
|---|---|---|

### Interface PolygonextendsBaseMapObject<Polygon>
|Field|Type|Description|
|---|---|---|

### Interface CircleextendsBaseMapObject<Circle>
|Field|Type|Description|
|---|---|---|

### Interface GroundOverlayextendsBaseMapObject<GroundOverlay>
|Field|Type|Description|
|---|---|---|

### Interface MarkerextendsBaseMapObject<Marker>
|Field|Type|Description|
|---|---|---|
|showInfoWindow()|`Promise<void>`| |
|hideInfoWindow()|`Promise<void>`| |

### Enum PatternItemType
|Field|Value|
|---|---|
|TYPE_GAP|2|
|TYPE_DOT|1|
|TYPE_DASH|0|

### Enum JointType
|Field|Value|
|---|---|
|ROUND|2|
|BEVEL|1|
|DEFAULT|0|

### Enum CapType
|Field|Value|
|---|---|
|TYPE_BUTT_CAP|0|
|TYPE_SQUARE_CAP|1|
|TYPE_ROUND_CAP|2|
|TYPE_CUSTOM_CAP|3|

### Enum CameraMoveReason
|Field|Value|
|---|---|
|REASON_DEVELOPER_ANIMATION|3|
|REASON_API_ANIMATION|2|
|REASON_GESTURE|1|

### Enum Color
|Field|Value|
|---|---|
|RED|-65536|
|DKGRAY|-12303292|
|GRAY|-7829368|
|WHITE|-1|
|BLUE|-16776961|
|BLACK|-16777216|
|LTGRAY|-3355444|
|MAGENTA|-65281|
|YELLOW|-256|
|CYAN|-16711681|
|GREEN|-16711936|
|TRANSPARENT|0|

### Enum MapType
|Field|Value|
|---|---|
|MAP_TYPE_NONE|0|
|MAP_TYPE_SATELLITE|2|
|MAP_TYPE_NORMAL|1|
|MAP_TYPE_HYBRID|4|
|MAP_TYPE_TERRAIN|3|

### Enum MarkerConstants
|Field|Value|
|---|---|
|MARKER_DRAG|2|
|MARKER_DRAG_END|3|
|MARKER_DRAG_START|1|

### Enum Hue
|Field|Value|
|---|---|
|HUE_GREEN|120|
|HUE_AZURE|210|
|HUE_ROSE|330|
|HUE_CYAN|180|
|HUE_ORANGE|30|
|HUE_MAGENTA|300|
|HUE_VIOLET|270|
|HUE_YELLOW|60|
|HUE_BLUE|240|
|HUE_RED|0|

### Interface Window
|Field|Type|Description|
|---|---|---|
|hmsEventHandlers|`{`| |
|[key|`string]`| |

### Public Method Summary
|Parameters|Return Type|Description|
|---|---|---|
|handleDisplacement(event:any)|`any`||
|dispatcher(mapId:string, promise:Promise<any>)|`any`||
|init()|`Promise<void>`||
|create(divId:string, initialProps:InitialProps)|`Promise<HuaweiMap>`||
|computeDistanceBetween(arg:ComputeDistance)|`Promise<void>`||
|hasLocationPermission()|`Promise<void>`||
|requestLocationPermission()|`Promise<void>`||
|asyncExec(clazz:string, func:string, args:any[]=[])|`Promise<void>`||
|initHms()|`any`||
|initEventHandler()|`any`||
|initConstantSetter()|`any`||
