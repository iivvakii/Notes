package org.example.notes.builder;

import org.example.notes.dto.NoteFilter;
import org.example.notes.model.Note;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class NoteQueryBuilder {

    public static Query build(NoteFilter filter) {
        Query query = new Query();
        if (filter.getTags() != null && !filter.getTags().isEmpty()) {
            query.addCriteria(Criteria.where("tag").in(filter.getTags()));
        }

        Sort sort = Sort.by(filter.getSortDirection(), filter.getSortBy());
        query.with(sort);

        return query;
    }
}
