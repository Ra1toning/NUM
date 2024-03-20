#! /usr/bin/python3
import itertools

# ********************************************************
# Name: Bulgan-Erdene Altankhuyag
# Email address: bulganerdene021@gmail.com
# ********************************************************

# This file may be opened in python 3
# Lines beginning with hashes are comments.

# If you are asked to write a procedure, please make sure it has 
# the specified name, and the specified number and order of arguments.  
# The names of the formal arguments need not be the same
# as in the problem specification.

# For each problem, the intended inputs to your procedures
# are specified (for example, "positive integers") 
# and your procedures need not do anything reasonable 
# for other possible inputs.

# You may write auxiliary procedures in addition to
# the requested one(s) -- for each of your auxiliary 
# procedures, please include a comment explaining
# what it does, and giving an example or two.

# You may also use procedures you have written 
# elsewhere in this assignment or previous assignments.
# They only need to be defined once somewhere within this file.

'''
This is meant to get you up to speed with python.

The reading, as needed, includes:

Learning Python
Python Cookbook
Effective Python
and the online Google Python Class

'''

# ********************************************************
# ** problem 0 ** (1 easy point) 
# Replace the number 0 in the definition below to indicate
# the number of hours you spent doing this assignment
# Decimal numbers (eg, 6.237) are fine.  Exclude time
# spent reading.

def hours():
    return 2

# ********************************************************
# ********************************************************
# ** problem 1 ** (9 points)
# Write a procedure

# sum_digits(n)

# that takes a positive integer and returns the sum of the digits

# Examples
# sum_digits(13) => 4
# sum_digits(1000000) => 1
# sum_digits(123456789) => 45
# sum_digits(9) => 9

# ********************************************************
# Replace the pass statement with your definition
def sum_digits(n):
    sum = 0
    while n > 0:
        sum += n % 10
        n = n // 10
    return sum


# ********************************************************
# ** problem 2 ** (10 points)
# Write a recursive procedure

# reduce(n)

# that takes a positive integer as input, and repeatedly
# sums the digits until the result is less than 10.
# Note: you probably want to call sum_digits from inside reduce

# Examples

# reduce(123455667888) => 9
# reduce(9999) => 9
# reduce(8888) => 5
# reduce(10101010019999) => 5

# ********************************************************
def reduce(n):
    if n < 10:
        return n
    else:
        return reduce(sum_digits(n))

# (Replace the pass statement with your procedure(s).)

# ********************************************************
# ** problem 3 ** (10 points)
# Write a procedure

# removep(lst,pred)

# that takes a list lst and returns that list minus 
# any elements that satisfy the given predicate pred.

# Examples:

# removep([1,2,3,4,5,6], lambda x: x % 2 == 0) =>[1,3,5]
# removep([1,2,3,4,5,6], lambda x: x % 2) => [2,4,6]
# removep([1,2,3,4,5,6], lambda a: a > 3) => [1,2,3]
# removep([1,2,3,4,5,6], lambda a: a < 3) => [3,4,5,6]

# ********************************************************
def removep(lst, pred):
    temp = list(lst)
    for i in lst:
        if pred(i):
            temp.remove(i)
    return temp
# Replace the pass statement with your procedure(s).)

## write the same function using a list comprehansion
def lcremovep(lst, pred):
    return [i for i in lst if not pred(i)]

# ********************************************************
# ** problem 4 ** (10 points)
# Write a procedure 

# collectp(lst, pred)

# that takes a list lst and returns that list containing only
# the elements that satisfy the given predicate pred.

# Examples:

# collectp([1,2,3,4,5,6]), lambda x: x%2 == 0) => [2,4,6]
# collectp([1,2,3,4,5,6]), lambda x: x % 2) => [1,3,5]
# collectp([1,2,3,4,5,6]), lambda a: a > 3) => [4,5,6]
# collectp([1,2,3,4,5,6]), lambda a: a < 3) => [1,2]


# ********************************************************
 
def collectp(lst, pred):
    temp = []
    for i in lst:
        if pred(i):
            temp.append(i)
    return temp

# Replace the pass statement with your procedure(s).)

