# Calculator
A java based calculator with 5 operators addition,subtraction,multiplication,division, and let.

Let has following syntax let(variable, value, expression)


Installation:
Checkout git respository.
In calc folder
mvn clean package
cd target
java -jar calc-1.0.0-jar-with-dependencies.jar input


The results are expected to be in integer range. You will be notified if it exceeds limit.


Following are examples of valid input and corresponding results
add(1, 2)	                                                                          3
add(1, mult(2, 3))	                                                                7
mult(add(2, 2), div(9, 3))	                                                        12
let(a, 5, add(a, a))	                                                              10
let(a, 5, let(b, mult(a, 10), add(b, a)))	                                          55
let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))	                                40
