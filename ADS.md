### Class Banner
|Field|Return Type|Description|
|---|---|---|
|constructor()|`any`| |
|on(eventName:  BannerAdEvents,  handler:  () => void)|`void`| |
|create(options:  BannerAdOptions)|`Promise<Banner>`| |
|loadAd(adParam:  AdParam)|`Promise<void>`| |
|pause()|`Promise<void>`| |
|resume()|`Promise<void>`| |
|destroy()|`Promise<void>`| |

### Interface AdParam
|Field|Type|Description|
|---|---|---|
|gender?|`Gender`| |
|adContentClassification?|`AdContentClassification`| |
|tagForUnderAgeOfPromise?|`UnderAgeOfPromise`| |
|tagForChildProtection?|`ChildProtection`| |
|nonPersonalizedAd?|`NonPersonalizedAd//Checkthis`| |
|appCountry?|`string`| |
|appLang?|`string`| |
|countryCode?|`string`| |

### Interface BannerAdOptions
|Field|Type|Description|
|---|---|---|
|adId?|`string`| |
|bannerAdSize?|`BannerAdSize`| |
|bgColor?|`Colors`| |
|anchor?|`'top' or 'bottom'`| |
|bannerRefresh?|`number//long`| |

### Interface SplashAdLogoOptions
|Field|Type|Description|
|---|---|---|
|hidden?|`boolean`| |
|anchor?|`'top' or 'bottom'`| |
|owner?|`string`| |
|copyright?|`string`| |
|bg?|`Colors`| |

### Interface SplashAdOptions
|Field|Type|Description|
|---|---|---|
|preload?|`boolean`| |
|orientation?|`ScreenOrientation`| |
|adId?|`string`| |
|ad?|`AdParam`| |
|sloganResId?|`string`| |
|wideSloganResId?|`string`| |
|audioFocusType?|`AudioFocusType`| |
|logo?|`SplashAdLogoOptions`| |

### Interface SplashAdLoadOptions
|Field|Type|Description|
|---|---|---|
|orientation?|`ScreenOrientation`| |
|adId?|`string`| |
|ad?|`AdParam`| |

### Interface RewardVerifyConfig
|Field|Type|Description|
|---|---|---|
|data?|`string`| |
|userId?|`string`| |

### Interface RewardAdOptions
|Field|Type|Description|
|---|---|---|
|adId?|`string`| |
|userId?|`string`| |
|data?|`string`| |
|immersive?|`boolean`| |
|rewardVerifyConfig?|`RewardVerifyConfig`| |

### Interface InterstitialAdOptions
|Field|Type|Description|
|---|---|---|
|adId?|`string`| |

### Interface NativeAdOptions
|Field|Type|Description|
|---|---|---|
|div?|`string`| |
|template?|`"small" or "full" or "banner" or "video"`| |
|bg?|`Colors`| |

### Interface AdSize
|Field|Type|Description|
|---|---|---|
|width?|`number`| |
|height?|`number`| |

### Interface VideoConfiguration
|Field|Type|Description|
|---|---|---|
|audioFocusType?|`AudioFocusType`| |
|clickToFullScreenRequest?|`boolean`| |
|startMuted?|`boolean`| |

### Interface NativeAdConfiguration
|Field|Type|Description|
|---|---|---|
|adSize?|`AdSize`| |
|choicesPosition?|`ChoicesPosition`| |
|mediaAspect?|`MediaAspect`| |
|mediaDirection?|`MediaDirection`| |
|returnUrlsForImages?|`boolean`| |
|requestCustomDislikeThisAd?|`boolean`| |
|requestMultiImages?|`boolean`| |
|videoConfiguration?|`VideoConfiguration`| |

### Interface NativeAdLoadOptions
|Field|Type|Description|
|---|---|---|
|adId?|`string`| |
|ad?|`AdParam`| |
|nativeAdOptions?|`NativeAdConfiguration`| |

### Interface NativeAdProps
|Field|Type|Description|
|---|---|---|
|width?|`number`| |
|height?|`number`| |
|paddingLeft?|`number`| |
|paddingTop?|`number`| |
|borderLeft?|`number`| |
|borderTop?|`number`| |
|x?|`number`| |
|y?|`number`| |

### Interface InitOptions
|Field|Type|Description|
|---|---|---|
|appCode?|`string`| |
|bannerFloat?|`boolean`| |