## write the same function using a list comprehansion
def lccollectp(lst, pred):
    return [i for i in lst if pred(i)]

# ********************************************************
# ** problem 5 (10 points)
# Write 

# power_set(lst)

# which treats the lst as a set and returns a list of all possible
# subsets

'''
Examples:

power_set([]) => [[]]
power_set([1]) => [[], [1]]
power_set([1,2]) => [[], [2], [1], [1,2]]
power_set([1,2,3]) => [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
power_set([1,2,3,4]) => 
[[], [1], [1, 2], [1, 2, 3], [1, 2, 3, 4], [1, 2, 4], [1, 3], [1, 3, 4], [1, 4], [2], [2, 3], [2, 3, 4], [2, 4], [3], [3, 4], [4]]
power_set([2,2]) => [[], [2], [2], [2, 2]]

toppings = ['onion','peppers','bacon','sausage','mushroom']
power_set(toppings) => 

[[], ['bacon'], ['bacon', 'mushroom'], ['bacon', 'sausage'], ['bacon',
'sausage', 'mushroom'], ['mushroom'], ['onion'], ['onion', 'bacon'],
['onion', 'bacon', 'mushroom'], ['onion', 'bacon', 'sausage'],
['onion', 'bacon', 'sausage', 'mushroom'], ['onion', 'mushroom'],
['onion', 'peppers'], ['onion', 'peppers', 'bacon'], ['onion',
'peppers', 'bacon', 'mushroom'], ['onion', 'peppers', 'bacon',
'sausage'], ['onion', 'peppers', 'bacon', 'sausage', 'mushroom'],
['onion', 'peppers', 'mushroom'], ['onion', 'peppers', 'sausage'],
['onion', 'peppers', 'sausage', 'mushroom'], ['onion', 'sausage'],
['onion', 'sausage', 'mushroom'], ['peppers'], ['peppers', 'bacon'],
['peppers', 'bacon', 'mushroom'], ['peppers', 'bacon', 'sausage'],
['peppers', 'bacon', 'sausage', 'mushroom'], ['peppers', 'mushroom'],
['peppers', 'sausage'], ['peppers', 'sausage', 'mushroom'],
['sausage'], ['sausage', 'mushroom']]
'''

# ********************************************************
## it is OK to use itertools module.
## 

def power_set(lst):
    temp = []
    for i in range(0, len(lst) + 1):
        pos = itertools.combinations(lst, i)
        temp.extend([list(j) for j in pos])
    return temp

# ********************************************************
# ** problem 6 ** (10 points)
# Write a procedure

# sumtree(tree)

# that takes a nested list tree and returns the sum of integers in the leaves
# you may NOT assume that all the leaves are integers
# if a leaf is not an integer, ignore it.

# Examples
# sumtree([1, 2, 3]) => 6
# sumtree([1, [2, [3]]]) => 6
# sumtree([[[]]]) => 0
# sumtree([[[[2]]]]) => 2

# sumtree([1,2,3,4]) => 10
# sumtree([1,"2","3",4]) => 5
# sumtree([1,"2","3",4, False]) => 5
# sumtree([1,"2","3",4, False, []]) => 5

# ********************************************************
def sumtree(tree):
    su = 0
    for i in tree:
        if type(i) is int:
            su += i
        if type(i) is list:
            su += sumtree(i)
    return su
# Replace the pass statement with your procedure(s).

# ********************************************************
# ** problem 7 ** (10 points)
# Write a procedure

# doubletree(tree)

# that takes a nested list tree and returns another tree with the same 
# structure, but with each element doubled
# you may NOT assume that all the leaves are integers
# if a leaf is not an integer, include it unmodified

# Examples

# doubletree([1, 2, 3]) => [2,4,6]
# doubletree([1, [2, [3]]]) => [2, [4, [6]]]
# doubletree([]) => []
# doubletree([[[[8, 8, 8]]]]) => [[[[16, 16, 16]]]]

# doubletree([1, 2, 3, "4", "5", ["6"]]) => [2, 4, 6, '4', '5', ['6']]
# doubletree([1, 2, 3, "4", "5", ["6"], True]) => [2, 4, 6, '4', '5', ['6'], True]

