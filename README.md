# InventoryManagementSystemAPI

Inventory Management System API --

- This project is build using Spring Boot for backend services and MySQL as DB.
- This project enables full product management and stock tracking application with strict business restrictions to manage invalid stock operations.

Tech Stack Used --
- Backend - SpringBoot,Spring Web,SPring Data JPA
- Database - MySQL
- ORM - Hibernate
- API Testing - Swagger
- Build Tool - Maven

Features --
- All CRUD operations on product entity
- Add Product. (201 CREATED)
- Get All Product, Product By Name, Products below specified threshold. (200 SUCCESS)
- Perform Soft Delete on product entity rather than hard delete.Set product status as inactive.
- Stock Updation operations 
    - Stock addition - Edge cases updation quantity should be greater than zero.
    - Stock removal - Edges cases updation quantity should be greater than zero. (400 BAD REQUEST)
                    - Provided quantity shoukd be present in stock else throw InvalidStockOperationException with message to try with lesser quantity.
                    - Check stock is not equal to zero.
- Used DTO(Data Transfer Object) to Data Hiding and security passed only necessary data.
- Added TimeStamp functionality at backend for data logging purpose.
- Created custom exception class for ProductNotFound and InvalidStockOperation for sending cutsom exception message.

All resources google drive link(STS-IDE,MySQL 8.4.4,JDK 21) download -> extract -> install - Refer image in drive(inventoryDocs)
https://drive.google.com/drive/folders/1C-EFAOEfKugkjs5t_5WL6NFLjvG9ffpz?usp=drive_link


Step 1 - Download JDK 21 -> Install keep default path/setting -> set bin path into environment variable Path folder (if required refer images) 

Step 2 - Download MySQL 8.4.4 -> Install keep everything default and click next-next (Check portNo-3306 and keep password as manager as spring app is configured with username=root and password=manager) if already installed MySQL then update db_config in application.properties in spring boot app (refer inventoryDocs/sts/16_application.properties_db_config.png) as your username or password -> set bin path into environment variable Path folder.

Step 3 - Download sts.4.30.3 -> Extract -> open/Double click SpringToolSuite4.exe -> keep default directory(or change directory if required) -> click File -> import -> select maven -> select existing maven project -> browse -> select spring_boot_backend_template (from downloaded InventoryManagementSystemAPI folder) -> Finish
3.1 - Right click on spring_boot_backend_template -> Run As -> Click Maven Build -> In goals type clean compile -> Run
3.2 - Right click on spring_boot_backend_template -> Run As -> Update Project -> Check/Tick (Force Update of snapshots/refernces) -> OK
3.3 - Right click on spring_boot_backend_template -> Run As -> Download Sources
3.4 - Right click on spring_boot_backend_template -> Run As -> SpringBoot App (Check Run Success)
3.5 - Test APIs Enter http://localhost:8080/swagger-ui/index.html#/ in browser -> Enter -> Test APIs -> Try Out -> Add Data -> Execute

Step 4 - Check DB data from cmd Line using mysql -u root(username) -p -> Enter password -> use inventoryapi.
