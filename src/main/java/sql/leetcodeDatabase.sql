--176. Second Highest Salary
--Write a SQL query to get the second highest salary from the Employee table.
--
--+----+--------+
--| Id | Salary |
--+----+--------+
--| 1  | 100    |
--| 2  | 200    |
--| 3  | 300    |
--+----+--------+
--For example, given the above Employee table, the query should return 200 as the second highest salary.
--If there is no second highest salary, then the query should return null.
--
--+---------------------+
--| SecondHighestSalary |
--+---------------------+
--| 200                 |
--+---------------------+

SELECT max(Salary) as SecondHighestSalary
FROM Employee
WHERE Salary < (SELECT max(Salary) FROM Employee);


--175. Combine Two Tables
--Add to List
--
--Share
--SQL Schema
--Table: Person
--
--+-------------+---------+
--| Column Name | Type    |
--+-------------+---------+
--| PersonId    | int     |
--| FirstName   | varchar |
--| LastName    | varchar |
--+-------------+---------+
--PersonId is the primary key column for this table.
--Table: Address
--
--+-------------+---------+
--| Column Name | Type    |
--+-------------+---------+
--| AddressId   | int     |
--| PersonId    | int     |
--| City        | varchar |
--| State       | varchar |
--+-------------+---------+
--AddressId is the primary key column for this table.

SELECT Person.FirstName, Person.LastName, Address.City, Address.State from Person LEFT JOIN Address on Person.PersonId = Address.PersonId;

--182. Duplicate Emails
--Add to List
--
--Share
--SQL Schema
--Write a SQL query to find all duplicate emails in a table named Person.
--
--+----+---------+
--| Id | Email   |
--+----+---------+
--| 1  | a@b.com |
--| 2  | c@d.com |
--| 3  | a@b.com |
--+----+---------+
--For example, your query should return the following for the above table:
--
--+---------+
--| Email   |
--+---------+
--| a@b.com |
--+---------+

select Email from person group by Email having count(Email) > 1;

--197. Rising Temperature
--
--SQL Schema
--Given a Weather table, write a SQL query to find all dates' Ids with higher temperature compared to its previous (yesterday's) dates.
--
--+---------+------------------+------------------+
--| Id(INT) | RecordDate(DATE) | Temperature(INT) |
--+---------+------------------+------------------+
--|       1 |       2015-01-01 |               10 |
--|       2 |       2015-01-02 |               25 |
--|       3 |       2015-01-03 |               20 |
--|       4 |       2015-01-04 |               30 |
--+---------+------------------+------------------+
--For example, return the following Ids for the above Weather table:
--
--+----+
--| Id |
--+----+
--|  2 |
--|  4 |
--+----+

select w1.Id from Weather as w1, Weather as w2 where w1.Temperature > w2.Temperature and TO_DAYS(w1.DATE)=TO_DAYS(w2.DATE)+1;

--196. Delete Duplicate Emails
--
--Write a SQL query to delete all duplicate email entries in a table named Person, keeping only unique emails based on its smallest Id.
--
--+----+------------------+
--| Id | Email            |
--+----+------------------+
--| 1  | john@example.com |
--| 2  | bob@example.com  |
--| 3  | john@example.com |
--+----+------------------+
--Id is the primary key column for this table.
--For example, after running your query, the above Person table should have the following rows:
--
--+----+------------------+
--| Id | Email            |
--+----+------------------+
--| 1  | john@example.com |
--| 2  | bob@example.com  |
--+----+------------------+

--solution1
delete from Person where Id not in ( select A.Id from (select min(Id) as Id from Person GROUP BY Email) A )
--solution2 sql server
DELETE FROM Person
WHERE Id NOT IN (
  SELECT * FROM (
    SELECT Min(Id)
    FROM Person
    GROUP BY Email
  ) Tmp
)
-- solution cross join
--EXPLANATION:
--Take the table in the example
--Id | Email
--1 | john@example.com
--2 | bob@example.com
--3 | john@example.com
--
--Join the table on itself by the Email and you'll get:
--FROM Person p1, Person p2 WHERE p1.Email = p2.Email
--
--p1.Id | p1.Email | p2.Id | p2.Email
--1 | john@example.com | 1 | john@example.com
--3 | john@example.com | 1 | john@example.com
--2 | bob@example.com | 2 | bob@example.com
--1 | john@example.com | 3 | john@example.com
--3 | john@example.com | 3 | john@example.com

DELETE p1
FROM Person p1, Person p2
WHERE p1.Email = p2.Email AND
p1.Id > p2.Id

