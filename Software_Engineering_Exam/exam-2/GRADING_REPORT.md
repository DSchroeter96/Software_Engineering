# SwEng Exam 2: 4.0909 [46 out of 100 points]

- ℹ The absolute grade out of 6 is graded based on the points out of 100, as explained in lectures.

## Question 1 [9 out of 9 points]

- ✔ **3 out of 3 points**: Suggestion based on an explanation that coverage is not enough on its ow
- ✔ **3 out of 3 points**: Suggestion based on an explanation that lines of code and code complexity are not the same
- ✔ **3 out of 3 points**: Suggestion based on an explanation that incremental code improvement is a good idea in general

## Question 2 [4 out of 14 points]

- ✔ **4 out of 4 points**: Explanation that the change breaks backward compatibility
- ❌ **0 out of 5 points**: No correct explanation of the maintainability problem
- ❌ **0 out of 5 points**: No correct suggestion on how to avoid the abstraction leak

## Question 3 [10 out of 15 points]

- ✔ **5 out of 5 points**: Explanation that speculatively generating images would improve performance
- ✔ **5 out of 5 points**: Explanation that batching images into a single request would improve performance
- ❌ **0 out of 5 points**: Suggestion based on the absolute time improvements, even though the moderation process is stated to take a day overall and is presumably less frequent

## Question 4 [21 out of 25 points]

- ⚠️ **4 out of 6 points**: Tests for most, but not all, success cases
- ⚠️ **1 out of 2 points**: Tests for error cases, but only with the empty or null list
- ✔ **3 out of 3 points**: Clean and maintainable test code
- ✔ **3 out of 3 points**: Clean and maintainable implementation

### Code functionality (graded automatically) [10 out of 11 points]

- ❌ **0 out of 1 point**: Test failed: The constructor should throw an `IllegalArgumentException` when given an empty list: Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
- ✔ **1 out of 1 point**: The constructor should throw an `IllegalArgumentException` when given a `null` list
- ✔ **1 out of 1 point**: `getLeft` should return the left sub-tree
- ✔ **1 out of 1 point**: `getLeft` should return `null` for a single-element list
- ✔ **1 out of 1 point**: `getLeft` should return `null` when the underlying list is empty
- ✔ **2 out of 2 points**: `getRight` should return the right sub-tree
- ✔ **1 out of 1 point**: `getRight` should return `null` for a single-element list
- ✔ **1 out of 1 point**: `getRight` should return `null` when the underlying list is empty
- ✔ **1 out of 1 point**: `getRoot` should return the lone element of a single-element list
- ✔ **1 out of 1 point**: `getRoot` should return the first element of a list with many elements
- ℹ Commit used for automated tests: `d217244` dated 2022-11-18 11:55:57 +0100 (the latest one in which your code compiles and follows our grading compatibility instructions)

## Question 5 [0 out of 35 points]

- ❌ **0 out of 35 points**: No answer complete enough to grade

## Survey [2 out of 2 points]

- ✔ **2 out of 2 points**: Survey completed, thank you very much

