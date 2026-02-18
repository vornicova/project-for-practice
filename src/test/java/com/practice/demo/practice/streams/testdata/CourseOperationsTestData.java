package com.practice.demo.practice.streams.testdata;

import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.GetLessonReducedPayload;
import org.openapitools.model.LessonPayload;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory class for creating test data for CourseOperations tests
 */
public class CourseOperationsTestData {

    // ============== Expected Data Constants ==============

    // Mixed courses expected values
    public static final int MIXED_COURSES_COUNT = 5;
    public static final int MIXED_COURSES_ACTIVE_COUNT = 3;
    public static final int MIXED_COURSES_INACTIVE_COUNT = 2;
    public static final long MIXED_COURSES_TOTAL_LESSONS = 36; // 5+8+10+6+7

    // All active courses expected values
    public static final int ALL_ACTIVE_COURSES_COUNT = 3;


    // Courses with varying lessons expected values
    public static final int VARYING_LESSONS_COURSES_COUNT = 4;
    public static final long VARYING_LESSONS_TOTAL_LESSONS = 20; // 2+5+10+3
    public static final int VARYING_LESSONS_MORE_THAN_5_COUNT_CORRECT = 1; // only 10
    public static final int VARYING_LESSONS_MORE_THAN_0_COUNT = 4; // all 4
    public static final long VARYING_LESSONS_MIN_5_COUNT = 2; // 5 and 10

    // Pattern matching courses expected values
    public static final int PATTERN_MATCHING_JAVA_COUNT = 4; // Java Basics, Advanced Java, Java Web Development

    // Long titles courses expected values
    public static final int LONGEST_TITLE_LENGTH = 65;

    // Courses with null descriptions expected values
    public static final int NULL_DESCRIPTIONS_NON_NULL_COUNT = 3; // A description, Another description, CSS description

    // Courses with various IDs expected values
    public static final int VARIOUS_IDS_COURSES_COUNT = 5;
    public static final int VARIOUS_IDS_GREATER_EQUAL_10_COUNT = 4; // 10, 15, 20, 25
    public static final int VARIOUS_IDS_GREATER_EQUAL_100_COUNT = 0;

    // Single course expected values
    public static final Long SINGLE_COURSE_ID = 1L;
    public static final String SINGLE_COURSE_TITLE = "Java Basics";
    public static final int SINGLE_COURSE_LESSONS = 5;

    // Courses with varying lessons - specific expected values
    public static final String COURSE_WITH_MOST_LESSONS_TITLE = "Course C";
    public static final int COURSE_WITH_MOST_LESSONS_COUNT = 10;

    /**
     * Creates a course with the given id, title, description, active status, and lessons
     */
    public static GetCoursePayload createCourse(Long id, String title, String description,
                                                 boolean isActive, int lessonCount) {
        GetCoursePayload course = new GetCoursePayload();
        course.setId(id);
        course.setTitle(title);
        course.setDescription(description);
        course.setIsActive(isActive);
        course.setLessons(createLessons(lessonCount));
        return course;
    }

    /**
     * Creates a course with default values
     */
    public static GetCoursePayload createDefaultCourse() {
        return createCourse(1L, "Java Basics", "Introduction to Java", true, 5);
    }

