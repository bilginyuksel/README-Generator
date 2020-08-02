## Automatic Readme.md file generator
This project's aim is to automatize documentation process. Basically I tried to generate README.md file from source code of the project. 
<br>
__In readme.generator package RG* classes exists you can easily support new programming languages by extending those classes.__
<br>

|Fields|Type|Description| Hello|
|---|---|---| ---|
|uniqueId|String|Unique id|test|
**** 
#### Variable TS Issue List
* If variable is equal to a string of characters it reads without spaces.
* When reading a variable which its type is a function like `() => void` it can't figure out the variable type.

_To fix both of them I should'nt use split function to split properties. To fix it I should read it char by char instead of splitting the whole string._

* Sometimes for class values adding extra spaces.