package com.practice.demo.practice.streams;

import org.openapitools.model.GetCoursePayload;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseOperationsImpl implements CourseOperations {

    @Override
    public List<GetCoursePayload> filterByActiveStatus(List<GetCoursePayload> courses, boolean isActive) {
        return courses.stream()
                .filter(course -> Boolean.valueOf(isActive).equals(course.getIsActive()))
                .toList();
    }

    @Override
    public List<GetCoursePayload> filterByTitlePattern(List<GetCoursePayload> courses, String titlePattern) {
        return courses.stream()
                .filter(course -> course.getTitle() != null)
                .filter(course -> course.getTitle().contains(titlePattern))
                .toList();
    }

    @Override
    public List<GetCoursePayload> filterCoursesWithLessonsMoreThan(
            List<GetCoursePayload> courses,
            int lessons
    ) {
        return courses.stream()
                .filter(course -> course.getLessons() != null && course.getLessons().size() > lessons)
                .toList();
    }

    @Override
    public List<String> mapToTitles(List<GetCoursePayload> courses) {
        return courses.stream()
                .map(GetCoursePayload::getTitle)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public List<GetCoursePayload> sortByIdDescending(List<GetCoursePayload> courses) {
        return courses.stream()
                .sorted(Comparator.comparing(GetCoursePayload::getId).reversed())
                .toList();
    }

    @Override
    public List<GetCoursePayload> sortByTitleAscAndLessonsDesc(List<GetCoursePayload> courses) {
        return courses.stream()
                .sorted(Comparator.comparing(GetCoursePayload::getTitle)
                        .thenComparing(
                                (GetCoursePayload c) -> c.getLessons().size(),
                                Comparator.reverseOrder()
                        ))
                .toList();

    }

    @Override
    public long countActiveCourses(List<GetCoursePayload> courses) {
        return courses.stream()
                .filter(GetCoursePayload::getIsActive)
                .count();
    }

    @Override
    public long countCoursesByLessonCount(List<GetCoursePayload> courses, int minLessonCount) {
        return courses.stream()
                .filter(course -> course.getLessons()
                        .size() >= minLessonCount)
                .count();
    }

    @Override
    public long countLessons(List<GetCoursePayload> courses) {
        return courses.stream()
                .mapToLong(course -> course.getLessons().size())
                .sum();

    }


    @Override
    public boolean anyCourseWithTitle(List<GetCoursePayload> courses, String title) {
        return courses.stream()
                .anyMatch(course -> course.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public boolean allCoursesActive(List<GetCoursePayload> courses) {
        return courses.stream()
                .allMatch(course -> course.getIsActive());
    }

    @Override
    public GetCoursePayload findCourseWithLongestTitle(List<GetCoursePayload> courses) {
        return courses.stream()
                .filter(c -> c.getTitle() != null)
                .max(Comparator.comparingInt(c -> c.getTitle().length()))
                .orElse(null);
    }

    @Override
    public GetCoursePayload findCourseWithMostLessons(List<GetCoursePayload> courses) {
        return courses.stream()
                .max(Comparator.comparingInt(course -> course.getLessons().size()))
                .orElse(null);
    }

    @Override
    public Map<Boolean, List<GetCoursePayload>> groupByActiveStatus(List<GetCoursePayload> courses) {
        return courses.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(GetCoursePayload::getIsActive));
    }

    @Override
    public List<GetCoursePayload> filterByMinIdAndCollect(List<GetCoursePayload> courses, Long minId) {
        return courses.stream()
                .filter(course -> course.getId() >= minId)
                .toList();
    }

    @Override
    public List<String> collectNonNullDescriptions(List<GetCoursePayload> courses) {
        return courses.stream()
                .map(GetCoursePayload::getDescription)
                .filter(Objects::nonNull)
                .toList();
    }
}

