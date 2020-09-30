# 项目管理系统

很他妈大牛的项目管理系统

## 数据字段

1、项目：项目编号、名称、描述、开始时间、完成时间
```mysql
CREATE TABLE Projects (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(64) NOT NULL ,
    project_desc VARCHAR(256) NOT NULL ,
    project_begin DATETIME,
    project_end DATETIME
);
```

2、功能：功能编号、项目编号、功能名称

```mysql

CREATE TABLE Functions(
    function_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT,
    function_name VARCHAR(64) NOT NULL ,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id)
);
```


3、学生：学号、姓名、性别、年龄、项目编号
```mysql
CREATE TABLE Students(
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(64) NOT NULL ,
    student_sex ENUM('男', '女') NOT NULL ,
    student_age INT,
    student_password VARCHAR(64) NOT NULL ,
    project_id INT # 可有可无，未加入|创建 项目时，为空。
);
```


4、管理员：id、账号、密码
```mysql
CREATE TABLE Managers(
    manager_id INT PRIMARY KEY AUTO_INCREMENT,
      manager_name VARCHAR(64) NOT NULL ,
      manager_password VARCHAR(64) NOT NULL
  );
```
