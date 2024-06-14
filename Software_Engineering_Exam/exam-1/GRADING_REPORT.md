# SwEng Exam 1: 5.05 [65 out of 100 points]

- ℹ The grade above is curved, based on your exam score, as explained in lectures.

## Question 1 [2 out of 8 points]


### Role [2 out of 2 points]

- ✔ **1 out of 1 point**: Role with _all_ necessary details
- ✔ **1 out of 1 point**: Role with _only_ necessary details

### Goal [0 out of 3 points]

- ❌ **0 out of 1 point**: No user-focused goal with _all_ necessary details
- ❌ **0 out of 2 points**: No user-focused goal with _only_ necessary details

### Reason [0 out of 3 points]

- ❌ **0 out of 2 points**: No user-focused reason with _all_ necessary details
- ❌ **0 out of 1 point**: No user-focused reason with _only_ necessary details

## Question 2 [11 out of 15 points]


### Branching [2 out of 4 points]

- ✔ **1 out of 1 point**: Mention of a flaw in the workflow
- ✔ **1 out of 1 point**: Proposal of a useful alternative
- ❌ **0 out of 2 points**: No complete explanation of the reasoning

### Testing [7 out of 7 points]

- ✔ **1 out of 1 point**: Mention of a flaw in the workflow
- ✔ **2 out of 2 points**: Complete explanation of the reasoning
- ✔ **2 out of 2 points**: Proposal of a useful alternative
- ✔ **2 out of 2 points**: Complete explanation of the alternative

### Merging [2 out of 4 points]

- ✔ **1 out of 1 point**: Mention of a flaw in the workflow
- ✔ **1 out of 1 point**: Proposal of a useful alternative
- ❌ **0 out of 2 points**: No complete explanation of the reasoning

## Question 3 [11 out of 15 points]


### Prints [0 out of 3 points]

- ❌ **0 out of 1 point**: No mention that print statements pollute the code
- ❌ **0 out of 1 point**: No proposal of a debugger instead of prints
- ❌ **0 out of 1 point**: No explanation of why the debugger is a better tool

### Names [4 out of 4 points]

- ✔ **2 out of 2 points**: Mention that the names are too long
- ✔ **2 out of 2 points**: Explanation that the names make the code hard to read and maintain

### Comments [4 out of 4 points]

- ✔ **1 out of 1 point**: Mention that the comments are not useful
- ✔ **3 out of 3 points**: Explanation that comments should say _why_, not _how_

### Commented-out code [3 out of 4 points]

- ❌ **0 out of 4 points**: No full explanation based on version control
- ✔ **1 point**: Partial explanation mentioning version control
- ✔ **2 points**: Partial explanation based on branches/versions

## Question 4 [22 out of 25 points]

- ✔ **1 out of 1 point**: Reasonable length for names
- ✔ **2 out of 2 points**: Explanation of what is tested in the test names
- ❌ **0 out of 3 points**: Unclear description of what is expected in the test names
- ✔ **2 out of 2 points**: Easy-to-read test contents
- ✔ **2 out of 2 points**: Separation of logical assertions into one per test

### Coverage [15 out of 15 points]

- ✔ **7 out of 7 points**: Statement coverage (passing tests only): 100%
- ✔ **8 out of 8 points**: Branch coverage (passing tests only): 100%
- ℹ Commit used for the coverage: 980a308f6ffc39269946dbc683deb20b2fa2fec4 dated 2022-10-21 11:56:54 +0200 (the latest one in which your code compiles and follows our grading compatibility instructions)

## Question 5 [17 out of 35 points]


### App behavior [3 out of 3 points]

- ✔ **1 out of 1 point**: Use of `System.in` for user roles
- ✔ **1 out of 1 point**: Use of the correct HTTP request
- ✔ **1 out of 1 point**: Printing of names to `System.out`

### Extracting `System.in` [2 out of 5 points]

- ✔ **2 out of 2 points**: Injection of the list of roles
- ❌ **0 out of 3 points**: Non-minimal edits: `hasNext()` and `nextLine()` calls should remain in `run`

### Extracting `System.out` [0 out of 2 points]

- ❌ **0 out of 2 points**: No injection of the list of names as an output dependency or a return value

### Extracting `URL#openStream` [3 out of 3 points]

- ✔ **2 out of 2 points**: Injection of the HTTP streaming logic
- ✔ **1 out of 1 point**: The URL logic of `JaasClient` is preserved

### Code quality [3 out of 9 points]

- ❌ **0 out of 2 points**: Unclear names in non-test code
- ❌ **0 out of 2 points**: Unclear test names
- ✔ **2 out of 2 points**: Separation of logical assertions into one per test
- ❌ **0 out of 3 points**: No complete checks for the three behaviors - null `path` in the constructor, a successful request, and a failed request
- ✔ **1 point**: Partial checks, only one behavior, or a significant portion of the tests make no assertions.

### Coverage [6 out of 13 points]

- ✔ **6 out of 13 points**: Branch coverage (passing tests only): 62.5%
- ℹ Commit used for the coverage: 980a308f6ffc39269946dbc683deb20b2fa2fec4 dated 2022-10-21 11:56:54 +0200 (the latest one in which your code compiles and follows our grading compatibility instructions)

## Survey [2 out of 2 points]

- ✔ **2 out of 2 points**: Survey completed, thank you very much

