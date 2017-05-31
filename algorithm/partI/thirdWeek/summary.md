##summary
Never return a reference of a data array which is the attribute in you own class. Or it will expose the internal representation of the class, which mean users can change the data or attributes in your class through the reference, and it will lead to a lot of problems.
You should return the copy of the data array in you class to usrs rather than the reference of the data array directly.
