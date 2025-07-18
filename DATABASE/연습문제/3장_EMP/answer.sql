create table Dept(
	deptno int not null,
    dname varchar(14),
    loc varchar(13),
    primary key(deptno));
    
create table Emp(
	empno int not null,
    ename varchar(10),
    job varchar(9),
    mgr int,
    hiredate date,
    sal int,
    comm int,
    deptno int,
    primary key(empno),
    foreign key(deptno) references Dept(deptno));
    
INSERT INTO Dept(deptno, dname, loc) 
  VALUES (10, 'ACCOUNTING', 'NEW YORK'),
		 (20, 'RESEARCH', 'DALLAS'),
         (30, 'SALES', 'CHICAGO'),
         (40, 'OPERATIONS', 'BOSTON');
         
INSERT INTO Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) 
  VALUES (7369, 'SMITH', 'CLERK', 7902, '1920-12-17', 800, null, 20),
		 (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600, 300, 30),
         (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250, 500, 30),
         (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975, null, 20);

select b.ename, a.loc
from Dept a, Emp b
where a.deptno = b.deptno;

alter table Dept add managername varchar(15);

update Dept set managername = 'JONES' where deptno = '20';

    
    