--595. Big Countries
--
--SQL Schema
--There is a table World
--
--+-----------------+------------+------------+--------------+---------------+
--| name            | continent  | area       | population   | gdp           |
--+-----------------+------------+------------+--------------+---------------+
--| Afghanistan     | Asia       | 652230     | 25500100     | 20343000      |
--| Albania         | Europe     | 28748      | 2831741      | 12960000      |
--| Algeria         | Africa     | 2381741    | 37100000     | 188681000     |
--| Andorra         | Europe     | 468        | 78115        | 3712000       |
--| Angola          | Africa     | 1246700    | 20609294     | 100990000     |
--+-----------------+------------+------------+--------------+---------------+
--A country is big if it has an area of bigger than 3 million square km or a population of more than 25 million.
--
--Write a SQL solution to output big countries' name, population and area.
--
--For example, according to the above table, we should output:
--
--+--------------+-------------+--------------+
--| name         | population  | area         |
--+--------------+-------------+--------------+
--| Afghanistan  | 25500100    | 652230       |
--| Algeria      | 37100000    | 2381741      |
--+--------------+-------------+--------------+

SELECT name, population, area FROM World WHERE (area>3000000 OR population>25000000)

--596. Classes More Than 5 Students
--
--SQL Schema
--There is a table courses with columns: student and class
--
--Please list out all classes which have more than or equal to 5 students.
--
--For example, the table:
--
--+---------+------------+
--| student | class      |
--+---------+------------+
--| A       | Math       |
--| B       | English    |
--| C       | Math       |
--| D       | Biology    |
--| E       | Math       |
--| F       | Computer   |
--| G       | Math       |
--| H       | Math       |
--| I       | Math       |
--+---------+------------+
--Should output:
--
--+---------+
--| class   |
--+---------+
--| Math    |
--+---------+

select class from courses group by class having count(distinct student) >= 5;

--183. Customers Who Never Order
--
--SQL Schema
--Suppose that a website contains two tables, the Customers table and the Orders table.
--Write a SQL query to find all customers who never order anything.
--
--Table: Customers.
--
--+----+-------+
--| Id | Name  |
--+----+-------+
--| 1  | Joe   |
--| 2  | Henry |
--| 3  | Sam   |
--| 4  | Max   |
--+----+-------+
--Table: Orders.
--
--+----+------------+
--| Id | CustomerId |
--+----+------------+
--| 1  | 3          |
--| 2  | 1          |
--+----+------------+
--Using the above tables as example, return the following:
--
--+-----------+
--| Customers |
--+-----------+
--| Henry     |
--| Max       |
--+-----------+
--s1
SELECT A.Name from Customers A WHERE A.Id NOT IN (SELECT B.CustomerId from Orders B)
--s2
select c.Name as Customers from Customers c left join Orders o on (c.Id = o.CustomerId ) where o.Id  IS NULL;

--184. Department Highest Salary
--
--SQL Schema
--The Employee table holds all employees. Every employee has an Id, a salary, and there is also a column for the department Id.
--
--+----+-------+--------+--------------+
--| Id | Name  | Salary | DepartmentId |
--+----+-------+--------+--------------+
--| 1  | Joe   | 70000  | 1            |
--| 2  | Jim   | 90000  | 1            |
--| 3  | Henry | 80000  | 2            |
--| 4  | Sam   | 60000  | 2            |
--| 5  | Max   | 90000  | 1            |
--+----+-------+--------+--------------+
--The Department table holds all departments of the company.
--
--+----+----------+
--| Id | Name     |
--+----+----------+
--| 1  | IT       |
--| 2  | Sales    |
--+----+----------+
--Write a SQL query to find employees who have the highest salary in each of the departments. For the above tables, your SQL query should return the following rows (order of rows does not matter).
--
--+------------+----------+--------+
--| Department | Employee | Salary |
--+------------+----------+--------+
--| IT         | Max      | 90000  |
--| IT         | Jim      | 90000  |
--| Sales      | Henry    | 80000  |
--+------------+----------+--------+

SELECT D.Name AS Department ,E.Name AS Employee ,E.Salary
from
	Employee E,
	Department D
WHERE E.DepartmentId = D.id
  AND (DepartmentId,Salary) in (SELECT DepartmentId, max(Salary) as max FROM Employee GROUP BY DepartmentId)


