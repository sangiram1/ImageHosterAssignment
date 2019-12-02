# ImageHosterAssignment
Course 5 :Image Hoster Assignment Uploaded by Sangeeta

This Project Handles the below Cases:
1. Bug Fixes - Image upload issues:
   Bug Fix#1   : Image Hoster will no longer throw exception when trying to access an image which has same title as another image.
   Bug Fix#2.1 : Only the owner of the image can edit the image. Non-owners would get an error message saying - "Only the owner 
   of the image can edit the image".
   Bug Fix#2.2 : Only the owner of the image can delete the image. Non-owners would get an error message saying - "Only the owner 
   of the image can delete the image".
2. Features - Implement new features:
   Feature#1   : Password Strength:
   The application would now check for password strength while user registers. The user would be presented with the given error           
   message saying - "Password must contain atleast 1 alphabet, 1 number & 1 special character" when the given criteria is not met.
   Feature#2   : Add Comments:
   The application would now allow the user to add comments to a given image. All the comments added to a particular image will be        
   displayed while trying to access the details of a given image.
   
Along with the above functionality changes, this code also provides a workaround for the below problems:
1. No Tag Assigned :
   Problem: It is mandatory to add atleast one tag while uploading an image. Even if we don't add, the image will be uploaded but 
   while trying to edit the image, the application would throw an ArrayIndexOutOfBoundsException as the edit.html page needs tags 
   to be converted as Strings concatenated by commas. Since, no tag is assigned, the code for converting tags into the given format
   would throw this exception.
   
   Workaround: Ideally, we should present user with an error message saying - "Atleast one tag has to be assigned to an image while        
   uploading". But, as a workaround, this project would make sure that the runtime exception is not thrown even if no tag is assigned.
   Indeed, it would return empty tag while loading edit.html.
   
2. HibernateException:
   Problem: By default, Hibernate uses JDBC connections which is very expensive. Hibernate is designed to use a connection pool by 
   default, an internal implementation. However, Hibernate’s built-in connection pooling isn’t designed for production use.This would
   allow only a limited set of connections to the application causing it to throw below exception when the connections are exceeded.
   org.hibernate.HibernateException: The internal connection pool has reached its maximum size and no connection is currently available!
   
   Workaround: To resolve this issue, we would use an external connection pool by using either a database connection provided by JNDI or
   an external connection pool configured via parameters and classpath. Here, this project uses c3p0 which is an external connection pool
   utility. To utilize this, some dependencies and required configuration changes have been included in pom.xml and persistence.xml files.
   
   
