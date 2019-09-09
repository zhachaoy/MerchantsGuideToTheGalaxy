# Merchant's Guide to the Galaxy

## 一.自顶向下分解
核心概念：

货币（Sliver，Gold，Iron等）

代币（glob，prok等）

字符（I，V，X等）

规则（"D", "L", and "V" can never be repeated等）

1. 读取输入文件
2. 初始化字符romannumeric的价值，比如I=1
2. 初始化代币token与字符关系，比如glob：I
3. 初始化设置代币token与货币关系，比如glob glob Silver is 34 Credits
4. 初始化输入问题，比如how much is pish glob pish tegj pish pish 
5. 计算代币token的价值
6. 计算货币的价值
7. 计算问题的价值
8. 输出结果

## 二.逐步求精思路
### 1.处理输入(Application主入口)：
1. 读取输入文件
2. 初始化核心概念价值
3. 计算缺失的概念价值

### 2.词法解析(Lexer)：
1. 对读取的输入进行分词解析
2. 字符串编码

### 3语法解析(Parser, Ruler)
1. 处理合法问题
2. 判断所有规则

### 4处理输出(Processor)
1. 判断是否为可处理问题
2. 计算结果
3. 拼接响应字符串

## 三.项目说明
1. 使用maven构建项目
2. 使用junit4.12用于单元测试
3. jdk1.8.212

## 四.问题附件
```
Merchant's Guide to the Galaxy

You decided to give up on earth after the latest financial collapse left 99.99% of the earth's population with 0.01% of the wealth. Luckily, with the scant sum of money that is left in your account, you are able to afford to rent a spaceship, leave earth, and fly all over the galaxy to sell common metals and dirt (which apparently is worth a lot).
 
Buying and selling over the galaxy requires you to convert numbers and units, and you decided to write a program to help you.
 
The numbers used for intergalactic transactions follows similar convention to the roman numerals and you have painstakingly collected the appropriate translation between them.
 
Roman numerals are based on seven symbols:
 
Symbol  Value
I：1
V：5
X:10
L:50
C:100
D:500
M:1,000
 
Numbers are formed by combining symbols together and adding the values. For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in order of value, starting with the largest values. When smaller values precede larger values, the smaller values are subtracted from the larger values, and the result is added to the total. For example MCMXLIV = 1000 + (1000 − 100) + (50 − 10) + (5 − 1) = 1944.
 
The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.) "D", "L", and "V" can never be repeated.
"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
Only one small-value symbol may be subtracted from any large-value symbol.
A number written in [16]Arabic numerals can be broken into digits. For example, 1903 is composed of 1, 9, 0, and 3. To write the Roman numeral, each of the non-zero digits should be treated separately. Inthe above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.
(Source: Wikipedia ( [17]http://en.wikipedia.org/wiki/Roman_numerals)
 
Input to your program consists of lines of text detailing your notes on the conversion between intergalactic units and roman numerals.
 
You are expected to handle invalid queries appropriately.
 
Test input:
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
 
Test Output:
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about
```
