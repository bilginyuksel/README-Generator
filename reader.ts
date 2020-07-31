import * as fs from 'fs';
/*
This types can be different for other languages.
In here I will develop these types for TypeScript files.
Basically TypeScript types are as below.

------------------------
|      DATA TYPES      |
------------------------
------------------------
1. interface
2. class
  2.1 Access Specifiers for elements inside class
    2.1.1. private
    2.1.2. protected
    2.1.3. public
    2.1.4. default - None
  2.2 Keywords for class
    2.2.1 abstract
    2.2.2 extends - Other class
    2.2.3 implements - Other class or interface. Dunno for TypeScript.
3. enum
4. function
5. variables
------------------------|
------------------------|

**** Note: Important fact !!!.
** Export keyword is important for data types. Whenever a data type has an export keyword.
** README generator will generate files which isExport = true...



*/
fs.readFile("types.ts", (err, data) =>{
  if(err) throw err;

  const fileData = data.toString();
  // First of all comment check
  // If you see any start /* like this you have to find the matching part */
  const keywords: string[] = ['class', 'protected', 'private', 'public', 'default', 'interface'];
  const specialCharacters: string[] = [':', ';', '{', '}', '[', ']', '<','>', '(', ')'];

  let words: string[] = [];
  let tmp: string = "";
  for(let i=0; i<fileData.length; ++i){
    // if(fileData[i] in specialCharacters) {
    //   words.push(tmp);
    //   words.push(fileData[i]);
    //   tmp = "";
    //   continue;
    // }
    // tmp+= fileData[i];
    console.log(fileData[i]);
  }

  
})