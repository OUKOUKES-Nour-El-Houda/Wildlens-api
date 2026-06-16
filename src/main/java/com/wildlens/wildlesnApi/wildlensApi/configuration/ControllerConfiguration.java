package com.wildlens.wildlesnApi.wildlensApi.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ControllerConfiguration {
    public static <T> ResponseEntity<Page<T>> getPageResponseEntity(Page<T> page) {
        if (page.isEmpty()) {
            return new ResponseEntity<>(page, NO_CONTENT);
        } else if(page.isLast()) {
            return new ResponseEntity<>(page, OK);
        } else {
            return new ResponseEntity<>(page, PARTIAL_CONTENT);
        }
    }

    public static <T> ResponseEntity<Collection<T>> getCollectionResponseEntity(Collection<T> list) {
        return new ResponseEntity<>(list, isEmpty(list) ? NO_CONTENT : OK);
    }

}
