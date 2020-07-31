"use strict";
exports.__esModule = true;
var fs = require("fs");
fs.readFile("types.ts", function (err, data) {
    if (err)
        throw err;
    var fileData = data.toString();
    // First of all comment check
    // If you see any start /* like this you have to find the matching part */
    var keywords = ['class', 'protected', 'private', 'public', 'default', 'interface'];
    var specialCharacters = [':', ';', '{', '}', '[', ']', '<', '>', '(', ')'];
    var words = [];
    var tmp = "";
    for (var i = 0; i < fileData.length; ++i) {
        // if(fileData[i] in specialCharacters) {
        //   words.push(tmp);
        //   words.push(fileData[i]);
        //   tmp = "";
        //   continue;
        // }
        // tmp+= fileData[i];
        console.log(fileData[i]);
    }
});
