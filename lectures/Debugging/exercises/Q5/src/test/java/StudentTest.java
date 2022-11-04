import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class StudentTest {

  @Test
  public void testAddition() {
    assertThat(1 + 1, Matchers.is(2));
  }

  @Test
  public void hashOfStudentsShouldNotChangeDuringTheDay() {
      Student student = new Student("Test", 12);
      int oldHashCode = student.hashCode();
      student.take("ML");
      assertThat(oldHashCode, is(student.hashCode()));
  }
}