# ********************************************************

def doubletree(tree):
    for j, i in enumerate(tree):
        if type(i) is int:
            tree[j] *= 2
        if type(i) is list:
            doubletree(i)
    return tree
# Replace the pass statement with your procedure(s).


# ********************************************************
# ** problem 8 ** (10 points)
# Write a procedure

# types(tree)

# that takes a nested list tree and returns a list of all
# the different data types of the leaf nodes, in alphabetical order,
# without duplication.

# Examples:

# types([1, [2.3, "a"], True, 3, 4, 5]) => ['bool', 'float', 'int', 'str']
# types([1,2,3,4]) => ['int']
# types([1,2,3,4, True, "hello"]) => ['bool', 'int', 'str']
# types([1,2,3,4, True, "hello", 1,2]) => ['bool', 'int', 'str']
# types([1,2,3,4, True, "hello", 1.2]) => ['bool', 'float', 'int', 'str']
# types([1,2,3,4, True, "hello", 1.2, {}]) => ['bool', 'dict', 'float', 'int', 'str']
# types([1,2,3,4, True, "hello", 1.2, {}, (1,2,3)]) => ['bool', 'dict', 'float', 'int', 'str', 'tuple']


# ********************************************************
def types(tree):
    container = set()
    for i in tree:
        if type(i) is list:
            temp = set(types(i))
            container = container.union(temp)
        else:
            container.add(type(i).__name__)
    return sorted(list(container))

# Replace the pass statement with your procedure(s).

## a common use of classes is to implement data structures
## Below is an example of a stack,
## which is a LIFO - last in first out - structure
## it is a collection.
## items are added to the stack with push and removed with pop
## -----------------------------------------------------------
class stack:

    def __init__(self,stuff=[]):
        self.items = stuff[:]
        self.size = len(stuff)

    def __repr__(self):
        return "stack({})".format(list(self.items))

    def isempty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)
        self.size += 1

    def peek(self):
        if self.isempty():
            return "Error: stack is empty"
        else:
            return self.items[-1]

    def pop(self):
        if self.isempty():
            return "Error: stack is empty"
        else:
            self.size -= 1
            return self.items.pop()

    ## swap the top two items in the stack
    def rotate(self):
        if self.size < 2:
            return "Error: stack has fewer than 2 elements"
        else:
            self.items[-1], self.items[-2] = self.items[-2], self.items[-1]

    ## define the iterator for stack.  Used in for or list comprehension
    def __iter__(self):
        """Return iterator for the stack."""
        if self.isempty():
            return None
        else:
            index = self.size -1
            while index >= 0:
                yield self.items[index]
                index -= 1

    def __eq__(self, other):
        if type(other) != type(self):
            return False
        if self.items == other.items:
            return True
        else:
            return False

    # copy constructor - clone the current instance
    def copy(self):
        s = stack(self.items)
        return s

## test the iterator
def itest(s):
    for i in s:
        print (i)
    return [x for x in s]

## revstr uses a stack to reverse a string
## ----------------------------------------
def revstr(str):
    s = stack()
    for c in str:
        s.push(c)
    result = []
    while (not s.isempty()):
        result.append(s.pop())
    return ''.join(result)

# ********************************************************
# ** problem 9 ** (10 points)
# 

# Write a procedure balanced(string) that reads string, and determines
# whether its parentheses are "balanced."  Hint: for left delimiters,
# push onto stack; for right delimiters, pop from stack and check
# whether popped element matches right delimiter.

def balanced(string):
    st = stack()
    for i in string:
        if i ==")":
            if(st.pop() == "Error: stack is empty"):
                return False
        elif i == "(":
            st.push(i)
    return st.peek() == "Error: stack is empty"

# ********************************************************
# ** problem 10 ** (10 points)
# 

## Write a queue data structure, similar to the stack above.
## Whereas a stack is LIFO (last in first out), a queue is 
## FIFO = first in, first out

## See Skiena, page 71

