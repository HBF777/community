package edu.uestc.community.dto;

import edu.uestc.community.model.Question;
import lombok.Data;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class PaginationDTO {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        if (page <1){
            page = 1;
        }
        if (page>totalPage){
            page = totalPage;
        }
        setPage(page);

        pages.clear();
        // 计算展示页码
        pages.add(page);
        for(int i=1; i<=3; i++){
            if (page-i>0){
                pages.add(0, page-i);
            }
            if (page+i <= totalPage){
                pages.add(page+i);
            }
        }
        // 是否展示上一页
        showPrevious = !Objects.equals(page, 1);

        // 是否展示下一页
        showNext = !Objects.equals(page,totalPage);

        // 是否展示最后一页
        showEndPage = !pages.contains(totalPage);

        // 是否展示第一页
        showFirstPage = !pages.contains(1);
    }
}
