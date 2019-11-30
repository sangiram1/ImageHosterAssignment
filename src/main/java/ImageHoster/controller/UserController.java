package ImageHoster.controller;

import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.model.UserProfile;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ImageService imageService;

  //This controller method is called when the request pattern is of type 'users/registration'
  //This method declares User type and UserProfile type object
  //Sets the user profile with UserProfile type object
  //Adds User type object to a model and returns 'users/registration.html' file
  @RequestMapping("users/registration")
  public String registration(Model model) {
    User user = new User();
    UserProfile profile = new UserProfile();
    user.setProfile(profile);
    model.addAttribute("User", user);
    return "users/registration";
  }

  //This controller method is called when the request pattern is of type 'users/registration' and also the incoming request is of POST type
  //This method calls the business logic and after the user record is persisted in the database, directs to login page
  @RequestMapping(value = "users/registration", method = RequestMethod.POST)
  //Modified by Sangeeta as part of Part B : Feature#1 - Implementing Password Strength Validation
  //Pass the user entered password to checkPasswordStrength to find if it meets the required validations.
  //Only if the entered password is valid, route to login.html page
  //If not, route to registration.html page with an error message
  //The error message would say "Password must contain at least 1 alphabet, 1 number & 1 special character"
  //public String registerUser(User user) {
  public String registerUser(User user, Model model) {
    //userService.registerUser(user);
    //return "redirect:/users/login";
    boolean isValidPassword = checkPasswordStrength(user.getPassword());
    if (isValidPassword) {
      userService.registerUser(user);
      // return "redirect:/users/login"; // Commented this to make the unit testcase work correctly
      // Ideally this should be redirect:/users/login only as the URL has to be updated
      // after successful registration. But, as per the instructions provided, to pass the testcases
      // We need to follow the code mentioned in unit test classes. Below is the Query response URL:
      // https://learn.upgrad.com/v/course/373/question/171492
      // .andExpect(redirectedUrl("/users/login")) should be in place of
      // .andExpect(view().name("users/login")) and following line in UserControllerTest.java
      return "users/login"; //Route to login.html page on successful registration
    } else {
      //Adding the required attributes to Model object needed by registration.html
      user = new User();
      UserProfile profile = new UserProfile();
      user.setProfile(profile);
      model.addAttribute("User", user);
      String error = "Password must contain atleast 1 alphabet, 1 number & 1 special character";
      model.addAttribute("passwordTypeError",error);
      return "users/registration"; // Route to registration.html page if password strength not met
    }
  }

  //This controller method is called when the request pattern is of type 'users/login'
  @RequestMapping("users/login")
  public String login() {
    return "users/login";
  }

  //This controller method is called when the request pattern is of type 'users/login' and also the incoming request is of POST type
  //The return type of the business logic is changed to User type instead of boolean type. The login() method in the business logic checks whether the user with entered username and password exists in the database and returns the User type object if user with entered username and password exists in the database, else returns null
  //If user with entered username and password exists in the database, add the logged in user in the Http Session and direct to user homepage displaying all the images in the application
  //If user with entered username and password does not exist in the database, redirect to the same login page
  @RequestMapping(value = "users/login", method = RequestMethod.POST)
  public String loginUser(User user, HttpSession session) {
    User existingUser = userService.login(user);
    if (existingUser != null) {
      session.setAttribute("loggeduser", existingUser);
      return "redirect:/images";
    } else {
      return "users/login";
    }
  }

  //This controller method is called when the request pattern is of type 'users/logout' and also the incoming request is of POST type
  //The method receives the Http Session and the Model type object
  //session is invalidated
  //All the images are fetched from the database and added to the model with 'images' as the key
  //'index.html' file is returned showing the landing page of the application and displaying all the images in the application
  @RequestMapping(value = "users/logout", method = RequestMethod.POST)
  public String logout(Model model, HttpSession session) {
    session.invalidate();

    List<Image> images = imageService.getAllImages();
    model.addAttribute("images", images);
    return "index";
  }

  //Added by Sangeeta as part of Part B : Feature#1 - Implementing Password Strength Validation
  //This method will take the password input by the user and validate the following:
  //Does the password entered has atleast one alphabet(a-z or A-Z)
  //Does the password entered has atleast one digit(0-9)
  //Does the password entered has atleast one special character(other than above two cases)
  //If all the above conditions are satisfied, the method would return true else false
  private boolean checkPasswordStrength(String password) {
    char[] passwordChars = password.toCharArray();
    int alphabetCount = 0;
    int numberCount = 0;
    int specialCharCount = 0;
    boolean isValidPassword = false;
    for (char letter : passwordChars) {
      if (Character.isLowerCase(letter) == true || Character.isUpperCase(letter) == true) {
        alphabetCount = alphabetCount + 1;
      } else if (Character.isDigit(letter) == true) {
        numberCount = numberCount + 1;
      } else {
        specialCharCount = specialCharCount + 1;
      }
    }

    if (alphabetCount >= 1 && numberCount >= 1 && specialCharCount >= 1) {
      isValidPassword = true;
    } else {
      isValidPassword = false;
    }
    return isValidPassword;
  }
}
