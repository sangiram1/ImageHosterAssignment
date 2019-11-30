/* Added by Sangeeta as part of Part B : Feature#2 - Implementing Add Comments Feature
 * This is a service class created for extending services related to Comment Entity
 */

package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  @Autowired
  private CommentRepository commentRepository;

  /* It has the following method : addNewComment
   * This method will call the take comment as parameter and call the method in repository
   * The method in Repository class would persist the comment entered by user to database.
   */
  public Comment addNewComment(Comment newComment) {
    return commentRepository.addNewComment(newComment);
  }

}