    /**
     * Creates a list of lessons
     */
    private static List<GetLessonReducedPayload> createLessons(int count) {
        List<GetLessonReducedPayload> lessons = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            GetLessonReducedPayload lesson = new GetLessonReducedPayload();
            lesson.setId((long) i);
            lesson.setName("Lesson " + i);
            lessons.add(lesson);
        }
        return lessons;
    }

    /**
     * Creates a list of mixed active and inactive courses
     */
    public static List<GetCoursePayload> createMixedCourses() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Java Basics", "Learn Java fundamentals", true, 5));
        courses.add(createCourse(2L, "Advanced Java", "Learn advanced Java concepts", true, 8));
        courses.add(createCourse(3L, "Spring Framework", "Learn Spring Framework", false, 10));
        courses.add(createCourse(4L, "Python Programming", "Learn Python", true, 6));
        courses.add(createCourse(5L, "JavaScript Guide", "Learn JavaScript", false, 7));
        return courses;
    }

    /**
     * Creates a list of all active courses
     */
    public static List<GetCoursePayload> createAllActiveCourses() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Java Basics", "Learn Java fundamentals", true, 5));
        courses.add(createCourse(2L, "Advanced Java", "Learn advanced Java concepts", true, 8));
        courses.add(createCourse(3L, "Python Programming", "Learn Python", true, 6));
        return courses;
    }

    /**
     * Creates a list of all inactive courses
     */
    public static List<GetCoursePayload> createAllInactiveCourses() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Spring Framework", "Learn Spring Framework", false, 10));
        courses.add(createCourse(2L, "JavaScript Guide", "Learn JavaScript", false, 7));
        return courses;
    }

    /**
     * Creates a list of courses with varying lesson counts
     */
    public static List<GetCoursePayload> createCoursesWithVaryingLessons() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Course A", "Description A", true, 2));
        courses.add(createCourse(2L, "Course B", "Description B", true, 5));
        courses.add(createCourse(3L, "Course C", "Description C", true, 10));
        courses.add(createCourse(4L, "Course D", "Description D", false, 3));
        return courses;
    }

    /**
     * Creates a list of courses with long titles
     */
    public static List<GetCoursePayload> createCoursesWithLongTitles() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Java", "Short title", true, 5));
        courses.add(createCourse(2L, "Advanced Java Programming", "Medium title", true, 8));
        courses.add(createCourse(3L, "Complete Java Programming Course for Beginners and Advanced Developers",
                                 "Very long title", true, 10));
        return courses;
    }

    /**
     * Creates an empty list
     */
    public static List<GetCoursePayload> createEmptyList() {
        return new ArrayList<>();
    }

    /**
     * Creates a single course
     */
    public static List<GetCoursePayload> createSingleCourse() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Java Basics", "Learn Java fundamentals", true, 5));
        return courses;
    }

    /**
     * Creates courses with specific titles for pattern matching
     */
    public static List<GetCoursePayload> createCoursesForPatternMatching() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Java Basics", "Description 1", true, 5));
        courses.add(createCourse(2L, "Advanced Java", "Description 2", true, 8));
        courses.add(createCourse(3L, "Python Programming", "Description 3", false, 6));
        courses.add(createCourse(4L, "JavaScript Guide", "Description 4", true, 7));
        courses.add(createCourse(5L, "Java Web Development", "Description 5", true, 9));
        return courses;
    }

    /**
     * Creates courses with null descriptions
     */
    public static List<GetCoursePayload> createCoursesWithNullDescriptions() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Java Basics", "A description", true, 5));
        courses.add(createCourse(2L, "Advanced Java", null, true, 8));
        courses.add(createCourse(3L, "Python Programming", "Another description", false, 6));
        courses.add(createCourse(4L, "JavaScript Guide", null, true, 7));
        courses.add(createCourse(5L, "CSS Basics", "CSS description", true, 3));
        return courses;
    }

    /**
     * Creates courses with high ids for min id filtering
     */
    public static List<GetCoursePayload> createCoursesWithVariousIds() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(5L, "Course A", "Description A", true, 5));
        courses.add(createCourse(10L, "Course B", "Description B", true, 8));
        courses.add(createCourse(15L, "Course C", "Description C", false, 6));
        courses.add(createCourse(20L, "Course D", "Description D", true, 7));
        courses.add(createCourse(25L, "Course E", "Description E", true, 9));
        return courses;
    }

    // ============== Prepared Sorted Lists for Expected Results ==============

    /**
     * Creates expected result of mixed courses sorted by ID in descending order
     */
    public static List<GetCoursePayload> createMixedCoursesSortedByIdDescending() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(5L, "JavaScript Guide", "Learn JavaScript", false, 7));
        courses.add(createCourse(4L, "Python Programming", "Learn Python", true, 6));
        courses.add(createCourse(3L, "Spring Framework", "Learn Spring Framework", false, 10));
        courses.add(createCourse(2L, "Advanced Java", "Learn advanced Java concepts", true, 8));
        courses.add(createCourse(1L, "Java Basics", "Learn Java fundamentals", true, 5));
        return courses;
    }

    /**
     * Creates expected result of mixed courses sorted by title ascending, then lessons descending
     */
    public static List<GetCoursePayload> createMixedCoursesSortedByTitleAscAndLessonsDesc() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(2L, "Advanced Java", "Learn advanced Java concepts", true, 8));
        courses.add(createCourse(1L, "Java Basics", "Learn Java fundamentals", true, 5));
        courses.add(createCourse(5L, "JavaScript Guide", "Learn JavaScript", false, 7));
        courses.add(createCourse(4L, "Python Programming", "Learn Python", true, 6));
        courses.add(createCourse(3L, "Spring Framework", "Learn Spring Framework", false, 10));
        return courses;
    }

    /**
     * Creates expected result of varying lessons courses sorted by ID in descending order
     */
    public static List<GetCoursePayload> createVaryingLessonsCoursesSortedByIdDescending() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(4L, "Course D", "Description D", false, 3));
        courses.add(createCourse(3L, "Course C", "Description C", true, 10));
        courses.add(createCourse(2L, "Course B", "Description B", true, 5));
        courses.add(createCourse(1L, "Course A", "Description A", true, 2));
        return courses;
    }

    /**
     * Creates expected result of various IDs courses sorted by ID in descending order
     */
    public static List<GetCoursePayload> createVariousIdsCoursesSortedByIdDescending() {
        List<GetCoursePayload> courses = new ArrayList<>();
        courses.add(createCourse(25L, "Course E", "Description E", true, 9));
        courses.add(createCourse(20L, "Course D", "Description D", true, 7));
        courses.add(createCourse(15L, "Course C", "Description C", false, 6));
        courses.add(createCourse(10L, "Course B", "Description B", true, 8));
        courses.add(createCourse(5L, "Course A", "Description A", true, 5));
        return courses;
    }
}

