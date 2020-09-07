# walletcontrol
> you are fucking overspending you piece of shit
- phongkatoaa

Lombok: _https://www.baeldung.com/intro-to-project-lombok_

Configuration guide: _https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d_

### Use cases:
-	Login
-	Logout
-	Register
-	Update monthly spending limit -> Warn user when monthly limit exceeded + show percent of spent compare to limit
-	Update daily spending detail
-	Update monthly subscription (spotify, netflix) -> new cronjob run at every end of month or specify the day to make payment
-	Reporting: pull all user data by year (user can select year) -> insert into charts: total by year/month/week, spending by category by year/month/week, spending by product by year/month/week

### Table schemas:
-	Users
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - UserId (PRIMARY KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - FirstName
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - LastName
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Username
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Password (Bcrypt)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Active (consider to remove)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - SpendingLimit
-	Roles
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - RoleId (PRIMARY KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Role
-	User_role 
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - UserId (FOREIGN KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - RoleId (FOREIGN KEY)
-	Payments
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - PaymentId (PRIMARY KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - UserId (FOREIGN KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - ProductId (FOREIGN KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Date
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Price
-	Products
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - ProductId (PRIMARY KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Product
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - UserId (FOREIGN KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - CategoryId (FOREIGN KEY)
-	Categories
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - CategoryId (PRIMARY KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - UserId (FOREIGN KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Category
-	MonthlySubscriptions
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - SubscriptionId (PRIMARY KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - ProductId (FOREIGN KEY)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - DueDate
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Price
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Active
