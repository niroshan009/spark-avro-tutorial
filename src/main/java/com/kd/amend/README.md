## What is this about? ##

StudentMain class has the logic to amend values to the input AVRO based file.

StudentMain will read avro file called `student.avro`. Which has similar representation of `sample-student.json` you may refer to this file since it is more human readable.

### Scenario ###

`student.avro` file will contain data of the student and his grades based on the school. \
Based on the school and subject we will grade the students grade and give a new value called rating. \
We will keep below table to give ratings based on the schools credibility and subject.\


| school_rank  | subject_rank | rating          |
|--------------|--------------|-----------------|
| A            | 1            | Excellent       |
| A            | 2            | Above Average   |
| A            | 3            | Poor            |
| B            | 1            | Good            |
| B            | 2            | Average         |
| B            | 3            | Poor            |


### Solution ###
Scenario will be broken down to 3 stages.
Stage 1: explode the avro dataset, so you can amend the values
Stage 2: Join with reference table(marks )
Stage 3: amend the value by joining the reference table
 * Dynamically construct the struct with amended values based on configuration dataset
Stage 4: reconstruct the exploded in stage 3 with amended value in stage 2
 * Dynamically reconstruct the original structure with amended structure in the stage 3


### Input and Output ###
Input Dataset 

| id | name   | education                                                                                                                                                                |
| -- |--------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|1234| Kamal  | [{School 1, A, [{Maths, 1, A}, {Science, 1, B}, {English, 2, F}, {Religion, 3, C}]}, {School 2, B, [{Maths, 1, B}, {Science, 1, B}, {English, 2, C}, {Religion, 3, C}]}] |

Output Dataset

| id    | name  | education                                                                                                                                                                                                                                      |
|-------|-------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1234  | Kamal | [{School 1, A, [{Maths, 1, A, Excellent}, {Science, 1, B, Excellent}, {English, 2, F, Above Average}, {Religion, 3, C, Poor}]}, {School 2, B, [{Maths, 1, B, Good}, {Science, 1, B, Good}, {English, 2, C, Average}, {Religion, 3, C, Poor}]}] |

![](/Users/kd/git/spark/spark-avro/subject-comparison.png)