### Interface IsLoadingResult
|Field|Type|Description|
|---|---|---|
|isLoading?|`boolean`| |

### Interface IsLoadedResult
|Field|Type|Description|
|---|---|---|
|isLoaded?|`boolean`| |

### Interface OaidResult
|Field|Type|Description|
|---|---|---|
|id?|`string`| |
|isLimitAdTracingEnabled?|`boolean`| |

### Interface ReferrerDetails
|Field|Type|Description|
|---|---|---|
|responseCode?|`number`| |
|installReferrer?|`string`| |
|referrerClickTimestamp?|`number`| |
|installBeginTimestamp?|`number`| |

### Interface VerifyResult
|Field|Type|Description|
|---|---|---|
|result?|`boolean`| |

### Enum BannerAdEvents
|Field|Value|
|---|---|
|LOADED|'loaded'|
|FAILED|'failed'|
|OPENED|'opened'|
|CLICKED|'clicked'|
|LEAVE|'leave'|
|CLOSED|'closed'|

### Enum SplashAdEvents
|Field|Value|
|---|---|
|LOADED|'loaded'|
|LOAD_FAILED|'loadFailed'|
|DISMISSED|'dismissed'|
|SHOWED|'showed'|
|CLICK|'click'|

### Enum RewardAdEvents
|Field|Value|
|---|---|
|LOADED|'loaded'|
|LOAD_FAILED|'loadFailed'|
|OPENED|'opened'|
|SHOW_FAILED|'showFailed'|
|CLOSED|'closed'|
|REWARDED|'rewarded'|

### Enum InterstitialAdEvents
|Field|Value|
|---|---|
|LOADED|'loaded'|
|LOAD_FAILED|'loadFailed'|
|DISMISSED|'dismissed'|
|SHOWED|'showed'|
|CLICK|'click'|
|METADATA_CHANGED|'metadaChanged'|

### Enum NativeAdEvents
|Field|Value|
|---|---|
|NATIVE_AD_LOADED|'nativeAdLoaded'|
|DISLIKED|'disliked'|
|VIDEO_START|'videoStart'|
|VIDEO_PLAY|'videoPlay'|
|VIDEO_PAUSE|'videoPause'|
|VIDEO_END|'videoEnd'|
|VIDEO_MUTE|'videoMute'|
|LOADED|'loaded'|
|FAILED|'failed'|
|OPENED|'opened'|
|CLICKED|'clicked'|
|LEAVE|'leave'|
|CLOSED|'closed'|

### Enum ErrorCodes
|Field|Value|
|---|---|
|INNER|0|
|INVALID_REQUEST|1|
|NETWORK_ERROR|2|
|NO_AD|3|
|AD_LOADING|4|
|LOW_API|5|
|BANNER_AD_EXPIRE|6|
|BANNER_AD_CANCEL|7|

### Enum InstallReferrerResponses
|Field|Value|
|---|---|
|SERVICE_DISCONNECTED|-1|
|DEVELOPER_ERROR|3|
|SERVICE_UNAVAILABLE|1|
|OK|0|
|FEATURE_NOT_SUPPORTED|2|

### Enum ScreenOrientation
|Field|Value|
|---|---|
|SCREEN_ORIENTATION_LANDSCAPE|0|
|SCREEN_ORIENTATION_UNSPECIFIED|-1|
|SCREEN_ORIENTATION_PORTRAIT|1|
|SCREEN_ORIENTATION_USER|2|
|SCREEN_ORIENTATION_BEHIND|3|
|SCREEN_ORIENTATION_SENSOR|4|
|SCREEN_ORIENTATION_NOSENSOR|5|
|SCREEN_ORIENTATION_SENSOR_LANDSCAPE|6|
|SCREEN_ORIENTATION_SENSOR_PORTRAIT|7|
|SCREEN_ORIENTATION_REVERSE_LANDSCAPE|8|
|SCREEN_ORIENTATION_REVERSE_PORTRAIT|9|
|SCREEN_ORIENTATION_FULL_SENSOR|10|
|SCREEN_ORIENTATION_USER_LANDSCAPE|11|
|SCREEN_ORIENTATION_USER_PORTRAIT|12|
|SCREEN_ORIENTATION_FULL_USER|13|
|SCREEN_ORIENTATION_LOCKED|14|