--1179. Reformat Department Table
--
--SQL Schema
--Table: Department
--
--+---------------+---------+
--| Column Name   | Type    |
--+---------------+---------+
--| id            | int     |
--| revenue       | int     |
--| month         | varchar |
--+---------------+---------+
--(id, month) is the primary key of this table.
--The table has information about the revenue of each department per month.
--The month has values in ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"].
--
--
--Write an SQL query to reformat the table such that there is a department id column and a revenue column for each month.
--
--The query result format is in the following example:
--
--Department table:
--+------+---------+-------+
--| id   | revenue | month |
--+------+---------+-------+
--| 1    | 8000    | Jan   |
--| 2    | 9000    | Jan   |
--| 3    | 10000   | Feb   |
--| 1    | 7000    | Feb   |
--| 1    | 6000    | Mar   |
--+------+---------+-------+
--
--Result table:
--+------+-------------+-------------+-------------+-----+-------------+
--| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
--+------+-------------+-------------+-------------+-----+-------------+
--| 1    | 8000        | 7000        | 6000        | ... | null        |
--| 2    | 9000        | null        | null        | ... | null        |
--| 3    | null        | 10000       | null        | ... | null        |
--+------+-------------+-------------+-------------+-----+-------------+
--
--Note that the result table has 13 columns (1 for the department id + 12 for the months).

select id,
	sum(case when month = 'jan' then revenue else null end) as Jan_Revenue,
	sum(case when month = 'feb' then revenue else null end) as Feb_Revenue,
	sum(case when month = 'mar' then revenue else null end) as Mar_Revenue,
	sum(case when month = 'apr' then revenue else null end) as Apr_Revenue,
	sum(case when month = 'may' then revenue else null end) as May_Revenue,
	sum(case when month = 'jun' then revenue else null end) as Jun_Revenue,
	sum(case when month = 'jul' then revenue else null end) as Jul_Revenue,
	sum(case when month = 'aug' then revenue else null end) as Aug_Revenue,
	sum(case when month = 'sep' then revenue else null end) as Sep_Revenue,
	sum(case when month = 'oct' then revenue else null end) as Oct_Revenue,
	sum(case when month = 'nov' then revenue else null end) as Nov_Revenue,
	sum(case when month = 'dec' then revenue else null end) as Dec_Revenue
from department
group by id
order by id

--627. Swap Salary
--
--Given a table salary, such as the one below, that has m=male and f=female values.
--Swap all f and m values (i.e., change all f values to m and vice versa) with a single update statement and no intermediate temp table.
--
--Note that you must write a single update statement, DO NOT write any select statement for this problem.
--
--
--
--Example:
--
--| id | name | sex | salary |
--|----|------|-----|--------|
--| 1  | A    | m   | 2500   |
--| 2  | B    | f   | 1500   |
--| 3  | C    | m   | 5500   |
--| 4  | D    | f   | 500    |
--After running your update statement, the above salary table should have the following rows:
--| id | name | sex | salary |
--|----|------|-----|--------|
--| 1  | A    | f   | 2500   |
--| 2  | B    | m   | 1500   |
--| 3  | C    | f   | 5500   |
--| 4  | D    | m   | 500    |

update salary set sex = TRIM(sex from "fm");

UPDATE salary
    SET sex  = (CASE WHEN sex = 'm'
        THEN  'f'
        ELSE 'm'
        END)

--620. Not Boring Movies
--
--Share
--SQL Schema
--X city opened a new cinema, many people would like to go to this cinema.
--The cinema also gives out a poster indicating the movies’ ratings and descriptions.
--Please write a SQL query to output movies with an odd numbered ID and a description that is not 'boring'. Order the result by rating.
--
--
--
--For example, table cinema:
--
--+---------+-----------+--------------+-----------+
--|   id    | movie     |  description |  rating   |
--+---------+-----------+--------------+-----------+
--|   1     | War       |   great 3D   |   8.9     |
--|   2     | Science   |   fiction    |   8.5     |
--|   3     | irish     |   boring     |   6.2     |
--|   4     | Ice song  |   Fantacy    |   8.6     |
--|   5     | House card|   Interesting|   9.1     |
--+---------+-----------+--------------+-----------+
--For the example above, the output should be:
--+---------+-----------+--------------+-----------+
--|   id    | movie     |  description |  rating   |
--+---------+-----------+--------------+-----------+
--|   5     | House card|   Interesting|   9.1     |
--|   1     | War       |   great 3D   |   8.9     |
--+---------+-----------+--------------+-----------+

SELECT * FROM cinema WHERE (id % 2 = 1) AND (description <> 'boring') ORDER BY rating DESC

