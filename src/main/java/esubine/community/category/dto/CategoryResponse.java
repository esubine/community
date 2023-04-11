package esubine.community.category.dto;

import esubine.community.category.model.CategoryEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryResponse {
    private final Long categoryId;
    private final String categoryName;

    public CategoryResponse(CategoryEntity category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
    }
}
