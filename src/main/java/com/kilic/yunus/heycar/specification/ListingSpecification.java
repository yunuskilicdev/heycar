package com.kilic.yunus.heycar.specification;

import com.kilic.yunus.heycar.dto.ListingSearchDto;
import com.kilic.yunus.heycar.model.Listing;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Yunus Kilic
 */
public class ListingSpecification {

    private ListingSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Listing> search(ListingSearchDto dto) {
        return (root, query, criteriaBuilder)
                -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(dto.getMake())) {
                predicates.add(criteriaBuilder.equal(root.get("make"), dto.getMake()));
            }
            if (StringUtils.hasText(dto.getModel())) {
                predicates.add(criteriaBuilder.equal(root.get("model"), dto.getModel()));
            }
            if (dto.getYear() > 0) {
                predicates.add(criteriaBuilder.equal(root.get("year"), dto.getYear()));
            }
            if (StringUtils.hasText(dto.getColor())) {
                predicates.add(criteriaBuilder.equal(root.get("color"), dto.getColor()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
