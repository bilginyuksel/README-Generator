### Interface PushData
|Field|Type|Description|
|---|---|---|
|[key:string]|`any`| |

### Public Method Summary
|Parameters|Return Type|Description|
|---|---|---|
|turnOnPush(args:string)|`Promise<string>`||
|turnOffPush(args:string)|`Promise<string>`||
|getID(args:string)|`Promise<string>`||
|getAAID(args:string)|`Promise<string>`||
|getToken(args:string)|`Promise<string>`||
|getCreationTime(args:string)|`Promise<string>`||
|deleteAAID(args:string)|`Promise<string>`||
|deleteToken(args:string)|`Promise<string>`||
|subscribe(args:string)|`Promise<string>`||
|unsubscribe(args:string)|`Promise<string>`||
|disableAutoInit(args:string)|`Promise<string>`||
|enableAutoInit(args:string)|`Promise<string>`||
|isAutoInitEnabled(args:string)|`Promise<string>`||
|test(args:string)|`Promise<string>`||
|onMessageReceived(args:string)|`Promise<PushData>`||
|run(funcName:string, args:string)|`any`||
|asyncExec(className:string, funcName:string, args:any[]=[])|`Promise<any>`||
