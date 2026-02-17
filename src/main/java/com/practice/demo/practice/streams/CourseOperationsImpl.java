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
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GetCoursePayload> filterByTitlePattern(List<GetCoursePayload> courses, String titlePattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GetCoursePayload> filterCoursesWithLessonsMoreThan(List<GetCoursePayload> courses, int lessons) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> mapToTitles(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GetCoursePayload> sortByIdDescending(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GetCoursePayload> sortByTitleAscAndLessonsDesc(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long countActiveCourses(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long countCoursesByLessonCount(List<GetCoursePayload> courses, int minLessonCount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long countLessons(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean anyCourseWithTitle(List<GetCoursePayload> courses, String title) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean allCoursesActive(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GetCoursePayload findCourseWithLongestTitle(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GetCoursePayload findCourseWithMostLessons(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Boolean, List<GetCoursePayload>> groupByActiveStatus(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GetCoursePayload> filterByMinIdAndCollect(List<GetCoursePayload> courses, Long minId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> collectNonNullDescriptions(List<GetCoursePayload> courses) {
        throw new UnsupportedOperationException();
    }
}