class queue:

    def __init__(self, stuff=[]):
        self.data = list(stuff)

    def __str__(self):
        return f"{self.data}"

    def __repr__(self):
        return f"Queue({self.data})"

    def isempty(self):
        return len(self.data) == 0

    def enqueue(self, item):
        self.data.append(item)

    def dequeue(self):
        if self.isempty():
            return ("queue is empty")
        return self.data.pop(0)

    # return error message if queue is empty
    def peek(self):
        if self.isempty():
            return "Queue is empty"
        return self.data[0]

    ## define the iterator for queue.  Used in for or list comprehension
    def __iter__(self):
        return iter(self.data)

    def __eq__(self, other):
        if isinstance(other, queue):
            return self.data == other.data
        return False


    # copy constructor - clone the current instance
    def copy(self):
        return queue(self.data)

# ********************************************************
# ********************************************************
# ********************************************************


### test function from google python course
### =======================================
# Provided simple test() function used in main() to print
# what each function returns vs. what it's supposed to return.
def test(got, expected):
    if (hasattr(expected, '__call__')):
        OK = expected(got)
    else:
        OK = (got == expected)
    if OK:
        prefix = ' OK '
    else:
        prefix = '  X '
    print ('%s got: %s expected: %s' % (prefix, repr(got), repr(expected)))


# Provided main() calls the above functions with interesting inputs,
# using test() to check if each result is correct or not.
def main():
  print ('hours')
  print('# is it greater than 0?')
  test(hours(), lambda x: x > 0)

  print ('sum_digits')
  test(sum_digits(10), 1)
  test(sum_digits(13), 4)
  test(sum_digits(1000000),1)
  test(sum_digits(123456789), 45)
  test(sum_digits(9),9)

  print
  print ('reduce')
  test(reduce(123455667888),9)
  test(reduce(9999),9)
  test(reduce(8888),5)
  test(reduce(10101010019999),5)

  print
  print ('removep')
  test(removep(range(7), lambda x: x % 2 == 0),[1,3,5])
  test(removep(range(7), lambda x: x % 2),[0,2,4,6])
  test(removep(range(7), lambda x: x > 3),[0,1,2,3])
  test(removep(range(7), lambda x: x < 3),[3,4,5,6])

  print
  print ('lcremovep')
  test(lcremovep(range(7), lambda x: x % 2 == 0),[1,3,5])
  test(lcremovep(range(7), lambda x: x % 2),[0,2,4,6])
  test(lcremovep(range(7), lambda x: x > 3),[0,1,2,3])
  test(lcremovep(range(7), lambda x: x < 3),[3,4,5,6])

  print
  print ('collectp')
  test(collectp(range(7), lambda x: x % 2 == 0),[0,2,4,6])
  test(collectp(range(7), lambda x: x % 2),[1,3,5])
  test(collectp(range(7), lambda x: x > 3),[4,5,6])
  test(collectp(range(7), lambda x: x < 3),[0,1,2])

  print
  print ('lccollectp')
  test(lccollectp(range(7), lambda x: x % 2 == 0),[0,2,4,6])
  test(lccollectp(range(7), lambda x: x % 2),[1,3,5])
  test(lccollectp(range(7), lambda x: x > 3),[4,5,6])
  test(lccollectp(range(7), lambda x: x < 3),[0,1,2])

  print
  print ('power_set')
  test(power_set([]), [[]])
  test(power_set([1]), [[], [1]])
  test(sorted(power_set([1,2])), 
       sorted([[], [2], [1], [1,2]]))
  test(sorted(power_set([1,2,3])), 
       sorted([[],[3], [2], [2,3], [1], [1,3], [1,2], [1,2,3]]))
  test(power_set([1,2,3,4]), 
       [[], [1], [2], [3], [4], [1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4], [1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4], [1, 2, 3, 4]])
  test(power_set([2,2]), [[], [2], [2], [2, 2]])

  toppings = ['onion','peppers','bacon','sausage','mushroom']

  test(power_set(toppings),
       [[], ['onion'], ['peppers'], ['bacon'], ['sausage'], ['mushroom'], ['onion', 'peppers'], ['onion', 'bacon'], ['onion', 'sausage'], ['onion', 'mushroom'], ['peppers', 'bacon'], ['peppers', 'sausage'], ['peppers', 'mushroom'], ['bacon', 'sausage'], ['bacon', 'mushroom'], ['sausage', 'mushroom'], ['onion', 'peppers', 'bacon'], ['onion', 'peppers', 'sausage'], ['onion', 'peppers', 'mushroom'], ['onion', 'bacon', 'sausage'], ['onion', 'bacon', 'mushroom'], ['onion', 'sausage', 'mushroom'], ['peppers', 'bacon', 'sausage'], ['peppers', 'bacon', 'mushroom'], ['peppers', 'sausage', 'mushroom'], ['bacon', 'sausage', 'mushroom'], ['onion', 'peppers', 'bacon', 'sausage'], ['onion', 'peppers', 'bacon', 'mushroom'], ['onion', 'peppers', 'sausage', 'mushroom'], ['onion', 'bacon', 'sausage', 'mushroom'], ['peppers', 'bacon', 'sausage', 'mushroom'], ['onion', 'peppers', 'bacon', 'sausage', 'mushroom']])

  print ('sumtree')
  test(sumtree([1, 2, 3]), 6)
  test(sumtree([1, [2, [3]]]), 6)
  test(sumtree([[[]]]), 0)
  test(sumtree([[[[2]]]]), 2)

  test(sumtree([1,2,3,4]), 10)
  test(sumtree([1,"2","3",4]), 5)
  test(sumtree([1,"2","3",4, False]), 5)
  test(sumtree([1,"2","3",4, False, []]), 5)

  print ('doubletree')
  test(doubletree([1, 2, 3]), [2,4,6])
  test(doubletree([1, [2, [3]]]), [2, [4, [6]]])
  test(doubletree([]), [])
  test(doubletree([[[[8, 8, 8]]]]), [[[[16, 16, 16]]]])
  test(doubletree([1, 2, 3, "4", "5", ["6"]]), [2, 4, 6, '4', '5', ['6']])
  test(doubletree([1, 2, 3, "4", "5", ["6"], True]), [2, 4, 6, '4', '5', ['6'], True])

  print ('types')

  test(types([1, [2.3, "a"], True, 3, 4, 5]), ['bool', 'float', 'int', 'str'])
  test(types([1,2,3,4]), ['int'])
  test(types([1,2,3,4, True, "hello"]), ['bool', 'int', 'str'])
  test(types([1,2,3,4, True, "hello", 1,2]), ['bool', 'int', 'str'])
  test(types([1,2,3,4, True, "hello", 1.2]), ['bool', 'float', 'int', 'str'])
  test(types([1,2,3,4, True, "hello", 1.2, {}]), ['bool', 'dict', 'float', 'int', 'str'])
  test(types([1,2,3,4, True, "hello", 1.2, {}, (1,2,3)]), ['bool', 'dict', 'float', 'int', 'str', 'tuple'])
