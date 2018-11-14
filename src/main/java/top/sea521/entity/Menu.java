package top.sea521.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer menuLevel;

    private String menuIcon;

    private Integer menuOrder;

    private Integer menuStatus;

    
}