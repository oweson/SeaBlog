package top.sea521.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    /**
     * pid==0就是根节点，下面的就是子节点
     */
    private Integer categoryId;

    private Integer categoryPid;
    @NotNull
    @Size(min = 3, max = 16, message = "{categoryName.name.length.error}")
    private String categoryName;
    @NotNull
    @Size(min = 6, max = 1000, message = "{deccribtion is valiaed}")
    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

    private Integer categoryStatus;

   
}