/* Added by Sangeeta as part of Part B : Feature#2 - Implementing Add Comments Feature
 * This is a repository class created for interacting with the database for handling comments.
 */

package ImageHoster.repository;

import ImageHoster.model.Comment;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {
  @PersistenceUnit(unitName = "imageHoster")
  private EntityManagerFactory emf;

  /* This method will take the comment added by user as parameter.
   * Creates an instance of EntityManager.
   * Starts a transaction.
   * The comment is persisted to the database table.
   * The transaction is committed if it is successful.
   * The transaction is rolled back in case of unsuccessful transaction.
   */
  public Comment addNewComment(Comment newComment) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      em.persist(newComment);
      transaction.commit();
    } catch(Exception e) {
      transaction.rollback();
    }
    return newComment;
  }
}
