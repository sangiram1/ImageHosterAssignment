/* Added by Sangeeta as part of Part B : Feature#2 - Implementing Add Comments Feature
 * This is a model class(JPA entity) created for representing table "comment" in database
 * It has the following attributes : id, text and createdDate
 * Mapped to users table with Many to One mapping
 */

package ImageHoster.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comment'.
//Hence the table named 'comment' will be created in the database with all the columns mapped
// to all the attributes in 'Comment' class
@Table(name = "comment")
public class Comment {
  //@Id annotation specifies that the corresponding attribute is a primary key
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  //@Column annotation specifies that the attribute will be mapped to the column in the database.
  //Here the column name is explicitly mentioned as 'id'
  @Column(name = "id")
  private int id;

  // Text is a Postgres specific column type that allows you to save
  // text based data that will be longer than 256 characters
  // This will contain the comments entered by users
  @Column(columnDefinition = "TEXT")
  private String text;

  @Column(name = "createdDate")
  private LocalDate createdDate;

  // The 'comment' table is mapped to 'users' table with Many:One mapping
  // One comment can have only one user (owner) but one user can have multiple comments
  // FetchType is EAGER
  @ManyToOne(fetch = FetchType.EAGER)
  // Below annotation indicates that the name of the column in 'comment' table referring the
  // primary key in 'users' table will be 'user_id'
  @JoinColumn(name = "user_id")
  private User user;

  // The 'comment' table is mapped to 'images' table with Many:One mapping
  // One comment can only belong to one image but one image can have multiple comments
  // FetchType is EAGER
  @ManyToOne(fetch = FetchType.EAGER)
  // Below annotation indicates that the name of the column in 'comment' table referring the
  // primary key in 'images' table will be 'image_id'
  @JoinColumn(name = "image_id")
  private Image image;

  // Getters & Setters for All the Attributes
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
