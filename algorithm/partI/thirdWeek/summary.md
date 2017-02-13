#summary
Never return a reference of a array which is in you own class. or it will expos the internal representation of the class, which mean users can change the data in you class through the reference and it will lead to a lot of problems.
You should return the copy of the data array in you class to usrs rather than the reference of the data array directly.
