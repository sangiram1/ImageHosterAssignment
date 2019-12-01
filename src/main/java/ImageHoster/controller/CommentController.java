package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import java.time.LocalDate;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private ImageService imageService;

  //Added by Sangeeta as part of Part B : Feature#2 - Implementing Add Comment Feature
  //This method would take the dynamic parameters and the comment model as input
  //The logged user will be determined from session object and added as the user creating comment
  //The image details will be fetched using the dynamic params and will be added to comment table
  //This would also set the date and time at which the comments was added.
  //commentService would help persist the comment to the database
  //Once the comment is persisted, the image will be displayed showing all comments & other details
  @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
  public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle")
      String title, @RequestParam("comment") String commentText, Comment newComment,
      HttpSession session) {
    Image image = imageService.getImage(imageId);
    User user = (User) session.getAttribute("loggeduser");
    newComment.setUser(user);
    newComment.setImage(image);
    newComment.setText(commentText);
    newComment.setCreatedDate(LocalDate.now());
    commentService.addNewComment(newComment);
    return "redirect:/images/" + image.getId() + "/" + image.getTitle();
  }

}