### Enum NonPersonalizedAd
|Field|Value|
|---|---|
|ALLOW_ALL|0|
|ALLOW_NON_PERSONALIZED|1|

### Enum ChildProtection
|Field|Value|
|---|---|
|TAG_FOR_CHILD_PROTECTION_UNSPECIFIED|-1|
|TAG_FOR_CHILD_PROTECTION_FALSE|0|
|TAG_FOR_CHILD_PROTECTION_TRUE|1|

### Enum UnderAgeOfPromise
|Field|Value|
|---|---|
|PROMISE_UNSPECIFIED|-1|
|PROMISE_FALSE|0|
|PROMISE_TRUE|1|

### Enum ConsentDebug
|Field|Value|
|---|---|
|CONSENT_DEBUG_DISABLED|0|
|CONSENT_DEBUG_NEED_CONSENT|1|
|CONSENT_DEBUG_NOT_NEED_CONSENT|2|

### Enum Consent
|Field|Value|
|---|---|
|CONSENT_PERSONALIZED|0|
|CONSENT_NON_PERSONALIZED|1|
|CONSENT_UNKNOWN|2|

### Enum AudioFocusType
|Field|Value|
|---|---|
|GAIN_AUDIO_FOCUS_ALL|0|
|NOT_GAIN_AUDIO_FOCUS_WHEN_MUTE|1|
|NOT_GAIN_AUDIO_FOCUS_ALL|2|

### Enum AdContentClassification
|Field|Value|
|---|---|
|AD_CONTENT_CLASSIFICATION_A|"A"|
|AD_CONTENT_CLASSIFICATION_PI|"PI"|
|AD_CONTENT_CLASSIFICATION_UNKOWN|""|
|AD_CONTENT_CLASSIFICATION_J|"J"|
|AD_CONTENT_CLASSIFICATION_W|"W"|

### Enum Colors
|Field|Value|
|---|---|
|RED|"RED"|
|DKGRAY|"DKGRAY"|
|GRAY|"GRAY"|
|WHITE|"WHITE"|
|BLUE|"BLUE"|
|BLACK|"BLACK"|
|LTGRAY|"LTGRAY"|
|MAGENTA|"MAGENTA"|
|YELLOW|"YELLOW"|
|CYAN|"CYAN"|
|GREEN|"GREEN"|
|TRANSPARENT|"TRANSPARENT"|

### Enum BannerAdSize
|Field|Value|
|---|---|
|BANNER_SIZE_360_57|"BANNER_SIZE_360_57"|
|BANNER_SIZE_360_144|"BANNER_SIZE_360_144"|
|BANNER_SIZE_160_600|"BANNER_SIZE_160_600"|
|BANNER_SIZE_300_250|"BANNER_SIZE_300_250"|
|BANNER_SIZE_320_100|"BANNER_SIZE_320_100"|
|BANNER_SIZE_320_50|"BANNER_SIZE_320_50"|
|BANNER_SIZE_468_60|"BANNER_SIZE_468_60"|
|BANNER_SIZE_728_90|"BANNER_SIZE_728_90"|
|BANNER_SIZE_DYNAMIC|"BANNER_SIZE_DYNAMIC"|
|BANNER_SIZE_INVALID|"BANNER_SIZE_INVALID"|
|BANNER_SIZE_SMART|"BANNER_SIZE_SMART"|

### Enum MediaAspect
|Field|Value|
|---|---|
|ASPECT_ANY|1|
|ASPECT_CUSTOM_SIZE|-1|
|ASPECT_LANDSCAPE|2|
|ASPECT_PORTRAIT|3|
|ASPECT_SQUARE|4|
|ASPECT_UNKNOWN|0|

### Enum ChoicesPosition
|Field|Value|
|---|---|
|BOTTOM_LEFT|3|
|BOTTOM_RIGHT|2|
|INVISIBLE|4|
|TOP_LEFT|0|
|TOP_RIGHT|1|

### Enum MediaDirection
|Field|Value|
|---|---|
|ANY|0|
|LANDSCAPE|2|
|PORTRAIT|1|

### Enum Gender
|Field|Value|
|---|---|
|FEMALE|2|
|MALE|1|
|UNKNOWN|0|