##################

  print ('stack')
  s = stack()
  s.push(1)
  s.push(2)
  s.push(3)
  s.push(4)
  test(s,stack([1, 2, 3, 4]))
  test(s == s.copy(), True)
  test([x for x in s], [4,3,2,1])
  test(s.peek(),4)
  test(3 in s, True)
  test(5 in s, False)
  test(s.pop(),4)
  test(s.pop(),3)
  test(s.peek(),2)
  test(revstr('abcdef'), 'fedcba')
  test(revstr(''), '')

  print ('balanced')

  test(balanced('dkdk'),True)
  test(balanced('()()()()'),True)
  test(balanced('()()()())'),False)
  test(balanced('()()()()(('),False)
  test(balanced('(a)s(d)f(g)gh(h)j(k'),False)

  print ('queue')
  d = queue()
  d.enqueue(9)
  d.enqueue(1)
  d.enqueue(2)
  test(d == d.copy(), True)
  test(d.peek(),9)
  test(d.data,[9, 1, 2])
  test([x for x in d], [9,1,2])
  test(2 in d, True)
  test(5 in d, False)
  test(d.dequeue(),9)
  test(d.dequeue(),1)
  test(d.dequeue(),2)
  test(2 in d, False)
  test(d.dequeue(),'queue is empty')
if __name__ == '__main__':
     main()
