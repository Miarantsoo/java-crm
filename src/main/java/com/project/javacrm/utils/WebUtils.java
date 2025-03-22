//package com.project.javacrm.utils;
//
//
//import org.springframework.data.domain.Sort;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class WebUtils {
//    public static final String ASC = "asc";
//    public static final String DESC = "desc";
//    public static final String SORT_CRITERIA_DELIMITER = ";";
//    public static final String SORT_PARTS_DELIMITER = ",";
//
//    public static Sort createSortObject(String sortParam) {
//        try {
//            String[] sortCriteria = sortParam.split(SORT_CRITERIA_DELIMITER);
//
//            List<Sort.Order> orders = Arrays.stream(sortCriteria)
//                    .map(criteria -> {
//                        String[] parts = criteria.split(SORT_PARTS_DELIMITER);
//                        if (parts.length != 2) {
//                            throw new IllegalArgumentException(
//                                    "Each sort criteria must have exactly 2 parts: property and direction"
//                            );
//                        }
//
//                        String property = parts[0].trim();
//                        String direction = parts[1].trim().toLowerCase();
//
//                        if (!direction.equals(ASC) && !direction.equals(DESC)) {
//                            throw new IllegalArgumentException(
//                                    "Sort direction must be either '"+ASC+"' or '"+DESC+"'"
//                            );
//                        }
//
//                        return new Sort.Order(
//                                Sort.Direction.fromString(direction),
//                                property
//                        );
//                    })
//                    .collect(Collectors.toList());
//
//            return Sort.by(orders);
//
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//    }
//}
//
