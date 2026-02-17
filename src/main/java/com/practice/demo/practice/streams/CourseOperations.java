package com.practice.demo.practice.streams;

import org.openapitools.model.GetCoursePayload;
import java.util.List;
import java.util.Map;

public interface CourseOperations {

    /**
     * Filter courses by active status using streams
     * @param courses the list of courses to filter
     * @param isActive filter predicate for active status
     * @return list of filtered courses
     */
    List<GetCoursePayload> filterByActiveStatus(List<GetCoursePayload> courses, boolean isActive);

    /**
     * Filter courses by title substring using streams
     * @param courses the list of courses to filter
     * @param titlePattern the pattern to search for in course titles
     * @return list of courses matching the pattern
     */
    List<GetCoursePayload> filterByTitlePattern(List<GetCoursePayload> courses, String titlePattern);

    /**
     * Filter courses that have more than
     * specific number of lessons using streams
     * @param courses the list of courses to filter
     * @param lessons number of lessons
     * @return list of courses containing lessons
     */
    List<GetCoursePayload> filterCoursesWithLessonsMoreThan(List<GetCoursePayload> courses, int lessons);

    /**
     * Get course titles mapped from payload objects
     * @param courses the list of courses to map
     * @return list of course titles
     */
    List<String> mapToTitles(List<GetCoursePayload> courses);

    /**
     * Sort courses by id in descending order
     * @param courses the list of courses to sort
     * @return list of sorted courses
     */
    List<GetCoursePayload> sortByIdDescending(List<GetCoursePayload> courses);

    /**
     * Sort courses by title in ascending order
     * and number of lessons in descending order
     * @param courses the list of courses to sort
     * @return list of sorted courses
     */
    List<GetCoursePayload> sortByTitleAscAndLessonsDesc(List<GetCoursePayload> courses);

    /**
     * Get the count of active courses
     * @param courses the list of courses to count
     * @return count of active courses
     */
    long countActiveCourses(List<GetCoursePayload> courses);

    /**
     * Get the count of courses with a specific lesson count
     * @param courses the list of courses to check
     * @param minLessonCount minimum number of lessons required
     * @return count of courses meeting criteria
     */
    long countCoursesByLessonCount(List<GetCoursePayload> courses, int minLessonCount);

    /**
     * Get total count of lessons
     * @param courses the list of courses to check
     * @return count of lessons
     */
    long countLessons(List<GetCoursePayload> courses);

    /**
     * Check if any course has the given title
     * @param courses the list of courses to search
     * @param title the title to search for
     * @return true if a course with the title exists
     */
    boolean anyCourseWithTitle(List<GetCoursePayload> courses, String title);

    /**
     * Check if all courses are active
     * @param courses the list of courses to check
     * @return true if all courses are active
     */
    boolean allCoursesActive(List<GetCoursePayload> courses);

    /**
     * Get the course with the longest title
     * @param courses the list of courses to search
     * @return the course with the longest title, or null if list is empty
     */
    GetCoursePayload findCourseWithLongestTitle(List<GetCoursePayload> courses);

    /**
     * Get the course with the most lessons
     * @param courses the list of courses to search
     * @return the course with the most lessons, or null if list is empty
     */
    GetCoursePayload findCourseWithMostLessons(List<GetCoursePayload> courses);

    /**
     * Group courses by active status
     * @param courses the list of courses to group
     * @return a map with boolean key (active/inactive) and list of courses as value
     */
    Map<Boolean, List<GetCoursePayload>> groupByActiveStatus(List<GetCoursePayload> courses);

    /**
     * Filter and collect courses as list
     * @param courses the list of courses to filter
     * @param minId minimum course id to include
     * @return list of courses with id >= minId
     */
    List<GetCoursePayload> filterByMinIdAndCollect(List<GetCoursePayload> courses, Long minId);

    /**
     * Transform and collect course descriptions into a list
     * @param courses the list of courses to transform
     * @return list of course descriptions filtered by non-null values
     */
    List<String> collectNonNullDescriptions(List<GetCoursePayload> courses);
}