### Class Interstitial
|Field|Return Type|Description|
|---|---|---|
|constructor()|`any`| |
|on(eventName:  InterstitialAdEvents,  handler:  () => void)|`void`| |
|create(options:  InterstitialAdOptions)|`Promise<Interstitial>`| |
|loadAd(adParam:  AdParam)|`Promise<void>`| |
|show()|`Promise<void>`| |
|destroy()|`Promise<void>`| |
|isLoaded()|`Promise<IsLoadedResult>`| |
|isLoading()|`Promise<IsLoadingResult>`| |

### Class NativeAlreadyDefinedError
|Field|Return Type|Description|
|---|---|---|
|NativeAlreadyDefinedError()|`any`| |

### Class Native
|Field|Return Type|Description|
|---|---|---|
|constructor()|`any`| |
|on(eventName:  NativeAdEvents,  handler:  () => void)|`void`| |
|beforeCreateHook(options:  NativeAdOptions)|`void`| |
|create(options:  NativeAdOptions)|`Promise<Native>`| |
|refreshProps()|`NativeAdProps`| |
|handleMutation()|`any`| |
|scroll()|`Promise<void>`| |
|loadAd(params:  NativeAdLoadOptions)|`Promise<void>`| |
|isLoaded()|`Promise<IsLoadedResult>`| |
|isLoading()|`Promise<IsLoadingResult>`| |
|show()|`Promise<void>`| |
|destroy()|`Promise<void>`| |
|setProps(props?:  NativeAdProps)|`Promise<void>`| |

### Class Reward
|Field|Return Type|Description|
|---|---|---|
|constructor()|`any`| |
|on(eventName:  RewardAdEvents,  handler:  () => void)|`void`| |
|create(options:  RewardAdOptions)|`Promise<Reward>`| |
|loadAd(adParam:  AdParam)|`Promise<void>`| |
|show()|`Promise<void>`| |
|pause()|`Promise<void>`| |
|resume()|`Promise<void>`| |
|destroy()|`Promise<void>`| |
|isLoaded()|`Promise<IsLoadedResult>`| |

### Class Splash
|Field|Return Type|Description|
|---|---|---|
|constructor()|`any`| |
|on(eventName:  SplashAdEvents,  handler:  () => void)|`void`| |
|create(options:  SplashAdOptions)|`Promise<Splash>`| |
|load(options:  SplashAdLoadOptions)|`Promise<void>`| |
|show()|`Promise<void>`| |
|destroy()|`Promise<void>`| |
|cancel()|`Promise<void>`| |
|pause()|`Promise<void>`| |
|resume()|`Promise<void>`| |
|isLoaded()|`Promise<IsLoadedResult>`| |
|isLoading()|`Promise<IsLoadingResult>`| |

### Interface Window
|Field|Type|Description|
|---|---|---|
|hmsEventHandlers|`{`| |
|[key:string]|`Handler[]`| |

### Class Ads
|Field|Return Type|Description|
|---|---|---|
|constructor(objectName:  string = 'ads')|`any`| |
|on(eventName:  any,  handler:  () => void)|`void`| |
|create(options:  any)|`Promise<Ads>`| |
|call(funcName:  string,  opts:  any = {})|`any`| |

### Public Method Summary
|Parameters|Return Type|Description|
|---|---|---|
|init(options:InitOptions)|`Promise<void>`||
|disconnectFromReferrerClient()|`Promise<void>`||
|getOaidResult()|`Promise<OaidResult>`||
|getReferrerDetails(isTest:boolean)|`Promise<ReferrerDetails>`||
|verifyAdId(adId:string, isLimitAdTracking:boolean)|`Promise<VerifyResult>`||
|setRequestOptions(options:AdParam)|`Promise<void>`||
|getSDKVersion()|`Promise<void>`||
|setVideoMuted(muted:boolean)|`Promise<void>`||
|setVideoVolume(volume:number)|`Promise<void>`||
|addTestDeviceId(id:string)|`Promise<void>`||
|getTestDeviceId()|`Promise<void>`||
|setUnderAgeOfPromise(b:boolean)|`Promise<void>`||
|setConsentStatus(status:Consent)|`Promise<void>`||
|setDebugNeedConsent(need:ConsentDebug)|`Promise<void>`||
|requestConsentUpdate()|`Promise<void>`||
|handleDisplacement(event:any)|`any`||
|asyncExec(clazz:string, func:string, args:any[]=[])|`Promise<any>`||
|initEventHandler()|`any`||
|initConstantSetter()|`any`||
