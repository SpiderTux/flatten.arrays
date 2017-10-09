# flatten.arrays
Java tool for flatting complex integer arrays.

Flatten arrays is a simple tool for flatting complex integer arrays.

Complex arrays are arrays that can store integers or well another integer arrays in an arbitrary way.

The API is easy:

To flat an array, you need to call the static method FlattenArray.flatArray(Object[]) passing as argument an Object array that represents the complex integer array.

There is available another static method to populate random complex integer arrays. You can generate a random array with the static method FlattenArray.populateObject().

In order to generate a complex integer array to test how this array is flatten, you would configure the parameters used to generate it:

maxLenghArray: Max lengh of each integer array generated.

maxIntValue:   Max integer value set when populating arrays.

maxDeepLevel:  Max deep level where new arrays will be generated.


You have public static methods  to set these properties.
