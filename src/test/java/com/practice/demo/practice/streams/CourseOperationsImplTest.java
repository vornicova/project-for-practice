package com.practice.demo.practice.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openapitools.model.GetCoursePayload;
import com.practice.demo.practice.streams.testdata.CourseOperationsTestData;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CourseOperationsImpl Tests")
class CourseOperationsImplTest {

    private CourseOperations courseOperations;

    @BeforeEach
    void setUp() {
        courseOperations = new CourseOperationsImpl();
    }

    // ============== filterByActiveStatus Tests ==============

    @Test
    @DisplayName("filterByActiveStatus should return only active courses when filter is true")
    void testFilterByActiveStatus() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<GetCoursePayload> result = courseOperations.filterByActiveStatus(courses, true);

        assertEquals(CourseOperationsTestData.MIXED_COURSES_ACTIVE_COUNT, result.size(),
            "Should have correct number of active courses");
        assertTrue(result.stream().allMatch(c -> c.getIsActive() != null && c.getIsActive()),
            "All courses should be active");
    }

    @Test
    @DisplayName("filterByActiveStatus should return only inactive courses when filter is false")
    void testFilterByInactiveCourses() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<GetCoursePayload> result = courseOperations.filterByActiveStatus(courses, false);

        assertEquals(CourseOperationsTestData.MIXED_COURSES_INACTIVE_COUNT, result.size(),
            "Should have correct number of inactive courses");
        assertTrue(result.stream().allMatch(c -> c.getIsActive() != null && !c.getIsActive()),
            "All courses should be inactive");
    }

    @Test
    @DisplayName("filterByActiveStatus with empty list should return empty list")
    void testFilterByActiveStatusWithEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        List<GetCoursePayload> result = courseOperations.filterByActiveStatus(courses, true);

        assertTrue(result.isEmpty(), "Result should be empty");
    }

    // ============== filterByTitlePattern Tests ==============

    @Test
    @DisplayName("filterByTitlePattern should return courses containing the pattern")
    void testFilterByTitlePattern() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesForPatternMatching();
        List<GetCoursePayload> result = courseOperations.filterByTitlePattern(courses, "Java");

        assertEquals(CourseOperationsTestData.PATTERN_MATCHING_JAVA_COUNT, result.size(),
            "Should find correct number of courses with 'Java' in title");
        assertTrue(result.stream().allMatch(c -> c.getTitle() != null && c.getTitle().contains("Java")),
            "All courses should contain 'Java' in title");
    }

    @Test
    @DisplayName("filterByTitlePattern should return empty list when no matches found")
    void testFilterByTitlePatternNoMatches() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<GetCoursePayload> result = courseOperations.filterByTitlePattern(courses, "Nonexistent");

        assertTrue(result.isEmpty(), "Should return empty list for non-matching pattern");
    }

    @Test
    @DisplayName("filterByTitlePattern with empty pattern should match nothing meaningful")
    void testFilterByTitlePatternWithEmptyPattern() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<GetCoursePayload> result = courseOperations.filterByTitlePattern(courses, "");

        assertEquals(courses.size(), result.size(), "All courses contain empty string");
    }

    // ============== filterCoursesWithLessonsMoreThan Tests ==============

    @Test
    @DisplayName("filterCoursesWithLessonsMoreThan should return courses with more lessons than specified")
    void testFilterCoursesWithLessonsMoreThan() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVaryingLessons();
        List<GetCoursePayload> result = courseOperations.filterCoursesWithLessonsMoreThan(courses, 5);

        assertEquals(CourseOperationsTestData.VARYING_LESSONS_MORE_THAN_5_COUNT_CORRECT, result.size(),
            "Should have correct number of courses with more than 5 lessons");
        assertTrue(result.stream().allMatch(c -> c.getLessons().size() > 5),
            "All courses should have more than 5 lessons");
    }

    @Test
    @DisplayName("filterCoursesWithLessonsMoreThan with high threshold should return empty list")
    void testFilterCoursesWithLessonsMoreThanHighThreshold() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<GetCoursePayload> result = courseOperations.filterCoursesWithLessonsMoreThan(courses, 100);

        assertTrue(result.isEmpty(), "Should return empty list when threshold is too high");
    }

    @Test
    @DisplayName("filterCoursesWithLessonsMoreThan with zero lessons should return courses with lessons")
    void testFilterCoursesWithLessonsMoreThanZero() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVaryingLessons();
        List<GetCoursePayload> result = courseOperations.filterCoursesWithLessonsMoreThan(courses, 0);

        assertEquals(CourseOperationsTestData.VARYING_LESSONS_MORE_THAN_0_COUNT, result.size(),
            "All courses should have more than 0 lessons");
    }

    // ============== mapToTitles Tests ==============

    @Test
    @DisplayName("mapToTitles should return list of all course titles")
    void testMapToTitles() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<String> result = courseOperations.mapToTitles(courses);

        assertEquals(CourseOperationsTestData.MIXED_COURSES_COUNT, result.size(),
            "Should have correct number of titles");
        assertTrue(result.contains("Java Basics"), "Should contain 'Java Basics'");
        assertTrue(result.contains("Advanced Java"), "Should contain 'Advanced Java'");
    }

    @Test
    @DisplayName("mapToTitles with single course should return single title")
    void testMapToTitlesWithSingleCourse() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createSingleCourse();
        List<String> result = courseOperations.mapToTitles(courses);

        assertEquals(1, result.size(), "Should have 1 title");
        assertEquals(CourseOperationsTestData.SINGLE_COURSE_TITLE, result.get(0),
            "Should be correct title");
    }

    @Test
    @DisplayName("mapToTitles with empty list should return empty list")
    void testMapToTitlesWithEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        List<String> result = courseOperations.mapToTitles(courses);

        assertTrue(result.isEmpty(), "Should return empty list");
    }

    // ============== sortByIdDescending Tests ==============

    @Test
    @DisplayName("sortByIdDescending should sort courses by id in descending order")
    void testSortByIdDescending() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVariousIds();
        List<GetCoursePayload> result = courseOperations.sortByIdDescending(courses);
        List<GetCoursePayload> expected = CourseOperationsTestData.createVariousIdsCoursesSortedByIdDescending();

        assertEquals(expected.size(), result.size(), "Should have correct number of courses");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getId(), result.get(i).getId(),
                "Course at index " + i + " should have correct id");
        }
    }

    @Test
    @DisplayName("sortByIdDescending with single course should return that course")
    void testSortByIdDescendingWithSingleCourse() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createSingleCourse();
        List<GetCoursePayload> result = courseOperations.sortByIdDescending(courses);

        assertEquals(1, result.size(), "Should have 1 course");
        assertEquals(CourseOperationsTestData.SINGLE_COURSE_ID, result.get(0).getId(),
            "Course should have correct id");
    }

    @Test
    @DisplayName("sortByIdDescending with empty list should return empty list")
    void testSortByIdDescendingWithEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        List<GetCoursePayload> result = courseOperations.sortByIdDescending(courses);

        assertTrue(result.isEmpty(), "Should return empty list");
    }

    // ============== sortByTitleAscAndLessonsDesc Tests ==============

    @Test
    @DisplayName("sortByTitleAscAndLessonsDesc should sort by title ascending then lessons descending")
    void testSortByTitleAscAndLessonsDesc() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<GetCoursePayload> result = courseOperations.sortByTitleAscAndLessonsDesc(courses);
        List<GetCoursePayload> expected = CourseOperationsTestData.createMixedCoursesSortedByTitleAscAndLessonsDesc();

        assertEquals(expected.size(), result.size(), "Should have correct number of courses");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getId(), result.get(i).getId(),
                "Course at index " + i + " should have correct id");
            assertEquals(expected.get(i).getTitle(), result.get(i).getTitle(),
                "Course at index " + i + " should have correct title");
        }
    }

    @Test
    @DisplayName("sortByTitleAscAndLessonsDesc with varying lessons should respect lesson count")
    void testSortByTitleAscAndLessonsDescWithVaryingLessons() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVaryingLessons();
        List<GetCoursePayload> result = courseOperations.sortByTitleAscAndLessonsDesc(courses);

        assertEquals(CourseOperationsTestData.VARYING_LESSONS_COURSES_COUNT, result.size(),
            "Should have correct number of courses");
        assertNotNull(result.get(0), "Result should not be null");
    }

    // ============== countActiveCourses Tests ==============

    @Test
    @DisplayName("countActiveCourses should count only active courses")
    void testCountActiveCourses() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        long result = courseOperations.countActiveCourses(courses);

        assertEquals(CourseOperationsTestData.MIXED_COURSES_ACTIVE_COUNT, result,
            "Should count correct number of active courses");
    }

    @Test
    @DisplayName("countActiveCourses with all active courses should return total count")
    void testCountActiveCoursesWithAllActive() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createAllActiveCourses();
        long result = courseOperations.countActiveCourses(courses);

        assertEquals(CourseOperationsTestData.ALL_ACTIVE_COURSES_COUNT, result,
            "Should count correct number of active courses");
    }

    @Test
    @DisplayName("countActiveCourses with empty list should return 0")
    void testCountActiveCoursesWithEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        long result = courseOperations.countActiveCourses(courses);

        assertEquals(0, result, "Should count 0 active courses");
    }

    // ============== countCoursesByLessonCount Tests ==============

    @Test
    @DisplayName("countCoursesByLessonCount should count courses with at least minLessonCount lessons")
    void testCountCoursesByLessonCount() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVaryingLessons();
        long result = courseOperations.countCoursesByLessonCount(courses, 5);

        assertEquals(CourseOperationsTestData.VARYING_LESSONS_MIN_5_COUNT, result,
            "Should count courses with at least 5 lessons");
    }

    @Test
    @DisplayName("countCoursesByLessonCount with high threshold should return 0")
    void testCountCoursesByLessonCountHighThreshold() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        List<GetCoursePayload> result = courseOperations.filterCoursesWithLessonsMoreThan(courses, 100);

        assertTrue(result.isEmpty(), "Should return empty list when threshold is too high");
    }

    @Test
    @DisplayName("countCoursesByLessonCount with zero threshold should count all")
    void testCountCoursesByLessonCountZeroThreshold() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVaryingLessons();
        long result = courseOperations.countCoursesByLessonCount(courses, 0);

        assertEquals(CourseOperationsTestData.VARYING_LESSONS_COURSES_COUNT, result,
            "Should count all courses");
    }

    // ============== countLessons Tests ==============

    @Test
    @DisplayName("countLessons should return total lesson count across all courses")
    void testCountLessons() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVaryingLessons();
        long result = courseOperations.countLessons(courses);

        assertEquals(CourseOperationsTestData.VARYING_LESSONS_TOTAL_LESSONS, result,
            "Should count correct total lessons");
    }

    @Test
    @DisplayName("countLessons with mixed courses should return correct total")
    void testCountLessonsWithMixedCourses() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        long result = courseOperations.countLessons(courses);

        assertEquals(CourseOperationsTestData.MIXED_COURSES_TOTAL_LESSONS, result,
            "Should count correct total lessons");
    }

    @Test
    @DisplayName("countLessons with empty list should return 0")
    void testCountLessonsWithEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        long result = courseOperations.countLessons(courses);

        assertEquals(0, result, "Should count 0 lessons");
    }

    // ============== anyCourseWithTitle Tests ==============

    @Test
    @DisplayName("anyCourseWithTitle should return true when course exists")
    void testAnyCourseWithTitle() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        boolean result = courseOperations.anyCourseWithTitle(courses, "Java Basics");

        assertTrue(result, "Should find course with title 'Java Basics'");
    }

    @Test
    @DisplayName("anyCourseWithTitle should return false when course not found")
    void testAnyCourseWithTitleNotFound() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        boolean result = courseOperations.anyCourseWithTitle(courses, "Non-existent Course");

        assertFalse(result, "Should not find non-existent course");
    }

    @Test
    @DisplayName("anyCourseWithTitle with empty list should return false")
    void testAnyCourseWithTitleEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        boolean result = courseOperations.anyCourseWithTitle(courses, "Any Title");

        assertFalse(result, "Should return false for empty list");
    }

    // ============== allCoursesActive Tests ==============

    @Test
    @DisplayName("allCoursesActive should return true when all courses are active")
    void testAllCoursesActive() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createAllActiveCourses();
        boolean result = courseOperations.allCoursesActive(courses);

        assertTrue(result, "All courses should be active");
    }

    @Test
    @DisplayName("allCoursesActive should return false when any course is inactive")
    void testAllCoursesActiveMixed() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        boolean result = courseOperations.allCoursesActive(courses);

        assertFalse(result, "Not all courses are active");
    }

    @Test
    @DisplayName("allCoursesActive with empty list should return true")
    void testAllCoursesActiveEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        boolean result = courseOperations.allCoursesActive(courses);

        assertTrue(result, "Empty list should return true");
    }

    // ============== findCourseWithLongestTitle Tests ==============

    @Test
    @DisplayName("findCourseWithLongestTitle should return course with longest title")
    void testFindCourseWithLongestTitle() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithLongTitles();
        GetCoursePayload result = courseOperations.findCourseWithLongestTitle(courses);

        assertNotNull(result, "Should find a course");
        assertNotNull(result.getTitle(), "Title should not be null");
        assertEquals(CourseOperationsTestData.LONGEST_TITLE_LENGTH, result.getTitle().length(),
            "Title should have correct length");
    }

    @Test
    @DisplayName("findCourseWithLongestTitle with single course should return that course")
    void testFindCourseWithLongestTitleSingleCourse() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createSingleCourse();
        GetCoursePayload result = courseOperations.findCourseWithLongestTitle(courses);

        assertNotNull(result, "Should find the course");
        assertEquals(CourseOperationsTestData.SINGLE_COURSE_TITLE, result.getTitle(),
            "Should be correct title");
    }

    @Test
    @DisplayName("findCourseWithLongestTitle with empty list should return null")
    void testFindCourseWithLongestTitleEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        GetCoursePayload result = courseOperations.findCourseWithLongestTitle(courses);

        assertNull(result, "Should return null for empty list");
    }

    // ============== findCourseWithMostLessons Tests ==============

    @Test
    @DisplayName("findCourseWithMostLessons should return course with most lessons")
    void testFindCourseWithMostLessons() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVaryingLessons();
        GetCoursePayload result = courseOperations.findCourseWithMostLessons(courses);

        assertNotNull(result, "Should find a course");
        assertEquals(CourseOperationsTestData.COURSE_WITH_MOST_LESSONS_TITLE, result.getTitle(),
            "Should be correct course");
        assertEquals(CourseOperationsTestData.COURSE_WITH_MOST_LESSONS_COUNT, result.getLessons().size(),
            "Should have correct number of lessons");
    }

    @Test
    @DisplayName("findCourseWithMostLessons with single course should return that course")
    void testFindCourseWithMostLessonsSingleCourse() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createSingleCourse();
        GetCoursePayload result = courseOperations.findCourseWithMostLessons(courses);

        assertNotNull(result, "Should find the course");
        assertEquals(CourseOperationsTestData.SINGLE_COURSE_ID, result.getId(),
            "Should be the single course");
    }

    @Test
    @DisplayName("findCourseWithMostLessons with empty list should return null")
    void testFindCourseWithMostLessonsEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        GetCoursePayload result = courseOperations.findCourseWithMostLessons(courses);

        assertNull(result, "Should return null for empty list");
    }

    // ============== groupByActiveStatus Tests ==============

    @Test
    @DisplayName("groupByActiveStatus should group courses by active status")
    void testGroupByActiveStatus() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createMixedCourses();
        Map<Boolean, List<GetCoursePayload>> result = courseOperations.groupByActiveStatus(courses);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.containsKey(true), "Should have active group");
        assertTrue(result.containsKey(false), "Should have inactive group");
        assertEquals(CourseOperationsTestData.MIXED_COURSES_ACTIVE_COUNT, result.get(true).size(),
            "Should have correct number of active courses");
        assertEquals(CourseOperationsTestData.MIXED_COURSES_INACTIVE_COUNT, result.get(false).size(),
            "Should have correct number of inactive courses");
    }

    @Test
    @DisplayName("groupByActiveStatus with all active courses should have only true key")
    void testGroupByActiveStatusAllActive() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createAllActiveCourses();
        Map<Boolean, List<GetCoursePayload>> result = courseOperations.groupByActiveStatus(courses);

        assertEquals(1, result.size(), "Should have 1 group");
        assertTrue(result.containsKey(true), "Should have active group");
        assertEquals(CourseOperationsTestData.ALL_ACTIVE_COURSES_COUNT, result.get(true).size(),
            "Should have correct number of active courses");
    }

    @Test
    @DisplayName("groupByActiveStatus with empty list should return empty map")
    void testGroupByActiveStatusEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        Map<Boolean, List<GetCoursePayload>> result = courseOperations.groupByActiveStatus(courses);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Map should be empty");
    }

    // ============== filterByMinIdAndCollect Tests ==============

    @Test
    @DisplayName("filterByMinIdAndCollect should return courses with id >= minId")
    void testFilterByMinIdAndCollect() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVariousIds();
        List<GetCoursePayload> result = courseOperations.filterByMinIdAndCollect(courses, 10L);

        assertEquals(CourseOperationsTestData.VARIOUS_IDS_GREATER_EQUAL_10_COUNT, result.size(),
            "Should have correct number of courses with id >= 10");
        assertTrue(result.stream().allMatch(c -> c.getId() != null && c.getId() >= 10),
            "All courses should have id >= 10");
    }

    @Test
    @DisplayName("filterByMinIdAndCollect with high min id should return fewer courses")
    void testFilterByMinIdAndCollectHighMinId() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVariousIds();
        List<GetCoursePayload> result = courseOperations.filterByMinIdAndCollect(courses, 100L);

        assertEquals(CourseOperationsTestData.VARIOUS_IDS_GREATER_EQUAL_100_COUNT, result.size(),
            "Should return correct number of courses");
    }

    @Test
    @DisplayName("filterByMinIdAndCollect with low min id should return all courses")
    void testFilterByMinIdAndCollectLowMinId() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithVariousIds();
        List<GetCoursePayload> result = courseOperations.filterByMinIdAndCollect(courses, 1L);

        assertEquals(CourseOperationsTestData.VARIOUS_IDS_COURSES_COUNT, result.size(),
            "Should return all courses");
    }

    // ============== collectNonNullDescriptions Tests ==============

    @Test
    @DisplayName("collectNonNullDescriptions should return only non-null descriptions")
    void testCollectNonNullDescriptions() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createCoursesWithNullDescriptions();
        List<String> result = courseOperations.collectNonNullDescriptions(courses);

        assertEquals(CourseOperationsTestData.NULL_DESCRIPTIONS_NON_NULL_COUNT, result.size(),
            "Should have correct number of non-null descriptions");
        assertTrue(result.stream().allMatch(Objects::nonNull), "All descriptions should be non-null");
    }

    @Test
    @DisplayName("collectNonNullDescriptions with all null should return empty list")
    void testCollectNonNullDescriptionsAllNull() {
        List<GetCoursePayload> courses = new java.util.ArrayList<>();
        courses.add(CourseOperationsTestData.createCourse(1L, "Course", null, true, 5));
        courses.add(CourseOperationsTestData.createCourse(2L, "Course2", null, true, 3));
        List<String> result = courseOperations.collectNonNullDescriptions(courses);

        assertTrue(result.isEmpty(), "Should return empty list");
    }

    @Test
    @DisplayName("collectNonNullDescriptions with empty list should return empty list")
    void testCollectNonNullDescriptionsEmptyList() {
        List<GetCoursePayload> courses = CourseOperationsTestData.createEmptyList();
        List<String> result = courseOperations.collectNonNullDescriptions(courses);

        assertTrue(result.isEmpty(), "Should return empty list");
    }
}

